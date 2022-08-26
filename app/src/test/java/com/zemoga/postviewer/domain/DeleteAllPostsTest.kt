package com.zemoga.postviewer.domain

import com.zemoga.postviewer.data.PostRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class DeleteAllPostsTest{

    @RelaxedMockK
    private lateinit var postRepository: PostRepository

    lateinit var deleteAllPosts: DeleteAllPosts

    @Before
    fun onPreExecute() {
        MockKAnnotations.init(this)
        deleteAllPosts = DeleteAllPosts(postRepository)
    }

    @Test
    fun `when called clear all posts`() = runBlocking {
        //When
        deleteAllPosts()

        //Then
        coVerify(exactly = 1) { postRepository.clearPosts() }
    }
}