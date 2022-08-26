package com.zemoga.postviewer.presentation.view.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.zemoga.postviewer.data.model.ComentsModel
import com.zemoga.postviewer.databinding.CustomItemCommentsBinding

class CommentsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = CustomItemCommentsBinding.bind(view)

    fun render(comment: ComentsModel, onClickListener: (ComentsModel) -> Unit) {
        binding.tvUserComment.text = comment.commentEmail
        binding.tvCommentBody.text = comment.commentBody

        itemView.setOnClickListener { onClickListener(comment) }
    }
}