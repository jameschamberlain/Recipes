package com.jameschamberlain.recipes.ui.recipes

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.jameschamberlain.recipes.GlideApp
import com.jameschamberlain.recipes.R
import com.jameschamberlain.recipes.data.Recipe
import com.jameschamberlain.recipes.databinding.FragmentRecipeDetailsBinding

class RecipeDetailsFragment : Fragment() {

    private lateinit var binding: FragmentRecipeDetailsBinding

    private val args: RecipeDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipeDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecipe(args.id)
        setHasOptionsMenu(true)
        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = ""
    }

    private fun setupRecipe(id: String) {
        Firebase.firestore.collection("recipes").document(id).get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot != null && documentSnapshot.exists()) {
                    val recipe = documentSnapshot.toObject<Recipe>()!!
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
            }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.recipe_details_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // User chose the "Back" item, go back.
                NavHostFragment.findNavController(this@RecipeDetailsFragment).navigateUp()
                true
            }
//            android.R.id.action_edit -> {
//                Toast.makeText(activity, "Edit", Toast.LENGTH_SHORT).show()
//                true
//            }
//            android.R.id.action_delete -> {
//                Toast.makeText(activity, "Delete", Toast.LENGTH_SHORT).show()
//                true
//            }
            else ->                 // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                super.onOptionsItemSelected(item)
        }
    }
}