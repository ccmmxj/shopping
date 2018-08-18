package com.shop.server.service.impl;

import com.shop.server.mapper.ManageRoleUserRelatedMapper;
import com.shop.server.model.ManageRoleUserRelated;
import com.shop.server.service.ManageRoleUserRelatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ManageRoleUserRelatedServiceImpl implements ManageRoleUserRelatedService {
    @Autowired
    private ManageRoleUserRelatedMapper eduRoleUserRelatedMapper;

    @Override
    public ManageRoleUserRelated findOne(Long id) {
        return eduRoleUserRelatedMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int saveOrUpdate(ManageRoleUserRelated eduRoleUserRelated) {
        if(eduRoleUserRelated.getId() != null) return update(eduRoleUserRelated);
        return save(eduRoleUserRelated);
    }

    @Override
    @Transactional
    public int save(ManageRoleUserRelated eduRoleUserRelated) {
        return eduRoleUserRelatedMapper.insertSelective(eduRoleUserRelated);
    }

    @Override
    @Transactional
    public int update(ManageRoleUserRelated eduRoleUserRelated) {
        return eduRoleUserRelatedMapper.updateByPrimaryKeySelective(eduRoleUserRelated);
    }

    @Override
    public List<ManageRoleUserRelated> findAll() {
        return eduRoleUserRelatedMapper.selectAll();
    }
}
