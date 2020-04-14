package com.daledawson.wananzhuo_kotlin.bean

/**
 * 创 建 人：zhengquan
 * 创建日期：2020/4/13
 * 修改时间：
 * 修改备注：
 */
data class CollectionResponse(
    val `data`: BData,
    val errorCode: Int,
    val errorMsg: String
)

data class BData(
    val curPage: Int,
    val datas: MutableList<SDataX>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)

data class SDataX(
    val author: String,
    val chapterId: Int,
    val chapterName: String,
    val courseId: Int,
    val desc: String,
    val envelopePic: String,
    val id: Int,
    val link: String,
    val niceDate: String,
    val origin: String,
    val originId: Int,
    val publishTime: Long,
    val title: String,
    val userId: Int,
    val visible: Int,
    val zan: Int
)