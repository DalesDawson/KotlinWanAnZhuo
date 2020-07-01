package com.e006.release.bean

import java.io.Serializable

data class HotSearchResponse(
    val `data`: List<HotData>,
    val errorCode: Int,
    val errorMsg: String
) : Serializable

data class HotData(
    val icon: String,
    val id: Int,
    val link: String,
    val name: String,
    val order: Int,
    val visible: Int
) : Serializable