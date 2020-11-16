package com.lk.handler;

import com.lk.common.constants.ErrorCodeEnum;
import com.lk.common.exception.DaoException;
import com.lk.common.exception.ServiceException;
import com.lk.common.exception.SysException;
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

    /**
     *捕捉并处理dao层自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = DaoException.class)
    public ReturnResult error(DaoException e){
        log.info(e.getMessage());
        return ReturnResult.error(e.getErrorCode(),e.getMessage());
    }

    /**
     *捕捉并处理service层自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = ServiceException.class)
    public ReturnResult error(ServiceException e){
        log.info(e.getMessage());
        return ReturnResult.error(e.getErrorCode(),e.getMessage());
    }

    /**
     *捕捉并处理sys系统自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = SysException.class)
    public ReturnResult error(SysException e){
        log.info(e.getMessage());
        return ReturnResult.error(e.getErrorCode(),e.getMessage());
    }
}