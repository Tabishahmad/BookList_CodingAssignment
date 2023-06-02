package com.example.bookapi.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.bookapi.domain.model.Book
import com.example.bookapi.presentation.core.ViewState
import com.example.bookapi.presentation.list.BookListViewModel
import kotlinx.coroutines.flow.StateFlow

@Composable
fun BookListScreen(){
    val viewModel : BookListViewModel = hiltViewModel()
    viewModel.fetchList()
    val uiStateFlow = viewModel.getviewStateFlow()
    BookList(uiStateFlow)
}
@Composable
fun BookList(uiStateFlow: StateFlow<ViewState<List<Book>>>) {
    val uiState by uiStateFlow.collectAsState()

    when (val bookListState = uiState) {
        is ViewState.Loading -> {
            // Show loading state
        }
        is ViewState.Success -> {
            val bookList = bookListState.result
            LazyVerticalGrid(columns = GridCells.Fixed(3)){
                items(bookList){book->
                    BookItem(book = book)
                }
            }
//
//            LazyVerticalGrid(
//                cells = GridCells.Fixed(3) // Adjust the number of columns as needed
//            ) {
//                BookItems(bookList)
//            }
        }
        is ViewState.Failure -> {
            val errorMessage = bookListState.failMessage
            // Show error state or handle error
        }
    }
}
@Composable
fun BookItem(book: Book) {
    Image(
        painter = rememberImagePainter(book.thumbnailUrl),
        contentDescription = book.bookTitle,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(4.dp)
            .clip(shape = RoundedCornerShape(4.dp))
    )
}
