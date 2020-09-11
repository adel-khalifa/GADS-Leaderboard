package com.greycom.gadsleaderboard.ui.activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.RecyclerView
import com.greycom.gadsleaderboard.R
import com.greycom.gadsleaderboard.ui.fragments.hours.TopLearnersFragment
import com.greycom.gadsleaderboard.ui.fragments.iq.IQFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class PagerAdapter() : RecyclerView.Adapter<PagerAdapter.PagerViewHolder>() {
    inner class PagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PagerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.activity_main,parent,false))


    override fun getItemCount()=2

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        when(position){
        }
    }


}