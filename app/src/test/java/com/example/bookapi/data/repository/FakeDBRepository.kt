package com.example.bookapi.data.repository

import com.example.bookapi.domain.model.Book
import com.example.bookapi.domain.repository.LocalDataBaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeDBRepository : LocalDataBaseRepository {
    private val fakeBookList = mutableListOf<Book>()

    override suspend fun setBookFavorite(book: Book) {
        book.isFav = !book.isFav
    }

    override suspend fun removeBookFromFavorites(book: Book) {
        fakeBookList.remove(book)
    }

    override suspend fun getBooksList(): List<Book> {
        return fakeBookList
    }

    override suspend fun getBook(bookId: String): Book? {
        return fakeBookList.find { it.bookHashId == bookId }
    }
}