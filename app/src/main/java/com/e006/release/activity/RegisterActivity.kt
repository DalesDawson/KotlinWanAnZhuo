package com.e006.release.activity

import android.annotation.SuppressLint
import android.text.TextUtils
import android.widget.Toast
import com.e006.release.R
import com.e006.release.base.BaseActivity
import com.e006.release.bean.RegisterResponse
import com.e006.release.http.ApiService
import com.e006.release.util.Logger
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.HashMap

/**
 * 创 建 人：zhengquan
 * 创建日期：2020/4/10
 * 修改时间：
 * 修改备注：
 */
class RegisterActivity : BaseActivity() {
    var name = ""
    var password = ""
    var repassword = ""
    override fun getLayoutId(): Int = R.layout.activity_register

    override fun initView() {
        rl_register.setOnClickListener {
            Logger.d("TTTT", "进这里没有")
            name = et_register_name.text.toString()
            password = et_register_password.text.toString()
            repassword = et_register_repassword.text.toString()
            Logger.d("TTTT", "各位都有值嘛$name+$password+$repassword")
            if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(
                    repassword
                )
            ) {
                if (password.equals(repassword)) {
                    register(name, password, repassword)
                } else {
                    Toast.makeText(this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show()
                }
            } else {
                Logger.d("TTTT", "有空的进这里没有")
                Toast.makeText(this, "请完善信息后注册", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun initData() {
    }

    @SuppressLint("CheckResult")
    private fun register(name: String, password: String, repassword: String) {
//        Okkt.instance.Builder().setUrl(HttpProvider.REGISTER)
//            .putBody(hashMapOf("username" to name,"password" to password,"repassword" to repassword))
//            .post(object : CallbackRule<RegisterResponse> {
//                override suspend fun onFailed(error: String) {
//                    Toast.makeText(this@RegisterActivity, "注册失败，请稍后重试！+$error", Toast.LENGTH_SHORT).show()
//                }
//
//                override suspend fun onSuccess(entity: RegisterResponse, flag: String) {
//                    Logger.d("aaaaa", entity.registerData.toString())
//                    if (entity.errorCode == 0) {
//                        Toast.makeText(this@RegisterActivity, "注册成功！", Toast.LENGTH_SHORT).show()
//                        finish()
//                    }
//                }
//            })
//        val baseUrl: String = HttpProvider.HOST
//        val retrofit = Retrofit.Builder()
//            .baseUrl(baseUrl)
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        val api = retrofit.create(Api::class.java)
        try {
            val map: HashMap<String, String> = HashMap()
            map["username"] = name
            map["password"] = password
            map["repassword"] = repassword
            ApiService.crate().register(map).enqueue(object : Callback<RegisterResponse> {
                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    Toast.makeText(this@RegisterActivity, t.message, Toast.LENGTH_SHORT).show()
                    Logger.d("---register", "注册出错了")
                }

                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>
                ) {
                    Logger.d("---register", "注册成功了")
                    if (response.body()?.errorCode == 0) {
                        Toast.makeText(this@RegisterActivity, "注册成功！", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(
                            this@RegisterActivity,
                            response.body()?.errorMsg,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            })
//            subscribeOn(Schedulers.io())
//                .unsubscribeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                    object : DisposableObserver<RegisterResponse>() {
//                    override fun onComplete() {
//
//                    }
//
//                    override fun onNext(t: RegisterResponse) {
//                        if (t.errorCode == 0) {
//                            Toast.makeText(this@RegisterActivity, "注册成功！", Toast.LENGTH_SHORT).show()
//                            finish()
//                        }
//                    }
//
//                    override fun onError(e: Throwable) {
//                        Toast.makeText(this@RegisterActivity, e.message, Toast.LENGTH_SHORT).show()
//                    }
//
//                }
//                { result ->//请求结果
//                    if (result.errorCode == 0) {
//                        Toast.makeText(this@RegisterActivity, "注册成功！", Toast.LENGTH_SHORT).show()
//                        finish()
//                    }
//                }, { error ->//请求错误
//                    Toast.makeText(this@RegisterActivity, error.message, Toast.LENGTH_SHORT).show()
//                }, {//请求完成
//                    println("complete")
//                }
//                )
        } catch (throwable: Throwable) {
        }
    }
}
