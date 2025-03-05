package com.example.comopseapp.UII.View

import RoomDbWithMVVMWithLayers.Data.Local.RDbEntity
import RoomDbWithMVVMWithLayers.UI.ViewModel.MyViewModel
import RoomDbWithMVVMWithLayers.UI.ViewModel.SaveDataViewModel
import RoomDbWithMVVMWithLayers.UI.ViewModel.UserViewModel
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.material3.TextField
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.comopseapp.R
import com.example.comopseapp.R.drawable.axis_bank_logo2
import com.example.comopseapp.ui.theme.AxisColor
import com.example.comopseapp.ui.theme.ComopseAppTheme
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import kotlin.properties.Delegates

class SaveData : ComponentActivity() {

    private lateinit var imageDp: Bitmap
    private var userId by Delegates.notNull<Int>()
    private var updateValue: Int = 0
    lateinit var saveDataViewModel: SaveDataViewModel
    private lateinit var userviewmodel: UserViewModel
    lateinit var myViewModel: MyViewModel
    private var PICK_IMAGE_REQUEST = 1
    private val CAMERA_REQUEST_CODE = 100
    private val CAMERA_PERMISSION_CODE = 101
    var firstName by mutableStateOf("")
    var middleName by mutableStateOf("")
    var lastName by mutableStateOf("")
    var email by mutableStateOf("")
    var phone by mutableStateOf("")


    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComopseAppTheme {

                  imageDp = BitmapFactory.decodeResource(
                    applicationContext.resources,
                    R.drawable.useraccount
                )
                SaveDataScreen()
            }
        }

        userviewmodel = ViewModelProvider(this).get(UserViewModel::class.java)

        saveDataViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(SaveDataViewModel::class.java)

        myViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(MyViewModel::class.java)

        val intent = intent
        updateValue = intent.getIntExtra("updateValue", 0)
        userId = intent.getIntExtra("userid", -1)

        if (userId != -1) {
            saveDataViewModel.getUserById(userId)
            saveDataViewModel.userLiveData.observe(this) {
                firstName = it.first_name
                middleName = it.middle_name
                lastName = it.last_name
                phone = it.phone_name
                email = it.email_name
                imageDp = byteArrayToBitmap(it.image)
                setContent {
                    SaveDataScreen()
                }

                Save_Update_Button = "Update"
            }
        }

        userviewmodel.toastMessage.observe(this) { message ->
            message?.let {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                userviewmodel.clearToast()
            }
        }

    }


    ////////////////////////////////////////////////////////////////////////////////////////////
    @Composable
    fun SaveDataScreen() {
        Column(
            modifier = Modifier
                .background(AxisColor)
                .fillMaxSize()
        ) {

            ShowData().MyActionBar(Title = "Customer Account")

            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.White,
                                AxisColor,
                                Color.White
                            )
                        )
                    )
            ) {
                UpperColumn()
                UserInfoForm()
            }
        }
    }



//////////////////////////////////////////////////////////////////////////////////

    @Composable
    fun UpperColumn() {
        val imagedp3 by remember { mutableStateOf(imageDp) }
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = axis_bank_logo2),
                contentDescription = "Axis Bank",
                modifier = Modifier
                    .padding(5.dp)
                    .align(Alignment.CenterHorizontally)
                    .size(30.dp),
                tint = AxisColor
            )
            Text(
                text = "Customer Account Sign_Up",
                style = MaterialTheme.typography.labelLarge.copy(fontSize = 15.sp),
                color = AxisColor,
                modifier = Modifier
                    .padding(5.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Image(
                bitmap = imagedp3.asImageBitmap(), contentDescription = "My DP",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .align(Alignment.CenterHorizontally)
            )
            Box (
                modifier = Modifier
                    .clip(CircleShape)
                    .align(Alignment.CenterHorizontally)
            ){
                MenuItems()
            }
        }
    }



//////////////////////////////////////////////////////////////////////////////////



    @Composable
    fun UserInfoForm() {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {


            TextField(
                modifier = Modifier
                    .padding(5.dp)
                    .align(Alignment.CenterHorizontally),
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text("First Name") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                )
            )

            TextField(
                modifier = Modifier
                    .padding(5.dp)
                    .align(Alignment.CenterHorizontally),
                value = middleName,
                onValueChange = { middleName = it },
                label = { Text("Middle Name") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                )
            )

            TextField(
                modifier = Modifier
                    .padding(5.dp)
                    .align(Alignment.CenterHorizontally),
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text("Last Name") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                )
            )

            TextField(
                modifier = Modifier
                    .padding(5.dp)
                    .align(Alignment.CenterHorizontally),
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Phone") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Phone
                )
            )

            TextField(
                modifier = Modifier
                    .padding(5.dp)
                    .align(Alignment.CenterHorizontally),
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Email
                )
            )
        }
        ButtonsFun(firstName, middleName, lastName, phone, email)
    }


    var Save_Update_Button by mutableStateOf("Save")

//////////////////////////////////////////////////////////////////////////////////

    @Composable
    fun ButtonsFun(
        firstname: String,
        middlename: String,
        lastname: String,
        phone: String,
        email: String
    ) {
        var context = LocalContext.current.applicationContext

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            Arrangement.Center
        ) {
            Button(
                onClick = {
                    if (userviewmodel.validateUser(email.trim(),phone.trim(),firstname.trim())){
                    if (updateValue == 1) {
                        val rDbEntity = RDbEntity(
                            userId,
                            firstname.trim(),
                            middlename.trim(),
                            lastname.trim(),
                            phone.trim(),
                            email.trim(),
                            "Dubai",
                            BitmapToByteArray(imageDp)
                        )
                        val result = saveDataViewModel.updateuser(rDbEntity)
                        if (result > 0) {
                            Toast.makeText(context, "Updated", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        val rDbEntity =
                                RDbEntity(0,
                                firstname.trim(),
                                middlename.trim(),
                                lastname.trim(),
                                phone.trim(),
                                email.trim(),
                                "Dubai",
                                BitmapToByteArray(imageDp))
                        val result = saveDataViewModel.insertUsers(rDbEntity)
                        if (result > 0) {
                            Clearfields()
                            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
                            val intent = Intent(applicationContext, ShowData::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                    else return@Button
                          },
                modifier = Modifier
                    .background(color = AxisColor)
                    .padding(5.dp),

                colors = ButtonDefaults.buttonColors(
                    AxisColor,
                    contentColor = Color.White
                )
            ) {
                Text(text = Save_Update_Button, color = Color.White)
            }
            Button(
                onClick = {
                    val intent = Intent(context, ShowData::class.java)
                    startActivity(intent)
                },
                modifier = Modifier
                    .background(color = AxisColor)
                    .padding(5.dp),
                colors = ButtonDefaults.buttonColors(AxisColor, contentColor = Color.White)
            )
            {
                Text(text = "Show", color = Color.White)
            }
        }
    }
//////////////////////////////////////////////////////////////////////////////////

    fun BitmapToByteArray(bitmapicons: Bitmap): ByteArray {
        val bitmap = bitmapicons
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }

    private fun byteArrayToBitmap(imageBytes: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }

    fun Clearfields() {
        firstName = ""
        middleName = ""
        lastName = ""
        phone = ""
        email = ""
        imageDp = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.useraccount)
    }

    //////////////////////////////////////////////////////////////////////////////////
    @Composable
    fun MenuItems(){
        var expend by remember {
            mutableStateOf(false)
        }
        Box(
            modifier = Modifier
//                .align(Alignment.End)

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
                    text = { Text("Gallery") },
                    onClick = {
                        val intent = Intent(Intent.ACTION_PICK)
                        intent.setType("image/*")
                        startActivityForResult(intent,PICK_IMAGE_REQUEST)
                        expend = false
                    }
                )
                DropdownMenuItem(
                    text = { Text("Camera") },
                    onClick = {
                        if (isCameraPermissionGranted()) {
                            openCamera()
                        } else {
                            requestCameraPermission()
                        }
                        expend = false
                    }
                )
            }
        }
    }

//////////////////////////////////////////////////////////////////////////////////
@Deprecated("Deprecated in Java")
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.data != null) {
        val imageUri: Uri = data.data!!
        try {
            val inputStream: InputStream? = contentResolver.openInputStream(imageUri)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            val compressedBitmap = Bitmap.createScaledBitmap(bitmap, 500, 500, true)

            imageDp = compressedBitmap
            setContent {
                SaveDataScreen()
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
        val photo: Bitmap = data?.extras?.get("data") as Bitmap
        imageDp=photo
//                Toast.makeText(this,imageDp.toString(),Toast.LENGTH_SHORT).show()
        setContent {
            SaveDataScreen()
        }
    }
}


//////////////////////////////////////////////////////////////////////////////////

    private fun isCameraPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            this, android.Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestCameraPermission() {
        ActivityCompat.requestPermissions(
            this, arrayOf(android.Manifest.permission.CAMERA),
            CAMERA_PERMISSION_CODE
        )
    }

    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE)
        } catch (e: Exception) {
            Toast.makeText(this, "$e", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }



    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun NavigationDrawerss() {
        val context= LocalContext.current
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
                                painter = painterResource(id = axis_bank_logo2),
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
                            Toast.makeText(context, "Menu", Toast.LENGTH_SHORT).show()
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
                SaveDataScreen()
                // Screen content
            }



    }

    @Preview
    @Composable
    fun MyPreview() {
//        NavigationDrawerss()
    }
}


