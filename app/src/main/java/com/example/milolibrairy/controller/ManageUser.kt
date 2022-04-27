package com.example.milolibrairy.controller

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import com.example.milolibrairy.models.reservation
import com.example.milolibrairy.models.user

class ManageUser(context: Context) {

    var dbHelperUser = FeedReaderDbHelper(context)
    fun AddUser(user: user) {
        val db=dbHelperUser.writableDatabase
        val values = ContentValues().apply {
            put(FeedReaderContractUser.FeedEntry.COLUMN_NAME_NOM,user.nom)
            put(FeedReaderContractUser.FeedEntry.COLUMN_NAME_PRENOM,user.prenom)
            put(FeedReaderContractUser.FeedEntry.COLUMN_NAME_EMAIL,user.email)
            put(FeedReaderContractUser.FeedEntry.COLUMN_NAME_PASSWORD,user.password)
            put(FeedReaderContractUser.FeedEntry.COLUMN_NAME_TYPE,user.type)
        }
        val newRowId = db?.insert(FeedReaderContractUser.FeedEntry.TABLE_NAME,null,values)
        
    }
    fun listuser():ArrayList<user>  {
        var mylist :ArrayList<user> = ArrayList<user>()
        val db=dbHelperUser.readableDatabase
        val projection = arrayOf(BaseColumns._ID,
            FeedReaderContractUser.FeedEntry.COLUMN_NAME_NOM,
            FeedReaderContractUser.FeedEntry.COLUMN_NAME_PRENOM,
            FeedReaderContractUser.FeedEntry.COLUMN_NAME_EMAIL,
            FeedReaderContractUser.FeedEntry.COLUMN_NAME_PASSWORD,
            FeedReaderContractUser.FeedEntry.COLUMN_NAME_TYPE
        )
        val sortOrder = "${FeedReaderContractUser.FeedEntry.COLUMN_NAME_NOM} DESC"
        val cursor = db.query(
            FeedReaderContractUser.FeedEntry.TABLE_NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            null,              // The columns for the WHERE clause
            null,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            sortOrder               // The sort order
        )
        while(cursor.moveToNext()){
            var itemId =cursor.getInt(cursor.getColumnIndexOrThrow(BaseColumns._ID))
            var itemNom = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContractUser.FeedEntry.COLUMN_NAME_NOM))
            var itemPrenom = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContractUser.FeedEntry.COLUMN_NAME_PRENOM))
            var itemEmail = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContractUser.FeedEntry.COLUMN_NAME_EMAIL))
            var itemPassword = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContractUser.FeedEntry.COLUMN_NAME_PASSWORD))
            var itemType = cursor.getInt(cursor.getColumnIndexOrThrow(FeedReaderContractUser.FeedEntry.COLUMN_NAME_TYPE))
            var myuser = user(itemId,itemNom,itemPrenom,itemEmail,itemPassword,itemType)
            mylist.add(myuser)

        }
        return mylist

    }


    fun finduser(user_id:Int):user?  {
        var myuser:user?=null
        val db=dbHelperUser.readableDatabase
        val projection = arrayOf(BaseColumns._ID,
            FeedReaderContractUser.FeedEntry.COLUMN_NAME_NOM,
            FeedReaderContractUser.FeedEntry.COLUMN_NAME_PRENOM,
            FeedReaderContractUser.FeedEntry.COLUMN_NAME_EMAIL,
            FeedReaderContractUser.FeedEntry.COLUMN_NAME_PASSWORD,
            FeedReaderContractUser.FeedEntry.COLUMN_NAME_TYPE
        )
        val sortOrder = "${FeedReaderContractUser.FeedEntry.COLUMN_NAME_NOM} DESC"
        val cursor = db.query(
            FeedReaderContractUser.FeedEntry.TABLE_NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            "${BaseColumns._ID} = ?",              // The columns for the WHERE clause
            arrayOf(user_id.toString()),          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            sortOrder               // The sort order
        )
        if(cursor.moveToFirst()){
            var itemId =cursor.getInt(cursor.getColumnIndexOrThrow(BaseColumns._ID))
            var itemNom = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContractUser.FeedEntry.COLUMN_NAME_NOM))
            var itemPrenom = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContractUser.FeedEntry.COLUMN_NAME_PRENOM))
            var itemEmail = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContractUser.FeedEntry.COLUMN_NAME_EMAIL))
            var itemPassword = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContractUser.FeedEntry.COLUMN_NAME_PASSWORD))
            var itemType = cursor.getInt(cursor.getColumnIndexOrThrow(FeedReaderContractUser.FeedEntry.COLUMN_NAME_TYPE))
            myuser = user(itemId,itemNom,itemPrenom,itemEmail,itemPassword,itemType)


        }
        return myuser

    }

    fun login(email: String,password : String): user? {
        val db=dbHelperUser.readableDatabase
        val projection = arrayOf(BaseColumns._ID,
            FeedReaderContractUser.FeedEntry.COLUMN_NAME_NOM,
            FeedReaderContractUser.FeedEntry.COLUMN_NAME_PRENOM,
            FeedReaderContractUser.FeedEntry.COLUMN_NAME_EMAIL,
            FeedReaderContractUser.FeedEntry.COLUMN_NAME_PASSWORD,
            FeedReaderContractUser.FeedEntry.COLUMN_NAME_TYPE
        )

        val cursor = db.query(
            FeedReaderContractUser.FeedEntry.TABLE_NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            "email = ? and password = ?",              // The columns for the WHERE clause
            arrayOf(email,password),          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            null               // The sort order
        )
        if(cursor.moveToNext()){
            return  user(cursor.getInt(cursor.getColumnIndexOrThrow(BaseColumns._ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContractUser.FeedEntry.COLUMN_NAME_NOM)),
                cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContractUser.FeedEntry.COLUMN_NAME_PRENOM)),
                cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContractUser.FeedEntry.COLUMN_NAME_EMAIL)),
                cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContractUser.FeedEntry.COLUMN_NAME_PASSWORD)),
                cursor.getInt(cursor.getColumnIndexOrThrow(FeedReaderContractUser.FeedEntry.COLUMN_NAME_TYPE)))
        }else{
            return  null
        }






    }
    fun updateuser(user: user){
        val db = dbHelperUser.writableDatabase
        val values = ContentValues().apply {
            put(FeedReaderContractUser.FeedEntry.COLUMN_NAME_NOM,user.nom)
            put(FeedReaderContractUser.FeedEntry.COLUMN_NAME_PRENOM,user.prenom)
            put(FeedReaderContractUser.FeedEntry.COLUMN_NAME_EMAIL,user.email)
            put(FeedReaderContractUser.FeedEntry.COLUMN_NAME_PASSWORD,user.password)
            put(FeedReaderContractUser.FeedEntry.COLUMN_NAME_TYPE,user.type)

        }
        db?.update(FeedReaderContractUser.FeedEntry.TABLE_NAME,values,"_id=?", arrayOf(user.id.toString()))

    }
}