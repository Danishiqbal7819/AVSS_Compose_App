//import com.google.android.material.chip.Chip
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.comopseapp.R
import com.example.comopseapp.ui.theme.ComopseAppTheme
import kotlinx.coroutines.launch

class Navigationss : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContent {
            ComopseAppTheme {
                NavigationDrawerz({ Mydata() })
            }
        }
    }

}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Mydata() {
    Column(
        Modifier
            .fillMaxSize()
            .padding(top = 60.dp)
            .height(500.dp)
            .background(Color.Red)) {
    }
}
@Composable
fun HorizontalScrollView(content: @Composable () -> Unit) {
    LazyRow {
        item {

        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawerz(content: @Composable () -> Unit) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Open)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                Modifier
                    .fillMaxHeight()
                    .width(300.dp)
                    .clip(RectangleShape)
            ) {
                Column(
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth()
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(
                                    Color.Red,
                                    Color(0xFF396AFA)
                                )
                            )
                        )
                        .padding(10.dp)
                ) {

                Row(
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(5.dp)
//                        .background(color = Color.White)

                ) {


                    Icon(
                        painter = painterResource(id = R.drawable.axis_bank_logo2),
                        tint = Color.White,
                        contentDescription = "Logo",
                        modifier = Modifier
                            .size(30.dp)
//                            .clip(CircleShape)
//                            .background(color = Color.White)
                            .align(Alignment.CenterVertically)



                    )
                    Text(
                        "Axis Bank", modifier = Modifier,
                        style = MaterialTheme.typography.labelLarge.copy(fontSize = 20.sp),
                        color = Color.White

                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = "MyDp",
                    Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .align(Alignment.CenterHorizontally)

                )
                    Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = "Star"
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            "Username",
                            modifier = Modifier,
                            style = MaterialTheme.typography.labelLarge.copy(fontSize = 10.sp),
                        )
                    }
                    Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Phone,
                            contentDescription = "Star"
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            "+9899289232", modifier = Modifier,
                            style = MaterialTheme.typography.labelLarge.copy(fontSize = 10.sp),
                        )
                    }

                }
                Text(
                    "", modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .align(Alignment.CenterHorizontally)
                        .background(Color.White)
                )
                NavigationDrawerItem(
                    label = { Text(text = "Home", color = Color.Black) },
                    selected = false,
                    onClick = { /*TODO*/ },
                    icon = {
                        Icon(imageVector = Icons.Filled.Home, contentDescription = "Menu")
                    }
                    )
                NavigationDrawerItem(
                    label = { Text(text = "Gallery", color = Color.Black) },
                    selected = false,
                    onClick = { /*TODO*/ },
                    icon = {
                        Icon(imageVector = Icons.Filled.Face, contentDescription = "Menu")
                    }

                )

                NavigationDrawerItem(
                    label = { Text(text = "About me", color = Color.Black) },
                    selected = false,
                    onClick = { /*TODO*/ },
                    icon = {
                        Icon(imageVector = Icons.Filled.Person, contentDescription = "Menu")
                    })

                Text(
                    "", modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .align(Alignment.CenterHorizontally)
                        .background(Color.White)
                )

                NavigationDrawerItem(
                    label = { Text(text = "Policy", color = Color.Black) },
                    selected = false,
                    onClick = { /*TODO*/ },
                    icon = {
                        Icon(imageVector = Icons.Filled.DateRange, contentDescription = "Menu")
                    }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Settings", color = Color.Black) },
                    selected = false,
                    onClick = { /*TODO*/ },
                    icon = {
                        Icon(imageVector = Icons.Filled.Settings, contentDescription = "Menu")
                    })

                NavigationDrawerItem(
                    label = { Text(text = "Logout", color = Color.Black) },
                    selected = false,
                    onClick = { /*TODO*/ },
                    icon = {
                        Icon(imageVector = Icons.Filled.MailOutline, contentDescription = "Menu")
                    })
                // ...other drawer items
            }
        },
        gesturesEnabled = true
    ) {
//Box (Modifier.fillMaxSize()){
        TopAppBar(
            title = { Text("Customer Account") },
            modifier = Modifier.background(color = Color.White),
            actions = {
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
                }

            },
            navigationIcon = {
                IconButton(onClick = {
                    scope.launch {
                        if (drawerState.isClosed) {
                            drawerState.open()
                        } else {
                            drawerState.close()
                        }
                    }
                }

                )

                {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
                }
            }
        )
//}
        content()
    }


}


@Composable
fun a() {
    var list = listOf("Danish", "Farhan", "Sameer", "Khaleek")
    LazyVerticalGrid(columns = GridCells.Fixed(1)) {
        items(list.size) {
            usercard(list = list, it)
        }
    }
}

@Composable
fun usercard(list: List<String>, it: Int) {
    Box {
        Text(text = list[it], color = Color.Red)
    }
}


@Preview
@Composable
fun Myprevieww() {
    a()
}

