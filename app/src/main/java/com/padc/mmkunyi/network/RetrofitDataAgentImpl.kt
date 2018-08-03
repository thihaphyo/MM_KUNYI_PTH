package com.padc.mmkunyi.network

import com.google.gson.Gson
import com.padc.mmkunyi.events.ApiErrorEvent
import com.padc.mmkunyi.events.ForceGetJobEvent
import com.padc.mmkunyi.events.SuccessGetJobEvent
import com.padc.mmkunyi.network.responses.GetJobsResponse
import com.padc.mmkunyi.utils.AppConstants
import okhttp3.OkHttpClient
import org.greenrobot.eventbus.EventBus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *
 * Created by Phyo Thiha on 7/28/18.
 */
class RetrofitDataAgentImpl : MMKuNyiDataAgent {

    private var mApi: Api? = null

    companion object {

        private var objectReference: RetrofitDataAgentImpl? = null

        fun getObjectReference(): RetrofitDataAgentImpl? {

            if (objectReference == null) {

                objectReference = RetrofitDataAgentImpl()
            }
            return objectReference
        }
    }


    private constructor() {

        val mOkHttpClient: OkHttpClient = OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .build()

        val retrofit: Retrofit = Retrofit.Builder()
                .client(mOkHttpClient)
                .baseUrl(AppConstants.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .build()

        mApi = retrofit.create(Api::class.java)


    }

    override fun loadJobList(accessToken: String, page: Int, isForceRefresh: Boolean) {

        val apiCall: Call<GetJobsResponse> = mApi!!.loadJobListFromServer(accessToken, page)
        apiCall.enqueue(object : Callback<GetJobsResponse> {

            override fun onResponse(call: Call<GetJobsResponse>?, response: Response<GetJobsResponse>?) {

                val getJobsResponse: GetJobsResponse? = response!!.body()
                if (getJobsResponse != null && getJobsResponse.isResponseOK()) {

                    if (isForceRefresh) {

                        val successEvent = ForceGetJobEvent(getJobsResponse.jobs)
                        EventBus.getDefault().post(successEvent)

                    } else {

                        val successEvent = SuccessGetJobEvent(getJobsResponse.jobs)
                        EventBus.getDefault().post(successEvent)
                    }

                } else {

                    if (getJobsResponse == null) {

                        val errorEvent = ApiErrorEvent(AppConstants.ERROR_RESPONSE_EMPTY)
                        EventBus.getDefault().post(errorEvent)

                    } else {

                        val errorEvent = ApiErrorEvent(getJobsResponse.message)
                        EventBus.getDefault().post(errorEvent)
                    }
                }

            }

            override fun onFailure(call: Call<GetJobsResponse>?, t: Throwable?) {

                val errorEvent = ApiErrorEvent(t!!.localizedMessage)
                EventBus.getDefault().post(errorEvent)
            }

        })
    }
}