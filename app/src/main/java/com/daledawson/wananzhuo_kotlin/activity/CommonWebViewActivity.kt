package com.daledawson.wananzhuo_kotlin.activity

import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.daledawson.wananzhuo_kotlin.R
import kotlinx.android.synthetic.main.activity_web.*
import kotlinx.android.synthetic.main.activity_web.view.*

/**
 * 创 建 人：zhengquan
 * 创建日期：2019/11/4
 * 修改时间：
 * 修改备注：
 */
class CommonWebViewActivity : AppCompatActivity() {
    companion object {
        const val LINK = "LINK"
    }

    var url: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        val intent = intent
        if (intent != null) {
            url = intent.getStringExtra(LINK)
            if (TextUtils.isEmpty(url)) {
                web_view.loadUrl(url)
            }
        }
    }
}