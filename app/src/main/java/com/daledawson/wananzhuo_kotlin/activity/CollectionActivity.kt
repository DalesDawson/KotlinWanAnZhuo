package com.daledawson.wananzhuo_kotlin.activity

import android.util.Log
import com.daledawson.wananzhuo_kotlin.R
import com.daledawson.wananzhuo_kotlin.base.BaseActivity
import com.daledawson.wananzhuo_kotlin.bean.BaseResponse
import com.daledawson.wananzhuo_kotlin.bean.CollectionResponse
import com.daledawson.wananzhuo_kotlin.http.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * 创 建 人：zhengquan
 * 创建日期：2020/4/13
 * 修改时间：
 * 修改备注：
 */
class CollectionActivity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.activity_collection

    override fun initView() {

    }

    override fun initData() {
        ApiService.get().getCollectionList(0).enqueue(object : Callback<CollectionResponse> {
            override fun onFailure(call: Call<CollectionResponse>, t: Throwable) {
                Log.d("-----tag",t.message)
            }

            override fun onResponse(call: Call<CollectionResponse>, response: Response<CollectionResponse>) {
                Log.d("-----tag",response.body().toString())
            }

        })
    }
}