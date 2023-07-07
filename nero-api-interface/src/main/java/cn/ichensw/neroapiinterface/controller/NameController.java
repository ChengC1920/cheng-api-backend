package cn.ichensw.neroapiinterface.controller;

import cn.ichensw.neroclientsdk.model.User;
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
    public String getUserNameByPost(@RequestBody User user, HttpServletRequest request) {
        return "POST 你的用户名字是：" + user.getUsername();
    }

}
