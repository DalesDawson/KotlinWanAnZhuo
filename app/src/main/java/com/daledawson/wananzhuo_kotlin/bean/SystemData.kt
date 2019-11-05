package com.daledawson.wananzhuo_kotlin.bean

data class SystemData(
    val `data`: List<Data>,
    val errorCode: Int,
    val errorMsg: String
)