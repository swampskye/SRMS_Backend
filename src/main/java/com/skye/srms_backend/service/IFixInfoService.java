package com.skye.srms_backend.service;

import com.skye.srms_backend.entity.FixInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.skye.srms_backend.entity.Server;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Skye_Zhao
 * @since 2023-04-15
 */
public interface IFixInfoService extends IService<FixInfo> {


    FixInfo addFixInfo(FixInfo fixInfo);

    List<FixInfo> getFixInfoListOrderByAscOnName();
}
