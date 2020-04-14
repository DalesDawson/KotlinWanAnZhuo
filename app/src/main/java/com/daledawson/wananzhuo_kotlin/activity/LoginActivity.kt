package com.daledawson.wananzhuo_kotlin.activity

import android.content.Intent
import android.text.TextUtils
import android.widget.Toast
import com.daledawson.wananzhuo_kotlin.App
import com.daledawson.wananzhuo_kotlin.R
import com.daledawson.wananzhuo_kotlin.base.BaseActivity
import com.daledawson.wananzhuo_kotlin.bean.BaseResponse
import com.daledawson.wananzhuo_kotlin.http.ApiService
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * 创 建 人：zhengquan
 * 创建日期：2020/4/8
 * 修改时间：
 * 修改备注：
 */
class LoginActivity : BaseActivity() {
    var name = ""
    var password = ""
    override fun getLayoutId(): Int = R.layout.activity_login

    override fun initView() {
        to_register.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    override fun initData() {
        rl_login.setOnClickListener {
            name = et_login_name.text.toString()
            password = et_login_password.text.toString()
            if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)) {
                login(name, password)
            } else {
                Toast.makeText(this@LoginActivity, "请输入完整的用户名或密码！", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun login(name: String, password: String) {
        val map: HashMap<String, String> = HashMap()
        map["username"] = name
        map["password"] = password
        ApiService.get().login(map).enqueue(object : Callback<BaseResponse> {
            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Toast.makeText(this@LoginActivity, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                if (response.body()!!.errorCode == 0) {
                    Toast.makeText(this@LoginActivity, "登录成功！", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}