package com.listener.mylibrary.component.listitem

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.listener.mylibrary.CurrentBook
import com.listener.mylibrary.Data.currentBooks1
import com.listener.mylibrary.ui.theme.MyLibraryTheme

@Composable
fun BookFull(
    currentBook: CurrentBook
) {
    TextButton(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 8.dp)
            .background(Color.LightGray, shape = RoundedCornerShape(10.dp)),
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Column {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Книга: ",
                    fontSize = 14.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Light
                )
                Text(
                    text = currentBook.book.name,
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Автор: ",
                    fontSize = 14.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Light
                )
                Text(
                    text = currentBook.book.author,
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Дата выдачи: ",
                    fontSize = 14.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Light
                )
                currentBook.issuingDate?.let {
                    Text(
                        text = it,
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                }
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Срок сдачи: ",
                    fontSize = 14.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Light
                )
                currentBook.dueDate?.let {
                    Text(
                        text = it,
                        fontSize = 14.sp,
                        color = if (currentBook.overdue == true) Color.Red else Color.Black
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
private fun BookFullPreview() {
    MyLibraryTheme {
        Column {
            BookFull(currentBook = currentBooks1[0])
            BookFull(currentBook = currentBooks1[0])
        }
    }
}