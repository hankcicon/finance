/**
 * 崇新OA系统
 */
package com.project_management.shoppingweb.service.Impl;

import com.project_management.shoppingweb.dao.model.WorkflowCustom;
import com.project_management.shoppingweb.repository.WorkflowCustomRepository;
import com.project_management.shoppingweb.service.WorkflowCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *@author sliansoft.com
 *2017年3月23日 上午11:29:27
 */

@Service("sysWorkflowCustomService")
public class WorkflowCustomServiceImpl implements WorkflowCustomService{


    @Autowired
    private WorkflowCustomRepository workflowCustomRepository;

    public Page<WorkflowCustom> findByPage(int pageNumber, int pageSize) {
        Pageable pageable = new PageRequest(pageNumber,pageSize, Sort.Direction.ASC, "workflowCustomId");
        return workflowCustomRepository.findAll(pageable);
    }

    public void delete(WorkflowCustom workflowCustom){
        workflowCustomRepository.delete(workflowCustom);
    }

    public WorkflowCustom insert(WorkflowCustom workflowCustom){
        return workflowCustomRepository.save(workflowCustom);
    }
    public WorkflowCustom update(WorkflowCustom workflowCustom){
        return workflowCustomRepository.save(workflowCustom);
    }
    public WorkflowCustom findByName(String name){
        return workflowCustomRepository.findByName(name);
    }

}