package com.example.bookapi.presentation.viewmodel

import android.content.Context
import com.example.bookapi.R
import com.example.bookapi.domain.model.Book
import com.example.bookapi.domain.model.NetworkResult
import com.example.bookapi.domain.usecase.UseCase
import com.example.bookapi.presentation.core.ViewState
import com.example.bookapi.presentation.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

@HiltViewModel
class BookViewModel @Inject constructor(private val useCase: UseCase, private val context: Context) :
    BaseViewModel() {

    private val uiStateFlow = MutableStateFlow<ViewState<List<Book>>>(ViewState.Loading(true))
    fun getviewStateFlow(): StateFlow<ViewState<List<Book>>> = uiStateFlow

    fun fetchList() {
        performCoroutineTask{
            useCase.getListUseCase().collect { result ->
                uiStateFlow.emit(when (result) {
                    is NetworkResult.Success -> ViewState.Success(result.data)
                    is NetworkResult.Failure -> ViewState.Failure(context.getString(R.string.faild_to_retrive))
                })
            }
        }
    }



    fun handleFavoriteBook(book: Book) {
        performCoroutineTask {
            val isCurrentlyFav = book.isFav
            book.isFav = !isCurrentlyFav
            if (isCurrentlyFav) {
                useCase.manageBookUseCase.removeBookFromFavorites(book)
            } else {
                useCase.manageBookUseCase.setBookFavorite(book)
            }
        }
    }

    suspend fun getAllFavouriteBooks(): List<Book> {
        return useCase.manageBookUseCase.getFavouriteBooksList()
    }

    fun isFavoriteBook(book: Book, callback: (Boolean) -> Unit) {
        performCoroutineTask {
            var result = useCase.manageBookUseCase.isFavoriteBook(book)
            callback(result)
        }
    }
}