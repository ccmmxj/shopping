package com.shop.server.service.impl;

import com.shop.server.mapper.ManageRolePersiomRelatedMapper;
import com.shop.server.model.ManageRolePersiomRelated;
import com.shop.server.service.ManageRolePersiomRelatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ManageRolePersiomRelatedServiceImpl implements ManageRolePersiomRelatedService {
    @Autowired
    private ManageRolePersiomRelatedMapper eduRolePersiomRelatedMapper;

    @Override
    public ManageRolePersiomRelated findOne(Long id) {
        return eduRolePersiomRelatedMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int saveOrUpdate(ManageRolePersiomRelated eduRolePersiomRelated) {
        if(eduRolePersiomRelated.getId() != null) return update(eduRolePersiomRelated);
        return save(eduRolePersiomRelated);
    }

    @Override
    @Transactional
    public int save(ManageRolePersiomRelated eduRolePersiomRelated) {
        return eduRolePersiomRelatedMapper.insertSelective(eduRolePersiomRelated);
    }

    @Override
    @Transactional
    public int update(ManageRolePersiomRelated eduRolePersiomRelated) {
        return eduRolePersiomRelatedMapper.updateByPrimaryKeySelective(eduRolePersiomRelated);
    }

    @Override
    public List<ManageRolePersiomRelated> findAll() {
        return eduRolePersiomRelatedMapper.selectAll();
    }
}
