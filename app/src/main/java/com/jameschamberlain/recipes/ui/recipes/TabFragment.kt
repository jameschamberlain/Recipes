package com.jameschamberlain.recipes.ui.recipes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.jameschamberlain.recipes.databinding.FragmentRecipeDetailsTabBinding

class TabFragment(private val isSteps: Boolean) : Fragment() {

    private var _binding: FragmentRecipeDetailsTabBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val model: RecipesViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRecipeDetailsTabBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        model.getSelectedRecipe().observe(viewLifecycleOwner, {
            if (isSteps) {
                val adapter = StepsAdapter(model.recipes[it].steps)
                binding.playersRecyclerView.adapter = adapter
                binding.playersRecyclerView.setHasFixedSize(true)
            }
            else {
                val adapter = IngredientsAdapter(model.recipes[it].ingredients)
                binding.playersRecyclerView.adapter = adapter
                binding.playersRecyclerView.setHasFixedSize(true)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}