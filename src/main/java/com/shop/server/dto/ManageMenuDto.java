package com.shop.server.dto;

import com.shop.server.model.ManageMenu;

import java.util.List;

public class ManageMenuDto extends ManageMenu {
    private List<ManageMenuDto> childManageMenu;

    public List<ManageMenuDto> getChildManageMenu() {
        return childManageMenu;
    }

    public void setChildManageMenu(List<ManageMenuDto> childManageMenu) {
        this.childManageMenu = childManageMenu;
    }
}
