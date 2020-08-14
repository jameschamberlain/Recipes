package com.jameschamberlain.recipes.data

data class Recipe(
    val name: String,
    val cookTime: Int,
    val prepTime: Int,
    val ingredients: List<String>,
    val quantity: Int,
    val steps: List<String>,
    val description: String,
    val image: String,
    val tags: List<String>
) {
    constructor() : this("", -1, -1,
    emptyList(), -1, emptyList(), "", "", emptyList())
}

