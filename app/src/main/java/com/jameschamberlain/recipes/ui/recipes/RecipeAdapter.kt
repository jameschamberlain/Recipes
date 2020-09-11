package com.jameschamberlain.recipes.ui.recipes

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.jameschamberlain.recipes.GlideApp
import com.jameschamberlain.recipes.data.Recipe
import com.jameschamberlain.recipes.databinding.ItemRecipeBinding

private const val TAG = "RecipeAdapter"

class RecipeAdapter(
    options: FirestoreRecyclerOptions<Recipe>,
    private val context: Context,
    private val parentFragment: RecipesFragment,
    private val viewModel: RecipesViewModel
) : FirestoreRecyclerAdapter<Recipe, RecipeAdapter.RecipeHolder>(options) {

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
        holder.descTextView.text = recipeDesc

        holder.parentLayout.setOnClickListener {
            viewModel.selectRecipe(position)
            val action = RecipesFragmentDirections
                .actionNavigationRecipesToNavigationRecipeDetails()
            NavHostFragment
                .findNavController(parentFragment)
                .navigate(action)
        }
    }


    class RecipeHolder(itemBinding: ItemRecipeBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        var imageView = itemBinding.imageView
        var titleTextView = itemBinding.titleTextView
        var descTextView = itemBinding.descTextView
        var parentLayout = itemBinding.parentLayout
    }

}