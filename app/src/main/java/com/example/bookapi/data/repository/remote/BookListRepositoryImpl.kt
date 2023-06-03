package com.example.bookapi.data.repository.remote

import android.content.Context
import com.example.bookapi.R
import com.example.bookapi.domain.repository.RemoteBookListRepository
import com.example.bookapi.domain.model.Book
import com.example.bookapi.domain.model.NetworkResult
import javax.inject.Inject

class BookListRepositoryImpl @Inject
        constructor(private val apiCall: BookDataSource,private val context: Context): RemoteBookListRepository {

    override suspend fun getBookList(): NetworkResult<Book> {
        println("getBookList(): NetworkResult<Book>")
        return try {
            val response = apiCall.downloadBookList()
            if (response.isSuccessful) {
                NetworkResult.Success(response.body()?.toBook() ?: emptyList())
            } else {
                NetworkResult.Failure(context.getString(R.string.faild_to_retrive))
            }
        } catch (e: Exception) {
            NetworkResult.Failure(context.getString(R.string.faild_to_retrive))
        }
    }
}