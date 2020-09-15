package com.jameschamberlain.recipes.ui.recipes

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jameschamberlain.recipes.databinding.ItemStepBinding

class StepsAdapter
internal constructor(
    private val steps: List<String>
) : RecyclerView.Adapter<StepsAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemStepBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.stepNumber.text = (position + 1).toString()
        holder.stepDescription.text = steps[position]
    }

    override fun getItemCount(): Int {
        return steps.size
    }


    class ViewHolder(itemBinding: ItemStepBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        var stepNumber: TextView = itemBinding.stepNumberTextView
        var stepDescription: TextView = itemBinding.stepDescTextView
    }

}