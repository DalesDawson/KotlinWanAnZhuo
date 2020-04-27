package com.daledawson.wananzhuo_kotlin.http

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.daledawson.wananzhuo_kotlin.App
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.adapter.rxjava2.Result.response
import java.io.IOException


/**
 * 创 建 人：zhengquan
 * 创建日期：2020/4/13
 * 修改时间：
 * 修改备注：
 */
class ReceivedCookiesInterceptor() : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse: Response = chain.proceed(chain.request())
        //这里获取请求返回的cookie
        if (originalResponse.headers("Set-Cookie").isNotEmpty()) {
            val cookies: HashSet<String> = HashSet(originalResponse.headers("set-cookie"))
            val sp: SharedPreferences =
                App.instance().getSharedPreferences("cookie", MODE_PRIVATE)
            val editor = sp.edit()
            editor.putStringSet("cookie", cookies)
            editor.apply()
        }
        return originalResponse
    }
}