package com.greycom.gadsleaderboard.data


import com.google.gson.annotations.SerializedName

class HoursResponse : ArrayList<HoursResponse.HoursResponseItem>(){
    data class HoursResponseItem(
        val badgeUrl: String,
        val country: String,
        val hours: Int,
        val name: String
    )
}