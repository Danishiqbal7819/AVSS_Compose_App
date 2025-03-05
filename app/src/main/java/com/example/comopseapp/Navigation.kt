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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.comopseapp.R
import com.example.comopseapp.ui.theme.ComopseAppTheme
import kotlinx.coroutines.launch

class Navigation : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContent {
            ComopseAppTheme {
                NavigationDrawerss()
            }
        }
    }

}


@Composable
fun Screen1(){
    Box {
      NavigationDrawerss()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Mydata() {
    Column(
        Modifier
            .fillMaxSize()
            .fillMaxSize()
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
fun NavigationDrawerss() {
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
                Row(
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(10.dp)
                ) {

                    Icon(
                        painter = painterResource(id = R.drawable.axis_bank_logo2),
                        contentDescription = "Logo",
                        Modifier
                            .size(20.dp)
                    )
                    Text(
                        "Axis Bank", modifier = Modifier
                            .background(Color.White)
                    )
                }


                Image(
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = "MyDp",
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .background(Color.White)
                        .padding(10.dp)
                        .clip(CircleShape),
                )
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

                    )
                NavigationDrawerItem(
                    label = { Text(text = "Gallery", color = Color.Black) },
                    selected = false,
                    onClick = { /*TODO*/ })

                NavigationDrawerItem(
                    label = { Text(text = "About me", color = Color.Black) },
                    selected = false,
                    onClick = { /*TODO*/ })

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
                )
                NavigationDrawerItem(
                    label = { Text(text = "Settings", color = Color.Black) },
                    selected = false,
                    onClick = { /*TODO*/ })

                NavigationDrawerItem(
                    label = { Text(text = "Logout", color = Color.Black) },
                    selected = false,
                    onClick = { /*TODO*/ })
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
//        Mydata()
    }


}


@Preview
@Composable
fun Myprevieww() {
NavigationDrawerss()
}

