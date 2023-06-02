package com.example.bookapi.presentation.core

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bookapi.presentation.composables.BookListScreen
import com.example.bookapi.presentation.composables.SplashScreen
import com.example.bookapi.presentation.core.base.Screen

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.SplashScreen.route)
    {
        composable(Screen.SplashScreen.route){
            SplashScreen(navController)
        }
        composable(Screen.BookListScreen.route){
            BookListScreen()
        }
    }
}