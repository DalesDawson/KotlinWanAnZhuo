package com.daledawson.demo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daledawson.demo.R
import com.daledawson.demo.bean.DataX
import com.daledawson.demo.util.TimeUitl
import kotlinx.android.synthetic.main.home_list_item.view.*

/**
 * 创 建 人：zhengquan
 * 创建日期：2019/10/18
 * 修改时间：
 * 修改备注：
 */
class HomeListAdapter2(var data: List<DataX>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflate = LayoutInflater.from(parent?.context).inflate(R.layout.home_list_item, null)
        return MyHolder(inflate)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MyHolder) {
            holder.bind(data[position])
        }
    }

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(date: DataX) {
            itemView.tv_home_list_item_name.text = date.shareUser
            itemView.tv_home_list_item_time.text =
                TimeUitl.getDateToString(date.publishTime, "yyyy-MM-dd HH:mm:ss")
            itemView.tv_home_list_item_title.text = date.title
            itemView.tv_home_list_item_type.text = date.superChapterName
        }
    }

}