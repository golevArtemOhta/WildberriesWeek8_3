package com.example.wildberriesweekfive3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wildberriesweekfive3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            openFragment()
        }
    }

    private fun openFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment, CatsFragment.newInstance())
            .commit()
    }
}