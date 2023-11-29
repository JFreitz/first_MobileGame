package com.firstapp.samplecodesliding

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager2: ViewPager2
    private lateinit var imageList: ArrayList<Int>
    private lateinit var adapter: ImageAdapter

    private var lastPage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        init()
        setUpTransformer()
        setUpPageChangeListener()
        setUpImageClickListeners()
    }

    private fun setUpTransformer() {
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.14f
        }

        viewPager2.setPageTransformer(transformer)
    }

    private fun init() {
        viewPager2 = findViewById(R.id.viewPager2)
        imageList = ArrayList()

        imageList.add(R.drawable.bedroom)
        imageList.add(R.drawable.bathroom)
        imageList.add(R.drawable.classroom)
        imageList.add(R.drawable.kitchen)
        imageList.add(R.drawable.library)
        imageList.add(R.drawable.living_room)
        imageList.add(R.drawable.office)
        imageList.add(R.drawable.playground)

        adapter = ImageAdapter(imageList, viewPager2)

        viewPager2.adapter = adapter
        viewPager2.offscreenPageLimit = 1
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
    }

    private fun setUpPageChangeListener() {
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                lastPage = position
            }
        })
    }

    private fun setUpImageClickListeners() {
        adapter.setOnItemClickListener(object : ImageAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                when (position) {
                    0 -> {
                        // Handle click for bedroom
                        // Add your code here
                        showToast("Bedroom Clicked")
                    }
                    1 -> {
                        // Handle click for bathroom
                        // Add your code here
                        showToast("Bathroom Clicked")
                    }
                    2 -> {
                        // Handle click for classroom
                        // Add your code here
                        showToast("Classroom Clicked")
                    }
                    3 -> {
                        // Handle click for kitchen
                        // Add your code here
                        showToast("Kitchen Clicked")
                    }
                    4 -> {
                        // Handle click for library
                        // Add your code here
                        showToast("Library Clicked")
                    }
                    5 -> {
                        // Handle click for living room
                        // Add your code here
                        showToast("Living Room Clicked")
                    }
                    6 -> {
                        // Handle click for office
                        // Add your code here
                        showToast("Office Clicked")
                    }
                    7 -> {
                        // Handle click for playground
                        // Add your code here
                        showToast("Playground Clicked")
                    }
                }
            }
        })
    }


    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        viewPager2.isUserInputEnabled = true
    }

    override fun onBackPressed() {
        if (lastPage == imageList.size - 1) {
            viewPager2.isUserInputEnabled = false
        } else {
            super.onBackPressed()
        }
    }
}
