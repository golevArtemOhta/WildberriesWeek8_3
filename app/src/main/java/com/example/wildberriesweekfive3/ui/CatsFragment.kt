package com.example.wildberriesweekfive3.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.wildberriesweekfive3.*
import com.example.wildberriesweekfive3.data.CatJSONItem
import com.example.wildberriesweekfive3.databinding.FragmentCatsBinding
import com.example.wildberriesweekfive3.db.repository.CatDBRepository
import com.example.wildberriesweekfive3.db.CatDateBase
import com.example.wildberriesweekfive3.db.CatViewModelFactrory
import com.facebook.drawee.backends.pipeline.Fresco


class CatsFragment : Fragment() {
    lateinit var binding: FragmentCatsBinding
    lateinit var catsViewModel: CatsViewModel
    lateinit var catItem: List<CatJSONItem>

    lateinit var navHostFragment: NavHostFragment
    lateinit var navController: NavController


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
        binding = FragmentCatsBinding.inflate(inflater)
        return binding.root


    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            imageButtonLike.setOnClickListener {
                catsViewModel.like(catItem[0])
                catsViewModel.request()
            }

            imageButtonDisLike.setOnClickListener {
                Toast.makeText(activity, "Не бывает плохих котиков!", Toast.LENGTH_LONG).show()
            }

            buttonOpenFavoriteCats.setOnClickListener {
                it.findNavController().navigate(R.id.likeCatsFragment)
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