package com.daledawson.wananzhuo_kotlin.adapter

import android.content.Context
import android.widget.TextView
import com.daledawson.wananzhuo_kotlin.R
import com.daledawson.wananzhuo_kotlin.base.BaseAdapter
import com.daledawson.wananzhuo_kotlin.base.BaseHolder
import com.daledawson.wananzhuo_kotlin.bean.DataX

/**
 * 创 建 人：zhengquan
 * 创建日期：2019/10/18
 * 修改时间：
 * 修改备注：
 */
class HomeListAdapter(ctx: Context, layoutRes: Int, mData:MutableList<DataX>) : BaseAdapter<DataX>(ctx, layoutRes, mData) {
    override fun convert(holder: BaseHolder, position: Int) {
        // 获取item中的TextView
        val name = holder.getView<TextView>(R.id.tv_home_list_item_name)
        name.text = this.mData[position].shareUser
//
        val time =holder.getView<TextView>(R.id.tv_home_list_item_time)
        time.text=this.mData[position].publishTime.toString()

        var title=holder.getView<TextView>(R.id.tv_home_list_item_title)
        title.text=this.mData[position].title

        var type=holder.getView<TextView>(R.id.tv_home_list_item_type)
        type.text=this.mData[position].superChapterName
//        // 获取item中的Button
//        val button = holder.getView<Button>(R.id.item_button)
//        button.text = "${Random.nextBoolean()}"
//        button.setOnClickListener {
//            Log.d("taonce", "button click item is $position")
//        }
//
//        // 获取item中的ImageView
//        val image = holder.getView<ImageView>(R.id.item_image)
//        image.setImageResource(R.mipmap.ic_launcher)
    }

}