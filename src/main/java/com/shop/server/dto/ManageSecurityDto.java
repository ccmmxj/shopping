package com.shop.server.dto;

import com.shop.server.model.ManagePersiom;
import com.shop.server.model.ManageSecurity;

import java.util.List;

public class ManageSecurityDto extends ManageSecurity {
    private List<ManagePersiom> eduPersioms;

    public List<ManagePersiom> getManagePersioms() {
        return eduPersioms;
    }

    public void setManagePersioms(List<ManagePersiom> eduPersioms) {
        this.eduPersioms = eduPersioms;
    }
}