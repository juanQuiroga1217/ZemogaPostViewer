package com.zemoga.postviewer.domain

import com.zemoga.postviewer.data.PostRepository
import com.zemoga.postviewer.domain.frontmodel.Post
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetPostsTest {

    @RelaxedMockK
    private lateinit var postRepository: PostRepository

    lateinit var getPosts: GetPosts

    @Before
    fun onPreExecute() {
        MockKAnnotations.init(this)
        getPosts = GetPosts(postRepository)
    }


    @Test
    fun `if there are no posts in database get values from api`() = runBlocking {
        //Given
        coEvery { postRepository.getAllPostsFromDatabase() } returns emptyList()

        //When
        getPosts()

        //Then
        coVerify(exactly = 1) { postRepository.getAllPostsFromApi() }

    }

    @Test
    fun `if there are no posts in cache and api returns values insert them into database`() =
        runBlocking {
            //Given
            val myList = listOf(Post("Title Test", true, "1", "Body Test", "1"))
            coEvery { postRepository.getAllPostsFromDatabase() } returns emptyList()
            coEvery { postRepository.getAllPostsFromApi() } returns myList

            //When
            val response = getPosts()

            //Then
            coVerify(exactly = 1) { postRepository.clearPosts() }
            coVerify(exactly = 1) { postRepository.insertPosts(any()) }
            assert(myList == response)
        }

    @Test
    fun `if there are posts in database return database list`() = runBlocking {
        //Given
        val myList = listOf(Post("Title Test", true, "1", "Body Test", "1"))
        coEvery { postRepository.getAllPostsFromDatabase() } returns myList
        coEvery { postRepository.getAllPostsFromApi() } returns emptyList()

        //When
        val response = getPosts()

        //Then
        coVerify(exactly = 0) { postRepository.clearPosts() }
        coVerify(exactly = 0) { postRepository.insertPosts(any()) }
        assert(myList == response)
    }

    @Test
    fun `if was impossible to get posts return empty list`() = runBlocking {
        //Given
        coEvery { postRepository.getAllPostsFromDatabase() } returns emptyList()
        coEvery { postRepository.getAllPostsFromApi() } returns emptyList()

        //When
        val response = getPosts()

        //Then
        coVerify(exactly = 0) { postRepository.clearPosts() }
        coVerify(exactly = 0) { postRepository.insertPosts(any()) }
        assert(response.isEmpty())
    }

}