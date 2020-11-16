package com.okali.cn.controller;

import com.okali.cn.entity.ChannelAddressEntity;
import com.okali.cn.service.ChannelAddressService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * <P>启动spring环境手动创建MockMvc实例</P>
 *
 * @author lsx
 * @date 2020/11/16
 * @email lishuxiang-ds@gome.com.cn
 */
@SpringBootTest
public class AddressControllerBootTest {

    @Autowired
    private WebApplicationContext context;


    // @Autowired
    private MockMvc mockMvc;


    @MockBean
    private ChannelAddressService channelAddressService;

    @BeforeEach
    public void setMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

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
