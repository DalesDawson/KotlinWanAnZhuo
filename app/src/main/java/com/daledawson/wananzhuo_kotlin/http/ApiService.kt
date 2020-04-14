package com.daledawson.wananzhuo_kotlin.http

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * 创 建 人：zhengquan
 * 创建日期：2020/4/13
 * 修改时间：
 * 修改备注：
 */
class ApiService private constructor() {
    companion object {
        var api: Api? = null
            get() {
                if (field == null) {
                    field = createApi()
                }
                return field
            }

        fun get(): Api {
            return api!!
        }

        var client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(ReceivedCookiesInterceptor())
            .addInterceptor(AddCookiesInterceptor())
            .build()

        private fun createApi(): Api {
            val retrofit = Retrofit.Builder()
                .baseUrl(HttpProvider.HOST)//设置网络请求的Url地址
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())//设置数据解析器，使得来自接口的json结果会自动解析成定义好了的字段和类型都相符的json对象接受类
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//想把返回值定义为Observable对象
                .build()
            // 返回网络请求接口的实例
            return retrofit.create(Api::class.java)
        }
    }
}