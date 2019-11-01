package com.daledawson.wananzhuo_kotlin

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.daledawson.wananzhuo_kotlin.adapter.HomeListAdapter
import com.daledawson.wananzhuo_kotlin.adapter.HomeListAdapter2
import com.daledawson.wananzhuo_kotlin.base.BaseFragment
import com.daledawson.wananzhuo_kotlin.bean.BannerData
import com.stormkid.okhttpkt.core.Okkt
import com.stormkid.okhttpkt.rule.CallbackRule
import kotlinx.android.synthetic.main.fragment_main.*
import com.daledawson.wananzhuo_kotlin.bean.ArticleData
import com.daledawson.wananzhuo_kotlin.bean.DataX
import com.daledawson.wananzhuo_kotlin.fragment.GlideImageLoader
import kotlin.collections.ArrayList


/**
 * 创 建 人：zhengquan
 * 创建日期：2019/10/16
 * 修改时间：
 * 修改备注：
 */
class MainFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener,RecyclerView.OnScrollListener{
    override fun onRefresh() {
        home_swipeRefreshLayout.isRefreshing = false
        initData()
    }

    var images: MutableList<String> = ArrayList()
    var list: MutableList<DataX> = ArrayList()
    lateinit var adapter: HomeListAdapter2
    override fun getLayoutId(): Int = R.layout.fragment_main
    var pageIndex:Int=0


    override fun initView() {
        home_recyclerView.layoutManager = LinearLayoutManager(context)
        home_recyclerView.setHasFixedSize(true)
        home_recyclerView.setNestedScrollingEnabled(false)
        home_recyclerView.setOnScrollChangeListener()
    }

    override fun initData() {
        getBannerList()
        getArticalList(pageIndex)
    }

    fun getBannerList() {
        Okkt.instance.Builder().setUrl(Api.BANNER_LIST).get(object : CallbackRule<BannerData> {
            override suspend fun onSuccess(entity: BannerData, flag: String) {
                Log.d("bbbbb", entity.toString())
                for (item in entity.data) {
                    images.add(item.imagePath)
                    Log.d("url", item.imagePath)
                }
                home_banner.setImages(images).setImageLoader(GlideImageLoader()).start()
            }

            override suspend fun onFailed(error: String) {
            }

        })
    }

    fun getArticalList(pageIndex: Int) {
        Okkt.instance.Builder().setUrl(Api.HOME_LIST + "$pageIndex" + "/json")
            .get(object : CallbackRule<ArticleData> {
                override suspend fun onFailed(error: String) {
                }

                override suspend fun onSuccess(entity: ArticleData, flag: String) {
                    Log.d("aaaaa", entity.data.datas.toString())
                    list = entity.data.datas
                    adapter = HomeListAdapter2(list)
                    home_recyclerView.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
            })
    }

}