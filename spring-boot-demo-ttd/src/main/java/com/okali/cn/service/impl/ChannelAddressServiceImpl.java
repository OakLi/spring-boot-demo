package com.okali.cn.service.impl;

import com.okali.cn.entity.ChannelAddressEntity;
import com.okali.cn.service.ChannelAddressService;

import org.springframework.stereotype.Service;

/**
 * <P>接口实现类</P>
 *
 * @author lsx
 * @date 2020/11/16
 * @email lishuxiang-ds@gome.com.cn
 */
@Service
public class ChannelAddressServiceImpl implements ChannelAddressService {

    /**
     * 根据商家id和codeId查询地址
     *
     * @param sellerId 商家id
     * @param codeId   地址codeId
     * @return 地址信息
     */
    @Override
    public ChannelAddressEntity findBySellerIdAndCodeId(String sellerId, String codeId) {
        ChannelAddressEntity channelAddressEntity = new ChannelAddressEntity();
        channelAddressEntity.setName("接口返回数据");
        return channelAddressEntity;
    }
}
