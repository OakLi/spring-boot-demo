package com.okali.cn.controller;



import com.okali.cn.entity.ChannelAddressEntity;
import com.okali.cn.service.ChannelAddressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address/")
public class AddressController {
    @Autowired
    private ChannelAddressService channelAddressService;


    @RequestMapping("/getChannelAddressByCodeId")
    public ResponseEntity<ChannelAddressEntity> getChannelAddress(String codeId, String sellerId) {
        return ResponseEntity.ok(channelAddressService.findBySellerIdAndCodeId(sellerId, codeId));
    }
}
