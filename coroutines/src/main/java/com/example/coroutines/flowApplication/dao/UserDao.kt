package com.example.coroutines.flowApplication.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.coroutines.flowApplication.entity.User
import kotlinx.coroutines.flow.Flow

/**

 * Created  by Administrator on 2022/1/18 21:41

 */
@Dao
interface UserDao {

    //如果userId相同则替换
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertUser(user:User)

     //这里不需要加suspend 因为返回是Flow不需要加
    @Query("SELECT * FROM user")
     fun getAllUser() : Flow<List<User>>

    //这里不需要加suspend 因为返回是Flow不需要加
    @Query("DELETE FROM user WHERE userId=:userId" )
    fun deleteUser(userId:String)

}