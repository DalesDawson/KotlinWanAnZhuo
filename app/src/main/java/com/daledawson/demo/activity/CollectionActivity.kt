package com.daledawson.demo.activity

import android.content.Intent
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.daledawson.demo.R
import com.daledawson.demo.adapter.CollectListAdapter
import com.daledawson.demo.base.BaseActivity
import com.daledawson.demo.base.BaseAdapter
import com.daledawson.demo.bean.CollectionResponse
import com.daledawson.demo.bean.SDataX
import com.daledawson.demo.http.ApiService
import com.daledawson.demo.util.Logger
import com.gyf.immersionbar.ImmersionBar
import kotlinx.android.synthetic.main.activity_collection.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * 创 建 人：zhengquan
 * 创建日期：2020/4/13
 * 修改时间：
 * 修改备注：
 */
class CollectionActivity : BaseActivity() {
    private var pageIndex: Int = 0
    var list: MutableList<SDataX> = ArrayList()
    lateinit var adapter: CollectListAdapter
    override fun getLayoutId(): Int = R.layout.activity_collection

    override fun initView() {
        ImmersionBar.with(this).titleBar(R.id.toolbar).init()
        rv_collect.layoutManager = LinearLayoutManager(this)
        adapter = CollectListAdapter(this, R.layout.home_list_item, list)
    }

    override fun initData() {
        adapter.setOnItemClickListener(object:BaseAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(this@CollectionActivity, CommonWebViewActivity::class.java)
                intent.putExtra(CommonWebViewActivity.LINK, list[position].link)
                startActivity(intent)
            }
        })
        rv_collect.adapter = adapter
        getList(pageIndex)
    }

    private fun getList(page: Int) {
        ApiService.crate().getCollectionList(page).enqueue(object : Callback<CollectionResponse> {
            override fun onFailure(call: Call<CollectionResponse>, t: Throwable) {
                t.message?.let { Logger.d("-----tag", it) }
            }

            override fun onResponse(
                call: Call<CollectionResponse>,
                response: Response<CollectionResponse>
            ) {
                Logger.d("-----tag", response.body().toString())
                if (response.body()?.errorCode == 0) {
                    if (response.body() != null && response.body()?.data != null) {
                        list = response.body()?.data?.datas!!
                        adapter.addListData(list, false)
                        adapter.notifyDataSetChanged()
                    } else {
                        Toast.makeText(
                            this@CollectionActivity,
                            "暂无数据",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        this@CollectionActivity,
                        response.body()?.errorMsg,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        })
    }
}