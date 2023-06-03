package com.example.bookapi.domain.usecase

import com.example.bookapi.domain.model.Book
import com.example.bookapi.domain.model.NetworkResult
import com.example.bookapi.domain.repository.RemoteBookListRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetRemoteListUseCase @Inject constructor (
    private val repository: RemoteBookListRepository
) {
     operator fun invoke(): Flow<NetworkResult<Book>> = flow {
        emit(repository.getBookList())
    }
}