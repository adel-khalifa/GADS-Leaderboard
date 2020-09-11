package com.greycom.gadsleaderboard.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.widget.DialogTitle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.greycom.gadsleaderboard.R
import com.greycom.gadsleaderboard.repo.MainRepo
import com.greycom.gadsleaderboard.repo.MainViewModel
import com.greycom.gadsleaderboard.repo.MainViewModelFactory
import com.jaeger.library.StatusBarUtil
import kotlinx.android.synthetic.main.activity_submission.*

class SubmissionActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submission)
        initViewModel()
        StatusBarUtil.setTransparent(this)
        sub_btn_back.setOnClickListener { finish() }
        sub_btn_submit.setOnClickListener { makeRequest() }

        viewModel.postProjectLiveData.observe(this, Observer {

            Log.i("SubActivity", "Observer result : $it")
            Toast.makeText(this@SubmissionActivity, it, Toast.LENGTH_LONG).show()
        })

    }

    private fun makeRequest() {
        viewModel.postProject(
            firstName = sub_edt_first_name.text.toString().trim(),
            lastName = sub_edt_last_name.text.toString().trim(),
            mail = sub_edt_mail.text.toString().trim(),
            gitUrl = sub_edt_git.text.toString().trim()
        )
    }

    private fun initViewModel() {
        val mainFactory = MainViewModelFactory(application, MainRepo)
        viewModel =
            ViewModelProvider(viewModelStore, mainFactory).get(MainViewModel::class.java)
    }

}