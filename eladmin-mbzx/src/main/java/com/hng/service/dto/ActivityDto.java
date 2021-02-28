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
package com.hng.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.io.Serializable;

/**
* @website https://el-admin.vip
* @description /
* @author zpp
* @date 2021-02-24
**/
@Data
public class ActivityDto implements Serializable {

    /** ID */
    private Long id;

    /** 创建者 */
    private String createBy;

    /** 更新者 */
    private String updateBy;

    /** 创建日期 */
    private Timestamp createTime;

    /** 更新时间 */
    private Timestamp updateTime;

    /** 活动标题 */
    private String title;

    /** 活动描述 */
    private String describe;

    /** 图片地址 */
    private String image;

    /** 优惠价格 */
    private BigDecimal priceNew;

    /** 原价 */
    private BigDecimal priceOld;

    /** 活动开始时间 */
    private Timestamp beginTime;

    /** 活动结束时间 */
    private Timestamp endTime;
}