package com.greycom.gadsleaderboard.ui.fragments.iq

import IQAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.greycom.gadsleaderboard.R
import com.greycom.gadsleaderboard.ui.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_iq.*


class IQFragment : Fragment(R.layout.fragment_iq) {

    private lateinit var iqAdapter: IQAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        (activity as MainActivity).viewModel.requestIqData()
        (activity as MainActivity).viewModel.iqLiveData.observe(viewLifecycleOwner, Observer {
            iqAdapter.asyncListDiffer.submitList(it.bodyData)
        })

    }

    private fun initRecycler() {
        iqAdapter = IQAdapter()
        iq_recycler.apply {
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = iqAdapter
        }
    }


}