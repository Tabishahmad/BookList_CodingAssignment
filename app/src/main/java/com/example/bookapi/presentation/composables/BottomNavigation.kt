package com.example.bookapi.presentation.composables

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bookapi.R
import com.example.bookapi.domain.model.Book
import com.example.bookapi.presentation.viewmodel.BookViewModel


@Composable
fun BottomNavigation(book: Book) {
    val viewModel : BookViewModel = hiltViewModel()
    var isFavorite by remember { mutableStateOf(book.isFav) }
    LaunchedEffect(Unit) {
        viewModel.isFavoriteBook(book) { isFav ->
            isFavorite = isFav
        }
    }

    BottomNavigation(
        backgroundColor = colorResource(id = R.color.purple_200),
        elevation = 3.dp){
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_share),
                    contentDescription = "share"
                )
            },
            label = { Text("Share") },
            selected = true,
            onClick = { }
        )
        BottomNavigationItem(
            icon = {
                val iconResource = if (isFavorite) {
                    R.drawable.ic_favorite
                } else {
                    R.drawable.ic_favorite_border
                }
                Icon(
                    painter = painterResource(id = iconResource),
                    contentDescription = "favorite"
                )
            },
            label = { Text("Favorite") },
            selected = isFavorite,
            onClick = {
                isFavorite = !isFavorite
                // Handle favorite book logic
                book?.let {
                    println("click on handleFavoriteBook")
                    viewModel.handleFavoriteBook(book)
                }
            }
        )
    }
}