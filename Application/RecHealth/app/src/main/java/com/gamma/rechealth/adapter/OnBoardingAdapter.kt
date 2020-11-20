package com.gamma.rechealth.adapter

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gamma.rechealth.onboarding.FirstOnBoarding
import com.gamma.rechealth.onboarding.SecondOnBoarding
import com.gamma.rechealth.onboarding.ThirdOnBoarding

class OnBoardingAdapter(val activity: FragmentActivity) : FragmentStateAdapter(activity) {
    val fragments = listOf(
        FirstOnBoarding(),
        SecondOnBoarding(),
        ThirdOnBoarding()
    )
    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

}