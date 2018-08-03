package com.padc.mmkunyi.viewholders

import android.annotation.SuppressLint
import android.view.View
import com.padc.mmkunyi.R
import com.padc.mmkunyi.data.vos.JobVO
import com.padc.mmkunyi.delegates.JobsDelegate
import com.padc.mmkunyi.utils.GlideApp
import kotlinx.android.synthetic.main.view_holder_job.view.*

/**
 *
 * Created by Phyo Thiha on 7/28/18.
 */
class JobViewHolder : BaseViewHolder<JobVO> {


    private var mDelegate: JobsDelegate? = null

    constructor(itemView: View?, delegate: JobsDelegate) : super(itemView) {

        mDelegate = delegate
        itemView!!.setOnClickListener { mDelegate!!.onTapJob(mData!!) }
        itemView.btnAppply.setOnClickListener { mDelegate!!.onTapApply(mData!!) }
    }

    @SuppressLint("StringFormatInvalid")
    override fun bindData(data: JobVO) {

        mData = data
        GlideApp.with(itemView.context)
                .load(mData!!.images[0])
                .placeholder(R.drawable.placeholder_job)
                .centerCrop()
                .into(itemView.ivJobHero)

        itemView.tvJobName.text = mData!!.shortDesc
        itemView.tvStats.text = itemView.context
                .getString(R.string.working_days, mData!!.jobDuration!!.totalWorkingDays)
        itemView.tvPrice.text = mData!!.offerAmount!!.duration.toUpperCase()
        itemView.tvActualPrice.text = mData!!.offerAmount!!.amount.toString()
    }

}