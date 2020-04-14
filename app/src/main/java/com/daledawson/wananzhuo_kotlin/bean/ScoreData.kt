package com.daledawson.wananzhuo_kotlin.bean

import java.io.Serializable

/**
 * 创 建 人：zhengquan
 * 创建日期：2020/4/14
 * 修改时间：
 * 修改备注：
 */
class ScoreData : Serializable {
    var coinCount: Int = 0
    var rank: Int = 0
    var userId: Int = 0
    var username: String? = null
}