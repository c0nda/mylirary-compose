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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.listener.mylibrary.Data.librarian
import com.listener.mylibrary.Data.mode
import com.listener.mylibrary.Data.reader
import com.listener.mylibrary.R
import com.listener.mylibrary.component.listitem.ReaderBook
import com.listener.mylibrary.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Scaffold(
        modifier
            .fillMaxSize()
            .background(color = Color.White),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.profile),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = Color.White
                ),
                modifier = Modifier.height(85.dp)
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
                                text = "ФИО: ",
                                fontSize = 14.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Light
                            )
                            Text(
                                text = if (mode == 'R') reader.name else librarian.name,
                                fontSize = 14.sp,
                                color = Color.Black
                            )
                        }
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "ID: ",
                                fontSize = 14.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Light
                            )
                            Text(
                                text = if (mode == 'R') reader.id.toString() else librarian.id.toString(),
                                fontSize = 14.sp,
                                color = Color.Black
                            )
                        }
                        if (mode == 'L') {
                            Row(modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = "Дата регистрации: ",
                                    fontSize = 14.sp,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Light
                                )
                                Text(
                                    text = librarian.registrationDate,
                                    fontSize = 14.sp,
                                    color = Color.Black
                                )
                            }
                        } else {
                            Row(modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = "Дата выдачи билета: ",
                                    fontSize = 14.sp,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Light
                                )
                                Text(
                                    text = reader.cardIssueDate,
                                    fontSize = 14.sp,
                                    color = Color.Black
                                )
                            }
                            Row(modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = "Последняя \nперерегистрация: ",
                                    fontSize = 14.sp,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Light
                                )
                                Text(
                                    text = "\n" + reader.lastRegistrationDate,
                                    fontSize = 14.sp,
                                    color = Color.Black
                                )
                            }
                        }
                        TextButton(
                            modifier = Modifier
                                .background(color = Color.Black, shape = RoundedCornerShape(20.dp))
                                .width(180.dp)
                                .height(36.dp),
                            shape = RoundedCornerShape(20.dp),
                            onClick = { /*TODO*/ }) {
                            Text(
                                color = Color.White,
                                text = stringResource(id = R.string.change_password)
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        TextButton(
                            modifier = Modifier
                                .background(color = Color.Black, shape = RoundedCornerShape(20.dp))
                                .width(100.dp)
                                .height(36.dp),
                            shape = RoundedCornerShape(20.dp),
                            onClick = { navController.navigate(Routes.MainLogin.route) {
                                popUpTo(route = Routes.Profile.route) {
                                    inclusive = true
                                }
                            } }) {
                            Text(
                                color = Color.Red,
                                text = "Выйти"
                            )
                        }
                    }
                }
            }
            if (mode == 'R') {
                item {
                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = "Сейчас читаю:"
                    )
                }
                items(reader.booksOnHand) { currentBook ->
                    Row {
                        Spacer(modifier = Modifier.width(8.dp))
                        ReaderBook(currentBook = currentBook)
                    }
                }
            }
        }
    }
}