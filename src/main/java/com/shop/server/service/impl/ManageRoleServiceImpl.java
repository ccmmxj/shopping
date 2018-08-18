package com.shop.server.service.impl;

import com.shop.server.mapper.ManageRoleMapper;
import com.shop.server.model.ManageRole;
import com.shop.server.service.ManageRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ManageRoleServiceImpl implements ManageRoleService {
    @Autowired
    private ManageRoleMapper eduRoleMapper;

    @Override
    public ManageRole findOne(Long id) {
        return eduRoleMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int saveOrUpdate(ManageRole eduRole) {
        if(eduRole.getId() != null) return update(eduRole);
        return save(eduRole);
    }

    @Override
    @Transactional
    public int save(ManageRole eduRole) {
        return eduRoleMapper.insertSelective(eduRole);
    }

    @Override
    @Transactional
    public int update(ManageRole eduRole) {
        return eduRoleMapper.updateByPrimaryKeySelective(eduRole);
    }

    @Override
    public List<ManageRole> findAll() {
        return eduRoleMapper.selectAll();
    }
}
