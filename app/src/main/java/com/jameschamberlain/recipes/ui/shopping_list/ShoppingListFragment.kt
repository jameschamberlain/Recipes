package com.jameschamberlain.recipes.ui.shopping_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jameschamberlain.recipes.R

class ShoppingListFragment : Fragment() {

    private lateinit var shoppingListViewModel: ShoppingListViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        shoppingListViewModel =
                ViewModelProviders.of(this).get(ShoppingListViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_shopping_list, container, false)
        val textView: TextView = root.findViewById(R.id.text_shopping_list)
        shoppingListViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}