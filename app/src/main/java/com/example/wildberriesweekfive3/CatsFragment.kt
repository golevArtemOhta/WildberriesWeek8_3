package com.example.wildberriesweekfive3

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.wildberriesweekfive3.databinding.FragmentCatsBinding
import com.facebook.drawee.backends.pipeline.Fresco



class CatsFragment : Fragment() {
    lateinit var binding: FragmentCatsBinding
    lateinit var catsViewModel: CatsViewModel
    lateinit var catItem: List<CatJSONItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        catsViewModel = ViewModelProvider(requireActivity()).get(CatsViewModel::class.java)
        binding = FragmentCatsBinding.inflate(inflater)
        return binding.root


    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        catsViewModel.request()

        with(binding){
            imageButtonLike.setOnClickListener {
                catsViewModel.like(catItem[0].id.toString())
                catsViewModel.request()

            }

            buttonOpenFavoriteCats.setOnClickListener {
                openFragment()
            }
        }


    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onStart() {
        super.onStart()
        catsViewModel.itemCat.observe(activity as LifecycleOwner, Observer {
            catItem = it
            val controller = Fresco.newDraweeControllerBuilder()
                .setUri(catItem[0].url)
                .build()
            binding.imageViewCat.controller = controller

        })

    }

    companion object {
        @JvmStatic
        fun newInstance() = CatsFragment()
    }

    private fun openFragment() {
        activity?.setTitle("LIKE CATS")
        val likeCatsFragmentCreate = LikeCatsFragment.newInstance()

        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.fragment, likeCatsFragmentCreate)
        transaction?.disallowAddToBackStack()
        transaction?.commit()

    }

}