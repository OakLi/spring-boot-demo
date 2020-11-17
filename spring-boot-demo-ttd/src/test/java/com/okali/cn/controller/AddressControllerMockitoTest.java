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
 * spy对象和mock对象的两点区别， @Spy 和 @Mock的 两点 区别， @SpyBean 和 @MockBean 的两点区别：
 *
 * 1、默认行为的不同
 *
 *     对于未指定mock的方法，spy默认会调用真实的方法，有返回值的返回真实的返回值，而mock默认不执行，有返回值的，默认返回null
 *
 * 2、mock的使用方式不同
 *
 *     mock对象的使用方式，spy对象这样使用会直接调用该方法，所以无法这样使用，比如：
 * Mockito.when(restTemplate.postForObject(ArgumentMatchers.anyString(), ArgumentMatchers.any(),
 *                    ArgumentMatchers.eq(BaseWxResponse.class),ArgumentMatchers.anyString())).thenReturn(result);
 *
 *
 *     spy对象的使用方式，要先执行do等方法，mock对象也可以这样使用，比如：
 * Mockito.doReturn(info).when(authorizationInfoService).findAuthorizationInfo(appid);
 *
 *
 * 对于@Spy和 @SpyBean的区别， @Mock 和 @MockBean的区别
 *
 * 1、spy和mock生成的对象不受spring管理
 *
 * 2、spy调用真实方法时，其它bean是无法注入的，要使用注入，要使用SpyBean
 *
 * 3、SpyBean和MockBean生成的对象受spring管理，相当于自动替换对应类型bean的注入，比如@Autowired等注入
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
