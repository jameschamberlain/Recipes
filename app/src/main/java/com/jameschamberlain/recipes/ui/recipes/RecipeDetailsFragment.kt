package com.jameschamberlain.recipes.ui.recipes

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.tabs.TabLayoutMediator
import com.jameschamberlain.recipes.GlideApp
import com.jameschamberlain.recipes.R
import com.jameschamberlain.recipes.data.Recipe
import com.jameschamberlain.recipes.databinding.FragmentRecipeDetailsBinding

class RecipeDetailsFragment : Fragment() {

    private var _binding: FragmentRecipeDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val model: RecipesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecipeDetailsBinding.inflate(inflater, container, false)

        sharedElementEnterTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setHasOptionsMenu(true)
        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = ""

        model.getSelectedRecipe().observe(viewLifecycleOwner, {
            setupRecipe(model.recipes[it])
        })

        binding.viewPager.adapter = TabAdapter(this@RecipeDetailsFragment)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = if (position == 0) getString(R.string.ingredients) else getString(R.string.steps)
        }.attach()
    }

    private fun setupRecipe(recipe: Recipe) {
        binding.name.text = recipe.name
        binding.cookTime.text = recipe.cookTime.toString()
        binding.prepTime.text = recipe.prepTime.toString()
        binding.desc.text = recipe.description
        binding.servesAmount.text = recipe.quantity.toString()
        binding.name.transitionName = recipe.name
        binding.imageView.apply {
            transitionName = recipe.image
            GlideApp.with(this)
                .load(recipe.image)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(8)))
                .into(this)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // User chose the "Back" item, go back.
                NavHostFragment.findNavController(this@RecipeDetailsFragment).navigateUp()
                true
            }
            else ->                 // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                super.onOptionsItemSelected(item)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}