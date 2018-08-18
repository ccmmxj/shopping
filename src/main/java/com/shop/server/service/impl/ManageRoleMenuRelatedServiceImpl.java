package com.shop.server.service.impl;

import com.shop.server.mapper.ManageRoleMenuRelatedMapper;
import com.shop.server.model.ManageRoleMenuRelated;
import com.shop.server.service.ManageRoleMenuRelatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ManageRoleMenuRelatedServiceImpl implements ManageRoleMenuRelatedService {
    @Autowired
    private ManageRoleMenuRelatedMapper eduRoleMenuRelatedMapper;

    @Override
    public ManageRoleMenuRelated findOne(Long id) {
        return eduRoleMenuRelatedMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int saveOrUpdate(ManageRoleMenuRelated eduRoleMenuRelated) {
        if(eduRoleMenuRelated.getId() != null) return update(eduRoleMenuRelated);
        return save(eduRoleMenuRelated);
    }

    @Override
    @Transactional
    public int save(ManageRoleMenuRelated eduRoleMenuRelated) {
        return eduRoleMenuRelatedMapper.insertSelective(eduRoleMenuRelated);
    }

    @Override
    @Transactional
    public int update(ManageRoleMenuRelated eduRoleMenuRelated) {
        return eduRoleMenuRelatedMapper.updateByPrimaryKeySelective(eduRoleMenuRelated);
    }

    @Override
    public List<ManageRoleMenuRelated> findAll() {
        return eduRoleMenuRelatedMapper.selectAll();
    }
}
