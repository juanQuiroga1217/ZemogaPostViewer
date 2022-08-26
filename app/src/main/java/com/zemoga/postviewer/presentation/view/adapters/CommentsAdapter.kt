package com.zemoga.postviewer.presentation.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zemoga.postviewer.R
import com.zemoga.postviewer.data.model.ComentsModel

class CommentsAdapter(
    private val commentsList: List<ComentsModel>,
    private val onClickListener: (ComentsModel) -> Unit
) : RecyclerView.Adapter<CommentsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CommentsViewHolder(layoutInflater.inflate(R.layout.custom_item_comments, parent, false))
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        val commentItem = commentsList[position]
        holder.render(commentItem, onClickListener)
    }

    override fun getItemCount(): Int = commentsList.size



}