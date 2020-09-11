package com.greycom.gadsleaderboard.repo

import com.greycom.gadsleaderboard.network.RetrofitInstance
import com.greycom.gadsleaderboard.network.RetrofitInstanceForSubmission

object MainRepo {


    suspend fun iqRequest() = RetrofitInstance.mApi.getIqData()

    suspend fun topHoursRequest() = RetrofitInstance.mApi.getTopData()


    suspend fun postProjectUrl(
        first: String,
        last: String,
        mail: String,
        url: String
    ) = RetrofitInstanceForSubmission.mApi.postProjectUrl(
        firstName = first,
        lastName = last,
        email = mail,
        projectUrl = url


    )
}