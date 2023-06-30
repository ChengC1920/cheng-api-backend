package cn.ichensw.neroapiinterface;

import cn.ichensw.neroclientsdk.client.NeroApiClient;
import cn.ichensw.neroclientsdk.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class NeroApiInterfaceApplicationTests {

    @Resource
    private NeroApiClient neroApiClient;

    @Test
    void contextLoads() {
        String result1 = neroApiClient.getNameByGet("Nero");
        User user = new User();
        user.setUserName("NeroApi");
        String result2 = neroApiClient.getUserNameByPost(user);
        System.out.println(result1);
        System.out.println(result2);
    }

}
