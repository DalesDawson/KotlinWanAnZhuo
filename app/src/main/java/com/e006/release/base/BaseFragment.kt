package com.e006.release.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * 创 建 人：zhengquan
 * 创建日期：2019/10/16
 * 修改时间：
 * 修改备注：
 */
abstract class BaseFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater!!.inflate(getLayoutId(), null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPresenter()
        initEvent()
        initView()
        initData()
    }

    abstract fun getLayoutId(): Int

    abstract fun initView()

    abstract fun initData()

    internal open fun initEvent() = Unit

    internal open fun initPresenter() = Unit

}
