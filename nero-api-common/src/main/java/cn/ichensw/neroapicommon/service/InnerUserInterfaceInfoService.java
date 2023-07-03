package cn.ichensw.neroapicommon.service;

/**
 *
 */
public interface InnerUserInterfaceInfoService {

    /**
     * 调用接口统计
     *
     * @param interfaceInfoId 接口ID
     * @param userId 用户ID
     * @return boolean 是否执行成功
     */
    boolean invokeCount(long interfaceInfoId, long userId);
}
