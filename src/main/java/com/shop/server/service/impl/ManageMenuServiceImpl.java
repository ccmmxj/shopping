package com.shop.server.service.impl;

import com.shop.server.mapper.ManageMenuMapper;
import com.shop.server.model.ManageMenu;
import com.shop.server.service.ManageMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ManageMenuServiceImpl implements ManageMenuService {
    @Autowired
    private ManageMenuMapper eduMenuMapper;

    @Override
    public ManageMenu findOne(Long id) {
        return eduMenuMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int saveOrUpdate(ManageMenu eduMenu) {
        if(eduMenu.getId() != null) return update(eduMenu);
        return save(eduMenu);
    }

    @Override
    @Transactional
    public int save(ManageMenu eduMenu) {
        return eduMenuMapper.insertSelective(eduMenu);
    }

    @Override
    @Transactional
    public int update(ManageMenu eduMenu) {
        return eduMenuMapper.updateByPrimaryKeySelective(eduMenu);
    }

    @Override
    public List<ManageMenu> findAll() {
        return eduMenuMapper.selectAll();
    }
}
