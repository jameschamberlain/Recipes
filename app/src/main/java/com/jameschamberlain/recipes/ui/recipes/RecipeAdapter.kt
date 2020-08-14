package com.jameschamberlain.recipes.ui.recipes

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.jameschamberlain.recipes.data.Recipe
import com.jameschamberlain.recipes.databinding.ItemRecipeBinding

private const val TAG = "RecipeAdapter"

class RecipeAdapter(options: FirestoreRecyclerOptions<Recipe>) : FirestoreRecyclerAdapter<Recipe, RecipeAdapter.RecipeHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeHolder {
        val itemBinding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecipeHolder, position: Int, model: Recipe) {
//        holder.imageView = model.image
        holder.titleTextView.text = model.name
        var recipeDesc = model.description
        if (model.description.length > 120)
            recipeDesc = recipeDesc.substring(0, 117) + "..."
        Log.e(TAG, recipeDesc)
        holder.descTextView.text = recipeDesc
    }


    inner class RecipeHolder(itemBinding: ItemRecipeBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        var imageView = itemBinding.imageView
        var titleTextView = itemBinding.titleTextView
        var descTextView = itemBinding.descTextView
    }

}