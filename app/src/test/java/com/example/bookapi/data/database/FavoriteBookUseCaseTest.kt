package com.example.bookapi.data.database


import com.example.bookapi.data.repository.FakeDBRepository
import com.example.bookapi.domain.model.Book
import com.example.bookapi.domain.usecase.ManageBookFavoriteUseCase
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class FavoriteBookUseCaseTest {

    @Mock
    private lateinit var favouriteBookDatabase: FavoriteBooksDatabase

    lateinit var fakeDBRepository : FakeDBRepository
    @Mock
    lateinit var favouriteBookDUO : FavouriteBooksDUO
    @Mock
    lateinit var favoriteBookUseCase: ManageBookFavoriteUseCase

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        fakeDBRepository = FakeDBRepository()
        favoriteBookUseCase = ManageBookFavoriteUseCase(fakeDBRepository)
    }
    @Test
    fun `removeBookFromFavorites should call removeBookFromFavorites on favouriteBookDUO`() = runBlockingTest {
        // Given
        val book = Book("abcd","This is a book","www.img.com",false)

        // When
        favoriteBookUseCase.removeBookFromFavorites(book)

        // Then
        Mockito.verify(favouriteBookDUO).removeBookFromFavorites(book)
    }

    @After
    fun closeDatabase() {
        favouriteBookDatabase.close()
    }
}