package com.jameschamberlain.recipes.ui.recipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.firebase.ui.firestore.ClassSnapshotParser
import com.firebase.ui.firestore.FirestoreArray
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.jameschamberlain.recipes.data.Recipe
import com.jameschamberlain.recipes.databinding.FragmentRecipesBinding

private const val TAG = "RecipesFragment"

class RecipesFragment : Fragment() {

    private lateinit var adapter: RecipeAdapter

    private lateinit var binding: FragmentRecipesBinding

    private lateinit var recipesViewModel: RecipesViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        recipesViewModel =
                ViewModelProvider(this).get(RecipesViewModel::class.java)
        binding = FragmentRecipesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val options = FirestoreRecyclerOptions.Builder<Recipe>()
            .setSnapshotArray(recipesViewModel.recipes)
            .setLifecycleOwner(this@RecipesFragment)
            .build()
        adapter = RecipeAdapter(options, requireContext(), this@RecipesFragment)
        binding.recipesRecyclerView.adapter = adapter

    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }
}