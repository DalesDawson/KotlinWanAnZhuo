package com.daledawson.kotlin_kaiyan.fragment


import android.content.Intent
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import com.daledawson.wananzhuo_kotlin.http.HttpProvider
import com.daledawson.wananzhuo_kotlin.base.BaseFragment
import com.daledawson.wananzhuo_kotlin.R
import com.daledawson.wananzhuo_kotlin.activity.CommonWebViewActivity
import com.daledawson.wananzhuo_kotlin.activity.SearchListActivity
import com.daledawson.wananzhuo_kotlin.bean.HotData
import com.daledawson.wananzhuo_kotlin.bean.HotSearchResponse
import com.google.android.flexbox.FlexboxLayout
import com.stormkid.okhttpkt.core.Okkt
import com.stormkid.okhttpkt.rule.CallbackRule
import kotlinx.android.synthetic.main.fragment_search.*


/**
 * 创 建 人：zhengquan
 * 创建日期：2019/10/16
 * 修改时间：
 * 修改备注：
 */
class SearchFragment : BaseFragment() {
    var hotWordList: List<HotData> = ArrayList()
    var hotWebList: List<HotData> = ArrayList()
    override fun getLayoutId(): Int {
        return R.layout.fragment_search
    }

    override fun initView() {
        search_tv.setOnClickListener {
            if (!TextUtils.isEmpty(search_view.text.toString())) {
                Log.d("TAG", search_view.text.toString())
                val intent = Intent(context, SearchListActivity::class.java)
                intent.putExtra(SearchListActivity.KEY_WORD, search_view.text.toString())
                startActivity(intent)
            } else {
                Toast.makeText(context, "请键入要搜索的关键词！", Toast.LENGTH_SHORT)
            }
        }
    }

    override fun initData() {
        getHotWord()
        getHotWeb()
    }

    private fun getHotWord() {
        Okkt.instance.Builder().setUrl(HttpProvider.HOT_WORD).get(object : CallbackRule<HotSearchResponse> {
            override suspend fun onSuccess(entity: HotSearchResponse, flag: String) {
                Log.d("HOT_WORD---", entity.toString())
                hotWordList = entity.data
                initHotWord()
            }

            override suspend fun onFailed(error: String) {

            }

        })
    }

    private fun getHotWeb() {
        Okkt.instance.Builder().setUrl(HttpProvider.HOT_FRIEND)
            .get(object : CallbackRule<HotSearchResponse> {
                override suspend fun onSuccess(entity: HotSearchResponse, flag: String) {
                    Log.d("HOT_FRIEND---", entity.toString())
                    hotWebList = entity.data
                    initHotWeb()
                }

                override suspend fun onFailed(error: String) {

                }

            })
    }

    private fun initHotWord() {
        for (item in hotWordList) {
            val view =
                LayoutInflater.from(context).inflate(R.layout.flex_box_layout_view_item, null)
            val tv = view.findViewById<TextView>(R.id.tv_mv_type)
            val params = tv.layoutParams
            if (params is FlexboxLayout.LayoutParams) {
                params.flexBasisPercent = 1.5f
                params.flexGrow = 1.0f
            }
            tv.setPadding(30, 15, 30, 15)
            tv.text = item.name
            tv.textSize = 15f
            search_hot_word_fl.addView(view)
            tv.setOnClickListener {
                val intent = Intent(context, SearchListActivity::class.java)
                intent.putExtra(SearchListActivity.KEY_WORD, item.name)
                startActivity(intent)
            }
        }
    }

    private fun initHotWeb() {
        for (item in hotWebList) {
            val view =
                LayoutInflater.from(context).inflate(R.layout.flex_box_layout_view_item, null)
            val tv = view.findViewById<TextView>(R.id.tv_mv_type)
            val params = tv.layoutParams
            if (params is FlexboxLayout.LayoutParams) {
                params.flexBasisPercent = 1.5f
                params.flexGrow = 1.0f
            }
            tv.setPadding(30, 15, 30, 15)
            tv.text = item.name
            tv.textSize = 15f
            search_hot_web_fl.addView(view)
            tv.setOnClickListener {
                val intent = Intent(context, CommonWebViewActivity::class.java)
                intent.putExtra(CommonWebViewActivity.LINK, item.link)
                startActivity(intent)
            }
        }
    }
}