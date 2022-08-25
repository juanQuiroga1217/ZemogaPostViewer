package com.zemoga.postviewer.presentation.view.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.zemoga.postviewer.databinding.CustomItemPostBinding
import com.zemoga.postviewer.data.model.PostModel

class PostsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = CustomItemPostBinding.bind(view)

    fun render(post: PostModel, onClickListener: (PostModel) -> Unit) {
        binding.tvPostTitle.text = post.postTitle
        if (post.isPostFavorite)
            binding.ivFavorite.visibility = View.VISIBLE

        itemView.setOnClickListener { onClickListener(post) }
    }
}