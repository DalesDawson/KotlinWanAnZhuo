package com.daledawson.wananzhuo_kotlin.bean

/**
 * 创 建 人：zhengquan
 * 创建日期：2020/4/10
 * 修改时间：
 * 修改备注：
 */
data class RegisterResponse(
    val errorCode: Int,
    val errorMsg: String,
    val registerData: RegisterData
)

data class RegisterData(
    val admin: Boolean,
    val chapterTops: List<Any>,
    val collectIds: List<Any>,
    val email: String,
    val icon: String,
    val id: Int,
    val nickname: String,
    val password: String,
    val publicName: String,
    val token: String,
    val type: Int,
    val username: String
)