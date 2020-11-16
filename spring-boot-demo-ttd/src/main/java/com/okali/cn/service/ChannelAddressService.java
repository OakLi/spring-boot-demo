package com.okali.cn.service;


import com.okali.cn.entity.ChannelAddressEntity;

public interface ChannelAddressService {


    /**
     * 根据商家id和codeId查询地址
     * @param sellerId    商家id
     * @param codeId      地址codeId
     * @return            地址信息
     */
    ChannelAddressEntity findBySellerIdAndCodeId(String sellerId, String codeId);



}
