package cn.ichensw.neroapiadmin.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.ichensw.neroapiadmin.model.dto.interfaceinfo.InterfaceInfoQueryRequest;
import cn.ichensw.neroapiadmin.model.entity.InterfaceInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.ichensw.neroapiadmin.model.vo.InterfaceInfoVO;

import javax.servlet.http.HttpServletRequest;

/**
* @author csw
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2023-06-07 09:37:06
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {

    /**
     * 校验
     *
     * @param interfaceInfo
     * @param add
     */
    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);


    /**
     * 获取查询条件
     *
     * @param interfaceInfoQueryRequest
     * @return
     */
    QueryWrapper<InterfaceInfo> getQueryWrapper(InterfaceInfoQueryRequest interfaceInfoQueryRequest);

    /**
     * 获取接口信息封装
     *
     * @param interfaceInfo
     * @param request
     * @return
     */
    InterfaceInfoVO getInterfaceInfoVO(InterfaceInfo interfaceInfo, HttpServletRequest request);

    /**
     * 分页获取接口信息封装
     *
     * @param interfaceInfoPage
     * @param request
     * @return
     */
    Page<InterfaceInfoVO> getInterfaceInfoVOPage(Page<InterfaceInfo> interfaceInfoPage, HttpServletRequest request);
}
