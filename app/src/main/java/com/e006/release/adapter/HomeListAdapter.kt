package com.e006.release.adapter

import android.content.Context
import android.text.Html
import android.text.TextUtils
import android.widget.ImageView
import android.widget.TextView
import com.e006.release.R
import com.e006.release.base.BaseAdapter
import com.e006.release.base.BaseHolder
import com.e006.release.bean.DataX
import com.e006.release.util.TimeUitl

/**
 * 创 建 人：zhengquan
 * 创建日期：2019/10/18
 * 修改时间：
 * 修改备注：
 */
class HomeListAdapter(ctx: Context, layoutRes: Int, mData: MutableList<DataX>) :
    BaseAdapter<DataX>(ctx, layoutRes, mData) {
    override fun convert(holder: BaseHolder, position: Int) {
        var user = if (TextUtils.isEmpty(this.mData[position].shareUser)) {
            this.mData[position].author
        } else {
            this.mData[position].shareUser
        }
        holder.getView<TextView>(R.id.tv_home_list_item_name).text = user
        holder.getView<TextView>(R.id.tv_home_list_item_time).text =
            TimeUitl.getDateToString(this.mData[position].publishTime, "yyyy-MM-dd")
        holder.getView<TextView>(R.id.tv_home_list_item_title).text =
            Html.fromHtml(this.mData[position].title)
        holder.getView<TextView>(R.id.tv_home_list_item_type).text =
            this.mData[position].superChapterName + "/" + this.mData[position].chapterName
        holder.getView<ImageView>(R.id.iv_home_list_item_like)
            .setImageResource(R.mipmap.icon_like_normal)
//        holder.getView<ImageView>(R.id.iv_home_list_item_like).setOnClickListener {
//            ApiService.crate().collect(this.mData[position].id)
//                .enqueue(object : Callback<BaseResponse<Any>> {
//                    override fun onFailure(call: Call<BaseResponse<Any>>, t: Throwable) {
//                        Toast.makeText(ctx, t.message, Toast.LENGTH_SHORT).show()
//                    }
//
//                    override fun onResponse(
//                        call: Call<BaseResponse<Any>>,
//                        response: Response<BaseResponse<Any>>
//                    ) {
//                        if (response.body()?.errorCode == 0) {
//                            Toast.makeText(ctx, "收藏成功！", Toast.LENGTH_SHORT).show()
//                            holder.getView<ImageView>(R.id.iv_home_list_item_like)
//                                .setImageResource(R.mipmap.icon_like_select)
//                        }else {
//                            Toast.makeText(
//                                ctx,
//                                response.body()?.errorMsg,
//                                Toast.LENGTH_SHORT
//                            ).show()
//                        }
//                    }
//
//                })
//        }
    }
}