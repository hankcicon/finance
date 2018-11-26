package com.project_management.shoppingweb.repository;

import com.project_management.shoppingweb.dao.model.DeBuy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeBuyRepository extends JpaRepository<DeBuy,Long> {

    DeBuy findByDeBuyId(Integer DeBuyId);
    List<DeBuy> findByUserId(Integer userId);
    DeBuy save(DeBuy deBuy);
    List<DeBuy> findAll();
    Page<DeBuy> findAll(Pageable pageable);
}
