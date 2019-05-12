package com.workshop.androidarchitecturecomponents.room

import android.arch.lifecycle.Observer
import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Room
import android.arch.persistence.room.migration.Migration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import java.util.*
import kotlin.concurrent.thread



class RoomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.workshop.androidarchitecturecomponents.R.layout.activity_room)

        // persistance database
        val userProfileDatabase = Room.databaseBuilder(this, AppDatabase::class.java, "room-database").build()

        // in memory database
        val db = Room.inMemoryDatabaseBuilder(this, AppDatabase::class.java).build()

        val userProfile = UserProfileEntity().apply {
            firstName = "Wasit"
            lastName = "Poonjaroen"
            birthDay = Date()
            address = AddressEntity().apply {
                province = "Bangkok111"
                zipCode = "10310"
            }
        }

        val careerMobileDeveloper = CareerEntity().apply {
            name = "Mobile developer"
        }

        val careerQA = CareerEntity().apply {
            name = "UQ"
        }

        thread {
            userProfileDatabase.userProfileDao().insertUserProfile(userProfile)
            userProfileDatabase.userProfileDao().insertCareer(careerMobileDeveloper)
            userProfileDatabase.userProfileDao().insertCareer(careerQA)
            val allUserProfile = userProfileDatabase.userProfileDao().getAllUserProfile()
            allUserProfile.observe(this, Observer {
                it?.forEach { userProfileEntity ->
                    Log.d("---->", "$userProfileEntity")
                }
            })

            // get combine 2 object
            val firstNameCareer = userProfileDatabase.userProfileDao().getFirstNameAndCareer("Wasit")
            firstNameCareer.observe(this, Observer {
                Log.d("---->", "$it")
            })
        }

        thread {
            db.userProfileDao().insertUserProfile(userProfile)
            db.userProfileDao().insertCareer(careerMobileDeveloper)
            db.userProfileDao().insertCareer(careerQA)
            val allUserProfile = db.userProfileDao().getAllUserProfile()
            allUserProfile.observe(this, Observer {
                it?.forEach { userProfileEntity ->
                    Log.d("----> memory", "$userProfileEntity")
                }
            })
            val firstNameCareer = db.userProfileDao().getFirstNameAndCareer("Wasit")
            firstNameCareer.observe(this, Observer {
                Log.d("----> memory", "$it")
            })
        }



    }

    // Migration (need to change version on AppDatabase class)
    private val MIGRATION_1_to_2: Migration = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE career "
                    + " ADD COLUMN salary INTEGER")
        }
    }
}
