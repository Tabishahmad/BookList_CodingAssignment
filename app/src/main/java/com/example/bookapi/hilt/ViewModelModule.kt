package com.example.bookapi.hilt

import android.content.Context
import com.example.bookapi.data.repository.remote.BookListRepositoryImpl
import com.example.bookapi.data.repository.remote.BookDataSource
import com.example.bookapi.domain.repository.RemoteBookListRepository
import com.example.bookapi.domain.repository.LocalDataBaseRepository
import com.example.bookapi.domain.usecase.UseCase
import com.example.bookapi.domain.usecase.GetRemoteListUseCase
import com.example.bookapi.domain.usecase.ManageBookFavoriteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {

    @Provides
    fun provideIListRepository(dataSource: BookDataSource,context: Context): RemoteBookListRepository {
        return BookListRepositoryImpl(dataSource,context)
    }
    @Provides
    fun provideBookUseCase(bookListRepository: RemoteBookListRepository,
                           dbRepository: LocalDataBaseRepository):UseCase{
        return UseCase(GetRemoteListUseCase(bookListRepository),
            ManageBookFavoriteUseCase(dbRepository))
    }
}