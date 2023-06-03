package com.example.bookapi.presentation.core

sealed class Screen(val route:String){
    object SplashScreen : Screen("splash_screen")
    object BookListScreen : Screen("book_list_screen")
    object BookDetailPreviewScreen : Screen("book_detail_preview_screen")
    object FavouriteBookListScreen : Screen("favourite_book_list_screen")
}
