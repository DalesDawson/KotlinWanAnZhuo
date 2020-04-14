package com.daledawson.kotlin_kaiyan.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.daledawson.wananzhuo_kotlin.http.HttpProvider
import com.daledawson.wananzhuo_kotlin.base.BaseFragment
import com.daledawson.wananzhuo_kotlin.R
import com.daledawson.wananzhuo_kotlin.activity.SystemListActivity
import com.daledawson.wananzhuo_kotlin.adapter.SystemAdapter
import com.daledawson.wananzhuo_kotlin.base.BaseAdapter
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
        systemRecyclerView.setLoadingMoreEnabled(false)

        systemRecyclerView.setLoadingListener(object : XRecyclerView.LoadingListener {
            override fun onLoadMore() {
            }

            override fun onRefresh() {
                getSystemList()
            }
        })

        adapter.setOnItemClickListener(object : BaseAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(context, SystemListActivity::class.java)
                intent.putExtra(SystemListActivity.CHILDREN, list[position])
                intent.putExtra(SystemListActivity.TITLE,list[position].name)
                startActivity(intent)
            }
        })
    }

    override fun initData() {
        getSystemList()
    }

    private fun getSystemList() {
        Okkt.instance.Builder().setUrl(HttpProvider.KNOWLEDGE_TREE).get(object : CallbackRule<SystemData> {
            override suspend fun onSuccess(entity: SystemData, flag: String) {
                Log.d("system", entity.toString())
                systemRecyclerView.refreshComplete()
                list = entity.data
                adapter.addListData(list, false)
                adapter.notifyDataSetChanged()
            }

            @SuppressLint("ShowToast")
            override suspend fun onFailed(error: String) {
                systemRecyclerView.refreshComplete()
                Toast.makeText(context, error, Toast.LENGTH_LONG)
            }

        })
    }
}
