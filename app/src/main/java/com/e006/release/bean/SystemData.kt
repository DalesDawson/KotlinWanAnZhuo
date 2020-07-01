package com.e006.release.bean

import java.io.Serializable

data class SystemData(
    val `data`: MutableList<SystemChildData>,
    val errorCode: Int,
    val errorMsg: String
):Serializable

data class SystemChildData(
    val children: MutableList<Children>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
):Serializable

data class Children(
    val children2: MutableList<Any>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
):Serializable