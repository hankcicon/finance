package com.project_management.shoppingweb.repository;

import com.project_management.shoppingweb.dao.model.Transaction;
import com.project_management.shoppingweb.dao.model.Workflow;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long>{
    /**
     * 新增，保存
     * @param transaction
     * @return
     */
    public Transaction save(Transaction transaction);

    /**
     * 删除
     * @param transaction
     */
    public void delete(Transaction transaction);
    /**
     * 查找
     * @param pageable
     * @return
     */
    public Page<Transaction> findAll(Pageable pageable);

    /**
     * 条件查找
     * @param specification
     * @param pageable
     * @return
     */
    public Page<Transaction> findAll(Specification specification, Pageable pageable);

    public Object findAll(Specification specification);

    public Transaction findByTransactionId(Integer transactionId);

    @Query("SELECT coalesce(SUM(t.moneyNum),0) FROM Transaction t WHERE DATE_FORMAT(t.transactionTime,'%Y')=DATE_FORMAT(NOW(),'%Y') AND t.buyStatus='Y' ")
    public BigInteger getYearMoney1();

    @Query("SELECT coalesce(SUM(t.moneyNum),0) FROM Transaction t WHERE DATE_FORMAT(t.transactionTime,'%Y-%m')=DATE_FORMAT(NOW(),'%Y-%m') AND t.buyStatus='Y'")
    public BigInteger getMonthMoney1();

    @Query("SELECT coalesce(SUM(t.moneyNum),0) FROM Transaction t WHERE DATE_FORMAT(t.transactionTime,'%Y-%m-%d')=DATE_FORMAT(NOW(),'%Y-%m-%d') AND t.buyStatus='Y'")
    public BigInteger getDayMoney1();

    @Query("SELECT coalesce(SUM(t.moneyNum),0) FROM Transaction t WHERE DATE_FORMAT(t.transactionTime,'%Y')=DATE_FORMAT(NOW(),'%Y') AND t.sellStatus='Y' ")
    public BigInteger getYearMoney0();

    @Query("SELECT coalesce(SUM(t.moneyNum),0) FROM Transaction t WHERE DATE_FORMAT(t.transactionTime,'%Y-%m')=DATE_FORMAT(NOW(),'%Y-%m') AND t.sellStatus='Y'")
    public BigInteger getMonthMoney0();

    @Query("SELECT coalesce(SUM(t.moneyNum),0) FROM Transaction t WHERE DATE_FORMAT(t.transactionTime,'%Y-%m-%d')=DATE_FORMAT(NOW(),'%Y-%m-%d') AND t.sellStatus='Y'")
    public BigInteger getDayMoney0();

    @Query("SELECT DATE_FORMAT(t.transactionTime,'%Y-%m-%d') ,coalesce(SUM(t.moneyNum),0)  FROM Transaction t WHERE t.transactionTime<=?2 AND t.transactionTime>=?1 AND t.buyStatus='Y' GROUP BY DATE_FORMAT(t.transactionTime,'%Y-%m-%d')")
    public List<Object> getDayStatistic1(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
    @Query("SELECT DATE_FORMAT(t.transactionTime,'%Y-%m') ,coalesce(SUM(t.moneyNum),0)  FROM Transaction t WHERE t.transactionTime<=?2 AND t.transactionTime>=?1 AND t.buyStatus='Y' GROUP BY DATE_FORMAT(t.transactionTime,'%Y-%m')")
    public List<Object> getMonthStatistic1(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
    @Query("SELECT DATE_FORMAT(t.transactionTime,'%Y') ,coalesce(SUM(t.moneyNum),0)  FROM Transaction t WHERE t.transactionTime<=?2 AND t.transactionTime>=?1 AND t.buyStatus='Y' GROUP BY DATE_FORMAT(t.transactionTime,'%Y')")
    public List<Object> getYearStatistic1(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
    @Query("SELECT DATE_FORMAT(t.transactionTime,'%Y-%m-%d') ,coalesce(SUM(t.moneyNum),0)  FROM Transaction t WHERE t.transactionTime<=?2 AND t.transactionTime>=?1 AND t.sellStatus='Y' GROUP BY DATE_FORMAT(t.transactionTime,'%Y-%m-%d')")
    public List<Object> getDayStatistic0(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
    @Query("SELECT DATE_FORMAT(t.transactionTime,'%Y-%m') ,coalesce(SUM(t.moneyNum),0)  FROM Transaction t WHERE t.transactionTime<=?2 AND t.transactionTime>=?1 AND t.sellStatus='Y' GROUP BY DATE_FORMAT(t.transactionTime,'%Y-%m')")
    public List<Object> getMonthStatistic0(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
    @Query("SELECT DATE_FORMAT(t.transactionTime,'%Y') ,coalesce(SUM(t.moneyNum),0)  FROM Transaction t WHERE t.transactionTime<=?2 AND t.transactionTime>=?1 AND t.sellStatus='Y' GROUP BY DATE_FORMAT(t.transactionTime,'%Y')")
    public List<Object> getYearStatistic0(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
    @Query(value = "SELECT * FROM transaction t WHERE t.sell_id=?3 AND ((t.buy_status!='O' OR t.sell_status!='O') AND t.buy_status!='D' AND t.sell_status!='D'OR t.sell_status is null OR t.buy_status is null)  limit ?1,?2",nativeQuery = true)
    public List<Transaction> findSellList(int pagaNum,int pageSize,int sellId);
    @Query(value = "SELECT * FROM transaction t WHERE t.buy_id=?3 AND ((t.buy_status!='O' OR t.sell_status!='O') AND t.buy_status!='D' AND t.sell_status!='D' OR t.sell_status is null OR t.buy_status is null) limit ?1,?2",nativeQuery = true)
    public List<Transaction> findBuyList(int pagaNum,int pageSize,int buyId);
    @Query(value = "SELECT * FROM transaction t WHERE t.sell_id=?3  AND t.transaction_id=?4 AND ((t.buy_status!='O' OR t.sell_status!='O') AND t.buy_status!='D' AND t.sell_status!='D'OR t.sell_status is null OR t.buy_status is null)  limit ?1,?2",nativeQuery = true)
    public List<Transaction> findSellList(int pagaNum,int pageSize,int sellId,int transactionId);
    @Query(value = "SELECT * FROM transaction t WHERE t.buy_id=?3 AND t.transaction_id=?4 AND ((t.buy_status!='O' OR t.sell_status!='O') AND t.buy_status!='D' AND t.sell_status!='D' OR t.sell_status is null OR t.buy_status is null) limit ?1,?2",nativeQuery = true)
    public List<Transaction> findBuyList(int pagaNum,int pageSize,int buyId,int transactionId);
    @Query(value = "SELECT * FROM transaction t WHERE (t.buy_id=?3 OR t.sell_id=?3) AND t.transaction_id=?4 AND (t.buy_status='O' AND t.sell_status='O') limit ?1,?2",nativeQuery = true)
    public List<Transaction> findFin(int pagaNum,int pageSize,int userId,int transactionId);
    @Query(value = "SELECT * FROM transaction t WHERE (t.buy_id=?3 OR t.sell_id=?3)  AND (t.buy_status='O' AND t.sell_status='O')  limit ?1,?2",nativeQuery = true)
    public List<Transaction> findFin(int pagaNum,int pageSize,int userId);
};
