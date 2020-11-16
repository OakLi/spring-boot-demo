package com.okali.cn.controller;

import com.okali.cn.entity.ChannelAddressEntity;
import com.okali.cn.service.ChannelAddressService;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * <P>Mockito和MockMvc结合使用</P>
 *  springboot 2.3版本中 可以使用 @ExtendWith(MockitoException.class)
 * @author lsx
 * @date 2020/11/16
 * @email lishuxiang-ds@gome.com.cn
 */
@RunWith(MockitoJUnitRunner.class)
public class AddressControllerMockitoMockMvcTest {

    private MockMvc mockMvc;

    @Mock
    private ChannelAddressService channelAddressService;

    @InjectMocks
    private AddressController addressController;

    @Before
    public void setUp() {
        // MockitoAnnotations.initMocks(this);
        // ReflectionTestUtils.setField(addressController,"channelAddressService",channelAddressService);
        mockMvc = MockMvcBuilders.standaloneSetup(addressController).alwaysDo(MockMvcResultHandlers.print()).build();
    }


    @Test
    public void test() throws Exception{
        if (addressController == null) {
            System.out.println("为空");
        }else {
            System.out.println("不为空");
        }
        ChannelAddressEntity mock = new ChannelAddressEntity();
        mock.setName("MockMvc和Mockito结合使用测试");
        Mockito.when(channelAddressService.findBySellerIdAndCodeId("0010000954", "3306546")).thenReturn(mock);
        mockMvc.perform(MockMvcRequestBuilders.get("/address/getChannelAddressByCodeId")
            .param("sellerId", "0010000954")
            .param("codeId", "3306546")
            .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk())
            // .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("200"))
            .andDo(MockMvcResultHandlers.print());
    }

}
