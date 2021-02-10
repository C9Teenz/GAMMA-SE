package com.gamma.rechealth.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gamma.rechealth.ui.NewRiwayatPenyakitFragment
import com.gamma.rechealth.ui.OldRiwayatPenyakitFragment

class CheckupFragmentAdapter (fragment: FragmentActivity) : FragmentStateAdapter(fragment) {

    private val fragments = listOf(
        OldRiwayatPenyakitFragment(),
        NewRiwayatPenyakitFragment()
    )

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = fragments[position]

}