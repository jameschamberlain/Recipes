package com.jameschamberlain.recipes.ui.recipes

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabAdapter(fragment: RecipeDetailsFragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {TabFragment(isSteps = false)}
            else -> {TabFragment(isSteps = true)}
        }
    }
}