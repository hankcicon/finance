package com.project_management.shoppingweb.service;

import com.project_management.shoppingweb.dao.model.Workflow;
import com.project_management.shoppingweb.dao.model.WorkflowCustom;
import com.project_management.shoppingweb.dao.vo.RequestResultVO;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface WorkflowCustomService {
    /**
     * 分页多条件查询
     * @param pageNumber    页号
     * @param pageSize  页表数
     * @return
     */
    public Page<WorkflowCustom> findByPage(int pageNumber, int pageSize);

    /**
     * 插入工作流
     * @param workflowCustom
     * @return
     */
    public WorkflowCustom insert(WorkflowCustom workflowCustom);

    /**
     * 更新工作流
     * @param workflowCustom
     * @return
     */
    public WorkflowCustom update(WorkflowCustom workflowCustom);

    /**
     * 删除工作流
     * @param workflowCustom
     */
    public void delete(WorkflowCustom workflowCustom);

    /**
     * 根据名称查找
     * @param name
     * @return
     */
    public WorkflowCustom findByName(String name);
}
