package com.shop.server.controller;

import com.shop.server.dto.TableData;
import com.shop.server.dto.WxOrderDto;
import com.shop.server.model.WxOrder;
import com.shop.server.model.WxProduct;
import com.shop.server.service.WxOrderService;
import com.shop.server.utils.LoginUtil;
import com.shop.server.utils.Result;
import com.shop.server.utils.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("shopping/manage/order")
public class ManageOrderController {

    @Autowired
    private WxOrderService wxOrderService;

    @PostMapping("list")
    @ResponseBody
    public TableData<WxOrderDto> list(TableData<WxOrderDto> tableData, String oid){
        return wxOrderService.findWxOrderDtoTable(tableData,LoginUtil.getLoginUser().getManageUser().getCompanyId(),oid);
    }

    @PostMapping("del")
    @ResponseBody
    public Result<WxOrder> del(Long id){
        WxOrder wxOrder = wxOrderService.delOrder(id,LoginUtil.getLoginUser().getManageUser().getCompanyId());
        if (wxOrder != null) {
            return ResultFactory.newInstaceSuccessResult("删除成功", 200L, wxOrder);
        }
        return ResultFactory.newInstaceSuccessResult("删除失败", 200L, wxOrder);
    }

    @PostMapping("upStatus")
    @ResponseBody
    public Result<WxOrder> upStatus(Long id,String type){
        WxOrder wxOrder = wxOrderService.upStatus(id,type,LoginUtil.getLoginUser().getManageUser().getCompanyId());
        if (wxOrder != null) {
            return ResultFactory.newInstaceSuccessResult("修改状态成功", 200L, wxOrder);
        }
        return ResultFactory.newInstaceSuccessResult("修改状态失败", 200L, wxOrder);
    }

    @PostMapping("rec")
    @ResponseBody
    public Result<WxOrder> rec(Long id){
        WxOrder wxOrder = wxOrderService.recOrder(id,LoginUtil.getLoginUser().getManageUser().getCompanyId());
        if (wxOrder != null) {
            return ResultFactory.newInstaceSuccessResult("恢復成功", 200L, wxOrder);
        }
        return ResultFactory.newInstaceSuccessResult("恢复失败", 200L, wxOrder);
    }
}
