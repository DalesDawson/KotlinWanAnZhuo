package com.daledawson.demo.base

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daledawson.demo.util.Logger

/**
 * 创 建 人：zhengquan
 * 创建日期：2019/10/18
 * 修改时间：
 * 修改备注：
 */
abstract class BaseAdapter<T>(
    var ctx: Context,
    private val layoutRes: Int,
    var mData: MutableList<T>
) : RecyclerView.Adapter<BaseHolder>() {

    private lateinit var mListener: OnItemClickListener
    private lateinit var mLongListener: OnItemLongClickListener

    fun setOnItemClickListener(mListener: OnItemClickListener) {
        this.mListener = mListener
    }

    fun setOnItemLongClickListener(mLongListener: OnItemLongClickListener) {
        this.mLongListener = mLongListener
    }

    /**
     * 数据和item的绑定交给了convert()方法，将ViewHolder和position传进去
     */
    override fun onBindViewHolder(holder: BaseHolder, position: Int) {
        convert(holder, position)
        holder.itemView.setOnClickListener {
            mListener.onItemClick(position)
        }
        holder.itemView.setOnLongClickListener { mLongListener.onItemLongClick(position) }
    }

    /**
     * 通过layout的id生成ViewHolder对象
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder {
        return BaseHolder(LayoutInflater.from(ctx).inflate(layoutRes, parent, false))
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    /**
     * 用来给具体Adapter实现逻辑的抽象方法
     */
    abstract fun convert(holder: BaseHolder, position: Int)

    /**
     * 添加一项数据
     * item:添加的数据
     * position:默认为最后一项
     */
    fun addData(item: T, position: Int = mData.size) {
        mData.add(position, item)
        notifyDataSetChanged()
    }

    /**
     * 添加数据
     * listData：添加的数据
     * isDelete：是否删除原来的数据
     */
    fun addListData(listData: MutableList<T>, isDelete: Boolean) {
        if (isDelete) {
            mData.clear()
        }
        mData.addAll(listData)
        notifyDataSetChanged()
    }

    /**
     * 添加数据
     * listData：添加的数据
     */
    fun setDataList(listData: MutableList<T>) {
        mData.addAll(listData)
        notifyDataSetChanged()
    }

    /**
     * 删除指定项数据
     * position:从0开始
     */
    fun deletePositionData(position: Int) {
        // 防止position越界
        if (position >= 0 && position < mData.size) {
            mData.remove(mData[position])
            notifyDataSetChanged()
        } else {
            Logger.d("taonce", "delete item failed, position error!")
        }
    }

    /**
     * 删除所有数据
     */
    fun deleteAllData() {
        mData.removeAll(mData)
        notifyDataSetChanged()
    }

    /**
     * item的点击事件
     */
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    /**
     * item的长按事件
     */
    interface OnItemLongClickListener {
        fun onItemLongClick(position: Int): Boolean
    }
}