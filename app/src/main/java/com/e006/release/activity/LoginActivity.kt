package com.e006.release.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.widget.Toast
import com.e006.release.R
import com.e006.release.base.BaseActivity
import com.e006.release.bean.BaseResponse
import com.e006.release.http.ApiService
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
        ApiService.crate().login(map).enqueue(object : Callback<BaseResponse<Any>> {
            override fun onFailure(call: Call<BaseResponse<Any>>, t: Throwable) {
                Toast.makeText(this@LoginActivity, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<BaseResponse<Any>>,
                response: Response<BaseResponse<Any>>
            ) {
                if (response.body()?.errorCode == 0) {
                    Toast.makeText(this@LoginActivity, "登录成功！", Toast.LENGTH_SHORT).show()
                    val sharedPreferences =
                        this@LoginActivity.getSharedPreferences("name", Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.putString("username", name)
                    editor.apply()
                    var intent = Intent()
                    intent.putExtra("name", name)
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        response.body()?.errorMsg,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }
}