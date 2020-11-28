package com.lk.seriver;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lk.bean.LabelDic;
import com.lk.common.vo.LabelDicVo;

import java.util.List;

public interface LabelDicService extends IService<LabelDic>{

    /**
     * 查询酒店特色
     * @return
     */
    List<LabelDicVo> fidLabelDicVo();

}
