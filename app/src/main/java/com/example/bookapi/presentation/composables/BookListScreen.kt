package com.example.bookapi.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.bookapi.domain.model.Book
import com.example.bookapi.presentation.core.Screen
import com.example.bookapi.presentation.core.ViewState
import com.example.bookapi.presentation.viewmodel.BookViewModel

@Composable
fun BookListScreen(navController: NavController){
    Scaffold(
        topBar = { SingleActionActionBar(){ handleFavClick(navController) } },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                BookListContent(navController)
            }
        },
        bottomBar = {}
    )
}
private fun handleFavClick(navController: NavController){
    navController.navigate(Screen.FavouriteBookListScreen.route)
}

@Composable
fun BookListContent(navController: NavController) {
    val viewModel : BookViewModel = hiltViewModel()
    viewModel.fetchList()
    val uiState by viewModel.getviewStateFlow().collectAsState()
    when (val bookListState = uiState) {
        is ViewState.Loading -> {
            // Show loading state
        }
        is ViewState.Success -> {
            val bookList = bookListState.result
            BookList(bookList = bookList, navController = navController)
        }
        is ViewState.Failure -> {
            val errorMessage = bookListState.failMessage
            // Show error state or handle error
        }
    }
 }
@Composable
fun BookList(bookList: List<Book>,navController: NavController){
    LazyVerticalGrid(columns = GridCells.Fixed(3)){
        items(bookList){book->
            BookItem(book = book){
                val bundle = bundleOf("book" to book)
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    key = "book",
                    value = book
                )
                navController.navigate(Screen.BookDetailPreviewScreen.route)
            }
        }
    }
}
@Composable
fun BookItem(book: Book, onClick: () -> Unit) {
    Image(
        painter = rememberImagePainter(book.thumbnailUrl),
        contentDescription = book.bookTitle,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(4.dp)
            .clip(shape = RoundedCornerShape(4.dp))
            .clickable(onClick = onClick) // Add clickable modifier with the provided onClick listener
    )
}
