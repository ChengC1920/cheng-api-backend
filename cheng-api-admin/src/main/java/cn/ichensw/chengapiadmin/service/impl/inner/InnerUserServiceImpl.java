package cn.ichensw.chengapiadmin.service.impl.inner;

import cn.ichensw.chengapiadmin.service.UserService;
import cn.ichensw.chengapicommon.model.entity.User;
import cn.ichensw.chengapicommon.service.InnerUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * 用户服务实现
 *
 */
@DubboService
@Slf4j
public class InnerUserServiceImpl implements InnerUserService {

    @Resource
    private UserService userService;

    @Override
    public User getInvokeUser(String accessKey) {
        return userService.query()
                .eq("accessKey", accessKey)
                .one();
    }
}
