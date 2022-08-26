package com.zemoga.postviewer.presentation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.zemoga.postviewer.R
import com.zemoga.postviewer.data.model.ComentsModel
import com.zemoga.postviewer.data.model.DetailsModel
import com.zemoga.postviewer.databinding.ActivityDetailsBinding
import com.zemoga.postviewer.domain.frontmodel.Post
import com.zemoga.postviewer.presentation.view.adapters.CommentsAdapter
import com.zemoga.postviewer.presentation.view.adapters.PostsAdapter
import com.zemoga.postviewer.presentation.viewmodel.DetailsViewModel
import com.zemoga.postviewer.presentation.viewmodel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    private val detailsViewModel: DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailsViewModel.onCreateDetails(intent.getStringExtra("postId"))

        detailsViewModel.detailsModel.observe(this, Observer { currentPost ->

            if (currentPost != null) {
                binding.tvPostBody.text = currentPost.post.postBody
                binding.toolbarDetails.toolbarTitle.text = currentPost.post.postId
                binding.tvUserEmail.text = currentPost.user!!.userEmail
                binding.tvUserName.text = currentPost.user.userName
                binding.tvUserPhone.text = currentPost.user.userPhone
                binding.tvUserWebsite.text = currentPost.user.userWebsite


                setUpToolbar(currentPost)
                listComments(currentPost)
            }
        })

        detailsViewModel.isLoading.observe(this, Observer {
            binding.progressBarDetails.isVisible = it
        })




    }

    private fun listComments(currentPost: DetailsModel) {
        val manager = LinearLayoutManager(this)
        binding.listComments.layoutManager = manager
        binding.listComments.adapter = CommentsAdapter(currentPost.comments) { comment ->
            onItemSelected(comment)
        }
    }

    private fun onItemSelected(comment: ComentsModel) {
        Toast.makeText(this, comment.commentName, Toast.LENGTH_SHORT)
    }


    private fun setUpToolbar(currentPost: DetailsModel) {

        val toolbarTitle = getString(R.string.title_posts) + currentPost.post.postId

        binding.toolbarDetails.toolbarTitle.text = toolbarTitle

        if (currentPost.post.isPostFavorite) {
            binding.toolbarDetails.imgButton.setImageDrawable(
                AppCompatResources.getDrawable(
                    this,
                    R.drawable.ic_favorite
                )
            )
        } else {
            binding.toolbarDetails.imgButton.setImageDrawable(
                AppCompatResources.getDrawable(
                    this,
                    R.drawable.ic_baseline_favorite_border_24
                )
            )
        }
        binding.toolbarDetails.imgBackButton.visibility = View.VISIBLE
        binding.toolbarDetails.imgBackButton.setOnClickListener(View.OnClickListener {
            onBackPressed()
        })
        binding.toolbarDetails.imgButton.setOnClickListener(View.OnClickListener {
            detailsViewModel.toggleFavorite(currentPost)
        })
        binding.toolbarDetails.imgButtonDelete.visibility = View.VISIBLE
        binding.toolbarDetails.imgButtonDelete.setOnClickListener(View.OnClickListener {
            detailsViewModel.deletePost(currentPost.post.postId)
            onBackPressed()
        })

    }

    override fun onBackPressed() {
        super.onBackPressed()
        val returnIntent = Intent(this, HomeActivity::class.java)
        startActivity(returnIntent)
        finish()
    }


}