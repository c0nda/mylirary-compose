package com.listener.mylibrary.component.listitem

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.listener.mylibrary.CurrentBook
import com.listener.mylibrary.navigation.Routes

@Composable
fun CurrentBookLight(
    currentBook: CurrentBook
) {
    TextButton(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp, top = 8.dp)
            .background(Color.LightGray, shape = RoundedCornerShape(10.dp)),
        onClick = {},
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Row(modifier = Modifier.height(30.dp)) {
            Text(
                text = currentBook.book.name,
                fontSize = 14.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.weight(1.toFloat()))
            Text(
                text = currentBook.id.toString(),
                fontSize = 14.sp,
                color = Color.Black,
                fontWeight = FontWeight.Normal
            )
        }
    }
}