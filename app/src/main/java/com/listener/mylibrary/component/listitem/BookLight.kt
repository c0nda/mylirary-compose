package com.listener.mylibrary.component.listitem

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.listener.mylibrary.Book
import com.listener.mylibrary.Data
import com.listener.mylibrary.navigation.Routes
import com.listener.mylibrary.ui.theme.MyLibraryTheme

@Composable
fun BookLight(
    book: Book,
    navController: NavController
) {
    TextButton(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, bottom = 8.dp)
            .background(Color.LightGray, shape = RoundedCornerShape(10.dp)),
        onClick = { navController.navigate(Routes.CurrentBook.route + "/${book.name}") },
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Column {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = book.name,
                fontSize = 14.sp,
                color = Color.Black
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = book.author,
                fontSize = 14.sp,
                color = Color.Black,
                fontWeight = FontWeight.Normal
            )
        }
    }
}