package com.example.bookapi.data.repository

import android.content.Context
import com.example.bookapi.R
import com.example.bookapi.data.repository.remote.BookDataSource
import com.example.bookapi.data.repository.remote.BookListRepositoryImpl
import com.example.bookapi.domain.model.Book
import com.example.bookapi.domain.model.NetworkResult
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Response

class BookListRepositoryImplTest {

    @Mock
    private lateinit var apiCall: BookDataSource

    @Mock
    private lateinit var context: Context

    private lateinit var bookListRepositoryImpl: BookListRepositoryImpl

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        this.bookListRepositoryImpl = BookListRepositoryImpl(this.apiCall, this.context)
    }


    /**Should return a list of books when the API call is successful*/
    @Test
    fun getBookListWhenApiCallIsSuccessful() {
        val book = Book("123", "Test Book", "Test Author")
        val bookList = listOf(book)
        val responseBody = ResponseBody.create(MediaType.parse("application/json"), "")
        val response = Response.success(bookList, responseBody)

        whenever(apiCall.downloadBookList()).thenReturn(response)
        whenever(context.getString(R.string.faild_to_retrive)).thenReturn("Failed to retrieve")

        val expected = NetworkResult.Success(bookList)
        val actual = bookListRepositoryImpl.getBookList()

        assertEquals(expected, actual)
    }

    /**Should return a failure with a specific message when the API call is unsuccessful*/
    @Test
    fun getBookListWhenApiCallIsUnsuccessful() {
        val errorResponse = Response.error<Book>(
            404,
            ResponseBody.create(MediaType.parse("application/json"), "")
        )

        whenever(apiCall.downloadBookList()).thenReturn(errorResponse)
        whenever(context.getString(R.string.faild_to_retrive)).thenReturn("Failed to retrieve book list")

        val result = bookListRepositoryImpl.getBookList()

        assertTrue(result is NetworkResult.Failure)
        assertEquals("Failed to retrieve book list", (result as NetworkResult.Failure).message)
    }

}