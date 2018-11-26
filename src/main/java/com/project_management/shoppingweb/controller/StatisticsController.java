package com.project_management.shoppingweb.controller;

import com.project_management.shoppingweb.dao.interfaceValue.StatisticsModel;
import com.project_management.shoppingweb.dao.model.DeBuy;
import com.project_management.shoppingweb.dao.model.Transaction;
import com.project_management.shoppingweb.repository.TransactionRepository;
import com.project_management.shoppingweb.service.DeBuyService;
import com.project_management.shoppingweb.service.common.ResultBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@RequestMapping("/statistics")
@RestController
public class StatisticsController {
    @Autowired
    private DeBuyService deBuyService;
    @Autowired
    private TransactionRepository transactionRepository;

    @RequestMapping(value = "/getMoney", method = RequestMethod.POST)
    public Object getMoney(@RequestBody StatisticsModel statisticsModel){
        BigInteger bigInteger = BigInteger.valueOf(0);
        int type = statisticsModel.getType();
        int period = statisticsModel.getPeriod();
        if(type==1){
            if(period==0){
                bigInteger = transactionRepository.getDayMoney1();
            }else if(period==1){
                bigInteger = transactionRepository.getMonthMoney1();
            }else if(period==2){
                bigInteger = transactionRepository.getYearMoney1();
            }
        }else if(type==0){
            if(period==0){
                bigInteger = transactionRepository.getDayMoney0();
            }else if(period==1){
                bigInteger = transactionRepository.getMonthMoney0();
            }else if(period==2){
                bigInteger = transactionRepository.getYearMoney0();
            }
        }
        Map<String,String> map = new HashMap<String,String>();
        map.put("totalMoney",""+bigInteger);
        return  ResultBuilder.buildSuccessResult(map);
    }
    @RequestMapping(value = "/getStatistic", method = RequestMethod.POST)
    public Object getStatistic(@RequestBody StatisticsModel statisticsModel){
        BigInteger bigInteger = BigInteger.valueOf(0);
        Object object=new Object();
        int type = statisticsModel.getType();
        int period = statisticsModel.getPeriod();
        if(type==1){
            if(period==0){
                 object= transactionRepository.getDayStatistic1(statisticsModel.getStartTime(),statisticsModel.getEndTime());
            }else if(period==1){
                object = transactionRepository.getMonthStatistic1(statisticsModel.getStartTime(),statisticsModel.getEndTime());
            }else if(period==2){
                object = transactionRepository.getYearStatistic1(statisticsModel.getStartTime(),statisticsModel.getEndTime());
            }
        }else if(type==0){
            if(period==0){
                object = transactionRepository.getDayStatistic0(statisticsModel.getStartTime(),statisticsModel.getEndTime());
            }else if(period==1){
                object = transactionRepository.getMonthStatistic0(statisticsModel.getStartTime(),statisticsModel.getEndTime());
            }else if(period==2){
                object = transactionRepository.getYearStatistic0(statisticsModel.getStartTime(),statisticsModel.getEndTime());
            }
        }

        return  ResultBuilder.buildSuccessResult(object);
    }


    @RequestMapping(value = "/allBuyMoney", method = RequestMethod.POST)
    public Object getAllBuyMoney(){
        List<DeBuy> deBuys = deBuyService.findAll();
        BigInteger SumMoney = BigInteger.valueOf(0);
        for(DeBuy deBuy : deBuys){
            if(deBuy.getMoneyNum()!=null){
                SumMoney = SumMoney.add(deBuy.getMoneyNum());
            }
        }
        return ResultBuilder.buildSuccessResult(SumMoney);
    }

    @RequestMapping(value = "/getDayMoney", method = RequestMethod.POST)
    public Object getDayMoney(){
        List<Transaction> transactions = transactionRepository.findAll();
        BigInteger SumMoney = BigInteger.valueOf(0);
        Date now = new Date();
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
        SimpleDateFormat dayFormat= new SimpleDateFormat("dd");
        String nowYear = yearFormat.format(now);
        String nowMonth = monthFormat.format(now);
        String nowDay = dayFormat.format(now);

        for(Transaction transaction : transactions){
            if(transaction.getMoneyNum()!=null){
                Date dataDate = transaction.getTransactionTime();
                if(nowYear.equals(yearFormat.format(dataDate))){
                    if(nowMonth.equals(monthFormat.format(dataDate))){
                        if(nowDay.equals(dayFormat.format(dataDate))){
                            SumMoney = SumMoney.add(transaction.getMoneyNum());
                        }
                    }
                }
            }
        }
        return ResultBuilder.buildSuccessResult(SumMoney);
    }

    @RequestMapping(value = "/getMonthMoney", method = RequestMethod.POST)
    public Object getMonthMoney(){
        List<Transaction> transactions = transactionRepository.findAll();
        BigInteger SumMoney = BigInteger.valueOf(0);
        Date now = new Date();
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
        SimpleDateFormat dayFormat= new SimpleDateFormat("dd");
        String nowYear = yearFormat.format(now);
        String nowMonth = monthFormat.format(now);
        String nowDay = dayFormat.format(now);

        for(Transaction transaction : transactions){
            if(transaction.getMoneyNum()!=null){
                Date dataDate = transaction.getTransactionTime();
                if(nowYear.equals(yearFormat.format(dataDate))){
                    if(nowMonth.equals(monthFormat.format(dataDate))){
                            SumMoney = SumMoney.add(transaction.getMoneyNum());
                    }
                }
            }
        }
        return ResultBuilder.buildSuccessResult(SumMoney);
    }

    @RequestMapping(value = "/getYearMoney", method = RequestMethod.POST)
    public Object getYearMoney(){
        List<Transaction> transactions = transactionRepository.findAll();
        BigInteger SumMoney = BigInteger.valueOf(0);
        Date now = new Date();
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
        SimpleDateFormat dayFormat= new SimpleDateFormat("dd");
        String nowYear = yearFormat.format(now);
        String nowMonth = monthFormat.format(now);
        String nowDay = dayFormat.format(now);

        for(Transaction transaction : transactions){
            if(transaction.getMoneyNum()!=null){
                Date dataDate = transaction.getTransactionTime();
                if(nowYear.equals(yearFormat.format(dataDate))){
                        SumMoney = SumMoney.add(transaction.getMoneyNum());
                }
            }
        }
        return ResultBuilder.buildSuccessResult(SumMoney);
    }

    @RequestMapping(value = "/getYearMoney1", method = RequestMethod.POST)
    public Object getYearMoney1(){
        BigInteger SumMoney = transactionRepository.getYearMoney1();
        return ResultBuilder.buildSuccessResult(SumMoney);
    }

    @RequestMapping(value = "/getMonthMoney1", method = RequestMethod.POST)
    public Object getMonthMoney1(){
        BigInteger SumMoney = transactionRepository.getMonthMoney1();
        return ResultBuilder.buildSuccessResult(SumMoney);
    }

    @RequestMapping(value = "/getDayMoney1", method = RequestMethod.POST)
    public Object getDayMoney1(){
        BigInteger SumMoney = transactionRepository.getDayMoney1();
        return ResultBuilder.buildSuccessResult(SumMoney);
    }


}
