package com.padc.mmkunyi.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 *
 * Created by Phyo Thiha on 7/28/18.
 */
abstract class BaseViewHolder<W>:RecyclerView.ViewHolder {

    protected var mData:W? = null

    constructor(itemView: View?) : super(itemView)

    abstract fun bindData(data:W)


}