package com.example.wildberriesweekfive3.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wildberriesweekfive3.R
import com.example.wildberriesweekfive3.databinding.ItemFavoriteCatBinding
import com.example.wildberriesweekfive3.db.CatModelLikeDB
import com.facebook.drawee.backends.pipeline.Fresco

class LikeCatAdapter : RecyclerView.Adapter<LikeCatAdapter.LikeCatHolder>() {
    val LikeCatList = ArrayList<CatModelLikeDB>()

    class LikeCatHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = ItemFavoriteCatBinding.bind(item)


        @SuppressLint("SetTextI18n")
        fun bind(favoriteCat: CatModelLikeDB) = with(binding) {
            val controller = Fresco.newDraweeControllerBuilder()
                .setUri(favoriteCat.url)
                .build()
            binding.imageCat.controller = controller
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

    fun getLikeCatsData(favoriteCats: List<CatModelLikeDB>) {
        LikeCatList.addAll(favoriteCats)
    }


}