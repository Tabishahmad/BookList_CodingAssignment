package com.example.bookapi.presentation.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.bookapi.domain.model.Book

@Composable
fun BookDetailPreviewScreen(book: Book) {
    Scaffold(
        topBar = { /* optional top app bar */ },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                BookDetailPreviewContent(book)
            }
        },
        bottomBar = { BottomNavigation(book) }
    )
}
@Composable
fun BookDetailPreviewContent(book: Book) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = book.bookTitle,
            style = TextStyle(fontSize = 24.sp),
            textAlign = TextAlign.Center
        )
    }
}

