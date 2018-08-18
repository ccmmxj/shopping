package com.shop.server.controller;

import com.shop.server.dto.TableData;
import com.shop.server.model.WxProduct;
import com.shop.server.service.WxProductService;
import com.shop.server.utils.LoginUtil;
import com.shop.server.utils.Result;
import com.shop.server.utils.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("shopping/manage/product")
public class ManageProductController {

    @Autowired
    private WxProductService wxProductService;

    @PostMapping("list")
    @ResponseBody
    public TableData<WxProduct> list(TableData<WxProduct> tableData, String title){
        return wxProductService.findProductTable(tableData,LoginUtil.getLoginUser().getManageUser().getCompanyId(),title);
    }

    @PostMapping("del")
    @ResponseBody
    public Result<WxProduct> del(Long id){
        WxProduct wxProduct = wxProductService.delProduct(id,LoginUtil.getLoginUser().getManageUser().getCompanyId());
        if (wxProduct != null) {
            return ResultFactory.newInstaceSuccessResult("删除成功", 200L, wxProduct);
        }
        return ResultFactory.newInstaceSuccessResult("删除失败", 200L, wxProduct);
    }

    @PostMapping("rec")
    @ResponseBody
    public Result<WxProduct> rec(Long id){
        WxProduct wxProduct = wxProductService.recProduct(id,LoginUtil.getLoginUser().getManageUser().getCompanyId());
        if (wxProduct != null) {
            return ResultFactory.newInstaceSuccessResult("恢復成功", 200L, wxProduct);
        }
        return ResultFactory.newInstaceSuccessResult("恢复失败", 200L, wxProduct);
    }

    @PostMapping("saveOrUpdate")
    @ResponseBody
    public Result<WxProduct> saveOrUpdate(WxProduct wxProduct){
        String message = "保存";
        if(wxProduct.getId() != null){
            message = "编辑";
        }
        wxProduct.setCompanyId(LoginUtil.getLoginUser().getManageUser().getCompanyId());
        int count = wxProductService.saveOrUpdate(wxProduct);
        if (count > 0 ) {
            return ResultFactory.newInstaceSuccessResult(message + "成功", 200L, wxProduct);
        }
        return ResultFactory.newInstaceSuccessResult(message + "失败", 200L, wxProduct);
    }
}
