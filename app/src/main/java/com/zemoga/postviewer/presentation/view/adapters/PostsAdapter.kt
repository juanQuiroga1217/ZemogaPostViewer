package com.zemoga.postviewer.presentation.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zemoga.postviewer.R
import com.zemoga.postviewer.data.model.PostModel

class PostsAdapter(
    private val postsList: List<PostModel>,
    private val onClickListener: (PostModel) -> Unit
) : RecyclerView.Adapter<PostsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PostsViewHolder(layoutInflater.inflate(R.layout.custom_item_post, parent, false))
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {

        val postItem = postsList[position]
        holder.render(postItem, onClickListener)

    }

    override fun getItemCount(): Int = postsList.size


}