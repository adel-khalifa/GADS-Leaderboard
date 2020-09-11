package com.greycom.gadsleaderboard.ui.fragments.hours

import TopLearnersAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.greycom.gadsleaderboard.R
import com.greycom.gadsleaderboard.ui.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_top_learners.*

class TopLearnersFragment : Fragment(R.layout.fragment_top_learners) {

    private lateinit var topAdapter: TopLearnersAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        (activity as MainActivity).viewModel.requestTopHoursData()
        (activity as MainActivity).viewModel.topHoursLiveData.observe(viewLifecycleOwner, Observer {
            topAdapter.asyncListDiffer.submitList(it.bodyData)
        })

    }

    private fun initRecycler() {
        topAdapter = TopLearnersAdapter()
        top_recycler.apply {
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = topAdapter
        }
    }


}