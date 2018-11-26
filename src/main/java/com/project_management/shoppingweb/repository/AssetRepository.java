package com.project_management.shoppingweb.repository;

import com.project_management.shoppingweb.dao.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetRepository extends JpaRepository<Asset,Long> {
    List<Asset> findByUserId(Integer userId);
    Asset findByAssetId(Integer assetId);
}
