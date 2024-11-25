package com.paging.data

import androidx.paging.PagingSource
import com.paging.data.ArticlePagingSource

/**
 * Repository class that mimics fetching [Article] instances from an asynchronous source.
 */
class ArticleRepository {
    /**
     * [PagingSource] for [Article]
     */
    fun articlePagingSource() = ArticlePagingSource()
}
