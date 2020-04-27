package com.daledawson.wananzhuo_kotlin.adapter

import android.content.Context
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.daledawson.wananzhuo_kotlin.R
import com.daledawson.wananzhuo_kotlin.base.BaseAdapter
import com.daledawson.wananzhuo_kotlin.base.BaseHolder
import com.daledawson.wananzhuo_kotlin.bean.BaseResponse
import com.daledawson.wananzhuo_kotlin.bean.DataX
import com.daledawson.wananzhuo_kotlin.http.ApiService
import com.daledawson.wananzhuo_kotlin.util.TimeUitl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        holder.getView<ImageView>(R.id.iv_project_list_like).setOnClickListener {
            ApiService.crate().collect(this.mData[position].id)
                .enqueue(object : Callback<BaseResponse<Any>> {
                    override fun onFailure(call: Call<BaseResponse<Any>>, t: Throwable) {
                        Toast.makeText(ctx, t.message, Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(
                        call: Call<BaseResponse<Any>>,
                        response: Response<BaseResponse<Any>>
                    ) {
                        if (response.body()?.errorCode == 0) {
                            Toast.makeText(ctx, "收藏成功！", Toast.LENGTH_SHORT).show()
                            holder.getView<ImageView>(R.id.iv_project_list_like)
                                .setImageResource(R.mipmap.icon_like_select)
                        }else {
                            Toast.makeText(
                                ctx,
                                response.body()?.errorMsg,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                })
        }
    }
}