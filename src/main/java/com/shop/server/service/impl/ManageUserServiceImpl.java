package com.shop.server.service.impl;

import com.shop.server.mapper.ManageUserMapper;
import com.shop.server.model.ManageUser;
import com.shop.server.service.ManageUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ManageUserServiceImpl implements ManageUserService {
    @Autowired
    private ManageUserMapper eduUserMapper;

    @Override
    public ManageUser findOne(Long id) {
        return eduUserMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int saveOrUpdate(ManageUser eduUser) {
        if(eduUser.getId() != null) return update(eduUser);
        return save(eduUser);
    }

    @Override
    @Transactional
    public int save(ManageUser eduUser) {
        return eduUserMapper.insertSelective(eduUser);
    }

    @Override
    @Transactional
    public int update(ManageUser eduUser) {
        return eduUserMapper.updateByPrimaryKeySelective(eduUser);
    }

    @Override
    public List<ManageUser> findAll() {
        return eduUserMapper.selectAll();
    }
}
