package com.gamma.rechealth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.gamma.rechealth.adapter.OnBoardingAdapter
import kotlinx.android.synthetic.main.activity_on_boarding.*

class OnBoardingActivity : AppCompatActivity() {
    var currentPosition = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)

        slider.adapter = OnBoardingAdapter(this)

        slider.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                changeIndicator(position)
                Log.d("TAG", "onPageSelected: $position")
                currentPosition = position
            }
        })
        btnNext.setOnClickListener {
            slider.currentItem = currentPosition+1
        }
    }

    private fun changeIndicator(position: Int) {
        indicator1.background = ContextCompat.getDrawable(this, R.drawable.item_unselected)
        indicator2.background = ContextCompat.getDrawable(this, R.drawable.item_unselected )
        indicator3.background = ContextCompat.getDrawable(this, R.drawable.item_unselected )
        when(position){
            0 -> {
                indicator1.background = ContextCompat.getDrawable(this, R.drawable.item_selected)
            }
            1->{
                indicator2.background = ContextCompat.getDrawable(this, R.drawable.item_selected)
            }
            else -> {
                indicator3.background = ContextCompat.getDrawable(this, R.drawable.item_selected)
            }
        }
    }
}