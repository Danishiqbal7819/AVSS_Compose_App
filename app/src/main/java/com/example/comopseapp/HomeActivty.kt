package com.example.comopseapp

import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.comopseapp.ui.theme.Purple40
import com.example.comopseapp.ui.theme.Purple80



@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(2.dp)
                .fillMaxSize()
                .background(Purple80)

        ) {
            GreetingSection()
            ChipSection(
                chips = listOf(
                    "Data structures",
                    "Algorithms",
                    "competitive programming",
                    "python"
                )
            )
            // function for suggestionSection
            SuggestionSection()
            UsersNames()
        }
    }
}


@Composable
fun GreetingSection(
    name: String = "Students"
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Good morning, $name",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = "We wish you have a good day!",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            )
        }
        // search icon
        Icon(
            painter = painterResource(id = R.drawable.location),
            contentDescription = "Search",
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun ChipSection(
    chips: List<String>
) {

    var selectedChipIndex by remember {
        mutableStateOf(0)
    }

    LazyRow(
        modifier = Modifier
            .background(Purple40)

    ) {
        items(chips.size) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                    .clickable {
                        selectedChipIndex = it
                    }
                    .clip(RoundedCornerShape(10.dp))
                    .background(
//                        Red
                        if (selectedChipIndex == it) Blue
                        else Red
                    )
                    .padding(15.dp)
            ) {
                Text(text = chips[it], color = White)
            }
        }
    }
}

@Composable
fun SuggestionSection(
    color: Color = Blue
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(
                Brush.horizontalGradient(
                    colors = listOf(
                        Red, // Red80
//                        Color(0xFF81F0A8), // Green80
                        Color(0xFF396AFA) // Blue80
                    )
                )
            )
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxWidth()
    ) {
        Column {
            // here are two text views or we can say only text
            Text(
                text = "Daily Coding",
                style = MaterialTheme.typography.labelMedium

            )
            Text(
                text = "do at least • 3-10 problems / day",
                style = MaterialTheme.typography.bodyLarge,
                color = White
            )
        }


        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Red)
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.location),
                contentDescription = "Play",
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
        }
    }


}

@Composable
fun UsersNames() {
    val context = LocalContext.current
    val application = Application.ACCESSIBILITY_SERVICE
    var listitems = listOf<String>(
        "Rahul", "Karan", "Emily", "Alexander", "Logan", "Benjamin",
        "Emily", "Oliver", "Ava", "Liam", "Noah", "Elijah",
        "Léa", "Léo", "Anaïs", "Gabriel", "Lucas", "Chloé",
        "Sophia", "Mia", "Isabella", "Alexander", "Logan", "Julia",
        "Yui", "Takeru", "Aoi", "Kaito", "Akira", "Mei",
        "Liam", "Emma", "Noah", "Olivia", "Elijah", "Ava"
    )

    val addressList = listOf(
        "Hapur, India", "Ghaziabd, India", "New York, America",
        "London, England", "Paris, France", "Tokyo, Japan",
        "Sydney, Australia", "Beijing, China", "Mumbai, India",
        "Delhi, India", "Kolkata, India", "Bangalore, India",
        "Hyderabad, India", "Chennai, India", "Pune, India",
        "Jaipur, India", "Lucknow, India", "Kanpur, India",
        "Los Angeles, America", "Chicago, America", "Houston, America",
        "Lyon, France", "Bordeaux, France", "Marseille, France",
        "Shanghai, China", "Guangzhou, China", "Shenzhen, China",
        "Brisbane, Australia", "Melbourne, Australia", "Perth, Australia"
    )
    Text(
        text = "Students", color = Black,
        style = MaterialTheme.typography.labelLarge.copy(fontSize = 20.sp),
        modifier = Modifier
            .padding(10.dp)
    )
    LazyVerticalGrid(
        GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 1.dp, start = 7.5.dp, end = 7.5.dp, bottom = 10.dp)
    )

    {

        items(listitems.size) {
            Box(
                modifier = Modifier
//                    .background(Brush.horizontalGradient(colors = listOf(SoftPink, BabyBlue, MintGreen)))
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(
                                Red, // Red80
//                        Color(0xFF81F0A8), // Green80
                                Color(0xFF396AFA) // Blue80
                            )
                        )
                    )
                    .padding(2.dp)
                    .height(100.dp)
                    .border(1.dp, White, RectangleShape)
                    .clickable {
                        Toast
                            .makeText(context, "Clicked:${listitems[it]}", Toast.LENGTH_SHORT)
                            .show()
//                        mainActivity.ToastFun("listitems[it]")
                    }

            ) {

                Column {

                    Row(
                        modifier = Modifier
                            .padding(2.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.user),
                            contentDescription = "play",
                            tint = White,
                            modifier = Modifier
                                .size(30.dp)
                                .align(Alignment.CenterVertically)
                        )
                        Text(
                            text = listitems[it],
                            style = MaterialTheme.typography.labelLarge.copy(fontSize = 20.sp),
                            color = White,
                            modifier = Modifier
                                .padding(5.dp)
//                            .width(100.dp)
//                            .height(20.dp)
                                .align(Alignment.CenterVertically)
                        )

                    }
                    Row(
                        modifier = Modifier
                            .padding(2.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.location),
                            contentDescription = "play",
                            tint = White,
                            modifier = Modifier
                                .size(20.dp)
                                .align(Alignment.CenterVertically)
                        )
                        Text(
                            text = addressList[it],
                            style = MaterialTheme.typography.labelLarge,
                            color = White,
                            modifier = Modifier
                                .padding(10.dp)
                                .align(Alignment.CenterVertically)
                        )
                    }
                }
            }
        }
    }
}
@Preview
@Composable
fun myPreview(){
    HomeScreen()
}