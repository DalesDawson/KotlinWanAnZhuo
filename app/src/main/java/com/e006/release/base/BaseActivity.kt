package com.e006.release.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gyf.immersionbar.ImmersionBar

/**
 * 创 建 人：zhengquan
 * 创建日期：2019/11/13
 * 修改时间：
 * 修改备注：
 */
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initPresenter()
        initEvent()
        initView()
        initData()
        ImmersionBar.with(this).init()
    }

    abstract fun getLayoutId(): Int

    abstract fun initView()

    abstract fun initData()

    internal open fun initEvent() = Unit

    internal open fun initPresenter() = Unit
}