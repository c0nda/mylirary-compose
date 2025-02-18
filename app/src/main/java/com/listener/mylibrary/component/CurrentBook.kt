package com.listener.mylibrary.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.listener.mylibrary.Book
import com.listener.mylibrary.Data.books
import com.listener.mylibrary.Data.currentBooks1
import com.listener.mylibrary.R
import com.listener.mylibrary.component.listitem.CurrentBookLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrentBook(
    modifier: Modifier = Modifier,
    bookName: String,
    navController: NavController
) {
    var book: Book = Book("", "", mutableListOf(), mutableListOf())
    for (i in books.indices) {
        if (books[i].name == bookName) {
            book = books[i]
            break
        }
    }
    Scaffold(
        modifier
            .fillMaxSize()
            .background(color = Color.White),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = book.name,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = Color.White
                ),
                modifier = Modifier.height(85.dp),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = "",
                            tint = Color.White)
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .background(Color.White)
                .fillMaxSize()
        ) {
            item {
                Row(modifier = Modifier.padding(8.dp)) {
                    Text(
                        modifier = Modifier
                            .height(160.dp)
                            .width(100.dp)
                            .background(color = Color.LightGray, shape = RoundedCornerShape(10.dp))
                            .padding(top = 50.dp),
                        text = stringResource(id = R.string.photo),
                        fontWeight = FontWeight.ExtraLight,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "Название: ",
                                fontSize = 14.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Light
                            )
                            Text(
                                text = book.name,
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
                                text = book.author,
                                fontSize = 14.sp,
                                color = Color.Black
                            )
                        }
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "Количество: ",
                                fontSize = 14.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Light
                            )
                            Text(
                                text = book.currentBooks.size.toString(),
                                fontSize = 14.sp,
                                color = Color.Black
                            )
                        }
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "Доступно: ",
                                fontSize = 14.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Light
                            )
                            Text(
                                text = (book.currentBooks.size - book.issuedBooks.size).toString(),
                                fontSize = 14.sp,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
            item {
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = stringResource(id = R.string.available_books)
                )
            }
            item {
                if (bookName == "Преступление и наказание") {
                    CurrentBookLight(currentBook = currentBooks1[1])
                    CurrentBookLight(currentBook = currentBooks1[2])
                }
            }
        }
    }
}