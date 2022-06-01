package com.example.wildberriesweekfive3

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


class LikeCatsFragment : Fragment() {
    lateinit var binding: FragmentLikeCatsBinding
    lateinit var catsViewModel: CatsViewModel
    lateinit var catFavoriteItem: List<CatJSONItem>
    private val adapter = LikeCatAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        catsViewModel = ViewModelProvider(requireActivity()).get(CatsViewModel::class.java)
        binding = FragmentLikeCatsBinding.inflate(inflater)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        catsViewModel.getFavoriteCats()
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