package com.padc.mmkunyi.network

import com.padc.mmkunyi.network.responses.GetJobsResponse
import com.padc.mmkunyi.utils.AppConstants
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 *
 * Created by Phyo Thiha on 7/28/18.
 */
interface Api {

    @POST(AppConstants.API_GET_JOBS)
    @FormUrlEncoded()
    fun loadJobListFromServer(
            @Field(AppConstants.PARAM_TOKEN) accessToken: String
            , @Field(AppConstants.PARAM_PAGE) page: Int): Call<GetJobsResponse>
}