package com.example.bookapi.data.database

import androidx.room.*
import com.example.bookapi.common.TABLE_NAME
import com.example.bookapi.domain.model.Book
import kotlinx.coroutines.flow.Flow


@Dao
interface FavouriteBookDUO {

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAllFavouriteBook(): Flow<List<Book>>

    @Insert
    suspend fun markFavouriteBook(book: Book)

    @Delete
    suspend fun removeBookFromFavourite(book: Book)
}