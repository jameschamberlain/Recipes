package com.jameschamberlain.recipes.ui.recipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.jameschamberlain.recipes.data.Recipe
import com.jameschamberlain.recipes.databinding.FragmentRecipesBinding

private const val TAG = "RecipesFragment"

class RecipesFragment : Fragment() {

    private lateinit var adapter: RecipeAdapter

    private var _binding: FragmentRecipesBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val model: RecipesViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
//        recipesViewModel =
//                ViewModelProvider(this).get(RecipesViewModel::class.java)
        _binding = FragmentRecipesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val options = FirestoreRecyclerOptions.Builder<Recipe>()
            .setSnapshotArray(model.recipes)
            .setLifecycleOwner(this@RecipesFragment)
            .build()
        adapter = RecipeAdapter(options, requireContext(), this@RecipesFragment, model)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}