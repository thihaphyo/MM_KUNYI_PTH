package com.padc.mmkunyi.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import com.padc.mmkunyi.R
import com.padc.mmkunyi.data.vos.JobVO
import com.padc.mmkunyi.delegates.JobsDelegate
import com.padc.mmkunyi.viewholders.JobViewHolder

/**
 *
 * Created by Phyo Thiha on 7/28/18.
 */
class JobListAdapter : BaseAdapter<JobVO, JobViewHolder> {


    private var mDelegate: JobsDelegate? = null

    constructor(delegate: JobsDelegate) {
        mDataList = ArrayList()
        mDelegate = delegate
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {

        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.view_holder_job, parent, false)
        return JobViewHolder(view, mDelegate!!)

    }

    override fun getItemCount(): Int {

        return mDataList.size

    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {

        holder.bindData(mDataList[position])

    }


    override fun setData(mList: MutableList<JobVO>, context: RecyclerView) {

        mDataList = mList
        val controller: LayoutAnimationController = AnimationUtils
                .loadLayoutAnimation(context.context, R.anim.layout_recycler_animation)
        context.layoutAnimation = controller
        notifyDataSetChanged()
        context.scheduleLayoutAnimation()

    }

    fun appendData(mList: List<JobVO>, context: RecyclerView) {

        mDataList.addAll(mList)
//        val controller: LayoutAnimationController = AnimationUtils
//                .loadLayoutAnimation(context.context, R.anim.layout_recycler_animation)
//        context.layoutAnimation = controller
        notifyDataSetChanged()
        //context.scheduleLayoutAnimation()
    }


}