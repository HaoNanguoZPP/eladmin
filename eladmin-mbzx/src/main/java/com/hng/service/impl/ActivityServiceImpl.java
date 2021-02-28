/*
*  Copyright 2019-2020 Zheng Jie
*
*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*
*  http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*/
package com.hng.service.impl;

import com.hng.domain.Activity;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import com.hng.repository.ActivityRepository;
import com.hng.service.ActivityService;
import com.hng.service.dto.ActivityDto;
import com.hng.service.dto.ActivityQueryCriteria;
import com.hng.service.mapstruct.ActivityMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
* @website https://el-admin.vip
* @description 服务实现
* @author zpp
* @date 2021-02-24
**/
@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;
    private final ActivityMapper activityMapper;

    @Override
    public Map<String,Object> queryAll(ActivityQueryCriteria criteria, Pageable pageable){
        Page<Activity> page = activityRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(activityMapper::toDto));
    }

    @Override
    public List<ActivityDto> queryAll(ActivityQueryCriteria criteria){
        return activityMapper.toDto(activityRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public ActivityDto findById(Long id) {
        Activity activity = activityRepository.findById(id).orElseGet(Activity::new);
        ValidationUtil.isNull(activity.getId(),"Activity","id",id);
        return activityMapper.toDto(activity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ActivityDto create(Activity resources) {
        return activityMapper.toDto(activityRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Activity resources) {
        Activity activity = activityRepository.findById(resources.getId()).orElseGet(Activity::new);
        ValidationUtil.isNull( activity.getId(),"Activity","id",resources.getId());
        activity.copy(resources);
        activityRepository.save(activity);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            activityRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<ActivityDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ActivityDto activity : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("创建者", activity.getCreateBy());
            map.put("更新者", activity.getUpdateBy());
            map.put("创建日期", activity.getCreateTime());
            map.put("更新时间", activity.getUpdateTime());
            map.put("活动标题", activity.getTitle());
            map.put("活动描述", activity.getDescribe());
            map.put("图片地址", activity.getImage());
            map.put("优惠价格", activity.getPriceNew());
            map.put("原价", activity.getPriceOld());
            map.put("活动开始时间", activity.getBeginTime());
            map.put("活动结束时间", activity.getEndTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}