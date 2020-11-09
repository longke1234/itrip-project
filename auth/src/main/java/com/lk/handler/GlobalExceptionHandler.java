package com.lk.handler;

import com.lk.common.constants.ErrorCodeEnum;
import com.lk.common.vo.ReturnResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author : lk
 * @version : 4.0
 * @project : itrip-project
 * @description : 自定义异常处理器
 * @date : 2020-11-09 14:37
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    /**
     * 捕获Exception,主要适用于解决漏网之鱼
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ReturnResult error(Exception e){
        log.info(e.getMessage(),e);
        return ReturnResult.error(ErrorCodeEnum.FAILED);
    }
}