package com.padc.mmkunyi.activities

import android.os.Bundle
import com.bumptech.glide.request.RequestOptions
import com.padc.mmkunyi.R
import com.padc.mmkunyi.data.models.JobModel
import com.padc.mmkunyi.utils.AppConstants
import com.padc.mmkunyi.utils.GlideApp
import kotlinx.android.synthetic.main.activity_job_detail.*


/**
 *
 * Created by Phyo Thiha on 8/3/18.
 */
class JobDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_detail)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        val jobID = intent.extras.getInt(AppConstants.JOB_ID)
        val jobObject = JobModel.getObjectReference()!!.getJobById(jobID)

        GlideApp.with(ivJobCover.context)
                .load(R.drawable.placeholder_job)
                .placeholder(R.drawable.placeholder_job)
                .apply(RequestOptions.circleCropTransform())
                .centerCrop()
                .into(ivJobCover)

        tvJobTitle.text = jobObject!!.shortDesc
        tvDescription.text = jobObject.fullDesc
        tvPrice.text = jobObject.offerAmount!!.amount.toString()
        tvLocation.text = jobObject.location

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}