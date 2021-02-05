package com.example.diversitydigitaltask.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.diversitydigitaltask.R
import com.example.diversitydigitaltask.model.YoutubeChannel
import com.example.diversitydigitaltask.internal.Utils
import kotlinx.android.synthetic.main.card_list_item.view.*


class YoutubeAdapter(
    var list: MutableList<YoutubeChannel>,
    var likedCardsList: MutableList<String>,
    var favCardsList: MutableList<String>,
    var dislikedCardsList: MutableList<String>,
    var utils: Utils
): RecyclerView.Adapter<YoutubeAdapter.YoutubeViewHolder>() {

    private lateinit var context: Context
    inner class YoutubeViewHolder(itemview: View): RecyclerView.ViewHolder(itemview) {
        var photo: ImageView = itemview.video_picture
        var name: TextView = itemview.video_name
        var description: TextView = itemview.video_description
        var fav: ImageView = itemview.favourite
        var fav_pressed: ImageView = itemview.favourite_pressed
        var dislike: ImageView = itemview.dislike
        var dislike_pressed: ImageView = itemview.dislike_pressed
        var like: ImageView = itemview.like
        var like_pressed: ImageView = itemview.like_pressed
        var play: ImageView = itemview.play
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YoutubeViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.card_list_item, parent, false)
        context = parent.context
        view.minimumWidth = parent.width/2
        return YoutubeViewHolder(view)
    }

    override fun onBindViewHolder(holder: YoutubeViewHolder, position: Int) {
        when(list[position].id) {
            1 -> holder.photo.setImageResource(R.drawable.phillip_lackner)
            2 -> holder.photo.setImageResource(R.drawable.tvf)
            3 -> holder.photo.setImageResource(R.drawable.travelling_desi)
            4 -> holder.photo.setImageResource(R.drawable.filter_copy)
        }

        holder.name.text = list[position].name
        holder.description.text = list[position].description

        if (favCardsList.contains(list[position].name)) {
            holder.fav_pressed.visibility = View.VISIBLE
            holder.fav.visibility = View.GONE
        } else {
            holder.fav_pressed.visibility = View.GONE
            holder.fav.visibility = View.VISIBLE
        }


        if (likedCardsList.contains(list[position].name)) {
            holder.like_pressed.visibility = View.VISIBLE
            holder.like.visibility = View.GONE
        } else {
            holder.like_pressed.visibility = View.GONE
            holder.like.visibility = View.VISIBLE
        }


        if (dislikedCardsList.contains(list[position].name)) {
            holder.dislike_pressed.visibility = View.VISIBLE
            holder.dislike.visibility = View.GONE
        } else {
            holder.dislike_pressed.visibility = View.GONE
            holder.dislike.visibility = View.VISIBLE
        }

        holder.play.setOnClickListener(View.OnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(list[position].link))
            context.startActivity(intent)
        })

        holder.like.setOnClickListener(View.OnClickListener {
            holder.like_pressed.visibility = View.VISIBLE
            holder.like.visibility = View.GONE
            holder.dislike_pressed.visibility = View.GONE
            holder.dislike.visibility = View.VISIBLE
            likedCardsList.add(list[position].name)
            dislikedCardsList.remove(list[position].name)
            utils.saveLikedCards(likedCardsList)
            utils.saveDislikedCards(dislikedCardsList)
        })

        holder.like_pressed.setOnClickListener(View.OnClickListener {
            holder.like_pressed.visibility = View.GONE
            holder.like.visibility = View.VISIBLE
            likedCardsList.remove(list[position].name)
            utils.saveLikedCards(likedCardsList)
        })

        holder.dislike.setOnClickListener(View.OnClickListener {
            holder.dislike_pressed.visibility = View.VISIBLE
            holder.dislike.visibility = View.GONE
            holder.like_pressed.visibility = View.GONE
            holder.like.visibility = View.VISIBLE
            dislikedCardsList.add(list[position].name)
            likedCardsList.remove(list[position].name)
            utils.saveDislikedCards(dislikedCardsList)
            utils.saveLikedCards(likedCardsList)
        })

        holder.dislike_pressed.setOnClickListener(View.OnClickListener {
            holder.dislike_pressed.visibility = View.GONE
            holder.dislike.visibility = View.VISIBLE
            dislikedCardsList.remove(list[position].name)
            utils.saveDislikedCards(dislikedCardsList)
        })

        holder.fav.setOnClickListener(View.OnClickListener {
            holder.fav_pressed.visibility = View.VISIBLE
            holder.fav.visibility = View.GONE
            favCardsList.add(list[position].name)
            utils.saveFavCards(favCardsList)
        })

        holder.fav_pressed.setOnClickListener(View.OnClickListener {
            holder.fav_pressed.visibility = View.GONE
            holder.fav.visibility = View.VISIBLE
            favCardsList.remove(list[position].name)
            utils.saveFavCards(favCardsList)
        })
    }

    override fun getItemCount(): Int {
        return list.size
    }
}