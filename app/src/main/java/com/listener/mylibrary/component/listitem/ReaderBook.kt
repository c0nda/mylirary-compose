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
import com.listener.mylibrary.CurrentBook
import com.listener.mylibrary.Data.currentBooks1
import com.listener.mylibrary.navigation.Routes
import com.listener.mylibrary.ui.theme.MyLibraryTheme

@Composable
fun ReaderBook(
    currentBook: CurrentBook
) {
    TextButton(
        modifier = Modifier
            .padding(top = 8.dp, end = 8.dp)
            .background(Color.LightGray, shape = RoundedCornerShape(10.dp)),
        onClick = { },
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Column {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = currentBook.book.name,
                fontSize = 14.sp,
                color = Color.Black
            )
            currentBook.issuingDate?.let {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "до $it",
                    fontSize = 14.sp,
                    color = if (currentBook.overdue == false) Color.Black else Color.Red,
                    fontWeight = FontWeight.Light
                )
            }
        }
    }
}