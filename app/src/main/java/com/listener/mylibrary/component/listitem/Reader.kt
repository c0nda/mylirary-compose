package com.listener.mylibrary.component.listitem

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.listener.mylibrary.Data.readers
import com.listener.mylibrary.R
import com.listener.mylibrary.Reader
import com.listener.mylibrary.ui.theme.MyLibraryTheme

@Composable
fun Reader(
    reader: Reader
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    TextButton(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 8.dp)
            .background(Color.LightGray, shape = RoundedCornerShape(10.dp)),
        onClick = { expanded = !expanded },
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Column {
            Row {
                Text(
                    text = stringResource(id = R.string.name) + ": ",
                    color = Color.Black,
                    fontWeight = FontWeight.Light
                )
                Text(
                    text = reader.name,
                    fontSize = 14.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.weight(1.0.toFloat()))
                if (!expanded) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "",
                        tint = Color.Black
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "",
                        tint = Color.Black
                    )
                }
            }
            Row {
                Text(
                    text = "ID: ",
                    color = Color.Black,
                    fontWeight = FontWeight.Light
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = reader.id.toString(),
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }
            if (expanded) {
                Row {
                    Text(
                        text = "Дата выдачи билета: ",
                        color = Color.Black,
                        fontWeight = FontWeight.Light
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = reader.cardIssueDate,
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                }
                Row {
                    Text(
                        text = "Последняя перерегистрация: ",
                        color = Color.Black,
                        fontWeight = FontWeight.Light
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = reader.lastRegistrationDate,
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                }
                Text(
                    text = "Книги на руках: ",
                    color = Color.Black,
                    fontWeight = FontWeight.Light
                )
                Column(modifier = Modifier.fillMaxWidth()) {
                    reader.booksOnHand.forEach { currentBook ->
                        ReaderBook(currentBook = currentBook)
                    }
                }
            }
        }
    }
}