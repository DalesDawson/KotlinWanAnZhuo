package com.daledawson.kotlin_kaiyan.fragment

import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.daledawson.wananzhuo_kotlin.Api
import com.daledawson.wananzhuo_kotlin.base.BaseFragment
import com.daledawson.wananzhuo_kotlin.R
import com.daledawson.wananzhuo_kotlin.adapter.SystemAdapter
import com.daledawson.wananzhuo_kotlin.bean.SystemChildData
import com.daledawson.wananzhuo_kotlin.bean.SystemData
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.stormkid.okhttpkt.core.Okkt
import com.stormkid.okhttpkt.rule.CallbackRule
import kotlinx.android.synthetic.main.fragment_system.*


/**
 * 创 建 人：zhengquan
 * 创建日期：2019/10/16
 * 修改时间：
 * 修改备注：
 */
class SystemFragment : BaseFragment() {
    var list: MutableList<SystemChildData> = ArrayList()
    var childList: MutableList<String> = ArrayList()
    lateinit var adapter: SystemAdapter
    override fun getLayoutId(): Int {
        return R.layout.fragment_system
    }

    override fun initView() {
        systemRecyclerView.layoutManager = LinearLayoutManager(context)
        systemRecyclerView.setHasFixedSize(true)
        systemRecyclerView.isNestedScrollingEnabled = false
        adapter = SystemAdapter(context!!, R.layout.system_list_item, list)

        systemRecyclerView.adapter = adapter
    }

    override fun initData() {
        getSystemList()
    }

    private fun getSystemList() {
        Okkt.instance.Builder().setUrl(Api.KNOWLEDGE_TREE).get(object : CallbackRule<SystemData> {
            override suspend fun onSuccess(entity: SystemData, flag: String) {
                Log.d("system", entity.toString())
                list = entity.data
                adapter.addListData(list, false)
                adapter.notifyDataSetChanged()
            }

            override suspend fun onFailed(error: String) {
                Toast.makeText(context, error, Toast.LENGTH_LONG)
            }

        })
    }
}
