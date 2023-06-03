package com.example.bookapi.presentation.core

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bookapi.domain.model.Book
import com.example.bookapi.presentation.composables.BookDetailPreviewScreen
import com.example.bookapi.presentation.composables.BookListScreen
import com.example.bookapi.presentation.composables.FavouriteBookListScreen
import com.example.bookapi.presentation.composables.SplashScreen

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.SplashScreen.route)
    {
        composable(Screen.SplashScreen.route){
            SplashScreen(navController)
        }
        composable(Screen.BookListScreen.route){
            BookListScreen(navController)
        }
        composable(Screen.BookDetailPreviewScreen.route){
            val book = navController.previousBackStackEntry?.savedStateHandle?.get<Book>("book")
            book?.let {
                BookDetailPreviewScreen(book)
            }
        }
        composable(Screen.FavouriteBookListScreen.route){
            FavouriteBookListScreen(navController)
        }
    }
}