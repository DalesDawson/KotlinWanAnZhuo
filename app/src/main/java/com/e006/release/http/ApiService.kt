package com.e006.release.http

import com.e006.release.util.Logger
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager


/**
 * 创 建 人：zhengquan
 * 创建日期：2020/4/13
 * 修改时间：
 * 修改备注：
 */
class ApiService private constructor() {

    companion object {
        private var api: Api? = null
            get() {
                if (field == null) {
                    field = createApi()
                }
                return field
            }

        fun crate(): Api {
            return api!!
        }
        private var httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
            Logger.d("ApiService", "message:$message")
        })
        private var client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(ReceivedCookiesInterceptor())
            .addInterceptor(AddCookiesInterceptor())
            .addInterceptor(httpLoggingInterceptor)
            .build()

        private fun createSSLSocketFactory(): SSLSocketFactory? {
            var ssfFactory: SSLSocketFactory? = null
            try {
                val sc = SSLContext.getInstance("TLS")
                sc.init(
                    null,
                    arrayOf<TrustManager>(TrustAllCerts()),
                    SecureRandom()
                )
                ssfFactory = sc.socketFactory
            } catch (e: Exception) {
            }
            return ssfFactory
        }

        private fun createApi(): Api {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
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