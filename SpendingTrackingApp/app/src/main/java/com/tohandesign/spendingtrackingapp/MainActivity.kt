package com.tohandesign.spendingtrackingapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.button.MaterialButton
import com.tohandesign.spendingtrackingapp.Onboarding.OnBoardingAdapter
import com.tohandesign.spendingtrackingapp.Onboarding.OnBoardingItem
import com.tohandesign.spendingtrackingapp.Retrofit.CurrencyApi


class MainActivity : AppCompatActivity() {

    val PREFS_FILENAME = "com.tohandesign.spendingtrackingapp"

    private lateinit var onBoardingAdapter: OnBoardingAdapter
    private lateinit var indicatorsContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setOnboardingItems()
        setupIndicators()
        setCurrentIndicator(0)


    }

    private fun setOnboardingItems() {


        onBoardingAdapter = OnBoardingAdapter(
                listOf(
                        OnBoardingItem(
                                onboardingImage = R.drawable.onboarding_1,
                                title = "Track Your Spending",
                                description = "You can easily track your expenses from a single platform."
                        ),
                        OnBoardingItem(
                                onboardingImage = R.drawable.onboarding_2,
                                title = "Choose Your Spending",
                                description = "You can record either invoice, rent or all other expenses."
                        ),
                        OnBoardingItem(
                                onboardingImage = R.drawable.onboarding_3,
                                title = "Different Currencies",
                                description = "You can add any currency you want and convert between currencies."
                        )
                )
        )

        val onboardingvp = findViewById<ViewPager2>(R.id.onBoarding)
        onboardingvp.adapter = onBoardingAdapter
        onboardingvp.registerOnPageChangeCallback(object :
        ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        (onboardingvp.getChildAt(0) as RecyclerView).overScrollMode =
            RecyclerView.OVER_SCROLL_NEVER
        findViewById<ImageView>(R.id.right_btn).setOnClickListener{
            if(onboardingvp.currentItem + 1 < onBoardingAdapter.itemCount) {
                onboardingvp.currentItem += 1
            } else {
                navigateToHome()
            }
        }
        findViewById<TextView>(R.id.skip_text).setOnClickListener {
            navigateToHome()
        }
        findViewById<MaterialButton>(R.id.startButton).setOnClickListener {
            navigateToHome()
        }
    }


    private fun setupIndicators() {
        indicatorsContainer = findViewById(R.id.indicators)
        val indicators = arrayOfNulls<ImageView>(onBoardingAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_bg
                    )
                )
                it.layoutParams = layoutParams
                indicatorsContainer.addView(it)
            }
        }
    }


    private fun setCurrentIndicator(position: Int) {
        val childCount = indicatorsContainer.childCount
        for (i in 0 until childCount) {
            val imageView = indicatorsContainer.getChildAt(i) as ImageView
            if(i == position) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active_bg
                    )
                )
            }else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_bg
                    )
                )
            }
        }
    }

    private fun navigateToHome(){
        val prefences = getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)
        val editor = prefences.edit()
        editor.putBoolean("IS_FIRST", false)
        editor.apply()
        startActivity(Intent(applicationContext, HomeActivity::class.java))
        finish()
    }

}