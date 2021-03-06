package com.daledawson.demo.activity

import android.content.Intent
import android.text.TextUtils
import androidx.recyclerview.widget.LinearLayoutManager
import com.daledawson.demo.http.HttpProvider
import com.daledawson.demo.R
import com.daledawson.demo.adapter.HomeListAdapter
import com.daledawson.demo.base.BaseActivity
import com.daledawson.demo.base.BaseAdapter
import com.daledawson.demo.bean.ArticleData
import com.daledawson.demo.bean.Children
import com.daledawson.demo.bean.DataX
import com.daledawson.demo.bean.SystemChildData
import com.daledawson.demo.util.Logger
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.MODE_SCROLLABLE
import com.gyf.immersionbar.ImmersionBar
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.stormkid.okhttpkt.core.Okkt
import com.stormkid.okhttpkt.rule.CallbackRule
import kotlinx.android.synthetic.main.activity_system_list.*

/**
 * 创 建 人：zhengquan
 * 创建日期：2019/12/24
 * 修改时间：
 * 修改备注：
 */
class SystemListActivity : BaseActivity() {
    private lateinit var childrenList: SystemChildData
    var list: MutableList<DataX> = ArrayList()
    lateinit var adapter: HomeListAdapter
    private var pageIndex: Int = 0
    private var position: Int = 0
    private var title: String = ""

    companion object {
        const val CHILDREN = "CHILDREN"
        const val TITLE = "TITLE"
    }

    override fun getLayoutId(): Int = R.layout.activity_system_list

    override fun initView() {
        ImmersionBar.with(this).titleBar(R.id.toolbar).init()
        system_list_tl.tabMode = MODE_SCROLLABLE
        system_list_rv.layoutManager = LinearLayoutManager(this)
        adapter = HomeListAdapter(this, R.layout.home_list_item, list)
        system_list_rv.adapter = adapter

        system_list_rv.setLoadingListener(object : XRecyclerView.LoadingListener {
            override fun onLoadMore() {
                pageIndex++
                getSystemList(pageIndex, childrenList.children[position].id)
            }

            override fun onRefresh() {
                pageIndex = 0
                list.clear()
                getSystemList(pageIndex, childrenList.children[position].id)
            }
        })

        adapter.setOnItemClickListener(object : BaseAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(this@SystemListActivity, CommonWebViewActivity::class.java)
                intent.putExtra(CommonWebViewActivity.LINK, list[position].link)
                startActivity(intent)
            }

        })

        adapter.setOnItemLongClickListener(object : BaseAdapter.OnItemLongClickListener {
            override fun onItemLongClick(position: Int): Boolean {
                return false
            }

        })
    }

    override fun initData() {
        childrenList = intent.getSerializableExtra(CHILDREN) as SystemChildData
        title = intent.getStringExtra(TITLE)
        if (!TextUtils.isEmpty(title)) {
            toolbar.title = title
        }
        Logger.d("SystemListActivity", childrenList.toString())
        initTitle()
    }

    private fun initTitle() {
        for (children: Children in childrenList.children) {
            system_list_tl.addTab(system_list_tl.newTab().setText(children.name))
        }
        system_list_tl.getTabAt(0)!!.select()
        getSystemList(pageIndex, childrenList.children[0].id)
        system_list_tl.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                pageIndex = 0
                position = tab!!.position
                Logger.i("TAG", "======我选中了====$position")
                getSystemList(pageIndex, childrenList.children[position].id)
            }

        })
    }

    private fun getSystemList(pageIndex: Int, cid: Int) {
        Okkt.instance.Builder()
            .setUrl(HttpProvider.KNOWLEDGE_LIST + "$pageIndex" + "/json?cid=" + "$cid")
            .get(object : CallbackRule<ArticleData> {
                override suspend fun onFailed(error: String) {

                }

                override suspend fun onSuccess(entity: ArticleData, flag: String) {
                    Logger.d("aaaaa", entity.data.datas.toString())
                    system_list_rv.loadMoreComplete()
                    system_list_rv.refreshComplete()
                    if (pageIndex == 0) {
                        list.clear()
                        list = entity.data.datas
                        adapter.addListData(list, true)
                    } else {
                        list.addAll(entity.data.datas)
                        adapter.addListData(list, false)
                    }
                    adapter.notifyDataSetChanged()
                }
            })
    }
}