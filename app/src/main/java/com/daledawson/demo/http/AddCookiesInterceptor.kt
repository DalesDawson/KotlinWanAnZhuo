package com.daledawson.demo.http

import android.content.Context
import com.daledawson.demo.App
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


/**
 * 创 建 人：zhengquan
 * 创建日期：2020/4/13
 * 修改时间：
 * 修改备注：
 */
class AddCookiesInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        val sharedPreferences =
            App.instance().getSharedPreferences("cookie", Context.MODE_PRIVATE)
        val cookies: HashSet<String> =
            sharedPreferences?.getStringSet("cookie", setOf("", "")) as HashSet<String>
        for (cookie in cookies) {
            builder.addHeader("Cookie", cookie)
        }
        return chain.proceed(builder.build())
    }
}
