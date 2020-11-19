package com.daledawson.demo.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * 创 建 人：zhengquan
 * 创建日期：2019/10/29
 * 修改时间：
 * 修改备注：
 */
object TimeUitl {
    /**
     *
     * @param milSecond  1541569323155
     * @param pattern yyyy-MM-dd HH:mm:ss
     * @return 2018-11-07 13:42:03
     */
    fun getDateToString(milSecond: Long, pattern: String): String {
        val date = Date(milSecond)
        val format = SimpleDateFormat(pattern, Locale.getDefault())
        return format.format(date)
    }
}
