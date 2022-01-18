package com.example.coroutines.flowApplication.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.coroutines.flowApplication.dao.UserDao
import com.example.coroutines.flowApplication.entity.User

/**

 * Created  by Administrator on 2022/1/18 21:34

 */
@Database(entities = [User::class] , version = 1, exportSchema = false)
abstract  class AppDatabase:RoomDatabase (){
    abstract fun userDao(): UserDao

    companion object{
        private var instance:AppDatabase?=null

        fun  getInstance(context:Context):AppDatabase{
            return instance ?: synchronized(this){
                Room.databaseBuilder(context,AppDatabase::class.java,"appDatabase.db")
                    .allowMainThreadQueries()
                    .build().also {
                        instance=it
                    }
            }
        }
    }
}