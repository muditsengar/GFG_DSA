package com.paging.ui

import androidx.recyclerview.widget.RecyclerView
import com.ms.gfg_dsa.databinding.ArticleViewholderBinding
import com.paging.data.Article
import com.paging.data.createdText

/**
 * View Holder for a [Article] RecyclerView list item.
 */
class ArticleViewHolder(
    private val binding: ArticleViewholderBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(article: Article) {
        binding.apply {
            binding.title.text = article.title
            binding.description.text = article.description
            binding.created.text = article.createdText
        }
    }
}
