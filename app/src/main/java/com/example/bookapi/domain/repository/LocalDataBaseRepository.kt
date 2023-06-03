package com.example.bookapi.domain.repository

import com.example.bookapi.domain.model.Book


interface LocalDataBaseRepository {
    suspend fun setBookFavorite(book: Book)
    suspend fun removeBookFromFavorites(book: Book)
    suspend fun getBooksList(): List<Book>
    suspend fun getBook(bookId: String):Book?
}