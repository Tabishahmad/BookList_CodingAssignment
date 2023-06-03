package com.example.bookapi.data.repository

import com.example.bookapi.data.database.FavouriteBooksDUO
import com.example.bookapi.domain.model.Book
import com.example.bookapi.domain.repository.LocalDataBaseRepository
import javax.inject.Inject

class RemoteDataBaseRepositoryImpl @Inject constructor(private val favouriteBookDUO:FavouriteBooksDUO):LocalDataBaseRepository {
    override suspend fun setBookFavorite(book: Book) {
        favouriteBookDUO.markFavouriteBook(book)
    }

    override suspend fun removeBookFromFavorites(book: Book) {
        favouriteBookDUO.removeBookFromFavorites(book)
    }

    override suspend fun getBooksList(): List<Book> {
        return favouriteBookDUO.getAllFavoriteBooks()
    }

    override suspend fun getBook(bookId:String): Book {
        return favouriteBookDUO.getBook(bookId)
    }

}