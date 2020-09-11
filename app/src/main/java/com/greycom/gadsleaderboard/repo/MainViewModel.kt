package com.greycom.gadsleaderboard.repo

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.greycom.gadsleaderboard.R
import com.greycom.gadsleaderboard.application.GADSApplication
import com.greycom.gadsleaderboard.data.HoursResponse
import com.greycom.gadsleaderboard.data.IQSkillResponse
import com.greycom.gadsleaderboard.network.NetworkState
import kotlinx.coroutines.launch

import java.io.IOException

class MainViewModel(val app: Application, private val mRepo: MainRepo) : AndroidViewModel(app) {

    private val iqType = 0
    private val hoursType = 1
    private val postType = 2
    var iqLiveData: MutableLiveData<NetworkState<IQSkillResponse>> = MutableLiveData()
    var topHoursLiveData: MutableLiveData<NetworkState<HoursResponse>> = MutableLiveData()
    var postProjectLiveData: MutableLiveData<String> = MutableLiveData()


    fun postProject(
        firstName: String,
        lastName: String,
        mail: String,
        gitUrl: String
    ) {
        viewModelScope.launch {

            if (isConnected()) {
                postProjectLiveData.value = "OnLoading"

                try {
                    val requestResult = mRepo.postProjectUrl(
                        first = firstName,
                        last = lastName,
                        mail = mail,
                        url = gitUrl
                    )
                    if (requestResult.isSuccessful) {
                        Log.i("SubActivity", "isSuccess")

                        postProjectLiveData.postValue(requestResult.message()+ "Success")
                    } else {
                        Log.i("SubActivity", "isFailed")

                        postProjectLiveData.postValue(requestResult.message() + "Failed")
                    }
                } catch (t: Throwable) {
                    when (t) {

                        is IOException -> handleConnectionFailed(postType)
                    }
                }
            } // if no internet connection
            else handleNoInternetConnection(postType)
        }
    }

    fun requestIqData() {

        viewModelScope.launch {
            if (isConnected()) {
                iqLiveData.value = NetworkState.OnLoading()

                try {
                    val requestResult = mRepo.iqRequest()
                    if (requestResult.isSuccessful) {
                        iqLiveData.postValue(NetworkState.OnSuccess(requestResult.body()!!))
                    } else {
                        iqLiveData.postValue(NetworkState.OnFailure(requestResult.message()))
                    }
                } catch (t: Throwable) {
                    when (t) {
                        is IOException -> handleConnectionFailed(iqType)
                    }
                }
            } // if no internet connection
            else handleNoInternetConnection(iqType)
        }
    }

    fun requestTopHoursData() {

        viewModelScope.launch {
            if (isConnected()) {
                topHoursLiveData.value = NetworkState.OnLoading()

                try {
                    val requestResult = mRepo.topHoursRequest()
                    if (requestResult.isSuccessful) {
                        topHoursLiveData.postValue(NetworkState.OnSuccess(requestResult.body()!!))
                    } else {
                        topHoursLiveData.postValue(NetworkState.OnFailure(requestResult.message()))
                    }
                } catch (t: Throwable) {
                    when (t) {
                        is IOException -> handleConnectionFailed(hoursType)
                    }
                }
            } // if no internet connection
            else handleNoInternetConnection(hoursType)
        }
    }


    private fun handleNoInternetConnection(type: Int) {
        val noInternetConnectionMessage: String =
            getApplication<GADSApplication>().getString(R.string.no_internet_connection)
        when (type) {
            iqType -> iqLiveData.postValue(NetworkState.OnFailure(noInternetConnectionMessage))
            hoursType ->
                topHoursLiveData.postValue(
                    NetworkState.OnFailure(noInternetConnectionMessage)
                )
            postType -> postProjectLiveData.postValue(noInternetConnectionMessage)

        }
    }

    private fun handleConnectionFailed(type: Int) {
        val connectionFailedMessage =
            getApplication<GADSApplication>().getString(R.string.connection_failed)
        when (type) {
            iqType -> iqLiveData.postValue(NetworkState.OnFailure(connectionFailedMessage))
            hoursType -> topHoursLiveData.postValue(NetworkState.OnFailure(connectionFailedMessage))
            postType -> postProjectLiveData.postValue(connectionFailedMessage)


        }
    }


    private fun isConnected(): Boolean {
        val connectivityManager = getApplication<GADSApplication>()
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities =
                connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when (type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }
}