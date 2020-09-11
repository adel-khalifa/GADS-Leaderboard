package com.greycom.gadsleaderboard.data


import com.google.gson.annotations.SerializedName

class IQSkillResponse : ArrayList<IQSkillResponse.IQSkillResponseItem>(){
    data class IQSkillResponseItem(
        val badgeUrl: String,
        val country: String,
        val name: String,
        val score: Int
    )
}