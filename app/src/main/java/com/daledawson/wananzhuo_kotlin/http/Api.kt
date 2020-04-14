package com.daledawson.wananzhuo_kotlin.http

import com.daledawson.wananzhuo_kotlin.bean.BaseResponse
import com.daledawson.wananzhuo_kotlin.bean.CollectionResponse
import com.daledawson.wananzhuo_kotlin.bean.RegisterResponse
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

/**
 * 创 建 人：zhengquan
 * 创建日期：2020/4/13
 * 修改时间：
 * 修改备注：
 */
interface Api {
    /**
     * 注册
     */
    @POST(HttpProvider.REGISTER)
    fun register(@QueryMap map: Map<String, String>): Call<RegisterResponse>

    /**
     * 登录
     */
    @POST(HttpProvider.LOGIN)
    fun login(@QueryMap map: Map<String, String>): Call<BaseResponse>

    /**
     * 获取收藏列表
     */
    @GET(HttpProvider.COLLECT_LIST)
    fun getCollectionList(@Path("page") page: Int): Call<CollectionResponse>
}