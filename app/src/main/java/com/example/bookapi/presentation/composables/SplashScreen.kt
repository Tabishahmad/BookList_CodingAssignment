package com.example.bookapi.presentation.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bookapi.presentation.core.Screen
import com.example.bookapi.R

@Composable
fun SplashScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Button(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(48.dp),
            onClick = { navController.navigate(Screen.BookListScreen.route) }
        ) {
            Text(text = stringResource(id = R.string.start_text_splash))
        }
    }
}

@Preview
@Composable
fun ScreenPreview() {
    SplashScreen(rememberNavController())
}
