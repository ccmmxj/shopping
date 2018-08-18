package com.shop.server.service.impl;

import com.shop.server.mapper.ManagePersiomMapper;
import com.shop.server.model.ManagePersiom;
import com.shop.server.service.ManagePersiomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ManagePersiomServiceImpl implements ManagePersiomService {
    @Autowired
    private ManagePersiomMapper eduPersiomMapper;

    @Override
    public ManagePersiom findOne(Long id) {
        return eduPersiomMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int saveOrUpdate(ManagePersiom eduPersiom) {
        if(eduPersiom.getId() != null) return update(eduPersiom);
        return save(eduPersiom);
    }

    @Override
    @Transactional
    public int save(ManagePersiom eduPersiom) {
        return eduPersiomMapper.insertSelective(eduPersiom);
    }

    @Override
    @Transactional
    public int update(ManagePersiom eduPersiom) {
        return eduPersiomMapper.updateByPrimaryKeySelective(eduPersiom);
    }

    @Override
    public List<ManagePersiom> findAll() {
        return eduPersiomMapper.selectAll();
    }
}
