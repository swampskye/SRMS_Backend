package com.skye.srms_backend.controller;

import com.skye.srms_backend.entity.FixInfo;
import com.skye.srms_backend.entity.Server;
import com.skye.srms_backend.service.IFixInfoService;
import com.skye.srms_backend.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Skye_Zhao
 * @since 2023-04-15
 */
@Slf4j
@RestController
@RequestMapping("/fix")
public class FixInfoController {

    @Autowired
    private IFixInfoService fixInfoService;


    @GetMapping("/all")
    public Result<List> getFixInfoList(){
        List<FixInfo> list = fixInfoService.getFixInfoListOrderByAscOnName();
        log.debug("#####################all serverList:"+list);
        return Result.success(list);
    }


    @PostMapping("/add")
    public Result<FixInfo> createFixInfo(@Validated @RequestBody FixInfo fixInfo, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            String failedMsg = bindingResult.getFieldError().getDefaultMessage();
            return Result.fail(failedMsg);
        }

        FixInfo addedFixInfo = fixInfoService.addFixInfo(fixInfo);

        if (addedFixInfo != null){
            return Result.success(addedFixInfo, "issue created successfully");
        }
        return Result.fail("issue created failed");
    }
//
//    @GetMapping("/info")
//    public Result<FixInfo> getFixInfo(@RequestBody String ){
//
//    }

}
