package com.jameschamberlain.recipes.ui.recipes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.firebase.ui.common.ChangeEventType
import com.firebase.ui.firestore.ChangeEventListener
import com.firebase.ui.firestore.ClassSnapshotParser
import com.firebase.ui.firestore.FirestoreArray
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.jameschamberlain.recipes.data.Recipe

class RecipesViewModel : ViewModel() {

    private val query: Query = Firebase.firestore.collection("recipes")
    val recipes = FirestoreArray(query, ClassSnapshotParser(Recipe::class.java))

    init {
        recipes.addChangeEventListener(KeepAliveListener)
    }

    override fun onCleared() {
        recipes.removeChangeEventListener(KeepAliveListener)
    }

    private object KeepAliveListener : ChangeEventListener {
        override fun onDataChanged() = Unit
        override fun onChildChanged(
            type: ChangeEventType,
            snapshot: DocumentSnapshot,
            newIndex: Int,
            oldIndex: Int
        ) = Unit

        override fun onError(e: FirebaseFirestoreException) = Unit
    }


    private val selectedRecipe = MutableLiveData<Int>()

    fun selectRecipe(position: Int) {
        selectedRecipe.value = position
    }

    fun getSelectedRecipe() : MutableLiveData<Int> {
        return selectedRecipe
    }

}