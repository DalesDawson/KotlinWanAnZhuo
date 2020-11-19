package com.daledawson.demo.bean

data class ProjectTree(
    val `data`: MutableList<ProjectTreeData>,
    val errorCode: Int,
    val errorMsg: String
)
data class ProjectTreeData(
    val children: List<Any>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)