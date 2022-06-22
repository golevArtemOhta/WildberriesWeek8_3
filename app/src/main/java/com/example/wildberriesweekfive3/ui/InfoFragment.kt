package com.example.wildberriesweekfive3.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.wildberriesweekfive3.databinding.FragmentInfoBinding
import android.content.Intent
import android.net.Uri


class InfoFragment : Fragment() {
    lateinit var binding: FragmentInfoBinding
    val uri: Uri = Uri.parse("https://github.com/golevArtemOhta") // missing 'http://' will cause crashed

    val intent = Intent(Intent.ACTION_VIEW, uri)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.textViewGitHub.setOnClickListener {
            startActivity(intent)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = InfoFragment()
    }
}