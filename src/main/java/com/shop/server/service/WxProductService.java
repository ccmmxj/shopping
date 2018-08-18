package com.shop.server.service;

import com.shop.server.dto.TableData;
import com.shop.server.model.WxProduct;

import java.util.List;

public interface WxProductService extends BaseService<WxProduct,Long> {
    TableData<WxProduct> findListTable(TableData<WxProduct> tableData,Long companyId, String type, Boolean isBanner);
    List<String> findType(Long companyId);
    List<WxProduct> findBannerList(Long companyId);
    TableData<WxProduct> findProductTable(TableData<WxProduct> tableData,Long companyId,String title);
    WxProduct delProduct(Long id,Long companyId);
    WxProduct recProduct(Long id,Long companyId);
}
