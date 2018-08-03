package com.padc.mmkunyi.network

/**
 *
 * Created by Phyo Thiha on 7/28/18.
 */
interface MMKuNyiDataAgent {

    fun loadJobList(accessToken:String,page:Int,isForceRefresh:Boolean)
}