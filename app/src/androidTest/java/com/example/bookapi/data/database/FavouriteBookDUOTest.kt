package com.example.bookapi.data.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import com.example.TestDispatcherRule
import com.example.bookapi.domain.model.Book
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class FavouriteBookDUOTest : TestCase() {




    private lateinit var favouriteBookDUO: FavouriteBooksDUO
    private lateinit var favouriteBookDatabase: FavoriteBooksDatabase

    @get: Rule
    val dispatcherRule = TestDispatcherRule()

    @Before
    public override fun setUp() {

        val context = ApplicationProvider.getApplicationContext<Context>()
        // initialize the db and dao variable
        favouriteBookDatabase = Room.inMemoryDatabaseBuilder(context,FavoriteBooksDatabase::class.java).build()
        favouriteBookDUO = favouriteBookDatabase.favouriteBookDUO()
    }

    @Test
    fun addItem_shouldReturn_theItem_inFlow() = runTest(){
        val item1 = Book("A1B2","Test Book","http://www.example.com")
        val item2 = Book("A2B3","Test Book 2","http://www.example.com")
        favouriteBookDUO.markFavouriteBook(item1)
        favouriteBookDUO.markFavouriteBook(item2)

        favouriteBookDUO.getAllFavoriteBooks().test {
            val list = awaitItem()
            assert(list.contains(item1))
            assert(list.contains(item2))
            cancel()
        }
    }
    @Test
    fun deletedItem_shouldNot_be_present_inFlow() = runTest() {
        val item1 = Book("A1B2","Test Book","http://www.example.com")
        val item2 = Book("A2B3","Test Book 2","http://www.example.com")
        favouriteBookDUO.markFavouriteBook(item1)
        favouriteBookDUO.markFavouriteBook(item2)
        favouriteBookDUO.removeBookFromFavorites(item2)

        favouriteBookDUO.getAllFavoriteBooks().test {
            val list = awaitItem()
            assert(list.size == 1)
            assert(list.contains(item1))
            cancel()
        }
    }

    @Test
    fun updateItem_shouldReturn_theItem_inFlow() = runTest {
        val item1 = Book("A1B2","Test Book","http://www.example.com")
        val item2 = Book("A2B3","Test Book 2","http://www.example.com")
        val item3 = Book("A2B3","Test Book 3","http://www.example.com")

        favouriteBookDUO.markFavouriteBook(item1)
        favouriteBookDUO.markFavouriteBook(item2)
        favouriteBookDUO.markFavouriteBook(item3)

        favouriteBookDUO.getAllFavoriteBooks().test {
            val list = awaitItem()
            assert(list.size == 2)
            assert(list.contains(item3))
            cancel()
        }
    }

    @After
    public fun closeDatabase() {
        favouriteBookDatabase.close()
    }
}