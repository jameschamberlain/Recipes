package com.jameschamberlain.recipes.ui.recipes

import android.os.Bundle
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
import com.jameschamberlain.recipes.GlideApp
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = ""

        model.getSelectedRecipe().observe(viewLifecycleOwner, Observer {
            setupRecipe(model.recipes[it])
        })
    }

    private fun setupRecipe(recipe: Recipe) {
        binding.name.text = recipe.name
        binding.cookTime.text = recipe.cookTime.toString()
        binding.prepTime.text = recipe.prepTime.toString()
        binding.desc.text = recipe.description
        binding.servesAmount.text = recipe.quantity.toString()
        GlideApp.with(requireContext())
            .load(recipe.image)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(8)))
            .into(binding.imageView)
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