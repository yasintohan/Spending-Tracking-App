package com.tohandesign.spendingtrackingapp.Onboarding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tohandesign.spendingtrackingapp.R

class OnBoardingAdapter(private val onBoardingItems: List<OnBoardingItem>):
RecyclerView.Adapter<OnBoardingAdapter.OnboardingItemViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingItemViewHolder {
        return OnboardingItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                    R.layout.onboarding_container,
                parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return onBoardingItems.size
    }

    override fun onBindViewHolder(holder: OnboardingItemViewHolder, position: Int) {
        holder.bind(onBoardingItems[position])
    }

    inner class OnboardingItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val imageOnboarding = view.findViewById<ImageView>(R.id.onBoarding_image)
        private val titleOnboarding = view.findViewById<TextView>(R.id.onBoarding_title)
        private val descOnboarding = view.findViewById<TextView>(R.id.onBoarding_desc)

        fun bind(onBoardingItem: OnBoardingItem) {
            imageOnboarding.setImageResource(onBoardingItem.onboardingImage)
            titleOnboarding.text = onBoardingItem.title
            descOnboarding.text = onBoardingItem.description
        }

    }
}