package RoomDbWithMVVMWithLayers.UI.ViewModel

import RoomDbWithMVVMWithLayers.Data.Local.MyRoomDb
import RoomDbWithMVVMWithLayers.Data.Local.RDbEntity
import RoomDbWithMVVMWithLayers.Data.Local.UserDao
import RoomDbWithMVVMWithLayers.Data.Repository.UserRepositoryRDB
import android.app.Application
import android.graphics.BitmapFactory
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.comopseapp.R

class SaveDataViewModel (application: Application):AndroidViewModel(application){
    private var db: UserDao
    private var userRepositoryRDB: UserRepositoryRDB
    private val _userLiveData = MutableLiveData<RDbEntity>()
    val userLiveData: LiveData<RDbEntity> get() = _userLiveData
    init {
        db= MyRoomDb.getInstance(application).UserDao()
        userRepositoryRDB= UserRepositoryRDB(db)
    }

    fun  insertUsers(rDbEntity: RDbEntity):Long{
        val result=userRepositoryRDB.insertUsers(rDbEntity)
        return result
    }

  fun updateuser(rDbEntity: RDbEntity):Int{
        return userRepositoryRDB.updateuser(rDbEntity)
   }

    fun getUserById(userId: Int) {
        val user = userRepositoryRDB.getUserById(userId)
        _userLiveData.postValue(user)
    }
}

class MyViewModel (application: Application):AndroidViewModel(application){
    var bitmapicon by mutableStateOf(
        BitmapFactory.decodeResource(application.resources, R.drawable.useraccount)
    )
}

