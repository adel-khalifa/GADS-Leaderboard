package com.greycom.gadsleaderboard.repo

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModelFactory(val app: Application, private val mRepo :MainRepo) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return (MainViewModel(app, mRepo) as T)
    }
}