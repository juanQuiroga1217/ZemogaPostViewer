package com.zemoga.postviewer.presentation.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.zemoga.postviewer.databinding.ActivityHomeBinding
import com.zemoga.postviewer.data.model.PostModel
import com.zemoga.postviewer.presentation.view.adapters.PostsAdapter
import com.zemoga.postviewer.presentation.viewmodel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private val postViewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        postViewModel.onCreate()

        postViewModel.postModel.observe(this, Observer { postsNewList ->
            if (!postsNewList.isNullOrEmpty()) {
                initRecyclerView(postsNewList)
            }
        })

        postViewModel.isLoading.observe(this, Observer {
            binding.progressBar.isVisible = it
        })

        val tabLayout = binding.tabLayoutHome

        binding.tabLayoutHome.addTab(tabLayout.newTab().setText("Posts"))
        binding.tabLayoutHome.addTab(tabLayout.newTab().setText("Favorites"))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tabLayout.selectedTabPosition == 0)
                    postViewModel.listAllPosts()
                else
                    postViewModel.listFavoritePosts()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

    }

    private fun initRecyclerView(postsNewList: List<PostModel>) {
        val manager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this, manager.orientation)
        binding.recyclerPosts.layoutManager = manager
        binding.recyclerPosts.adapter = PostsAdapter(postsNewList) { post ->
            onItemSelected(
                post
            )
        }
        binding.recyclerPosts.addItemDecoration(decoration)
    }

    private fun updateRecyclerView(postNewList: List<PostModel>) {
        binding.recyclerPosts.adapter = PostsAdapter(postNewList) { post ->
            onItemSelected(
                post
            )
        }
    }

    private fun onItemSelected(post: PostModel) {
        Toast.makeText(this, post.postId, Toast.LENGTH_SHORT).show()
    }

}