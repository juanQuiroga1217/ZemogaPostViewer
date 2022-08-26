package com.zemoga.postviewer.presentation.view

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.zemoga.postviewer.R
import com.zemoga.postviewer.databinding.ActivityHomeBinding
import com.zemoga.postviewer.domain.frontmodel.Post
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
                binding.recyclerPosts.visibility = View.VISIBLE
                initRecyclerView(postsNewList)
            } else {
                binding.recyclerPosts.visibility = View.INVISIBLE
                showDialog()
            }
        })

        postViewModel.isLoading.observe(this, Observer {
            binding.progressBar.isVisible = it
        })

        binding.btnDeleteAll.setOnClickListener(View.OnClickListener {
            deletePosts()
        })

        setUpToolbar()

    }

    private fun setUpToolbar() {

        val toolbarTitle = binding.toolbarHome.toolbarTitle

        toolbarTitle.text = getString(R.string.title_posts)

        binding.toolbarHome.imgButton.setImageDrawable(
            AppCompatResources.getDrawable(
                this,
                R.drawable.ic_baseline_refresh_24
            )
        )
        binding.toolbarHome.imgButton.setOnClickListener(View.OnClickListener {
            postViewModel.onCreate()
        })

        val tabLayout = binding.tabLayoutHome

        binding.tabLayoutHome.addTab(tabLayout.newTab().setText(getString(R.string.title_posts)))
        binding.tabLayoutHome.addTab(tabLayout.newTab().setText(getString(R.string.title_favorites)))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tabLayout.selectedTabPosition == 0) {
                    postViewModel.listAllPosts()
                } else
                    postViewModel.listFavoritePosts()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
        showAlert()
    }

    private fun showDialog() {
        val builder = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.custom_dialog, null)
        builder.setView(view)
        val dialog = builder.create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
        dialog.findViewById<ImageButton>(R.id.btnCloseMessage)
            ?.setOnClickListener(View.OnClickListener {
                dialog.dismiss()
            })
        dialog.findViewById<Button>(R.id.btnOk)?.setOnClickListener(View.OnClickListener {
            dialog.dismiss()
        })
    }

    private fun showAlert() {
        postViewModel.alert.observe(this, Observer {
            if (it){
                val builder = AlertDialog.Builder(this)
                val view = layoutInflater.inflate(R.layout.custom_dialog, null)
                builder.setView(view)
                val dialog = builder.create()
                dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog.show()

                dialog.findViewById<ImageView>(R.id.imgMessage)?.setImageDrawable(AppCompatResources.getDrawable(
                    this,
                    R.drawable.ic_baseline_warning_24
                ))
                dialog.findViewById<ImageButton>(R.id.btnCloseMessage)
                    ?.setOnClickListener(View.OnClickListener {
                        dialog.dismiss()
                    })
                dialog.findViewById<Button>(R.id.btnOk)?.setOnClickListener(View.OnClickListener {
                    dialog.dismiss()
                })
                val textView = dialog.findViewById<TextView>(R.id.tvMessage)

                if (textView != null) {
                    textView.text = getString(R.string.check_internet_connection)
                }

            }
        })

    }

    private fun initRecyclerView(postsNewList: List<Post>) {
        val manager = LinearLayoutManager(this)
        binding.recyclerPosts.layoutManager = manager
        binding.recyclerPosts.adapter = PostsAdapter(postsNewList) { post ->
            onItemSelected(
                post
            )
        }
    }

    private fun onItemSelected(post: Post) {
        val detailsIntent = Intent(this, DetailsActivity::class.java)
        detailsIntent.putExtra("postId", post.postId)
        startActivity(detailsIntent)
        finish()
    }

    private fun deletePosts() {
        postViewModel.deleteAllPosts()
    }
}