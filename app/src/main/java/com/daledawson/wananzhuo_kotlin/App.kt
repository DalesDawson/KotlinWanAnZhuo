package com.daledawson.wananzhuo_kotlin

import android.app.Application
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
        Okkt.instance.setBase(Api.HOST).initHttpClient()
    }
}