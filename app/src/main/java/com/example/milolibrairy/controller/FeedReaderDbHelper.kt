package com.example.milolibrairy.controller

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper



class FeedReaderDbHelper(context : Context) : SQLiteOpenHelper(context,
    DATABASE_NAME,null,
    DATABASE_VERSION
) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(com.example.milolibrairy.controller.FeedReaderContractUser.SQL_CREATE_ENTRIES)
        db.execSQL(com.example.milolibrairy.controller.FeedReaderContractBook.SQL_CREATE_ENTRIES)
        db.execSQL(com.example.milolibrairy.controller.FeedReaderContractReservation.SQL_CREATE_ENTRIES)
        db.execSQL(com.example.milolibrairy.controller.FeedReaderContractFeedback.SQL_CREATE_ENTRIES)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(com.example.milolibrairy.controller.FeedReaderContractUser.SQL_DELETE_ENTRIES)
        db.execSQL(com.example.milolibrairy.controller.FeedReaderContractBook.SQL_DELETE_ENTRIES)
        db.execSQL(com.example.milolibrairy.controller.FeedReaderContractReservation.SQL_DELETE_ENTRIES)
        db.execSQL(com.example.milolibrairy.controller.FeedReaderContractFeedback.SQL_DELETE_ENTRIES)

        onCreate(db)
    }
    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
    companion object{
        const val DATABASE_NAME="myDB.db"
        const val DATABASE_VERSION=2
    }



}