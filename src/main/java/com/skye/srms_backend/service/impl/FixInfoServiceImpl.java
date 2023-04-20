package com.skye.srms_backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.skye.srms_backend.entity.FixInfo;
import com.skye.srms_backend.entity.Server;
import com.skye.srms_backend.entity.User;
import com.skye.srms_backend.mapper.FixInfoMapper;
import com.skye.srms_backend.service.IFixInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Skye_Zhao
 * @since 2023-04-15
 */
@Slf4j
@Service
public class FixInfoServiceImpl extends ServiceImpl<FixInfoMapper, FixInfo> implements IFixInfoService {

    @Override
    public FixInfo addFixInfo(FixInfo fixInfo) {
        log.debug("fixInfo before insert============"+fixInfo);
        fixInfo.setCreatedDate(LocalDateTime.now());
        this.baseMapper.insert(fixInfo);
        log.debug("fixInfo after insert============="+fixInfo);
        return fixInfo;
    }

    @Override
    public List<FixInfo> getFixInfoListOrderByAscOnName() {
        LambdaQueryWrapper<FixInfo> lambdaQueryWrapper = new LambdaQueryWrapper<FixInfo>();
        lambdaQueryWrapper.orderByDesc(FixInfo::getCreatedDate);
        List<FixInfo> fixInfoList = this.baseMapper.selectList(lambdaQueryWrapper);
        return fixInfoList;
    }
}
