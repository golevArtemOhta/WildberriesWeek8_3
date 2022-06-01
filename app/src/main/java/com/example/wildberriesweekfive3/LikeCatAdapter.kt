package com.example.wildberriesweekfive3

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wildberriesweekfive3.databinding.ItemFavoriteCatBinding

class LikeCatAdapter: RecyclerView.Adapter<LikeCatAdapter.LikeCatHolder>() {
    val LikeCatList = ArrayList<FavoriteCatJSON>()

    class LikeCatHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = ItemFavoriteCatBinding.bind(item)


        @SuppressLint("SetTextI18n")
        fun bind(favoriteCat: FavoriteCatJSON) = with(binding) {
            tvCatId.text = favoriteCat.image_id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikeCatHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favorite_cat, parent, false)
        return LikeCatHolder(view)
    }

    override fun onBindViewHolder(holder: LikeCatHolder, position: Int) {
        holder.bind(LikeCatList[position])

    }

    override fun getItemCount(): Int {
        return LikeCatList.size
    }

    fun getLikeCatsData(favoriteCats: List<FavoriteCatJSON>) {
        LikeCatList.addAll(favoriteCats)
    }



}