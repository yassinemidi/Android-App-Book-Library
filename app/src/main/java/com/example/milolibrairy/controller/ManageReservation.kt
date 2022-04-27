package com.example.milolibrairy.controller

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import com.example.milolibrairy.models.reservation

class ManageReservation(var context: Context)
{
    var dbHelperReservation = FeedReaderDbHelper(context)

    fun addReservation(resv: reservation) {


        val db=dbHelperReservation.writableDatabase

        val values = ContentValues().apply {
            put(FeedReaderContractReservation.FeedEntry.COLUMN_NAME_ID_BOOK,resv.id_book)
            put(FeedReaderContractReservation.FeedEntry.COLUMN_NAME_ID_USER,resv.id_user)

        }
        val newRowId = db?.insert(FeedReaderContractReservation.FeedEntry.TABLE_NAME,null,values)

    }

    fun listReservation():ArrayList<reservation>  {
        var mylist :ArrayList<reservation> = ArrayList<reservation>()
        val db=dbHelperReservation.readableDatabase
        val projection = arrayOf(BaseColumns._ID,
            FeedReaderContractReservation.FeedEntry.COLUMN_NAME_ID_USER,
            FeedReaderContractReservation.FeedEntry.COLUMN_NAME_ID_BOOK
        )
        val sortOrder = "${FeedReaderContractReservation.FeedEntry.COLUMN_NAME_ID_USER} DESC"
        val cursor = db.query(
            FeedReaderContractReservation.FeedEntry.TABLE_NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            null,              // The columns for the WHERE clause
            null,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            sortOrder               // The sort order
        )
        while(cursor.moveToNext()){
            var itemId =cursor.getInt(cursor.getColumnIndexOrThrow(BaseColumns._ID))
            var itemUser = cursor.getInt(cursor.getColumnIndexOrThrow(FeedReaderContractReservation.FeedEntry.COLUMN_NAME_ID_USER))
            var itemBook = cursor.getInt(cursor.getColumnIndexOrThrow(FeedReaderContractReservation.FeedEntry.COLUMN_NAME_ID_BOOK))
            var myresv = reservation(itemId,itemBook,itemUser)
            mylist.add(myresv)

        }
        return mylist

    }



    fun deleteReservation(id: String) {
        val db = dbHelperReservation.writableDatabase
        db.delete(FeedReaderContractReservation.FeedEntry.TABLE_NAME,"_id = ?", arrayOf(id))

    }

    fun updateReservation(resv: reservation){
        val db = dbHelperReservation.writableDatabase
        val values = ContentValues().apply {
            put(FeedReaderContractReservation.FeedEntry.COLUMN_NAME_ID_BOOK,resv.id_book)

            put(FeedReaderContractReservation.FeedEntry.COLUMN_NAME_ID_USER,resv.id_user)

        }
        db.update(FeedReaderContractReservation.FeedEntry.TABLE_NAME,values,"_id=?", arrayOf(resv.id.toString()))

    }

    fun userReservation(user_id:Int):ArrayList<reservation>  {
        var mylist :ArrayList<reservation> = ArrayList<reservation>()
        val db=dbHelperReservation.readableDatabase
        val projection = arrayOf(BaseColumns._ID,
            FeedReaderContractReservation.FeedEntry.COLUMN_NAME_ID_USER,
            FeedReaderContractReservation.FeedEntry.COLUMN_NAME_ID_BOOK
        )
        val sortOrder = "${FeedReaderContractReservation.FeedEntry.COLUMN_NAME_ID_USER} DESC"
        val cursor = db.query(
            FeedReaderContractReservation.FeedEntry.TABLE_NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            "${FeedReaderContractReservation.FeedEntry.COLUMN_NAME_ID_USER} = ?",              // The columns for the WHERE clause
            arrayOf(user_id.toString()),          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            sortOrder               // The sort order
        )
        while(cursor.moveToNext()){
            var itemId =cursor.getInt(cursor.getColumnIndexOrThrow(BaseColumns._ID))
            var itemUser = cursor.getInt(cursor.getColumnIndexOrThrow(FeedReaderContractReservation.FeedEntry.COLUMN_NAME_ID_USER))
            var itemBook = cursor.getInt(cursor.getColumnIndexOrThrow(FeedReaderContractReservation.FeedEntry.COLUMN_NAME_ID_BOOK))
            var myresv = reservation(itemId,itemBook,itemUser)
            mylist.add(myresv)

        }
        return mylist

    }
}
