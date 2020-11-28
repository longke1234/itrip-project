package com.lk.service.impl;

import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import com.lk.common.constants.ErrorCodeEnum;
import com.lk.common.exception.ServiceException;
import com.lk.condition.SearchHotelCondition;
import com.lk.dao.SearchHotelRepository;
import com.lk.service.SearchHotelService;
import com.lk.vo.HotelVo;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : lk
 * @version : 4.0
 * @project : itrip-project
 * @description : 搜索热门陈氏实现类
 * @date : 2020-11-27 11:12
 */
@Service
@Slf4j
public class SearchHotelServiceImpl implements SearchHotelService {

    @Resource
    SearchHotelRepository searchHotelRepository;

    @Override
    public List<HotelVo> searchHotelByHotelCity(Long cityId, Long pageSize) {
        //查询所有数据
        List<HotelVo> hotelVoList = searchHotelRepository.findByCityId(cityId)
                //指定数据长度
                .stream().limit(pageSize)
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(hotelVoList)) {
            //集合数据为空
            throw new ServiceException(ErrorCodeEnum.BIZ_DATA_NULL);
        }
        return hotelVoList;
    }

    @Override
    public Page<HotelVo> searchHotelPage(SearchHotelCondition condition) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        //将所有的条件动态封装到BoolQueryBuilder对象中，是否动态查询
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        //判断条件是否为空，来动态拼接条件
        if (StringUtils.hasText(condition.getDestination())) {
            //详情 地址，表示一定要拼接，与实体类中的城市冗余字段匹配
            builder.must(QueryBuilders.matchQuery("redundantCityName", condition.getDestination()));
        }
        if (!StringUtils.isEmpty(condition.getHotelLevel())) {
            //酒店级别
            builder.must(QueryBuilders.matchQuery("hotelLevel", condition.getHotelLevel()));
        }
        if (StringUtils.hasText(condition.getKeywords())) {
            //关键词，全文搜索
            builder.must(QueryBuilders.queryStringQuery(condition.getKeywords()));
        }
        if (!StringUtils.isEmpty(condition.getMinPrice())) {
            //最低价
            builder.filter(QueryBuilders.rangeQuery("minPrice").gt(condition.getMinPrice())
            );
        }
        if (!StringUtils.isEmpty(condition.getMaxPrice())) {
            //最高价
            builder.filter(QueryBuilders.rangeQuery("maxPrice").lt(condition.getMaxPrice())
            );
        }
        if (StringUtils.hasText(condition.getFeatureIds())) {
            //酒店特色
            builder.must(QueryBuilders.termQuery("featureId", condition.getFeatureIds()));
        }
        //指定排序
        Sort.Order order = null;
        if (!StringUtils.isEmpty(condition.getAscSort())) {
            order = Sort.Order.asc(condition.getAscSort());
        }
        if (!StringUtils.isEmpty(condition.getDescSort())) {
            order = Sort.Order.desc(condition.getDescSort());
        }
        PageRequest pageable = PageRequest.of(condition.getPageNo(),
                condition.getPageSize());
        if (order != null) {
            pageable = PageRequest.of(condition.getPageNo(),
                    condition.getPageSize(), Sort.by(order));
        }
        queryBuilder.withQuery(builder);
        queryBuilder.withPageable(pageable);
        Page<HotelVo> hotelVOPage = searchHotelRepository.search(queryBuilder.build());
        return hotelVOPage;

    }
}