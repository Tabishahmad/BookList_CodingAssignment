package com.example.bookapi.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bookapi.presentation.core.base.Screen

@Composable
fun SplashScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Hello, Jetpack Compose!")
        Button(
            modifier = Modifier.padding(16.dp),
            onClick = { navController.navigate(Screen.BookListScreen.route) }
        ) {
            Text(text = "Click Me!")
        }
    }
}

@Preview
@Composable
fun MyScreenPreview() {
    SplashScreen(rememberNavController())
}
