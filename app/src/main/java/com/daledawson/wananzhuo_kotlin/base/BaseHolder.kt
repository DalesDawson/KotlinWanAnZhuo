package com.daledawson.wananzhuo_kotlin.base

import android.view.View
import androidx.collection.SparseArrayCompat
import androidx.recyclerview.widget.RecyclerView

/**
 * 创 建 人：zhengquan
 * 创建日期：2019/10/18
 * 修改时间：
 * 修改备注：
 */
class BaseHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    // 使用`SparseArrayCompat`是为了复用view，不用每次`getView`都去`find`
    private val mViews = SparseArrayCompat<View>()

    /**
     * 通过resId获取view
     * 将获取到的View转换成具体的View,比如：TextView、Button等等
     * 这里主要用到的是Kotlin里面的`as`操作符
     */
    fun <V : View> getView(id: Int): V {
        var view = mViews[id]
        if (view == null) {
            view = itemView.findViewById(id)
            mViews.put(id, view)
        }
        return view as V
    }
}
