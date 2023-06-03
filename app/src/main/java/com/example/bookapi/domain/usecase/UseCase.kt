package com.example.bookapi.domain.usecase

data class UseCase(val getListUseCase: GetRemoteListUseCase,
                   val manageBookUseCase: ManageBookFavoriteUseCase) {
}