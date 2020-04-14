package com.daledawson.kotlin_kaiyan.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.text.Html
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.daledawson.wananzhuo_kotlin.http.HttpProvider
import com.daledawson.wananzhuo_kotlin.base.BaseFragment
import com.daledawson.wananzhuo_kotlin.R
import com.daledawson.wananzhuo_kotlin.activity.CommonWebViewActivity
import com.daledawson.wananzhuo_kotlin.adapter.ProjectListAdapter
import com.daledawson.wananzhuo_kotlin.base.BaseAdapter
import com.daledawson.wananzhuo_kotlin.bean.ArticleData
import com.daledawson.wananzhuo_kotlin.bean.DataX
import com.daledawson.wananzhuo_kotlin.bean.ProjectTree
import com.stormkid.okhttpkt.core.Okkt
import com.stormkid.okhttpkt.rule.CallbackRule
import kotlinx.android.synthetic.main.fragment_project.*
import com.daledawson.wananzhuo_kotlin.bean.ProjectTreeData
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.MODE_SCROLLABLE
import com.jcodecraeer.xrecyclerview.XRecyclerView


/**
 * 创 建 人：zhengquan
 * 创建日期：2019/10/16
 * 修改时间：
 * 修改备注：
 */
class ProjectFragment : BaseFragment() {
    var treeList: MutableList<ProjectTreeData> = ArrayList()
    var list: MutableList<DataX> = ArrayList()
    lateinit var adapter: ProjectListAdapter
    private var pageIndex: Int = 1
    private var position: Int = 0
    override fun getLayoutId(): Int {
        return R.layout.fragment_project
    }

    override fun initView() {
        project_tl.tabMode = MODE_SCROLLABLE
        project_rv.layoutManager = LinearLayoutManager(context)
        adapter = ProjectListAdapter(context!!, R.layout.project_list_item, list)
        project_rv.adapter = adapter
        adapter.setOnItemClickListener(object : BaseAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(context, CommonWebViewActivity::class.java)
                intent.putExtra(CommonWebViewActivity.LINK, list[position].link)
                startActivity(intent)
            }

        })
        project_rv.setLoadingListener(object : XRecyclerView.LoadingListener {
            override fun onLoadMore() {
                pageIndex++
                getProjectList(pageIndex, treeList[position].id)
            }

            override fun onRefresh() {
                pageIndex = 1
                list.clear()
                getProjectList(pageIndex, treeList[position].id)
            }

        })

    }

    override fun initData() {
        getProjectTree()
    }

    private fun getProjectTree() {
        Okkt.instance.Builder().setUrl(HttpProvider.PROJECT_TREE).get(object : CallbackRule<ProjectTree> {
            override suspend fun onSuccess(entity: ProjectTree, flag: String) {
                Log.d("projectTree", entity.toString())
                treeList = entity.data
                initTitle()
            }

            @SuppressLint("ShowToast")
            override suspend fun onFailed(error: String) {
                Toast.makeText(context, error, Toast.LENGTH_LONG)
            }

        })
    }

    private fun initTitle() {
        for (tree: ProjectTreeData in treeList) {
            project_tl.addTab(project_tl.newTab().setText(Html.fromHtml(tree.name)))
        }
        project_tl.getTabAt(0)!!.select()
        getProjectList(pageIndex, treeList[0].id)
        project_tl.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                pageIndex = 1
                position = tab!!.position
                Log.i("TAG", "======我选中了====$position")
                getProjectList(pageIndex, treeList[position].id)
            }

        })
    }

    private fun getProjectList(pageIndex: Int, cid: Int) {
        Okkt.instance.Builder().setUrl(HttpProvider.PROJECT_LIST + "$pageIndex" + "/json?cid=" + "$cid")
            .get(object : CallbackRule<ArticleData> {
                override suspend fun onFailed(error: String) {

                }

                override suspend fun onSuccess(entity: ArticleData, flag: String) {
                    Log.d("aaaaa", entity.data.datas.toString())
                    project_rv.loadMoreComplete()
                    project_rv.refreshComplete()
                    if (pageIndex == 1) {
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