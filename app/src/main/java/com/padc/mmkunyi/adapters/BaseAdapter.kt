package com.padc.mmkunyi.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import com.padc.mmkunyi.viewholders.BaseViewHolder

/**
 *
 * Created by Phyo Thiha on 7/28/18.
 */
abstract class BaseAdapter<D, VH : BaseViewHolder<D>> : RecyclerView.Adapter<VH> {

    protected var mDataList: MutableList<D> = ArrayList()


    constructor() : super()

    abstract fun setData(mList: MutableList<D>,context:RecyclerView)

//    fun appendData(mList: List<D>) {
//
//        mDataList.plus(mList)
//        notifyDataSetChanged()
//    }


}