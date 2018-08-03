package com.padc.mmkunyi.data.vos

import com.google.gson.annotations.SerializedName

/**
 *
 * Created by Phyo Thiha on 7/28/18.
 */
class JobDurationVO {

    @SerializedName("jobEndDate")
    val jobEndDate: String = ""
    @SerializedName("jobStartDate")
    val jobStartDate: String = ""
    @SerializedName("totalWorkingDays")
    val totalWorkingDays: Int = 0
    @SerializedName("workingDaysPerWeek")
    val workingDaysPerWeek: Double = 0.0
    @SerializedName("workingHoursPerDay")
    val workingHoursPerDay: Double = 0.0
}