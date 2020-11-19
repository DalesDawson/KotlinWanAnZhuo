package com.daledawson.demo.adapter

import android.content.Context
import android.text.Html
import android.text.TextUtils
import android.widget.TextView
import com.daledawson.demo.R
import com.daledawson.demo.base.BaseAdapter
import com.daledawson.demo.base.BaseHolder
import com.daledawson.demo.bean.SDataX
import com.daledawson.demo.util.TimeUitl

/**
 * 创 建 人：zhengquan
 * 创建日期：2019/10/18
 * 修改时间：
 * 修改备注：
 */
class CollectListAdapter(ctx: Context, layoutRes: Int, mData: MutableList<SDataX>) :
    BaseAdapter<SDataX>(ctx, layoutRes, mData) {
    override fun convert(holder: BaseHolder, position: Int) {
        var user = if (TextUtils.isEmpty(this.mData[position].author)) {
            this.mData[position].author
        } else {
            this.mData[position].author
        }
        holder.getView<TextView>(R.id.tv_home_list_item_name).text = user
        holder.getView<TextView>(R.id.tv_home_list_item_time).text =
            TimeUitl.getDateToString(this.mData[position].publishTime, "yyyy-MM-dd")
        holder.getView<TextView>(R.id.tv_home_list_item_title).text =
            Html.fromHtml(this.mData[position].title)
        holder.getView<TextView>(R.id.tv_home_list_item_type).text =
            this.mData[position].chapterName
//        holder.getView<ImageView>(R.id.iv_home_list_item_like)
//            .setImageResource(R.mipmap.icon_like_normal)

//        holder.getView<ImageView>(R.id.iv_home_list_item_like).setOnClickListener {
//            ApiService.get().collect(this.mData[position].id)
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
//                        }
//                    }
//
//                })
//        }
    }
}