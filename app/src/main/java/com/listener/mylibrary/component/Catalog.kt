package com.listener.mylibrary.component

import androidx.compose.foundation.BasicTooltipBox
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberBasicTooltipState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.listener.mylibrary.Data.books
import com.listener.mylibrary.Data.mode
import com.listener.mylibrary.R
import com.listener.mylibrary.component.listitem.BookLight
import com.listener.mylibrary.ui.theme.MyLibraryTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun Catalog(
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
                        text = stringResource(id = R.string.catalog),
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
                actions = {
                    BasicTooltipBox(
                        positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(
                            spacingBetweenTooltipAndAnchor = 0.dp
                        ),
                        state = rememberBasicTooltipState(isPersistent = false),
                        tooltip = {
                            Text(
                                text = stringResource(id = R.string.view),
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Light
                            )
                        }
                    ) {
                        IconButton(
                            onClick = {},
                            colors = IconButtonColors(
                                contentColor = Color.White,
                                containerColor = Color.Black,
                                disabledContentColor = Color.White,
                                disabledContainerColor = Color.Black
                            )
                        ) {
                            Image(
                                modifier = Modifier.size(15.dp),
                                painter = painterResource(id = R.mipmap.ic_view),
                                contentDescription = null
                            )
                        }
                    }
                    BasicTooltipBox(
                        positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(
                            spacingBetweenTooltipAndAnchor = 0.dp
                        ),
                        tooltip = {
                            Text(
                                text = stringResource(id = R.string.filter),
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Light
                            )
                        },
                        state = rememberBasicTooltipState()
                    ) {
                        IconButton(
                            onClick = { }, colors = IconButtonColors(
                                contentColor = Color.White,
                                containerColor = Color.Black,
                                disabledContentColor = Color.White,
                                disabledContainerColor = Color.Black
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = null
                            )
                        }
                    }
                    if (mode == 'L') {
                        BasicTooltipBox(
                            positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(
                                spacingBetweenTooltipAndAnchor = 0.dp
                            ),
                            tooltip = {
                                Text(
                                    text = stringResource(id = R.string.edit),
                                    color = Color.White,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Light
                                )
                            },
                            state = rememberBasicTooltipState()
                        ) {
                            IconButton(onClick = { }) {
                                Icon(
                                    imageVector = Icons.Default.Create,
                                    contentDescription = null,
                                    tint = Color.White
                                )
                            }
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(innerPadding)
        ) {
            item {
                OutlinedTextField(
                    value = "",
                    onValueChange = { },
                    enabled = false,
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Search,
                            contentDescription = stringResource(id = R.string.search)
                        )
                    },
                    modifier = Modifier
                        .height(70.dp)
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, top = 15.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Black,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        cursorColor = Color.Black
                    ),
                    placeholder = { Text(stringResource(id = R.string.search)) },
                    textStyle = TextStyle.Default.copy(fontSize = 14.sp),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(26.dp))
            }
            items(books) { book ->
                BookLight(book = book, navController = navController)
            }
        }
    }
}

