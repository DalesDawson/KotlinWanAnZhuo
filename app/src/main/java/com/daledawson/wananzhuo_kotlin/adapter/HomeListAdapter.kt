package com.daledawson.wananzhuo_kotlin.adapter

import android.content.Context
import android.text.TextUtils
import android.widget.ImageView
import android.widget.TextView
import com.daledawson.wananzhuo_kotlin.R
import com.daledawson.wananzhuo_kotlin.base.BaseAdapter
import com.daledawson.wananzhuo_kotlin.base.BaseHolder
import com.daledawson.wananzhuo_kotlin.bean.DataX
import com.daledawson.wananzhuo_kotlin.util.TimeUitl

/**
 * 创 建 人：zhengquan
 * 创建日期：2019/10/18
 * 修改时间：
 * 修改备注：
 */
class HomeListAdapter(ctx: Context, layoutRes: Int, mData: MutableList<DataX>) :
    BaseAdapter<DataX>(ctx, layoutRes, mData) {
    override fun convert(holder: BaseHolder, position: Int) {
        var user: String = ""
        if (TextUtils.isEmpty(this.mData[position].shareUser)) {
            user = this.mData[position].author
        } else {
            user = this.mData[position].shareUser
        }
        holder.getView<TextView>(R.id.tv_home_list_item_name).text = user
        holder.getView<TextView>(R.id.tv_home_list_item_time).text =
            TimeUitl.getDateToString(this.mData[position].publishTime, "yyyy-MM-dd HH:mm:ss")
        holder.getView<TextView>(R.id.tv_home_list_item_title).text = this.mData[position].title
        holder.getView<TextView>(R.id.tv_home_list_item_type).text =
            this.mData[position].superChapterName
        holder.getView<ImageView>(R.id.iv_home_list_item_like)
            .setImageResource(R.mipmap.icon_like_normal)
    }
}