package com.padc.mmkunyi.data.vos

import com.google.gson.annotations.SerializedName

/**
 *
 * Created by Phyo Thiha on 7/28/18.
 */
class RelevantVO {

    @SerializedName("relevancyPercentage")
    val relevancyPercentage: Double = 0.0
    @SerializedName("seekerId")
    val seekerId: Int = 0
    @SerializedName("seekerName")
    val seekerName: String = ""
    @SerializedName("seekerProfilePicUrl")
    val seekerProfilePicUrl: String = ""
    @SerializedName("seekerSkill")
    val seekerSkill: List<SeekerSkillVO> = ArrayList()
    @SerializedName("whyRelevant")
    val whyRelevant: String = ""


}