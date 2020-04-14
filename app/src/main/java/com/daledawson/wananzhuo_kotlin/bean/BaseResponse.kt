package com.daledawson.wananzhuo_kotlin.bean

/**
 * 创 建 人：zhengquan
 * 创建日期：2020/4/13
 * 修改时间：
 * 修改备注：
 */
open class BaseResponse(
    open var errorCode: Int,
    open var errorMsg: String
)