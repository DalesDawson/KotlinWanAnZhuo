package com.daledawson.demo.adapter

import android.content.Context
import com.daledawson.demo.base.BaseAdapter
import com.daledawson.demo.base.BaseHolder
import com.daledawson.demo.bean.SystemChildData
import kotlinx.android.synthetic.main.system_list_item.view.*
import android.widget.TextView
import com.daledawson.demo.R
import com.daledawson.demo.bean.Children
import com.google.android.flexbox.FlexboxLayout
import android.view.LayoutInflater


/**
 * 创 建 人：zhengquan
 * 创建日期：2019/11/4
 * 修改时间：
 * 修改备注：
 */
class SystemAdapter(ctx: Context, layoutRes: Int, mData: MutableList<SystemChildData>) :
    BaseAdapter<SystemChildData>(ctx, layoutRes, mData) {
    var mContext = ctx
    override fun convert(holder: BaseHolder, position: Int) {
        holder.setIsRecyclable(false)
        holder.itemView.system_list_title.text = this.mData[position].name
        initCategoryFlexBox(this.mData[position].children, holder, mContext)
    }

    private fun initCategoryFlexBox(list: List<Children>, holder: BaseHolder, ctx: Context) {
        val inflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        for (item in list) {
            val view = inflater.inflate(R.layout.flex_box_layout_view_item, null)
            val tv = view.findViewById<TextView>(R.id.tv_mv_type)
            val params = tv.layoutParams
            if (params is FlexboxLayout.LayoutParams) {
                params.flexBasisPercent = 1.5f
            }
            tv.setPadding(30, 15, 30, 15)
            tv.text = item.name
            tv.textSize = 15f
            holder.itemView.system_list_flexBoxLayout.addView(view)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}
