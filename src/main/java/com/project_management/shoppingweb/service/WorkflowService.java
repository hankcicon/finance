package com.project_management.shoppingweb.service;

import com.project_management.shoppingweb.dao.interfaceValue.WorkflowPageModel;
import com.project_management.shoppingweb.dao.model.Workflow;
import org.springframework.data.domain.Page;

import java.lang.reflect.InvocationTargetException;

public interface WorkflowService {
    /**
     * 分页查找
     * @param workflowPageModel
     * @return
     */
    public Page<Workflow> findByPage(WorkflowPageModel workflowPageModel);

    /**
     * 获取业务信息
     * @param workflow
     * @return
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws InvocationTargetException
     */
    public Object findBusinessInfo(Workflow workflow);

    /**
     * 插入工作流
     * @param targetObject
     * @return
     */
    public Workflow insertForBusiness(Object targetObject);

    /**
     * 选择更新
     * @param workflow
     * @return
     */
    public Object updateSelective(Workflow workflow);

    /**
     * 审核函数
     * @param workflow
     */
    public void update(Workflow workflow);
}
