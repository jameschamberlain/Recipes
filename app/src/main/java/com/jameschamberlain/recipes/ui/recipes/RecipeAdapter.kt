package com.jameschamberlain.recipes.ui.recipes

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.storage.FirebaseStorage
import com.jameschamberlain.recipes.GlideApp
import com.jameschamberlain.recipes.MyAppGlideModule
import com.jameschamberlain.recipes.data.Recipe
import com.jameschamberlain.recipes.databinding.ItemRecipeBinding

private const val TAG = "RecipeAdapter"

class RecipeAdapter(options: FirestoreRecyclerOptions<Recipe>, private val context: Context) : FirestoreRecyclerAdapter<Recipe, RecipeAdapter.RecipeHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeHolder {
        val itemBinding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecipeHolder, position: Int, model: Recipe) {
        GlideApp.with(context)
            .load(model.image)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(8)))
            .into(holder.imageView)
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