package com.daledawson.kotlin_kaiyan.fragment

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.daledawson.wananzhuo_kotlin.base.BaseFragment
import com.daledawson.wananzhuo_kotlin.R
import com.daledawson.wananzhuo_kotlin.activity.CollectionActivity
import com.daledawson.wananzhuo_kotlin.activity.LoginActivity
import com.daledawson.wananzhuo_kotlin.bean.BaseResponse
import com.daledawson.wananzhuo_kotlin.bean.ScoreData
import com.daledawson.wananzhuo_kotlin.http.ApiService
import kotlinx.android.synthetic.main.fragment_mine.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * 创 建 人：zhengquan
 * 创建日期：2019/10/16
 * 修改时间：
 * 修改备注：
 */
class MineFragment : BaseFragment() {
    override fun getLayoutId(): Int = R.layout.fragment_mine

    override fun initView() {
        name_tv.setOnClickListener {
            startActivityForResult(Intent(context, LoginActivity::class.java), 123)
        }

        rl_collect.setOnClickListener {
            startActivity(Intent(context, CollectionActivity::class.java))
        }

        rl_logout.setOnClickListener {
            ApiService.crate().logout().enqueue(object : Callback<BaseResponse<Any>> {
                override fun onFailure(call: Call<BaseResponse<Any>>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                }

                @SuppressLint("CommitPrefEdits")
                override fun onResponse(
                    call: Call<BaseResponse<Any>>,
                    response: Response<BaseResponse<Any>>
                ) {
                    if (response.body()?.errorCode == 0) {
                        Toast.makeText(context, "已退出登录！", Toast.LENGTH_SHORT).show()
                        val sharedPreferences =
                            context?.getSharedPreferences("cookie", Context.MODE_PRIVATE)
                        val editor = sharedPreferences?.edit()
                        editor?.remove("cookie")
                        val sp =
                            context?.getSharedPreferences("name", Context.MODE_PRIVATE)
                        val ed = sp?.edit()
                        ed?.remove("name")
                    }
                }

            })
        }
    }

    override fun initData() {
        val sharedPreferences =
            context?.getSharedPreferences("name", Context.MODE_PRIVATE)
        var username = sharedPreferences?.getString("username", "")
        if (!TextUtils.isEmpty(username)) {
            name_tv.text = username
            ApiService.crate().getIntegral().enqueue(object : Callback<BaseResponse<ScoreData>> {
                override fun onFailure(call: Call<BaseResponse<ScoreData>>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<BaseResponse<ScoreData>>,
                    response: Response<BaseResponse<ScoreData>>
                ) {
                    var score = response.body()?.data?.coinCount
                    score_tv.visibility = View.VISIBLE
                    line.visibility = View.VISIBLE
                    score_tv.text = "$score+积分"
                }

            })
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == 123) {
                var name = data?.getStringExtra("name")
                name_tv.text = name
            }
        }
    }
}