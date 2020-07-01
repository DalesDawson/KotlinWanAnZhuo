package com.e006.release.bean

/**
 * 创 建 人：zhengquan
 * 创建日期：2020/4/13
 * 修改时间：
 * 修改备注：
 */
class BaseResponse<T> {
    var data: T? = null
    var errorMsg = ""
    var errorCode = 0


}