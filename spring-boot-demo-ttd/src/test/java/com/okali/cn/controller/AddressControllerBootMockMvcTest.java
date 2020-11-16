package com.okali.cn.controller;

import com.okali.cn.entity.ChannelAddressEntity;
import com.okali.cn.service.ChannelAddressService;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * <P>启动spring环境测试</P>
 * 新版本的springboot中，springbootTest已经包含runWith(SpringExtension.class)，不用再使用RunWith
 *runWith(SpringExtension.class) 和低版本中的runWith(SpringJUnit4ClassRunner.class)作用一样
 * 将Junit关联spring环境
 *
 * 注意 @SpringBootTest  @WebMvcTest  不能同时使用，同时使用的时候Mock相关的都不生效
 *
 *
 *
 * @author lsx
 * @date 2020/11/16
 * @email lishuxiang-ds@gome.com.cn
 */
@SpringBootTest
@AutoConfigureMockMvc
public class AddressControllerBootMockMvcTest {

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
