package com.project_management.shoppingweb.repository;

import com.project_management.shoppingweb.dao.model.WorkflowCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Repository
public interface WorkflowCustomRepository extends JpaSpecificationExecutor<WorkflowCustom>,JpaRepository<WorkflowCustom,Long>{
    /**
     * 分页查询
     * @param pageable
     * @return
     */
    public Page<WorkflowCustom> findAll(Pageable pageable);
//    /**
//     * 批量删除
//     * @param WorkflowCustomList
//     */
//    @Transactional
//    public void deleteInBatch(List<WorkflowCustom> WorkflowCustomList);

    /**
     * 删除
     * @param WorkflowCustom
     */
    public void delete(WorkflowCustom WorkflowCustom);

    /**
     * 保存
     * @param WorkflowCustom
     */
    public WorkflowCustom save(WorkflowCustom WorkflowCustom);

    /**
     * 根据工作流类型id进行查找
     * @param WorkflowCustomId
     * @return
     */
    public WorkflowCustom findByWorkflowCustomId(Integer WorkflowCustomId);

    /**
     * 根据工作流名称查找
     * @param name
     * @return
     */
    public WorkflowCustom findByName(String name);

}
