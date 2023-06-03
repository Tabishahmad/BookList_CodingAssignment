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
import com.example.bookapi.presentation.viewmodel.BookViewModel

@Composable
fun FavouriteBookListScreen(navController: NavController){
    Scaffold(
        topBar = { TitleActionBar()},
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                FavouriteListContent(navController)
            }
        },
        bottomBar = {}
    )
}


@Composable
private fun FavouriteListContent(navController: NavController) {
    val viewModel : BookViewModel = hiltViewModel()
    val bookList by viewModel.getAllFavouriteBooks().collectAsState(initial = emptyList())
    FavouriteBookList(bookList = bookList, navController = navController)
}
@Composable
private fun FavouriteBookList(bookList : List<Book>,navController: NavController){
    LazyVerticalGrid(columns = GridCells.Fixed(3)){
        items(bookList){book->
            FavouriteBookItem(book = book){
                openBookPreViewFavorite(book,navController)
            }
        }
    }
}
private fun openBookPreViewFavorite(book: Book,navController: NavController){
    navController.currentBackStackEntry?.savedStateHandle?.set(
        key = "book",
        value = book
    )
    navController.navigate(Screen.BookDetailPreviewScreen.route)
}
@Composable
private fun FavouriteBookItem(book: Book, onClick: () -> Unit) {
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
