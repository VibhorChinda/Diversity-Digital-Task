package com.example.diversitydigitaltask.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.diversitydigitaltask.internal.Utils
import com.example.diversitydigitaltask.adapter.YoutubeAdapter
import com.example.diversitydigitaltask.model.YoutubeChannel
import com.example.diversitydigitaltask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var list: MutableList<YoutubeChannel>
    private lateinit var youtubeAdapter: YoutubeAdapter
    private lateinit var utils: Utils
    private var likedCardList: MutableSet<String>? = null
    private var dislikedCardList: MutableSet<String>? = null
    private var favCardList: MutableSet<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        utils = Utils(this)
        likedCardList = utils.retrieveLikedCardsList()
        dislikedCardList = utils.retrieveDislikedCardsList()
        favCardList = utils.retrieveFavCardsList()

        if(likedCardList == null) {
            likedCardList = mutableSetOf()
        }

        if(dislikedCardList == null) {
            dislikedCardList = mutableSetOf()
        }

        if(favCardList == null) {
            favCardList = mutableSetOf()
        }

        formRecyclerViewBody()
        setupRecyclerView()
    }

    private fun formRecyclerViewBody() {
        val c1 = YoutubeChannel(1, "Phillip Lackner", "This channel will help beginners to learn the fundamentals of Android Development, but on the other hand I will also upload more advanced stuff", "https://youtu.be/asuOWE5KuFM")
        val c2 = YoutubeChannel(2, "The Viral Fever", "It's not on TV, it's on TVF. Subscribe to The Viral Fever for your weekly dose of humour & drama. TVF has created young India's favourite digital entertainment, catering to their changing tastes and trends", "https://youtu.be/xcUHB9n8Kws")
        val c3 = YoutubeChannel(3, "Travelling Desi", "I am Traveling Desi, an average Indian budget travel who has travelled to more than 25 countries so far including USA, UK, Canada and about 20 in Europe alone and with all my travel experience in all range of budgets, I bring you my experience on this channel to share the information I gathered over years. ", "https://youtu.be/yOCmA0zhc38")
        val c4 = YoutubeChannel(4, "Filtercopy", "Freshly brewed videos from the sharable content platform of Pocket Aces. We make awesome short videos that you can relate to! Sister channels include: Dice Media and Gobble.", "https://youtu.be/FXefFtmA88c")
        list = mutableListOf()
        list.add(c1)
        list.add(c2)
        list.add(c3)
        list.add(c4)
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            binding.recyclerView.setHasFixedSize(true)
            youtubeAdapter = YoutubeAdapter(list, likedCardList!!.toMutableList(), favCardList!!.toMutableList(), dislikedCardList!!.toMutableList(), utils)
            adapter = youtubeAdapter
            layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        }
    }
}