package com.example.bookapi.presentation.core.base

sealed class Screen(val route:String){
    object SplashScreen : Screen("splash_screen")
    object BookListScreen : Screen("book_list_screen")
}
