package com.zemoga.postviewer.domain

import com.zemoga.postviewer.data.DetailsRepository
import com.zemoga.postviewer.data.PostRepository
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class DeletePostTest {

    @RelaxedMockK
    private lateinit var detailsRepository: DetailsRepository

    lateinit var deletePost: DeletePost

    @Before
    fun onPreExecute() {
        MockKAnnotations.init(this)
        deletePost = DeletePost(detailsRepository)
    }


    @Test
    fun `when called run delete post`() = runBlocking {
        //Given
        val postId = "1"

        //When
        deletePost(postId)

        //Then
        coVerify(exactly = 1) { detailsRepository.deletePost(postId) }
    }
}