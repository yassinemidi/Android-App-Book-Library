package com.example.milolibrairy.controller

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns

import com.example.milolibrairy.models.feedback

class ManageFeedback(var context:Context){
    var dbHelperFeedback = FeedReaderDbHelper(context)
    fun addFeedback(feedback: feedback) {
        val db=dbHelperFeedback.writableDatabase
        val values = ContentValues().apply {
            put(FeedReaderContractFeedback.FeedEntry.COLUMN_NAME_ID_BOOK,feedback.id_book)

            put(FeedReaderContractFeedback.FeedEntry.COLUMN_NAME_ID_USER,feedback.id_user)

            put(FeedReaderContractFeedback.FeedEntry.COLUMN_NAME_COMMENT,feedback.comment)

            put(FeedReaderContractFeedback.FeedEntry.COLUMN_NAME_NOTE,feedback.note)

        }
        val newRowId = db?.insert(FeedReaderContractFeedback.FeedEntry.TABLE_NAME,null,values)

    }

    fun findFeedbackUser(id_book:Int,id_user:Int):feedback? {
        var myfdbk:feedback?=null
        val db=dbHelperFeedback.readableDatabase
        val projection = arrayOf(BaseColumns._ID,
            FeedReaderContractFeedback.FeedEntry.COLUMN_NAME_ID_USER,
            FeedReaderContractFeedback.FeedEntry.COLUMN_NAME_ID_BOOK,
            FeedReaderContractFeedback.FeedEntry.COLUMN_NAME_COMMENT,
            FeedReaderContractFeedback.FeedEntry.COLUMN_NAME_NOTE
        )
        val sortOrder = "${FeedReaderContractFeedback.FeedEntry.COLUMN_NAME_ID_USER} DESC"
        val cursor = db.query(
            FeedReaderContractFeedback.FeedEntry.TABLE_NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            "${FeedReaderContractFeedback.FeedEntry.COLUMN_NAME_ID_USER} = ? and ${FeedReaderContractFeedback.FeedEntry.COLUMN_NAME_ID_BOOK} = ?",              // The columns for the WHERE clause
            arrayOf(id_user.toString(),id_book.toString()),          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            sortOrder               // The sort order
        )
        if(cursor.moveToFirst()){
            var itemId =cursor.getInt(cursor.getColumnIndexOrThrow(BaseColumns._ID))
            var itemUser = cursor.getInt(cursor.getColumnIndexOrThrow(FeedReaderContractFeedback.FeedEntry.COLUMN_NAME_ID_USER))
            var itemBook = cursor.getInt(cursor.getColumnIndexOrThrow(FeedReaderContractFeedback.FeedEntry.COLUMN_NAME_ID_BOOK))
            var itemcomm= cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContractFeedback.FeedEntry.COLUMN_NAME_COMMENT))
            var itemnote= cursor.getInt(cursor.getColumnIndexOrThrow(FeedReaderContractFeedback.FeedEntry.COLUMN_NAME_NOTE))
            myfdbk = feedback(itemId,itemUser,itemBook,itemnote,itemcomm)

        }
        return myfdbk

    }

    fun findFeedbackBook(id_book:Int):ArrayList<feedback> {
        var myfdbks=ArrayList<feedback>()
        val db=dbHelperFeedback.readableDatabase
        val projection = arrayOf(BaseColumns._ID,
            FeedReaderContractFeedback.FeedEntry.COLUMN_NAME_ID_USER,
            FeedReaderContractFeedback.FeedEntry.COLUMN_NAME_ID_BOOK,
            FeedReaderContractFeedback.FeedEntry.COLUMN_NAME_COMMENT,
            FeedReaderContractFeedback.FeedEntry.COLUMN_NAME_NOTE
        )
        val sortOrder = "${FeedReaderContractFeedback.FeedEntry.COLUMN_NAME_ID_USER} DESC"
        val cursor = db.query(
            FeedReaderContractFeedback.FeedEntry.TABLE_NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            "${FeedReaderContractFeedback.FeedEntry.COLUMN_NAME_ID_BOOK} = ?",              // The columns for the WHERE clause
            arrayOf(id_book.toString()),          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            sortOrder               // The sort order
        )
        while(cursor.moveToNext()){
            var itemId =cursor.getInt(cursor.getColumnIndexOrThrow(BaseColumns._ID))
            var itemUser = cursor.getInt(cursor.getColumnIndexOrThrow(FeedReaderContractFeedback.FeedEntry.COLUMN_NAME_ID_USER))
            var itemBook = cursor.getInt(cursor.getColumnIndexOrThrow(FeedReaderContractFeedback.FeedEntry.COLUMN_NAME_ID_BOOK))
            var itemcomm= cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContractFeedback.FeedEntry.COLUMN_NAME_COMMENT))
            var itemnote= cursor.getInt(cursor.getColumnIndexOrThrow(FeedReaderContractFeedback.FeedEntry.COLUMN_NAME_NOTE))
            myfdbks.add(feedback(itemId,itemUser,itemBook,itemnote,itemcomm))

        }
        return myfdbks

    }



    fun listFeedback():ArrayList<feedback>  {
        var mylist :ArrayList<feedback> = ArrayList<feedback>()
        val db=dbHelperFeedback.readableDatabase
        val projection = arrayOf(BaseColumns._ID,
            FeedReaderContractFeedback.FeedEntry.COLUMN_NAME_ID_USER,
            FeedReaderContractFeedback.FeedEntry.COLUMN_NAME_ID_BOOK,
            FeedReaderContractFeedback.FeedEntry.COLUMN_NAME_COMMENT,
            FeedReaderContractFeedback.FeedEntry.COLUMN_NAME_NOTE
        )
        val sortOrder = "${FeedReaderContractFeedback.FeedEntry.COLUMN_NAME_NOTE} DESC"
        val cursor = db.query(
            FeedReaderContractFeedback.FeedEntry.TABLE_NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            null,              // The columns for the WHERE clause
            null,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            sortOrder               // The sort order
        )
        while(cursor.moveToNext()){
            var itemId =cursor.getInt(cursor.getColumnIndexOrThrow(BaseColumns._ID))
            var itemUser = cursor.getInt(cursor.getColumnIndexOrThrow(FeedReaderContractFeedback.FeedEntry.COLUMN_NAME_ID_USER))
            var itemBook = cursor.getInt(cursor.getColumnIndexOrThrow(FeedReaderContractFeedback.FeedEntry.COLUMN_NAME_ID_BOOK))
            var itemcomm= cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContractFeedback.FeedEntry.COLUMN_NAME_COMMENT))
            var itemnote= cursor.getInt(cursor.getColumnIndexOrThrow(FeedReaderContractFeedback.FeedEntry.COLUMN_NAME_NOTE))
            var myfdbk = feedback(itemId,itemBook,itemUser,itemnote,itemcomm)
            mylist.add(myfdbk)

        }
        return mylist

    }
    fun deleteFeedback(id: String) {
        val db = dbHelperFeedback.writableDatabase
        db.delete(FeedReaderContractFeedback.FeedEntry.TABLE_NAME,"_id=?", arrayOf(id))

    }

    fun deleteFeedbackBook(book_id:Int) {
        val db = dbHelperFeedback.writableDatabase
        db.delete(FeedReaderContractFeedback.FeedEntry.TABLE_NAME,"id_book=?", arrayOf(book_id.toString()))

    }



    fun updateFeedback(feedback: feedback){
        val db = dbHelperFeedback.writableDatabase
        val values = ContentValues().apply {
            put(FeedReaderContractFeedback.FeedEntry.COLUMN_NAME_ID_BOOK,feedback.id_book)
            put(FeedReaderContractFeedback.FeedEntry.COLUMN_NAME_ID_USER,feedback.id_user)
            put(FeedReaderContractFeedback.FeedEntry.COLUMN_NAME_NOTE,feedback.note)
            put(FeedReaderContractFeedback.FeedEntry.COLUMN_NAME_COMMENT,feedback.comment)
        }
        db.update(FeedReaderContractFeedback.FeedEntry.TABLE_NAME,values,"_id=?", arrayOf(feedback.id.toString()))

    }


}