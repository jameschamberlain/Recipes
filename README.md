Recipes
===================================

This is an app to add and show recipes. It is still in the early stages and currently only supports viewing recipes as a list and the details of each recipe.
<div>
  <img src="/screenshots/recipes_list.png" alt="Recipe list screenshot" width="20%"/>
  <img src="/screenshots/recipe_details.png" alt="Recipe details screenshot" width="20%"/>
  <img src="/screenshots/recipe_details_scrolled.png" alt="Recipe details scrolled screenshot" width="20%"/>
</div>

Building
---------------

To build this project, use "Import Project" in Android Studio.

Architecture
---------------

The architecture is built around [Android Architecture components](https://developer.android.com/topic/libraries/architecture/).

This has been a long process as I originally built the app without following Android's app architecture guides. Therefore, I have been slowly migrating the app across. This includes using the [Navigation component](https://developer.android.com/guide/navigation) to reduce the number of activities, in favour of fragments, and generally making the code more idiomatic. I have also been transitioning to using [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)s to help seperate logic and UI related code. The app also uses [view binding](https://developer.android.com/topic/libraries/view-binding) to eliminate the risk of null pointers and also remove the need for explicitly coding types.

Firebase
---------------

I chose to use Firebase because of my familiarity with it:
* [Cloud Firestore](https://firebase.google.com/docs/firestore/) is used to store all recipe data.

I am also using the new Firebase Kotlin extension (KTX) libraries to improve the readability of Kotlin code when communicating with Firebase APIs.

