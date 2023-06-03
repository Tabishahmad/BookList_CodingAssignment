package com.example.bookapi.data.database

import androidx.room.*
import com.example.bookapi.common.DATABASE_TABLE_NAME
import com.example.bookapi.domain.model.Book

/**
 * interface as a Data Access Object, it contains methods for
 * interacting with the underlying database using the Room library.
 */
@Dao
interface FavouriteBooksDUO {

    @Query("SELECT * FROM $DATABASE_TABLE_NAME")
    suspend fun getAllFavoriteBooks(): List<Book>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun markFavouriteBook(book: Book)

    @Delete
    suspend fun removeBookFromFavorites(book: Book)

    @Query("SELECT * FROM $DATABASE_TABLE_NAME WHERE bookHashId = :bookId")
    suspend fun getBook(bookId: String): Book
}