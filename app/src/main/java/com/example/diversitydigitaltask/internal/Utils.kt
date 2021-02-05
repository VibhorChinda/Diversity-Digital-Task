package com.example.diversitydigitaltask.internal

import android.content.Context
import android.content.SharedPreferences

class Utils(
    context: Context
) {
    private var sharedPreferences: SharedPreferences = context.getSharedPreferences(
        "Data",
        Context.MODE_PRIVATE
    )
    private var editor: SharedPreferences.Editor

    init {
        editor = sharedPreferences.edit()
    }

    companion object {
        const val SHARED_PREFERENCE_FAV_CARDS = "favCards"
        const val SHARED_PREFERENCE_LIKED_CARDS = "likedCards"
        const val SHARED_PREFERENCE_DISLIKED_CARDS = "dislikedCards"
    }

    fun saveLikedCards(likedCardsList: MutableList<String>) = editor.putStringSet(
        SHARED_PREFERENCE_LIKED_CARDS, likedCardsList.toSet()).commit()

    fun saveDislikedCards(dislikedCardsList: MutableList<String>) = editor.putStringSet(
        SHARED_PREFERENCE_DISLIKED_CARDS, dislikedCardsList.toSet()).commit()

    fun saveFavCards(favList: MutableList<String>) = editor.putStringSet(SHARED_PREFERENCE_FAV_CARDS, favList.toSet()).commit()

    fun retrieveLikedCardsList(): MutableSet<String>? = sharedPreferences.getStringSet(
        SHARED_PREFERENCE_LIKED_CARDS, null)

    fun retrieveDislikedCardsList(): MutableSet<String>? = sharedPreferences.getStringSet(
        SHARED_PREFERENCE_DISLIKED_CARDS, null)

    fun retrieveFavCardsList(): MutableSet<String>? = sharedPreferences.getStringSet(
        SHARED_PREFERENCE_FAV_CARDS, null)
}
