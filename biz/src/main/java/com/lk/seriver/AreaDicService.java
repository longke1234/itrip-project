package com.lk.seriver;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lk.bean.AreaDic;
import com.lk.common.vo.AreDicVo;

import java.util.List;

public interface AreaDicService extends IService<AreaDic>{
    /**
     * 查询热门城市id和城市名
     * @param type
     * @return
     */
    List<AreDicVo> findAreaDicVoById(Integer type);

    /**
     * 根据城市Id查询商圈
     * @param cityId
     * @return
     */
    List<AreDicVo> findAreaDicVoByCityId(Long cityId);
}
