package com.project_management.shoppingweb.repository;

import com.project_management.shoppingweb.dao.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card,Long> {
    List<Card> findByUserId(Integer userId);
    Card findByCardId(Integer cardId);
}
