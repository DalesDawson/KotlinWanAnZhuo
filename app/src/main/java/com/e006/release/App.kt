package com.e006.release

import android.app.Application
import com.e006.release.http.HttpProvider
import com.stormkid.okhttpkt.core.Okkt

/**
 * 创 建 人：zhengquan
 * 创建日期：2019/10/16
 * 修改时间：
 * 修改备注：
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        Okkt.instance.setBase(HttpProvider.HOST).initHttpClient()
//        setRxJavaErrorHandler()
    }

    companion object {
        //情况一：声明可空的属性
        private var instance: App? = null
        fun instance() = instance!!
        //情况二：声明延迟初始化属性
        //private lateinit var instance: MainApplication
        //fun instance() = instance
    }

//    private fun setRxJavaErrorHandler() {
//        RxJavaPlugins.setErrorHandler {
//            it.printStackTrace();
//        }
//    }
}