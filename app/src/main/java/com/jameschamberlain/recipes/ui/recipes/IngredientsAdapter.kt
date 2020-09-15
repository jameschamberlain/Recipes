package com.jameschamberlain.recipes.ui.recipes

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jameschamberlain.recipes.databinding.ItemIngredientBinding

class IngredientsAdapter
internal constructor(
    private val ingredients: List<String>
) : RecyclerView.Adapter<IngredientsAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemIngredientBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.ingredient.text = ingredients[position]
    }

    override fun getItemCount(): Int {
        return ingredients.size
    }


    class ViewHolder(itemBinding: ItemIngredientBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        var ingredient: TextView = itemBinding.ingredientTextView
    }

}