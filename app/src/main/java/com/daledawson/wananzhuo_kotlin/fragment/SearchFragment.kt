package com.daledawson.kotlin_kaiyan.fragment

import android.opengl.Visibility
import android.view.ActionProvider
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import com.daledawson.wananzhuo_kotlin.base.BaseFragment
import com.daledawson.wananzhuo_kotlin.R
import kotlinx.android.synthetic.main.fragment_search.*


/**
 * 创 建 人：zhengquan
 * 创建日期：2019/10/16
 * 修改时间：
 * 修改备注：
 */
class SearchFragment : BaseFragment() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_search
    }

    override fun initView() {
//        search_view.setOnCloseListener(object :SearchView.OnCloseListener{
//            override fun onClose(): Boolean {
//            }
//
//        })
    }

    override fun initData() {
    }
}