package com.example.bookapi.hilt

import android.content.Context
import androidx.room.Room
import com.example.bookapi.common.DATABASE_TABLE_NAME
import com.example.bookapi.data.database.FavouriteBooksDUO
import com.example.bookapi.data.database.FavoriteBooksDatabase
import com.example.bookapi.data.repository.RemoteDataBaseRepositoryImpl
import com.example.bookapi.domain.repository.LocalDataBaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun provideFavouriteBookDUO(favouriteBookDatabase: FavoriteBooksDatabase): FavouriteBooksDUO {
        return favouriteBookDatabase.favouriteBookDUO()
    }
    @Provides
    fun provideDatabaseRepo(favouriteBookDUO: FavouriteBooksDUO):LocalDataBaseRepository{
        return RemoteDataBaseRepositoryImpl(favouriteBookDUO)
    }
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): FavoriteBooksDatabase {
        return Room.databaseBuilder(
            appContext,
            FavoriteBooksDatabase::class.java,
            DATABASE_TABLE_NAME
        ).build()
    }
}