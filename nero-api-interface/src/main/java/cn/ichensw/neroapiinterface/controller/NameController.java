package cn.ichensw.neroapiinterface.controller;

import cn.ichensw.neroclientsdk.model.User;
import cn.ichensw.neroclientsdk.utils.SignUtils;
import com.sun.deploy.util.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * @author csw
 */
@RestController
@RequestMapping("/name")
public class NameController {

    @GetMapping("/get")
    public String getNameByGet(String name, HttpServletRequest request) {
        System.out.println(request.getHeader("nero"));
        if (Strings.isBlank(name)) {
            throw new RuntimeException("错误");
        }
        return "GET 你的名字是：" + name;
    }

    @PostMapping("/post")
    public String getNameByPost(@RequestParam String name) {
        return "POST 你的名字是：" + name;
    }

    @PostMapping("/user")
    public String getUserNameByPost(@RequestBody User user, HttpServletRequest request) throws UnsupportedEncodingException {
        String accessKey = request.getHeader("accessKey");
        String nonce = request.getHeader("nonce");
        String timestamp = request.getHeader("timestamp");
        String sign = request.getHeader("sign");
        String body = request.getHeader("body");
        // TODO: 通过 accessKey 查询是否存在该用户
        if (!"nero".equals(accessKey)) {
            throw new RuntimeException("无权限");
        }

        // 随机数校验是否合法
        if (Long.parseLong(nonce) > 10000) {
            throw new RuntimeException("无权限");
        }
        // 时间戳 和 当前时间不能超过 5 分钟 (300000毫秒)
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        long difference = currentTimeMillis - Long.parseLong(timestamp);
        if (Math.abs(difference) > 300000) {
            throw new RuntimeException("无权限");
        }
        // 校验签名
        // TODO: 实际应该通过 accessKey 查询数据库中的 secretKey 生成 sign 和前端传递的 sign 对比
        String serverSign = SignUtils.genSign(body, "abcdefgh");
        if (!sign.equals(serverSign)) {
            throw new RuntimeException("无权限");
        }
        return "POST 你的用户名字是：" + user.getUsername();
    }

}
