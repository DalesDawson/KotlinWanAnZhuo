package com.daledawson.demo.activity

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.daledawson.demo.http.HttpProvider
import com.daledawson.demo.R
import com.daledawson.demo.adapter.HomeListAdapter
import com.daledawson.demo.base.BaseActivity
import com.daledawson.demo.base.BaseAdapter
import com.daledawson.demo.bean.ArticleData
import com.daledawson.demo.bean.DataX
import com.daledawson.demo.util.Logger
import com.gyf.immersionbar.ImmersionBar
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.stormkid.okhttpkt.core.Okkt
import com.stormkid.okhttpkt.rule.CallbackRule
import kotlinx.android.synthetic.main.activity_search_list.*

/**
 * 创 建 人：zhengquan
 * 创建日期：2019/12/30
 * 修改时间：
 * 修改备注：
 */
class SearchListActivity : BaseActivity() {
    private var pageIndex: Int = 0
    var list: MutableList<DataX> = ArrayList()
    lateinit var adapter: HomeListAdapter

    companion object {
        const val KEY_WORD = "KEY_WORD"
    }

    private lateinit var keyWords: String
    override fun getLayoutId(): Int = R.layout.activity_search_list

    override fun initView() {
        ImmersionBar.with(this).titleBar(R.id.toolbar).init()
        search_list_rv.layoutManager = LinearLayoutManager(this)
        search_list_rv.setHasFixedSize(true)
        search_list_rv.isNestedScrollingEnabled = false
        adapter = HomeListAdapter(this, R.layout.home_list_item, list)
        search_list_rv.adapter = adapter
        adapter.setOnItemClickListener(object :BaseAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(this@SearchListActivity, CommonWebViewActivity::class.java)
                intent.putExtra(CommonWebViewActivity.LINK, list[position].link)
                startActivity(intent)
            }
        })
        search_list_rv.setLoadingListener(object : XRecyclerView.LoadingListener {
            override fun onLoadMore() {
                pageIndex++
                getSearchList(keyWords, pageIndex)
            }

            override fun onRefresh() {
                pageIndex = 0
                list.clear()
                getSearchList(keyWords, pageIndex)
            }

        })
    }

    override fun initData() {
        pageIndex = 0
        keyWords = intent.getStringExtra(KEY_WORD) as String
        search_list_title.visibility=View.GONE
        toolbar.title=keyWords
        getSearchList(keyWords, pageIndex)
    }

    private fun getSearchList(keyWord: String, page: Int) {
        Okkt.instance.Builder().setUrl(HttpProvider.SEARCH_WORD + "$pageIndex" + "/json")
            .putBody(hashMapOf("k" to "$keyWord"))
            .post(object : CallbackRule<ArticleData> {
                override suspend fun onFailed(error: String) {

                }

                override suspend fun onSuccess(entity: ArticleData, flag: String) {
                    search_list_rv.refreshComplete()
                    search_list_rv.loadMoreComplete()
                    Logger.d("aaaaa", entity.data.datas.toString())
                    Logger.d("aaaaa", "pageIndex$pageIndex")
                    if (pageIndex == 0) {
                        list = entity.data.datas
                    } else {
                        list.addAll(entity.data.datas)
                    }
                    adapter.addListData(list, false)
                    adapter.notifyDataSetChanged()
                }
            })
    }
}