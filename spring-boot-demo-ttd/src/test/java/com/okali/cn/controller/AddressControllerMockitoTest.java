package com.okali.cn.controller;

import com.alibaba.fastjson.JSON;
import com.okali.cn.entity.ChannelAddressEntity;
import com.okali.cn.service.ChannelAddressService;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

/**
 * <P>使用Mockito进行单元测试，不启动spring环境</P>
 *
 * @author lsx
 * @date 2020/11/16
 * @email lishuxiang-ds@gome.com.cn
 */
@RunWith(MockitoJUnitRunner.class)
public class AddressControllerMockitoTest {


    @Mock
    private ChannelAddressService channelAddressService;

    @InjectMocks
    private AddressController addressController;

    @BeforeEach
    public void setUp() {
        // MockitoAnnotations.initMocks(this);
        // ReflectionTestUtils.setField(addressController,"channelAddressService",channelAddressService);
    }

    @Test
    public void test(){
        ChannelAddressEntity mock = new ChannelAddressEntity();
        mock.setName("模拟返回");
        Mockito.when(channelAddressService.findBySellerIdAndCodeId("0010000954","3306546")).thenReturn(mock);
        ResponseEntity<ChannelAddressEntity> channelAddress = addressController.getChannelAddress("3306546", "0010000954");
        System.out.println(JSON.toJSONString(channelAddress));
    }

    @Test
    public void testSpy() {
        addressController =  Mockito.spy(addressController);
        ChannelAddressEntity mock = new ChannelAddressEntity();
        mock.setName("Mock Spy测试");
        Mockito.doReturn(ResponseEntity.ok(mock)).when(addressController).getChannelAddress("","");
        ResponseEntity<ChannelAddressEntity> channelAddress = addressController.getChannelAddress("", "");
        System.out.println(JSON.toJSONString(channelAddress));
    }

}
