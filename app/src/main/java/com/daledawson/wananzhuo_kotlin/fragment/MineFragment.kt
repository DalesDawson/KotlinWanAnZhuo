package com.daledawson.kotlin_kaiyan.fragment

import android.content.Intent
import android.view.View
import com.daledawson.wananzhuo_kotlin.base.BaseFragment
import com.daledawson.wananzhuo_kotlin.R
import com.daledawson.wananzhuo_kotlin.activity.CollectionActivity
import com.daledawson.wananzhuo_kotlin.activity.LoginActivity
import kotlinx.android.synthetic.main.fragment_mine.*


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
            startActivity(Intent(context, LoginActivity::class.java))
        }

        rl_collect.setOnClickListener {
            startActivity(Intent(context, CollectionActivity::class.java))
        }
    }

    override fun initData() {
    }

}