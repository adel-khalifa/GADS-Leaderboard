package com.greycom.gadsleaderboard.network

import com.greycom.gadsleaderboard.data.HoursResponse
import com.greycom.gadsleaderboard.data.IQSkillResponse
import retrofit2.Response
import retrofit2.http.*

interface NetworkInterface {
 // https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/viewform
    // https://gadsapi.herokuapp.com/api/skilliq
    //        Email Address = entry.1824927963
//    Name = entry.1877115667
//    Last Name = entry.2006916086
//    Link to project = entry.284483984

    @GET("/api/skilliq")
    suspend fun getIqData(): Response<IQSkillResponse>


    @GET("/api/hours")
    suspend fun getTopData(): Response<HoursResponse>


    @FormUrlEncoded
    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    suspend fun postProjectUrl(

        @Field("entry.1877115667") firstName: String,
        @Field("entry.2006916086") lastName: String,
        @Field("entry.1824927963") email: String,
        @Field("entry.284483984") projectUrl: String

    ): Response<Void>
}