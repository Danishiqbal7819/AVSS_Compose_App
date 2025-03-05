package com.example.comopseapp.UII.View

import RoomDbWithMVVMWithLayers.Data.Local.RDbEntity
import RoomDbWithMVVMWithLayers.UI.ViewModel.ShowDataRDbViewModel
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ScrollView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.comopseapp.R
import com.example.comopseapp.ui.theme.ComopseAppTheme
import kotlinx.coroutines.launch

class ShowData : ComponentActivity() {
    private lateinit var showRDbDataViewModel: ShowDataRDbViewModel
    private lateinit var datalist1:MutableList<RDbEntity>

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        datalist1= mutableListOf()

              showRDbDataViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))
                .get(ShowDataRDbViewModel::class.java)
        showRDbDataViewModel.fetchAllUsers()

        showRDbDataViewModel.usersLiveData.observe(this) { users ->
            datalist1.clear()
            datalist1.addAll(users)
            setContent {
                ComopseAppTheme{
//                    Screen1()
                    UsersNames(datalist = datalist1)
                }
            }
        }
    }

    @Composable
    fun UsersNames(datalist: MutableList<RDbEntity>) {
        Column(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
        ) {
            MyActionBar("Customer Details")

            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                modifier = Modifier
                    .fillMaxHeight()
            ) {
                items(datalist) { user ->
                    UserCard(user = user)
                }
            }
        }
    }
    @Composable
    fun UserCard(user: RDbEntity) {

        Box(
            modifier = Modifier
                .padding(8.dp)
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            Color.Red,
                            Color(0xFF396AFA)
                        )
                    )
                )
                .clip(RoundedCornerShape(8.dp))
                .border(1.dp, Color.White, RectangleShape)
                .clickable {
                }
        )
        {
            Box( modifier = Modifier
                .align(Alignment.TopEnd)
            ) {
                MenuItems(user)
            }
            Row(  modifier = Modifier
                .padding(8.dp)
            ) {
                Image(
                    bitmap = byteArrayToBitmap(user.image).asImageBitmap(),
                    contentDescription = "User Icon",
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .align(Alignment.CenterVertically),
                )
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
Row{
                Text(
                    text = "${user.first_name} ${user.middle_name} ${user.last_name}",
                    style = MaterialTheme.typography.labelLarge.copy(fontSize = 20.sp),
                    color = Color.White,
                    modifier = Modifier
                        .padding(3.dp)
                )
}

                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_add_call_24),
                        contentDescription = "Phone Icon",
                        tint = Color.White,
                        modifier = Modifier
                            .size(20.dp)
                            .height(20.dp)
                            .fillMaxWidth()

                    )
                    Text(
                        text = user.phone_name,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White,
                        modifier = Modifier
                            .padding(start = 8.dp)
                    )
                }
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_alternate_email_24),
                        contentDescription = "Email Icon",
                        tint = Color.White,
                        modifier = Modifier
                            .size(20.dp)
                    )
                    Text(
                        text = user.email_name,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White,
                        modifier = Modifier
                            .padding(start = 8.dp)
                    )
                }
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.location),
                        contentDescription = "Location Icon",
                        tint = Color.White,
                        modifier = Modifier
                            .size(20.dp)
                    )
                    Text(
                        text = user.address_name,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White,
                        modifier = Modifier
                            .padding(start = 8.dp)
                    )
                }
            }
        }}
    }



    @Composable
    fun MenuItems(user: RDbEntity){
        var expend by remember {
            mutableStateOf(false)
        }
        Box(
            modifier = Modifier
                .padding(top =8.dp, end = 3.dp)

        ) {
            IconButton(onClick = { expend = !expend },
            ) {
                Icon(Icons.Default.Edit, contentDescription = "More options", tint = Color.White)
            }
            DropdownMenu(
                expanded = expend,
                onDismissRequest = { expend = false },
                modifier = Modifier
                    .width(100.dp)
            ) {
                DropdownMenuItem(
                    text = { Text("Delete") },
                    onClick = {
                        if (showRDbDataViewModel.deleteUser(user) > 0) {
                            showRDbDataViewModel.fetchAllUsers()
                            Toast
                                .makeText(
                                    applicationContext,
                                    "Deleted Succefully",
                                    Toast.LENGTH_SHORT
                                )
                                .show()
                        } else {
                            Toast
                                .makeText(applicationContext, "Failed", Toast.LENGTH_SHORT)
                                .show()
                        }
                        expend = false
                    }
                )
                DropdownMenuItem(
                    text = { Text("Update") },
                    onClick = {
                        val intent=Intent(applicationContext,SaveData::class.java)
                        intent.putExtra("userid",user.id)
                        intent.putExtra("updateValue",1)
                        startActivity(intent)
                        expend = false
                    }
                )
            }
        }
    }
    private fun byteArrayToBitmap(imageBytes: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }



    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MyActionBar(Title:String) {
        TopAppBar(
            title = { Text(Title) },
            modifier = Modifier.background(color = Color.White),
            actions = {
                IconButton(onClick = {  }) {
                    Icon(imageVector = Icons.Filled.Search, contentDescription ="Search" )
                }

            },
            navigationIcon = {
                IconButton(onClick = {
                }

                )

                {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
                }
            }
        )
    }
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun NavigationDrawerss() {
        val context = LocalContext.current
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()


            ModalNavigationDrawer(
                drawerState = drawerState,
                drawerContent = {
                    ModalDrawerSheet(
                        Modifier
                            .fillMaxHeight()
                            .width(300.dp)
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
                            onClick = {
                                Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show()
                            },

                            )
                        NavigationDrawerItem(
                            label = { Text(text = "Gallery", color = Color.Black) },
                            selected = false,
                            onClick = {
                                Toast.makeText(context, "Gallery", Toast.LENGTH_SHORT).show()
                            })

                        NavigationDrawerItem(
                            label = { Text(text = "About me", color = Color.Black) },
                            selected = false,
                            onClick = {
                                Toast.makeText(context, "About me", Toast.LENGTH_SHORT).show()
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
                            onClick = {
                                Toast.makeText(context, "Policy", Toast.LENGTH_SHORT).show()
                            })

                        NavigationDrawerItem(
                            label = { Text(text = "Settings", color = Color.Black) },
                            selected = false,
                            onClick = {
                                Toast.makeText(context, "Settings", Toast.LENGTH_SHORT).show()
                            })


                        NavigationDrawerItem(
                            label = { Text(text = "Logout", color = Color.Black) },
                            selected = false,
                            onClick = {
                                Toast.makeText(context, "Logout", Toast.LENGTH_SHORT).show()
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
            }
//}

            // Screen content


        }




    @Preview
    @Composable
    fun MyPreview(){
        MaterialTheme {
        }
    }
}
