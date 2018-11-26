package com.project_management.shoppingweb.service.Impl;
import com.alibaba.fastjson.JSONObject;
import com.project_management.shoppingweb.dao.interfaceValue.StatisticsModel;
import com.project_management.shoppingweb.dao.interfaceValue.TransPageModel;
import com.project_management.shoppingweb.dao.model.Transaction;
import com.project_management.shoppingweb.repository.TransactionRepository;

import com.project_management.shoppingweb.repository.UserRepository;
import com.project_management.shoppingweb.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.project_management.shoppingweb.service.common.ResultBuilder.buildSuccessResult;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserRepository userRepository;
    @PersistenceContext
    private EntityManager entityManager;



    /**
     * 查找
     * @param transPageModel
     * @return
     */
    public Page<Transaction> getByPage(TransPageModel transPageModel){
        Integer pageNumber=transPageModel.getPageNow();
        Integer pageSize=transPageModel.getPageSize();
        Pageable pageable = new PageRequest(pageNumber,pageSize, Sort.Direction.ASC, "transactionId");

        //String  =transPageModel.getWorkflowType();
        Specification<Transaction> spec=new Specification<Transaction>() {
            @Override
            public Predicate toPredicate(Root<Transaction> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicate = new ArrayList<>();
                //工作流类型
                if (transPageModel.getBuyId()!= null) {
                    predicate.add(criteriaBuilder.equal(root.get("buyId").as(Integer.class),transPageModel.getBuyId()));
                }
                if (transPageModel.getSellId()!= null) {
                    predicate.add(criteriaBuilder.equal(root.get("sellId").as(Integer.class),transPageModel.getSellId()));
                }
                if (transPageModel.getBuyStatus()!= null) {
                    predicate.add(criteriaBuilder.equal(root.get("buyStatus").as(String.class),transPageModel.getBuyStatus()));
                }
                if (transPageModel.getSellStatus()!= null) {
                    predicate.add(criteriaBuilder.equal(root.get("sellStatus").as(String.class),transPageModel.getSellStatus()));
                }
                if (transPageModel.getSellName()!= null) {
                    predicate.add(criteriaBuilder.equal(root.get("sellName").as(String.class),transPageModel.getSellName()));
                }
                if (transPageModel.getBuyName()!= null) {
                    predicate.add(criteriaBuilder.equal(root.get("buyName").as(String.class),transPageModel.getBuyName()));
                }
                if (transPageModel.getTransactionName()!= null) {
                    predicate.add(criteriaBuilder.equal(root.get("transactionName").as(String.class),transPageModel.getTransactionName()));
                }
                if (transPageModel.getTransactionId()!= null) {
                    predicate.add(criteriaBuilder.equal(root.get("transactionId").as(Integer.class),transPageModel.getTransactionId()));
                }
                if(transPageModel.getStartTime()!= null){
                     //大于或等于传入时间  
                    predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("transactionTime").as(Date.class),transPageModel.getStartTime()));
                }
                if(transPageModel.getEndTime()!= null){
                    //大于或等于传入时间  
                    predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("transactionTime").as(Date.class),transPageModel.getEndTime()));
                }
                Predicate[] pre = new Predicate[predicate.size()];
                return criteriaQuery.where(predicate.toArray(pre)).getRestriction();
            }
        };
        return transactionRepository.findAll(spec,pageable);
    }

    public Object getByPage1(TransPageModel transPageModel){

        Integer pageNumber=transPageModel.getPageNow()*transPageModel.getPageSize();
        Integer pageSize=pageNumber+transPageModel.getPageSize()-1;
        if(transPageModel.getTransactionId()==null){
            Integer count=transactionRepository.findFin(pageNumber, pageSize, transPageModel.getUserId()).size();
            List<Transaction> res=transactionRepository.findFin(pageNumber, pageSize, transPageModel.getUserId());
            Map<String,Object> resMap=new HashMap<>();
            resMap.put("content",res);
            resMap.put("totalElements",count);
            return resMap;
        }
        else {
            Integer count=transactionRepository.findFin(pageNumber, pageSize, transPageModel.getUserId(),transPageModel.getTransactionId()).size();
            List<Transaction> res=transactionRepository.findFin(pageNumber, pageSize, transPageModel.getUserId(),transPageModel.getTransactionId());
            Map<String,Object> resMap=new HashMap<>();
            resMap.put("content",res);
            resMap.put("totalElements",count);
            return resMap;
        }

    }
    public Object getByPageAd(TransPageModel transPageModel){
        Integer pageNumber=transPageModel.getPageNow()*transPageModel.getPageSize();
        Integer pageSize=pageNumber+transPageModel.getPageSize()-1;
        Pageable pageable = new PageRequest(pageNumber,pageSize, Sort.Direction.ASC, "transactionId");
        if(transPageModel.getSellId()!=null){
            List<Transaction> res;
            Integer count;
            if(transPageModel.getTransactionId()==null) {
                res= transactionRepository.findSellList(pageNumber, pageSize, transPageModel.getSellId());
                 count= transactionRepository.findSellList(0, 20000, transPageModel.getSellId()).size();
            }
            else{
                res = transactionRepository.findSellList(pageNumber, pageSize, transPageModel.getSellId(),transPageModel.getTransactionId());
                count = transactionRepository.findSellList(0, 20000, transPageModel.getSellId(),transPageModel.getTransactionId()).size();

            }
            Map<String,Object> resMap=new HashMap<>();
            resMap.put("content",res);
            resMap.put("totalElements",count);
            return resMap;
        }
        else{
            List<Transaction> res;
            Integer count;
            Integer aaa=transPageModel.getTransactionId();
            if(transPageModel.getTransactionId()==null) {
                res= transactionRepository.findBuyList(pageNumber, pageSize, transPageModel.getBuyId());
                count= transactionRepository.findBuyList(0, 20000, transPageModel.getBuyId()).size();
            }
            else{
                res = transactionRepository.findBuyList(pageNumber, pageSize, transPageModel.getBuyId(),transPageModel.getTransactionId());
                count = transactionRepository.findBuyList(0, 20000, transPageModel.getBuyId(),transPageModel.getTransactionId()).size();

            }
            Map<String,Object> resMap=new HashMap<>();
            resMap.put("content",res);
            resMap.put("totalElements",count);
            return resMap;
        }

    }

    public Page<Transaction> getSellByPage(TransPageModel transPageModel){
        Integer pageNumber=transPageModel.getPageNow();
        Integer pageSize=transPageModel.getPageSize();
        Pageable pageable = new PageRequest(pageNumber,pageSize, Sort.Direction.ASC, "transactionId");

        Specification<Transaction> spec=new Specification<Transaction>() {
            @Override
            public Predicate toPredicate(Root<Transaction> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicate = new ArrayList<>();
                predicate.add(criteriaBuilder.equal(root.get("sellStatus").as(String.class),"Y"));
                List<Predicate> orPredicate=new ArrayList<>();

                if (transPageModel.getSellName()!= null) {
                    predicate.add(criteriaBuilder.like(root.get("sellName").as(String.class),transPageModel.getSellName()));
                }
                if (transPageModel.getTransactionId()!= null) {
                    predicate.add(criteriaBuilder.equal(root.get("transactionId").as(Integer.class),transPageModel.getTransactionId()));
                }
                predicate.add(criteriaBuilder.isNull(root.get("buyId")));

                Predicate[] pre = new Predicate[predicate.size()];
                return criteriaQuery.where(predicate.toArray(pre)).getRestriction();
            }
        };
        return transactionRepository.findAll(spec,pageable);
    }

    public Page<Transaction> getBuyByPage(TransPageModel transPageModel){
        Integer pageNumber=transPageModel.getPageNow();
        Integer pageSize=transPageModel.getPageSize();
        Pageable pageable = new PageRequest(pageNumber,pageSize, Sort.Direction.ASC, "transactionId");
        Specification<Transaction> spec=new Specification<Transaction>() {
            @Override
            public Predicate toPredicate(Root<Transaction> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicate = new ArrayList<>();
                //工作流类型
                if (transPageModel.getBuyName()!= null) {
                    predicate.add(criteriaBuilder.like(root.get("buyName").as(String.class),transPageModel.getBuyName()));
                }

                predicate.add(criteriaBuilder.equal(root.get("buyStatus").as(String.class),"Y"));

                if (transPageModel.getTransactionName()!= null) {
                    predicate.add(criteriaBuilder.equal(root.get("transactionName").as(String.class),transPageModel.getTransactionName()));
                }
                if (transPageModel.getTransactionId()!= null) {
                    predicate.add(criteriaBuilder.equal(root.get("transactionId").as(Integer.class),transPageModel.getTransactionId()));
                }
                if(transPageModel.getStartTime()!= null){
                    //大于或等于传入时间  
                    predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("transactionTime").as(Date.class),transPageModel.getStartTime()));
                }
                if(transPageModel.getEndTime()!= null){
                    //大于或等于传入时间  
                    predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("transactionTime").as(Date.class),transPageModel.getEndTime()));
                }

                predicate.add(criteriaBuilder.isNull(root.get("sellId")));

                Predicate[] pre = new Predicate[predicate.size()];
                return criteriaQuery.where(predicate.toArray(pre)).getRestriction();
            }
        };
        return transactionRepository.findAll(spec,pageable);
    }

    public Object getStatistics(StatisticsModel statisticsModel){

//        Specification<Transaction> spec=new Specification<Transaction>() {
//            @Override
//            public Predicate toPredicate(Root<Transaction> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
//                List<Predicate> predicate = new ArrayList<>();
//                criteriaQuery=criteriaBuilder.createQuery(Transaction.class);
//
//                //工作流类型
//                if (statisticsModel.getType()!= null) {
//                    if(statisticsModel.getType().equals(0))
//                        predicate.add(criteriaBuilder.equal(root.get("sellStatus").as(String.class),"Y"));
//                    else if(statisticsModel.getType().equals(1))
//                        predicate.add(criteriaBuilder.equal(root.get("buyStatus").as(String.class),"Y"));
//                }
//                if(statisticsModel.getStartTime()!= null){
//                    //大于或等于传入时间  
//                    predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("transactionTime").as(Date.class),statisticsModel.getStartTime()));
//                }
//                if(statisticsModel.getEndTime()!= null){
//                    //大于或等于传入时间  
//                    predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("transactionTime").as(Date.class),statisticsModel.getEndTime()));
//                }
//
//                Predicate[] pre = new Predicate[predicate.size()];
//                return criteriaQuery.multiselect(criteriaBuilder.sum(root.get("moneyNum")).alias("totalMoney")
//                        //root.get("transactionTime")
//                ).where(predicate.toArray(pre)).groupBy(root.get("transactionTime")).getRestriction();
//
//            }
//        };
//        return transactionRepository.findAll(spec);
        return null;

    }

    /**
     * 新增
     * @param transaction
     * @return
     */
    public Transaction insert(Transaction transaction){
        if(transaction.getBuyId()!=null)
            transaction.setBuyerName(userRepository.findByUserId(transaction.getBuyId()).getUsername());
        if(transaction.getSellId()!=null)
            transaction.setSellerName(userRepository.findByUserId(transaction.getSellId()).getUsername());
        return transactionRepository.save(transaction);
    }

    /**
     * 更新
     * @param transaction
     * @return
     */
    public Transaction update(Transaction transaction){
        //获取数据库中数据
        Transaction transactionOld=transactionRepository.findByTransactionId(transaction.getTransactionId());
        if(transaction==null) return null;
        //获取原始对象中的所有public方法
        Method[] methods = transaction.getClass().getDeclaredMethods();
        //用于提取不包含指定关键词的方法
        String regExpression = "^(get)(?!Id|CreateTime)(\\w+)";
        Pattern pattern = Pattern.compile(regExpression);
        Matcher matcher;
        try {
            for (Method method : methods) {
                matcher = pattern.matcher(method.getName());
                //正则匹配以get开头，后面不能匹配Id、CreateTime这两个单词的方法
                if (matcher.find()) {
                    Object res = method.invoke(transaction, null);
                    //忽略值为空的字段
                    if (res != null) {
                        //取出get方法名后面的字段名
                        String fieldName = matcher.group(2);
                        //找到该字段名的set方法
                        Method setMethod = transactionOld.getClass().getMethod("set" + fieldName, method.getReturnType());
                        //调用实体对象的set方法更新字段值
                        setMethod.invoke(transactionOld, res);
                    }
                }
            }
        }catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        if(transaction.getBuyId()!=null)
            transactionOld.setBuyerName(userRepository.findByUserId(transactionOld.getBuyId()).getUsername());
        if(transaction.getSellId()!=null)
            transactionOld.setSellerName(userRepository.findByUserId(transactionOld.getSellId()).getUsername());
        return transactionRepository.save(transactionOld);
    }

    /**
     * 删除
     * @param transaction
     */
    public void delete(Transaction transaction){
        transactionRepository.delete(transaction);
        return;
    }
}
