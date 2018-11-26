package com.project_management.shoppingweb.service.Impl;

import com.project_management.shoppingweb.dao.interfaceValue.WorkflowPageModel;
import com.project_management.shoppingweb.dao.model.Workflow;
import com.project_management.shoppingweb.repository.DeBuyRepository;
import com.project_management.shoppingweb.repository.DeSellRepository;
import com.project_management.shoppingweb.repository.WorkflowRepository;
import com.project_management.shoppingweb.service.WorkflowCustomService;
import com.project_management.shoppingweb.service.WorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class WorkflowServiceImpl implements WorkflowService{
    @Autowired
    private WorkflowRepository workflowRepository;
    @Autowired
    private DeBuyRepository deBuyRepository;
    @Autowired
    private DeSellRepository deSellRepository;
    @Autowired
    private WorkflowCustomService workflowCustomService;

    public Page<Workflow> findByPage(WorkflowPageModel workflowPageModel){

        Integer pageNumber=workflowPageModel.getPageNow();
        Integer pageSize=workflowPageModel.getPageSize();
        Pageable pageable = new PageRequest(pageNumber,pageSize, Sort.Direction.ASC, "workflowId");

        String worklowType=workflowPageModel.getWorkflowType();
        Specification<Workflow> spec=new Specification<Workflow>() {
            @Override
            public Predicate toPredicate(Root<Workflow> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicate = new ArrayList<>();
                //工作流类型
                if (worklowType!= null && worklowType.trim().length() > 0) {
                    predicate.add(criteriaBuilder.equal(root.get("workflowType").as(String.class), worklowType.trim()));
                }

                Predicate[] pre = new Predicate[predicate.size()];
                return criteriaQuery.where(predicate.toArray(pre)).getRestriction();
            }
        };
        return workflowRepository.findAll(spec,pageable);
    }

    public void update(Workflow workflow){
        Map<String,Object> classMap=new HashMap<>();
        classMap.put("DeSellRepository",deSellRepository);
        classMap.put("DeBuyRepository",deBuyRepository);
        //更新表的状态
        workflowRepository.save(workflow);
        Object businessObject=this.findBusinessInfo(workflow);
        ClassLoader loader=Thread.currentThread().getContextClassLoader();
        String className=workflow.getBusinessTable();
        try {
            Class setClazz=loader.loadClass("com.project_management.shoppingweb.dao.model"+className);
            Method setMethod=setClazz.getMethod("setStatus",String.class);
            setMethod.invoke(businessObject,workflow.getWorkflowResult());
            //更新操作
            Class clazz = loader.loadClass("com.project_management.shoppingweb.repository."+className+"Repository");
            Method method=clazz.getMethod("update",Object.class);
            method.invoke(classMap.get(className+"Repository"),businessObject);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public Workflow insert(Workflow workflow){
        return workflowRepository.save(workflow);
    }

    public Workflow insertForBusiness(Object targetObject){
        try {
            Workflow workflow = new Workflow();
            // 获取对象的类名
            String className = targetObject.getClass().toString()
                    .replace("com.project_management.shoppingweb.dao.model.", "");
            //将表名转换成数据库内的表名的形式
            workflow.setBusinessTable(className);
            //设置表id
            String getIdMethodStr = "get" + className + "Id";
            Method method = targetObject.getClass().getMethod(getIdMethodStr, new Class[]{});
            Integer businessId = (Integer) method.invoke(targetObject, null);
            workflow.setBusinessId(businessId);
            //设置审核类型
            String getTypeMethodStr = "getType";
            method = targetObject.getClass().getMethod(getTypeMethodStr, new Class[]{});
            String businessType = (String) method.invoke(targetObject, null);
            workflow.setWorkflowType(businessType + "审核");

            String workflowCustomName = businessType + "审核";
            //设置用户id
            String getUserMethodStr = "getUserId";
            method = targetObject.getClass().getMethod(getUserMethodStr, new Class[]{});
            Integer userId = (Integer) method.invoke(targetObject, null);
            workflow.setEmployeeId(userId);
            //设置工作流创建时间
            workflow.setWorkflowTime(new Date());
            //设置审核人
            workflow.setAuditUser(workflowCustomService.findByName(workflowCustomName).getWorkflowCustom());

            return this.insert(workflow);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object findBusinessInfo(Workflow workflow){
        try {
            Map<String,Object> classMap=new HashMap<>();
            classMap.put("DeSellRepository",deSellRepository);
            classMap.put("DeBuyRepository",deBuyRepository);
            ClassLoader loader=Thread.currentThread().getContextClassLoader();
            String className=workflow.getBusinessTable();
            Class clazz=loader.loadClass("com.project_management.shoppingweb.repository."+className+"Repository");
            Method method=clazz.getMethod("findBy"+className+"Id",Integer.class);
            return method.invoke(classMap.get(className+"Repository"),workflow.getBusinessId());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object updateSelective(Workflow workflow) {
        //获取数据库中数据
        Workflow workflowOld=workflowRepository.findByWorkflowId(workflow.getWorkflowId());
        if(workflowOld==null) return null;
        //获取原始对象中的所有public方法
        Method[] methods = workflow.getClass().getDeclaredMethods();
        //用于提取不包含指定关键词的方法
        String regExpression = "^(get)(?!Id|CreateTime)(\\w+)";
        Pattern pattern = Pattern.compile(regExpression);
        Matcher matcher;
//        try {
//            for (Method method : methods) {
//                matcher = pattern.matcher(method.getName());
//                //正则匹配以get开头，后面不能匹配Id、CreateTime这两个单词的方法
//                if (matcher.find()) {
//                    Object res = method.invoke(workflow, null);
//                    //忽略值为空的字段
//                    if (res != null) {
//                        //取出get方法名后面的字段名
//                        String fieldName = matcher.group(2);
//                        //找到该字段名的set方法
//                        Method setMethod = workflowOld.getClass().getMethod("set" + fieldName, method.getReturnType());
//                        //调用实体对象的set方法更新字段值
//                        setMethod.invoke(workflowOld, res);
//                    }
//                }
//            }
//        }catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
        return workflowRepository.save(workflowOld);
    }
}
