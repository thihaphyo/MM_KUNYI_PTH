package com.padc.mmkunyi.data.models

import com.padc.mmkunyi.data.vos.JobVO
import com.padc.mmkunyi.events.ApiErrorEvent
import com.padc.mmkunyi.events.ForceGetJobEvent
import com.padc.mmkunyi.events.SuccessGetJobEvent
import com.padc.mmkunyi.network.MMKuNyiDataAgent
import com.padc.mmkunyi.network.RetrofitDataAgentImpl
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 *
 * Created by Phyo Thiha on 7/28/18.
 */
class JobModel : BaseModel {

    private constructor() {

        mDataAgent = RetrofitDataAgentImpl.getObjectReference()
        EventBus.getDefault().register(this)
    }


    private var mPage: Int = 1

    private var mJobRepo: HashMap<Int, JobVO> = HashMap()

    companion object {

        private var objectReference: JobModel? = null

        private var mDataAgent: MMKuNyiDataAgent? = null

        fun getObjectReference(): JobModel? {

            if (objectReference == null) {

                objectReference = JobModel()

            }

            return objectReference
        }

    }

    fun loadJobList() {

        mDataAgent!!.loadJobList(accessToken, mPage, false)
        mPage+=1
    }

    fun forceRefreshJobList() {
        mPage = 1
        mDataAgent!!.loadJobList(accessToken, mPage, true)
    }

    fun setDataRepo(jobList: List<JobVO>) {

        for (job in jobList) {
            mJobRepo[job.jobPostId] = job
        }
    }

    fun getJobById(id: Int): JobVO? {

        return mJobRepo[id]
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    fun onSuccessGetJobList(successGetJobEvent: SuccessGetJobEvent) {
        setDataRepo(successGetJobEvent.jobList)
    }



    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    fun onFailGetJobList(apiErrorEvent: ApiErrorEvent) {

    }
}