package com.example.bookapi.presentation.composables

import android.app.Activity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.bookapi.domain.model.Book
import com.example.bookapi.presentation.core.Screen
import com.example.bookapi.presentation.core.ViewState
import com.example.bookapi.presentation.viewmodel.BookViewModel

@Composable
fun BookListScreen(navController: NavController){
    Scaffold(
        topBar = { SingleActionActionBar(){ handleFavClick(navController) } },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                BookListContent(navController)
            }
        },
        bottomBar = {}
    )
}
private fun handleFavClick(navController: NavController){
    navController.navigate(Screen.FavouriteBookListScreen.route)
}

@Composable
private fun BookListContent(navController: NavController) {
    val viewModel : BookViewModel = hiltViewModel()

    //avoid calling on orientation changes
    var hasFetched by rememberSaveable{ mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if (!hasFetched) {
            viewModel.fetchList()
            hasFetched = true
        }
    }
    val uiState by viewModel.getviewStateFlow().collectAsState()
    when (val bookListState = uiState) {
        is ViewState.Loading -> {
            // Show loading state
            showLoading()
        }
        is ViewState.Success -> {
            val bookList = bookListState.result
            BookList(bookList = bookList, navController = navController)
        }
        is ViewState.Failure -> {
            // Show error state or handle error
            showFailureMessage(bookListState.failMessage)
        }
    }
    ExitDialogComposable(navController)
 }

@Composable
private fun showLoading(){
    // Show loading state
    LazyVerticalGrid(columns = GridCells.Fixed(3)){
        items(20) { index ->
            ShimmerBookItem()
        }
    }
}
@Composable
private fun showFailureMessage(failureMessage: String){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = failureMessage,
            style = TextStyle(color = Color.Red),
            modifier = Modifier.padding(16.dp)
        )
    }
}
@Composable
private fun BookList(bookList: List<Book>,navController: NavController){
    LazyVerticalGrid(columns = GridCells.Fixed(3)){
        items(bookList){book->
            BookItem(book = book)   {
                openBookPreView(book = book, navController = navController)
            }
        }
    }
}
private fun openBookPreView(book: Book,navController: NavController){
    navController.currentBackStackEntry?.savedStateHandle?.set(
        key = "book",
        value = book
    )
    navController.navigate(Screen.BookDetailPreviewScreen.route)
}
@Composable
private fun BookItem(book: Book, onClick: () -> Unit) {
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
private fun ShimmerBookItem(modifier: Modifier = Modifier) {
    Box(modifier = modifier.padding(4.dp)) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(color = Color.LightGray, shape = RoundedCornerShape(4.dp))
                .animateShimmer()
        )
    }
}


@Composable
private fun Modifier.animateShimmer(): Modifier {
    val infiniteTransition = rememberInfiniteTransition()
    val translateAnim by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    return this.graphicsLayer(translationX = translateAnim)
}
@Composable
private fun ExitDialogComposable(navController: NavController) {
    val showExitDialog = remember { mutableStateOf(false) }
    val context = LocalContext.current
    if (showExitDialog.value) {
        AlertDialog(
            onDismissRequest = { showExitDialog.value = false },
            title = { Text("Exit Confirmation") },
            text = { Text("Are you sure you want to exit?") },
            confirmButton = {
                Button(onClick = {
                    // Handle exit action
                    val activity = context as? Activity
                    activity?.finish()
                    showExitDialog.value = false
                }) {
                    Text("Exit")
                }
            },
            dismissButton = {
                Button(onClick = { showExitDialog.value = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    // Rest of your composable code

    // Back button press handler
    BackHandler {
        showExitDialog.value = true
    }
}



