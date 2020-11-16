package com.lk.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author : lk
 * @version : 4.0
 * @project : itrip-project
 * @description : MyBatisPlus时间自动填充器
 * @date : 2020-11-12 16:19
 */
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        //自动填充创建时间
        this.strictInsertFill(metaObject,"creationDate", LocalDateTime::now,LocalDateTime.class);
        //自动填充修改时间
        this.strictInsertFill(metaObject,"modifyDate",LocalDateTime::now,LocalDateTime.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        //自动填充修改时间
        this.strictInsertFill(metaObject,"modifyDate",LocalDateTime::now,LocalDateTime.class);
    }
}