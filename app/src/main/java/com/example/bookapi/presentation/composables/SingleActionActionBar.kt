package com.example.bookapi.presentation.composables

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.bookapi.R

@Composable
fun SingleActionActionBar(onItemClicked: () -> Unit){
    val mContext = LocalContext.current
    val icon: Painter = painterResource(id = R.drawable.ic_fav_book)
    TopAppBar(
        title = { Text(mContext.getString(R.string.app_name), color = Color.White) } ,
        backgroundColor = Color(mContext.getColor(R.color.purple_200)),
        actions = {
            IconButton(onClick = onItemClicked) {
                Icon(icon, "")
            }
        }
    )
}