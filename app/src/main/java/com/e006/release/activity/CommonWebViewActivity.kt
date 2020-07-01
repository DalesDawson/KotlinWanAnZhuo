package com.e006.release.activity

import android.text.TextUtils
import com.e006.release.R
import com.e006.release.base.BaseActivity
import com.gyf.immersionbar.ImmersionBar
import kotlinx.android.synthetic.main.activity_web.*

/**
 * 创 建 人：zhengquan
 * 创建日期：2019/11/4
 * 修改时间：
 * 修改备注：
 */
class CommonWebViewActivity : BaseActivity() {
    var url: String = ""

    companion object {
        const val LINK = "LINK"
    }

    override fun getLayoutId(): Int = R.layout.activity_web

    override fun initView() {
        ImmersionBar.with(this).titleBar(R.id.toolbar).init()
    }

    override fun initData() {
        val intent = intent
        if (intent != null) {
            url = intent.getStringExtra(LINK)
            if (!TextUtils.isEmpty(url)) {
                web_view.loadUrl(url)
            }
        }
    }
}