package com.workshop.androidarchitecturecomponents.room

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import io.reactivex.Flowable

@Dao
interface UserProfileDao {

    @Insert
    fun insertUserProfile(userProfileEntity: UserProfileEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateUserProfile(userProfileEntity: UserProfileEntity)

    @Update
    fun updateUserProfile(userProfileEntity: UserProfileEntity)

    @Delete
    fun deleteUserProfile(userProfileEntity: UserProfileEntity)

    @Query("SELECT * FROM user_profile")
    fun getAllUserProfile(): LiveData<List<UserProfileEntity>> // return liveData

    @Query("SELECT * FROM user_profile WHERE id = :id")
    fun getUserProfileById(id: Int): Flowable<UserProfileEntity> // return Observer from Rxjava

    @Query("DELETE FROM user_profile")
    fun deleteAllUserProfile()

    @Insert
    fun insertCareer(careerEntity: CareerEntity)

    @Query("SELECT user_profile.first_name AS firstName, career.name AS career FROM user_profile, career WHERE user_profile.first_name = :firstName")
    fun getFirstNameAndCareer(firstName: String): LiveData<FirstNameCareer>

//    @Insert
//    fun insertAddress(addressEntity: AddressEntity)
//
//    @Query("SELECT * FROM address")
//    fun getAllAddress() : LiveData<List<AddressEntity>> // return liveData

}