package com.daledawson.wananzhuo_kotlin

import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.daledawson.wananzhuo_kotlin.activity.CommonWebViewActivity
import com.daledawson.wananzhuo_kotlin.activity.HomeActivity
import com.daledawson.wananzhuo_kotlin.adapter.HomeListAdapter
import com.daledawson.wananzhuo_kotlin.base.BaseAdapter
import com.daledawson.wananzhuo_kotlin.base.BaseFragment
import com.daledawson.wananzhuo_kotlin.bean.BannerData
import com.stormkid.okhttpkt.core.Okkt
import com.stormkid.okhttpkt.rule.CallbackRule
import kotlinx.android.synthetic.main.fragment_main.*
import com.daledawson.wananzhuo_kotlin.bean.ArticleData
import com.daledawson.wananzhuo_kotlin.bean.DataX
import com.daledawson.wananzhuo_kotlin.util.GlideImageLoader
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout
import kotlin.collections.ArrayList
import com.daledawson.wananzhuo_kotlin.activity.MainActivity
import com.daledawson.wananzhuo_kotlin.bean.Data
import com.youth.banner.listener.OnBannerListener


/**
 * 创 建 人：zhengquan
 * 创建日期：2019/10/16
 * 修改时间：
 * 修改备注：
 */
class MainFragment : BaseFragment() {

    var images: MutableList<String> = ArrayList()
    var bannerList: MutableList<Data> = ArrayList()
    var list: MutableList<DataX> = ArrayList()
    lateinit var adapter: HomeListAdapter
    override fun getLayoutId(): Int = R.layout.fragment_main
    var pageIndex: Int = 0


    override fun initView() {
        home_recyclerView.layoutManager = LinearLayoutManager(context)
        home_recyclerView.setHasFixedSize(true)
        home_recyclerView.isNestedScrollingEnabled = false
        adapter = HomeListAdapter(context!!, R.layout.home_list_item, list)
        home_recyclerView.adapter = adapter

//        home_recyclerView.setLoadingListener(object : XRecyclerView.LoadingListener {
//            override fun onLoadMore() {
//                pageIndex++
//                getArticleList(pageIndex)
//            }
//
//            override fun onRefresh() {
//                pageIndex = 0
//                getArticleList(pageIndex)
//            }
//        })
        home_refreshLayout.setTargetView(home_scrollView)
        home_refreshLayout.setOnRefreshListener(object : RefreshListenerAdapter() {
            override fun onRefresh(refreshLayout: TwinklingRefreshLayout?) {
                super.onRefresh(refreshLayout)
                pageIndex = 0
                images.clear()
                list.clear()
                getBannerList()
                getArticleList(pageIndex)
            }

            override fun onLoadMore(refreshLayout: TwinklingRefreshLayout?) {
                super.onLoadMore(refreshLayout)
                pageIndex++
                getArticleList(pageIndex)
            }
        })
        adapter.setOnItemClickListener(object : BaseAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(context, CommonWebViewActivity::class.java)
                intent.putExtra(CommonWebViewActivity.LINK, list[position].link)
                startActivity(intent)
            }

        })

        adapter.setOnItemLongClickListener(object :BaseAdapter.OnItemLongClickListener{
            override fun onItemLongClick(position: Int): Boolean {
                return false
            }

        })

        home_banner.setOnBannerListener {
            val intent = Intent(context, CommonWebViewActivity::class.java)
            intent.putExtra(CommonWebViewActivity.LINK, bannerList[it].url)
            startActivity(intent)
        }
    }

    override fun initData() {
        getBannerList()
        getArticleList(pageIndex)
    }

    private fun getBannerList() {
        Okkt.instance.Builder().setUrl(Api.BANNER_LIST).get(object : CallbackRule<BannerData> {
            override suspend fun onSuccess(entity: BannerData, flag: String) {
                home_refreshLayout.finishRefreshing()
                home_refreshLayout.finishRefreshing()
                Log.d("bbbbb", entity.toString())
                bannerList = entity.data
                for (item in entity.data) {
                    images.add(item.imagePath)
                    Log.d("url", item.imagePath)
                }
                home_banner.setImages(images).setImageLoader(GlideImageLoader()).start()
            }

            override suspend fun onFailed(error: String) {
                home_refreshLayout.finishRefreshing()
            }

        })
    }

    private fun getArticleList(pageIndex: Int) {
        Okkt.instance.Builder().setUrl(Api.HOME_LIST + "$pageIndex" + "/json")
            .get(object : CallbackRule<ArticleData> {
                override suspend fun onFailed(error: String) {
                    home_refreshLayout.finishRefreshing()
                    home_refreshLayout.finishLoadmore()
                }

                override suspend fun onSuccess(entity: ArticleData, flag: String) {
                    home_refreshLayout.finishRefreshing()
                    home_refreshLayout.finishLoadmore()
                    Log.d("aaaaa", entity.data.datas.toString())
                    Log.d("aaaaa", "pageIndex$pageIndex")
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