package com.project_management.shoppingweb.repository;

import com.project_management.shoppingweb.dao.model.Workflow;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface WorkflowRepository extends JpaSpecificationExecutor<Workflow>,JpaRepository<Workflow,Long> {
    /**
     * 查找
     * @param pageable
     * @return
     */
    public Page<Workflow> findAll(Pageable pageable);
    public Page<Workflow> findAll(Specification specification,Pageable pageable);

    /**
     * 插入与更新
     * @param workflow
     * @return
     */
    public Workflow save(Workflow workflow);

    /**
     * 根据工作流Id查找
     * @param workflowId
     * @return
     */
    public Workflow findByWorkflowId(Integer workflowId);
}
