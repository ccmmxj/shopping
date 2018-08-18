package com.shop.server.service.impl;

import com.shop.server.mapper.ManageCompanyMapper;
import com.shop.server.mapper.ManageUserMapper;
import com.shop.server.model.ManageCompany;
import com.shop.server.model.ManageUser;
import com.shop.server.service.ManageCompanyService;
import com.shop.server.service.ManageUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ManageCompanyServiceImpl implements ManageCompanyService {
    @Autowired
    private ManageCompanyMapper manageCompanyMapper;

    @Override
    public ManageCompany findOne(Long id) {
        return manageCompanyMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int saveOrUpdate(ManageCompany manageCompany) {
        if(manageCompany.getId() != null) return update(manageCompany);
        return save(manageCompany);
    }

    @Override
    @Transactional
    public int save(ManageCompany manageCompany) {
        Date now = new Date();
        manageCompany.setGmtCreated(now);
        manageCompany.setGmtModified(now);
        return manageCompanyMapper.insertSelective(manageCompany);
    }

    @Override
    @Transactional
    public int update(ManageCompany manageCompany) {
        Date now = new Date();
        manageCompany.setGmtModified(now);
        return manageCompanyMapper.updateByPrimaryKeySelective(manageCompany);
    }

    @Override
    public List<ManageCompany> findAll() {
        return manageCompanyMapper.selectAll();
    }
}
