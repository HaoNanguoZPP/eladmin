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
package com.hng.rest;

import me.zhengjie.annotation.Log;
import com.hng.domain.Activity;
import com.hng.service.ActivityService;
import com.hng.service.dto.ActivityQueryCriteria;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @website https://el-admin.vip
* @author zpp
* @date 2021-02-24
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "api/mbzx/activity管理")
@RequestMapping("/api/activity")
public class ActivityController {

    private final ActivityService activityService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('activity:list')")
    public void download(HttpServletResponse response, ActivityQueryCriteria criteria) throws IOException {
        activityService.download(activityService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询api/mbzx/activity")
    @ApiOperation("查询api/mbzx/activity")
    @PreAuthorize("@el.check('activity:list')")
    public ResponseEntity<Object> query(ActivityQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(activityService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增api/mbzx/activity")
    @ApiOperation("新增api/mbzx/activity")
    @PreAuthorize("@el.check('activity:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Activity resources){
        return new ResponseEntity<>(activityService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改api/mbzx/activity")
    @ApiOperation("修改api/mbzx/activity")
    @PreAuthorize("@el.check('activity:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody Activity resources){
        activityService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除api/mbzx/activity")
    @ApiOperation("删除api/mbzx/activity")
    @PreAuthorize("@el.check('activity:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Long[] ids) {
        activityService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}