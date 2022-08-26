package com.zemoga.postviewer.presentation.view.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.zemoga.postviewer.databinding.CustomItemPostBinding
import com.zemoga.postviewer.data.model.PostModel
import com.zemoga.postviewer.domain.frontmodel.Post

class PostsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = CustomItemPostBinding.bind(view)

    fun render(post: Post, onClickListener: (Post) -> Unit) {
        binding.tvPostTitle.text = post.title
        if (post.isFavorite)
            binding.ivFavorite.visibility = View.VISIBLE
        else
            binding.ivFavorite.visibility = View.INVISIBLE

        itemView.setOnClickListener { onClickListener(post) }
    }
}