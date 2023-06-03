package com.example.bookapi.domain.usecase

import com.example.bookapi.domain.model.Book
import com.example.bookapi.domain.repository.LocalDataBaseRepository
import javax.inject.Inject

class ManageBookFavoriteUseCase @Inject constructor(val dbRepository: LocalDataBaseRepository) {
    suspend fun setBookFavorite(book: Book){
        dbRepository.setBookFavorite(book)
    }
    suspend fun removeBookFromFavorites(book: Book){
        dbRepository.removeBookFromFavorites(book)
    }
    suspend fun isFavoriteBook(book: Book): Boolean {
        return dbRepository.getBook(book.bookHashId) != null
    }
    suspend fun getBooksList(): List<Book> {
        return dbRepository.getBooksList()
    }
}