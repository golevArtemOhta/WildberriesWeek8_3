package com.example.wildberriesweekfive3.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wildberriesweekfive3.databinding.FragmentLikeCatsBinding
import com.example.wildberriesweekfive3.db.repository.CatDBRepository
import com.example.wildberriesweekfive3.db.CatDateBase
import com.example.wildberriesweekfive3.db.CatModelLikeDB
import com.example.wildberriesweekfive3.db.CatViewModelFactrory


class LikeCatsFragment : Fragment() {
    lateinit var binding: FragmentLikeCatsBinding
    lateinit var catsViewModel: CatsViewModel
    lateinit var catFavoriteItem: List<CatModelLikeDB>
    private val adapter = LikeCatAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dao = CatDateBase.getDatabase(requireActivity().applicationContext).catDao()
        val roomRepository = CatDBRepository(dao)
        val viewModelFactory = CatViewModelFactrory(roomRepository)
        catsViewModel = ViewModelProvider(this, viewModelFactory)[CatsViewModel::class.java]
        binding = FragmentLikeCatsBinding.inflate(inflater)
        return binding.root
    }



    @SuppressLint("NotifyDataSetChanged")
    override fun onStart() {
        super.onStart()
        catsViewModel.itemFavoriteCatJSON.observe(activity as LifecycleOwner, Observer {
            catFavoriteItem = it
            adapter.getLikeCatsData(catFavoriteItem)
            adapter.notifyDataSetChanged()

        })

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.rvFavoriteCats.layoutManager = LinearLayoutManager(context)
        binding.rvFavoriteCats.adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance() = LikeCatsFragment()
    }
}