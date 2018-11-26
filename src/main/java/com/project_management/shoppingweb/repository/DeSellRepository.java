package com.project_management.shoppingweb.repository;

import com.project_management.shoppingweb.dao.model.DeSell;
import com.project_management.shoppingweb.dao.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeSellRepository extends JpaRepository<DeSell,Long> {
    DeSell findByDeSellId(Integer sellId);
    List<DeSell> findByUserId(Integer userId);
    DeSell save(DeSell deSell);
    List<DeSell> findAll();
    Page<DeSell> findAll(Pageable pageable);
}
