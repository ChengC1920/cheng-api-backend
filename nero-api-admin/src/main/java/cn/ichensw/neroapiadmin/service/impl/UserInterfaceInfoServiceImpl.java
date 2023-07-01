package cn.ichensw.neroapiadmin.service.impl;

import cn.hutool.Hutool;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ichensw.neroapiadmin.common.ErrorCode;
import cn.ichensw.neroapiadmin.constant.CommonConstant;
import cn.ichensw.neroapiadmin.exception.BusinessException;
import cn.ichensw.neroapiadmin.exception.ThrowUtils;
import cn.ichensw.neroapiadmin.model.dto.interfaceinfo.InterfaceInfoQueryRequest;
import cn.ichensw.neroapiadmin.model.dto.userinterfaceinfo.UserInterfaceInfoQueryRequest;
import cn.ichensw.neroapiadmin.model.entity.InterfaceInfo;
import cn.ichensw.neroapiadmin.model.entity.User;
import cn.ichensw.neroapiadmin.model.entity.UserInterfaceInfo;
import cn.ichensw.neroapiadmin.model.vo.InterfaceInfoVO;
import cn.ichensw.neroapiadmin.model.vo.UserVO;
import cn.ichensw.neroapiadmin.service.UserInterfaceInfoService;
import cn.ichensw.neroapiadmin.mapper.UserInterfaceInfoMapper;
import cn.ichensw.neroapiadmin.utils.SqlUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.NumberUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author csw
 * @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service实现
 * @createDate 2023-06-17 12:32:57
 */
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
        implements UserInterfaceInfoService {

    @Override
    public void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add) {
        if (userInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Long userId = userInterfaceInfo.getUserId();
        Long interfaceInfoId = userInterfaceInfo.getInterfaceInfoId();
        Integer totalNum = userInterfaceInfo.getTotalNum();
        Integer leftNum = userInterfaceInfo.getLeftNum();

        // 创建时，参数不能为空
        if (add) {
            ThrowUtils.throwIf(userId == null || interfaceInfoId == null || totalNum == null || leftNum == null, ErrorCode.PARAMS_ERROR);
        }
        // 有参数则校验
        if (leftNum < 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "剩余次数不能小于0");
        }
        if (totalNum < 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "总调用次数不能小于0");
        }
    }


    /**
     * 获取查询包装类
     *
     * @param interfaceInfoQueryRequest
     * @return
     */
    @Override
    public QueryWrapper<UserInterfaceInfo> getQueryWrapper(UserInterfaceInfoQueryRequest interfaceInfoQueryRequest) {

        QueryWrapper<UserInterfaceInfo> queryWrapper = new QueryWrapper<>();
        if (interfaceInfoQueryRequest == null) {
            return queryWrapper;
        }

        String searchText = interfaceInfoQueryRequest.getSearchText();
        Long id = interfaceInfoQueryRequest.getId();
        Long userId = interfaceInfoQueryRequest.getUserId();
        Long interfaceInfoId = interfaceInfoQueryRequest.getInterfaceInfoId();
        Integer totalNum = interfaceInfoQueryRequest.getTotalNum();
        Integer leftNum = interfaceInfoQueryRequest.getLeftNum();
        Integer status = interfaceInfoQueryRequest.getStatus();
        String sortField = interfaceInfoQueryRequest.getSortField();
        String sortOrder = interfaceInfoQueryRequest.getSortOrder();

        // 拼接查询条件
        if (StringUtils.isNotBlank(searchText)) {
            queryWrapper.like("name", searchText);
        }
        queryWrapper.eq(ObjectUtils.isNotEmpty(id), "id", id);
        queryWrapper.eq(ObjectUtils.isNotEmpty(totalNum), "totalNum", totalNum);
        queryWrapper.eq(ObjectUtils.isNotEmpty(leftNum), "leftNum", leftNum);
        queryWrapper.eq(ObjectUtils.isNotEmpty(interfaceInfoId), "interfaceInfoId", interfaceInfoId);
        queryWrapper.eq(ObjectUtils.isNotEmpty(status), "status", status);
        queryWrapper.eq(ObjectUtils.isNotEmpty(userId), "userId", userId);
        queryWrapper.eq("isDelete", false);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }


    @Override
    public Page<UserInterfaceInfo> getUserInterfaceInfoVOPage(Page<UserInterfaceInfo> userInterfaceInfoPage, HttpServletRequest request) {
        List<UserInterfaceInfo> interfaceInfoList = userInterfaceInfoPage.getRecords();
        Page<UserInterfaceInfo> interfaceInfoVOPage = new Page<>(userInterfaceInfoPage.getCurrent(), userInterfaceInfoPage.getSize(), userInterfaceInfoPage.getTotal());
        if (CollectionUtils.isEmpty(interfaceInfoList)) {
            return interfaceInfoVOPage;
        }
        interfaceInfoVOPage.setRecords(interfaceInfoList);
        return interfaceInfoVOPage;
    }
}




