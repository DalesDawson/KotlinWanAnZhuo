package com.daledawson.wananzhuo_kotlin.adapter

import android.content.Context
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.daledawson.wananzhuo_kotlin.R
import com.daledawson.wananzhuo_kotlin.base.BaseAdapter
import com.daledawson.wananzhuo_kotlin.base.BaseHolder
import com.daledawson.wananzhuo_kotlin.bean.DataX
import com.daledawson.wananzhuo_kotlin.util.GlideImageLoader
import com.daledawson.wananzhuo_kotlin.util.TimeUitl

/**
 * 创 建 人：zhengquan
 * 创建日期：2019/10/18
 * 修改时间：
 * 修改备注：
 */
class ProjectListAdapter(ctx: Context, layoutRes: Int, mData: MutableList<DataX>) :
    BaseAdapter<DataX>(ctx, layoutRes, mData) {
    override fun convert(holder: BaseHolder, position: Int) {
        Glide.with(ctx).load(mData[position].envelopePic).into(holder.getView(R.id.project_list_iv))
        holder.getView<TextView>(R.id.tv_project_list_name).text =
            Html.fromHtml(this.mData[position].title)
        holder.getView<TextView>(R.id.tv_project_list_name_title).text =
            Html.fromHtml(this.mData[position].desc)
        holder.getView<TextView>(R.id.tv_project_list_date).text =
            TimeUitl.getDateToString(this.mData[position].publishTime, "yyyy-MM-dd")
        holder.getView<TextView>(R.id.tv_project_list_author).text =
            this.mData[position].author
        holder.getView<ImageView>(R.id.iv_project_list_like)
            .setImageResource(R.mipmap.icon_like_normal)
    }
}