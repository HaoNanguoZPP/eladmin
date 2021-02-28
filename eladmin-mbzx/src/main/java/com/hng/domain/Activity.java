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
package com.hng.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiModelProperty;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.io.Serializable;

/**
* @website https://el-admin.vip
* @description /
* @author zpp
* @date 2021-02-24
**/
@Entity
@Data
@Table(name="t_activity")
public class Activity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "ID")
    private Long id;

    @Column(name = "create_by")
    @ApiModelProperty(value = "创建者")
    private String createBy;

    @Column(name = "update_by")
    @ApiModelProperty(value = "更新者")
    private String updateBy;

    @Column(name = "create_time")
    @ApiModelProperty(value = "创建日期")
    private Timestamp createTime;

    @Column(name = "update_time")
    @ApiModelProperty(value = "更新时间")
    private Timestamp updateTime;

    @Column(name = "title")
    @ApiModelProperty(value = "活动标题")
    private String title;

    @Column(name = "describe")
    @ApiModelProperty(value = "活动描述")
    private String describe;

    @Column(name = "image")
    @ApiModelProperty(value = "图片地址")
    private String image;

    @Column(name = "price_new")
    @ApiModelProperty(value = "优惠价格")
    private BigDecimal priceNew;

    @Column(name = "price_old")
    @ApiModelProperty(value = "原价")
    private BigDecimal priceOld;

    @Column(name = "begin_time")
    @ApiModelProperty(value = "活动开始时间")
    private Timestamp beginTime;

    @Column(name = "end_time")
    @ApiModelProperty(value = "活动结束时间")
    private Timestamp endTime;

    public void copy(Activity source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}