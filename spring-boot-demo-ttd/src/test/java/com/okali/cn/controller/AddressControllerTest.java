package com.okali.cn.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.okali.cn.entity.ChannelAddressEntity;
import com.okali.cn.service.ChannelAddressService;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * 使用MockMvc测试controller接口
 * spring只实例化controller层的bean，service层的bean使用mockBean
 */
@WebMvcTest(AddressController.class)
class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChannelAddressService channelAddressService;

    @Test
    public void getChannelAddressTest() throws Exception{
        ChannelAddressEntity mock = new ChannelAddressEntity();
        mock.setName("测试只启动web");
        BDDMockito.given(channelAddressService.findBySellerIdAndCodeId("0010000954", "3354006")).willReturn(mock);
        mockMvc.perform(MockMvcRequestBuilders.get("/address/getChannelAddressByCodeId")
            .param("sellerId", "0010000954")
            .param("codeId", "3354006")
            .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk())
            // .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("200"))
            .andDo(MockMvcResultHandlers.print());
    }

}
