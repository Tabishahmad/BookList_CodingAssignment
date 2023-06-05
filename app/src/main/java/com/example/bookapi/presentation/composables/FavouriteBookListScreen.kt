package com.example.bookapi.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.bookapi.domain.model.Book
import com.example.bookapi.presentation.core.Screen
import com.example.bookapi.presentation.viewmodel.BookViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

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
    val viewModel: BookViewModel = hiltViewModel()
    val bookListState: MutableState<List<Book>> = rememberSaveable{ mutableStateOf(emptyList()) }

    LaunchedEffect(Unit) {
        val books = withContext(Dispatchers.IO) {
            viewModel.getAllFavouriteBooks()
        }
        bookListState.value = books
    }

    val bookList: List<Book> = bookListState.value
    FavouriteBookList(bookList = bookList, navController = navController)
}


@Composable
private fun FavouriteBookList(bookList : List<Book>,navController: NavController){
    if (bookList.isEmpty()) {
        EmptyContentMessage(text = "No favorite books")
    } else {
        LazyVerticalGrid(columns = GridCells.Fixed(3)) {
            items(bookList) { book ->
                FavouriteBookItem(book = book) {
                    openBookPreViewFavorite(book, navController)
                }
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
@Composable
private fun EmptyContentMessage(text: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.Red,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}