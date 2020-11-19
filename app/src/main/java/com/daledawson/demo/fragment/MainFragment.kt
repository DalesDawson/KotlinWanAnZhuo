package com.daledawson.demo.fragment

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.daledawson.demo.R
import com.daledawson.demo.activity.CommonWebViewActivity
import com.daledawson.demo.adapter.HomeListAdapter
import com.daledawson.demo.base.BaseAdapter
import com.daledawson.demo.base.BaseFragment
import com.daledawson.demo.bean.*
import com.stormkid.okhttpkt.core.Okkt
import com.stormkid.okhttpkt.rule.CallbackRule
import kotlinx.android.synthetic.main.fragment_main.*
import com.daledawson.demo.util.GlideImageLoader
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout
import kotlin.collections.ArrayList
import com.daledawson.demo.http.HttpProvider
import com.daledawson.demo.util.Logger


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

        adapter.setOnItemLongClickListener(object : BaseAdapter.OnItemLongClickListener {
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

    override fun onResume() {
        super.onResume()
        getBannerList()
        getArticleList(pageIndex)
    }

    override fun initData() {
//        getBannerList()
//        getArticleList(pageIndex)
    }

    private fun getBannerList() {
//        ApiService.crate().getBanner()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(object : Observer<BannerData> {
//                override fun onComplete() {
//                    Logger.d("aaa", "onComplete")
//                }
//
//                override fun onNext(t: BannerData) {
//                    home_refreshLayout.finishRefreshing()
//                    home_refreshLayout.finishRefreshing()
//                    Logger.d("aaa", "onNext-banner数据：$t")
//                    bannerList = t.data
//                    for (item in t.data) {
//                        images.add(item.imagePath)
//                        Logger.d("url", item.imagePath)
//                    }
//                    home_banner.setImages(images).setImageLoader(GlideImageLoader()).start()
//                    home_banner.start()
//                    getArticleList(pageIndex)
//                }
//
//                override fun onError(e: Throwable) {
//                    Logger.d("aaa", "onError$e")
//                    home_refreshLayout.finishRefreshing()
//                }
//
//                override fun onSubscribe(d: Disposable) {
//                }
//
//            })
//    }
        Okkt.instance.Builder().setUrl(HttpProvider.BANNER_LIST)
            .get(object : CallbackRule<BannerData> {
                override suspend fun onSuccess(entity: BannerData, flag: String) {
                    home_refreshLayout.finishRefreshing()
                    home_refreshLayout.finishRefreshing()
                    Logger.d("bbbbb", entity.toString())
                    bannerList = entity.data
                    for (item in entity.data) {
                        images.add(item.imagePath)
                        Logger.d("url", item.imagePath)
                    }
                    home_banner.setImages(images).setImageLoader(GlideImageLoader()).start()
                }

                override suspend fun onFailed(error: String) {
                    home_refreshLayout.finishRefreshing()
                }

            })
    }

    private fun getArticleList(pageIndex: Int) {
//        Logger.d("---getHomeList", "进入方法")
//        ApiService.crate().getHomeList(pageIndex)
//            .subscribeOn(Schedulers.io())
//            .unsubscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(object : Observer<ArticleData> {
//                override fun onComplete() {
//                    Logger.d("---getArticleList", "onComplete")
//                }
//
//                override fun onSubscribe(d: Disposable) {
//                    Logger.d("---getArticleList", "onSubscribe")
//                }
//
//                override fun onError(e: Throwable) {
//                    Logger.d("---getArticleList", e.message)
//                    home_refreshLayout.finishRefreshing()
//                    home_refreshLayout.finishLoadmore()
//                }
//
//                override fun onNext(t: ArticleData) {
//                    home_refreshLayout.finishRefreshing()
//                    home_refreshLayout.finishLoadmore()
//                    Logger.d("---getArticleList", t.data.datas.toString())
//                    Logger.d("---getArticleList", "pageIndex$pageIndex")
//                    if (pageIndex == 0) {
//                        list = t.data.datas
//                    } else {
//                        list.addAll(t.data.datas)
//                    }
//                    adapter.addListData(list, false)
//                    adapter.notifyDataSetChanged()
//                }
//
//            })

//    }
        Okkt.instance.Builder().setUrl(HttpProvider.HOME_LIST + "$pageIndex" + "/json")
            .get(object : CallbackRule<ArticleData> {
                override suspend fun onFailed(error: String) {
                    home_refreshLayout.finishRefreshing()
                    home_refreshLayout.finishLoadmore()
                }

                override suspend fun onSuccess(entity: ArticleData, flag: String) {
                    home_refreshLayout.finishRefreshing()
                    home_refreshLayout.finishLoadmore()
//                    Logger.d("aaaaa", entity.data.datas.toString())
//                    Logger.d("aaaaa", "pageIndex$pageIndex")
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