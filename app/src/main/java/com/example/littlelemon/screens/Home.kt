package com.example.littlelemon.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.littlelemon.MenuItemRoom
import com.example.littlelemon.R
import com.example.littlelemon.appDatabase
import com.example.littlelemon.karlaFontFamily
import com.example.littlelemon.markaziFontFamily
import com.example.littlelemon.navigation.Profile
import com.example.littlelemon.ui.theme.PrimaryGreen
import com.example.littlelemon.ui.theme.PrimaryYellow
import com.example.littlelemon.ui.theme.SecondaryWhiteSmoke
import androidx.compose.ui.platform.LocalContext

@Composable
fun Home(navController: NavHostController) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    // Fetch menuItems from appDatabase
    val unfilteredMenuItems by appDatabase.menuItemDao().getAll().observeAsState(initial = emptyList())
    var searchPhrase by remember { mutableStateOf("") }

    Column(modifier= Modifier
        .fillMaxWidth()
    ) {

        /**
         * HEADER
         */
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .weight(1.0f)
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .weight(2.5f)
            ) {
                // Hamburger menu could go here.
            }
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Little Lemon Logo",
                modifier = Modifier
                    // TODO: try 25.pd padding on other screen logo's
                    .weight(5.0f)
                    .padding(20.dp)
            )
            Box(modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .weight(2.5f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "User profile picture",
                    modifier = Modifier
                        .padding(15.dp)
                        .clickable { navController.navigate(Profile.route) }
                )
            }
        }

        /**
         * HERO BANNER
         */
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(PrimaryGreen)
                .weight(3.2f)
                .padding(horizontal = 20.dp),
        ) {
            items(count = 1) {
                Text(
                    text = "Little Lemon",
                    color = PrimaryYellow,
                    fontFamily = markaziFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 64.sp,
                    modifier = Modifier
                        .height(54.dp)
                )
                Text(
                    text = "Chicago",
                    color = Color.White,
                    fontFamily = markaziFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 40.sp,
                    modifier = Modifier
                        .height(40.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(modifier = Modifier
                    .fillMaxWidth()
                ) {
                    Text(
                        text = "We are a family-owned Mediterranean restaurant, " +
                                "focused on traditional recipes served with a modern twist.",
                        color = Color.White,
                        fontFamily = karlaFontFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .weight(7.0f)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.hero_image),
                        contentDescription = "Hero image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .weight(3.0f)
                            .height(125.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )
                }

                // SEARCH BOX
                fun handleSearch() {
                    keyboardController?.hide()
                    focusManager.clearFocus()
                    // For now we'll just use live filtering
                }
                OutlinedTextField(
                    value = searchPhrase,
                    onValueChange = { searchPhrase = it},
                    shape = RoundedCornerShape(10.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = SecondaryWhiteSmoke,
                        focusedContainerColor = Color.White,
                        focusedBorderColor = PrimaryYellow,
                        cursorColor = PrimaryGreen
                    ),
                    textStyle = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = karlaFontFamily,
                        lineHeight = 0.sp),
                    placeholder = {Text(text = "Enter search phrase")},
                    // Search Button
                    leadingIcon = { IconButton(onClick = { handleSearch() }) {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = "Search Button"
                        )
                    }},
                    // Clear button shows up if search field is not empty
                    trailingIcon = { if(searchPhrase.isNotEmpty()) {
                        IconButton(onClick = { searchPhrase = "" }) {
                            Icon(
                                imageVector = Icons.Outlined.Clear,
                                contentDescription = "Clear Button"
                            )
                        }
                    } },
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(vertical = 10.dp),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(onSearch = {
                        handleSearch()
                    })
                )
            }
        }

        /**
         * LOWER SECTION
         */
        Column(modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .weight(4.3f)
            .padding(20.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "ORDER FOR DELIVERY!",
                    fontFamily = karlaFontFamily,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp,
                )

                /**
                 * FILTER BY CATEGORY BUTTONS
                 */
                var starters by remember { mutableStateOf(false)}
                var mains by remember { mutableStateOf(false)}
                var desserts by remember { mutableStateOf(false)}
                var drinks by remember { mutableStateOf(false)}
                var categoryFilter by remember { mutableStateOf("") }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly)
                {
                    // STARTERS
                    Button(
                        onClick = {
                            categoryFilter =
                                if(categoryFilter != "starters") {
                                    "starters"
                                } else ""
                            starters = !starters
                            mains = false
                            desserts = false
                            drinks = false
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (starters) {
                                PrimaryGreen
                            } else {
                                SecondaryWhiteSmoke
                            }
                        ),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .padding(top = 5.dp, bottom = 10.dp),
                        contentPadding = PaddingValues(horizontal = 10.dp)
                    ) {
                        Text(
                            text = "Starters",
                            color = if (starters) {
                                SecondaryWhiteSmoke
                            } else {
                                PrimaryGreen
                            },
                            fontFamily = karlaFontFamily,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }

                    // MAINS
                    Button(
                        onClick = {
                            categoryFilter =
                                if(categoryFilter != "mains") {
                                    "mains"
                                } else ""
                            starters = false
                            mains = !mains
                            desserts = false
                            drinks = false
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (mains) {
                                PrimaryGreen
                            } else {
                                SecondaryWhiteSmoke
                            }
                        ),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .padding(top = 5.dp, bottom = 10.dp),
                        contentPadding = PaddingValues(horizontal = 10.dp)
                    ) {
                        Text(
                            text = "Mains",
                            color = if (mains) {
                                SecondaryWhiteSmoke
                            } else {
                                PrimaryGreen
                            },
                            fontFamily = karlaFontFamily,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }

                    // DESSERTS
                    Button(
                        onClick = {
                            categoryFilter =
                                if(categoryFilter != "desserts") {
                                    "desserts"
                                } else ""
                            starters = false
                            mains = false
                            desserts = !desserts
                            drinks = false
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (desserts) {
                                PrimaryGreen
                            } else {
                                SecondaryWhiteSmoke
                            }
                        ),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .padding(top = 5.dp, bottom = 10.dp),
                        contentPadding = PaddingValues(horizontal = 10.dp)
                    ) {
                        Text(
                            text = "Desserts",
                            color = if (desserts) {
                                SecondaryWhiteSmoke
                            } else {
                                PrimaryGreen
                            },
                            fontFamily = karlaFontFamily,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }

                    // DRINKS
                    Button(
                        onClick = {
                            categoryFilter = ""
                            starters = false
                            mains = false
                            desserts = false
                            drinks = false
                            Toast.makeText(
                                context,
                                "Drinks menu coming soon!",
                                Toast.LENGTH_LONG).show()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (drinks) {
                                PrimaryGreen
                            } else {
                                SecondaryWhiteSmoke
                            }
                        ),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .padding(top = 5.dp, bottom = 10.dp),
                        contentPadding = PaddingValues(horizontal = 10.dp)
                    ) {
                        Text(
                            text = "Drinks",
                            color = if (drinks) {
                                SecondaryWhiteSmoke
                            } else {
                                PrimaryGreen
                            },
                            fontFamily = karlaFontFamily,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                }

                HorizontalDivider(modifier = Modifier.padding(vertical = 1.dp))

                // Filter menu items using filter buttons
                val categoryFilteredItems = unfilteredMenuItems.filter {
                    it.category.contains(categoryFilter, ignoreCase = true)
                }

                // Filter menu items further by using the search bar
                var menuItems = categoryFilteredItems
                if (searchPhrase.isNotEmpty()) {
                    menuItems = categoryFilteredItems.filter {
                        it.title.contains(searchPhrase, ignoreCase = true)
                    }
                }

                MenuItemsList(menuItems)
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItemsList(items: List<MenuItemRoom>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(
            items = items,
            itemContent = { menuItem ->
                Text(
                    text = menuItem.title,
                    fontFamily = karlaFontFamily,
                    fontWeight = FontWeight.Bold
                )

                Row{
                    Column(modifier = Modifier
                        .weight(0.7f)){
                        Text(
                            text = menuItem.description,
                            fontFamily = karlaFontFamily,
                            fontWeight = FontWeight.Normal,
                            color = PrimaryGreen,
                            letterSpacing = 0.1.sp,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            lineHeight = 18.sp,
                            modifier = Modifier.padding(top = 10.dp)
                        )
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(
                            text = "$%.2f".format(menuItem.price.toDouble()),
                            fontFamily = karlaFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = PrimaryGreen
                        )
                    }

                    GlideImage(
                        // Quick fix.
                        // The project's required json image links return blank images for
                        // the dessert and fish.
                        model =
                        when (menuItem.title) {
                            "Lemon Desert" -> (R.drawable.lemon_dessert) //yes, it's spelled wrong
                            "Grilled Fish" -> (R.drawable.grilled_fish)
                            else -> menuItem.image
                        },
                        // The model should have simply been this:
//                        model = menuItem.image,
                        contentDescription = "${menuItem.title} image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(85.dp)
                            .height(75.dp)
                            .padding(start = 10.dp)
                    )
                }
                HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp))
            }
        )
    }
}


