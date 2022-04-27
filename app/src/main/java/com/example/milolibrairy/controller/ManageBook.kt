package com.example.milolibrairy.controller

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.provider.BaseColumns
import com.example.milolibrairy.models.book
import java.io.ByteArrayOutputStream


class ManageBook(context: Context) {

    var dbHelperUser = FeedReaderDbHelper(context)

    fun addBook(bk: book) {
        val db=dbHelperUser.writableDatabase
        val values = ContentValues().apply {
            put(FeedReaderContractBook.FeedEntry.COLUMN_NAME_TITLE,bk.title)
            put(FeedReaderContractBook.FeedEntry.COLUMN_NAME_AUTHOR,bk.author)
            put(FeedReaderContractBook.FeedEntry.COLUMN_NAME_DESCRIPTION,bk.description)
            put(FeedReaderContractBook.FeedEntry.COLUMN_NAME_CATEGORY,bk.category)
            put(FeedReaderContractBook.FeedEntry.COLUMN_NAME_IMAGE,bk.image)
            put(FeedReaderContractBook.FeedEntry.COLUMN_NAME_LINK,bk.link)
        }
        val newRowId = db?.insert(FeedReaderContractBook.FeedEntry.TABLE_NAME,null,values)

    }
    fun listbooks():ArrayList<book>  {
        var mylist = ArrayList<book>()
        val db=dbHelperUser.readableDatabase
        val projection = arrayOf(
            BaseColumns._ID,
            FeedReaderContractBook.FeedEntry.COLUMN_NAME_TITLE,
            FeedReaderContractBook.FeedEntry.COLUMN_NAME_AUTHOR,
            FeedReaderContractBook.FeedEntry.COLUMN_NAME_DESCRIPTION,
            FeedReaderContractBook.FeedEntry.COLUMN_NAME_CATEGORY,
            FeedReaderContractBook.FeedEntry.COLUMN_NAME_IMAGE,
            FeedReaderContractBook.FeedEntry.COLUMN_NAME_LINK
        )
        val sortOrder = "${FeedReaderContractBook.FeedEntry.COLUMN_NAME_TITLE} DESC"
        val cursor = db.query(
            FeedReaderContractBook.FeedEntry.TABLE_NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            null,              // The columns for the WHERE clause
            null,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            sortOrder               // The sort order
        )
        while(cursor.moveToNext()){
            var itemId =cursor.getInt(cursor.getColumnIndexOrThrow(BaseColumns._ID))
            var itemTitle = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContractBook.FeedEntry.COLUMN_NAME_TITLE))
            var itemAuthor = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContractBook.FeedEntry.COLUMN_NAME_AUTHOR))
            var itemDescription = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContractBook.FeedEntry.COLUMN_NAME_DESCRIPTION))
            var itemCategory = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContractBook.FeedEntry.COLUMN_NAME_CATEGORY))
            var itemImage = cursor.getBlob(cursor.getColumnIndexOrThrow(FeedReaderContractBook.FeedEntry.COLUMN_NAME_IMAGE))
            var itemLink = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContractBook.FeedEntry.COLUMN_NAME_LINK))
            var mybook = book(itemId,itemTitle,itemAuthor,itemDescription,itemCategory,itemImage,itemLink)
            mylist.add(mybook)

        }
        return mylist

    }


    fun getBytes(bitmap: Bitmap):ByteArray{
        val stream= ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG,0,stream)
        return stream.toByteArray()
    }

    fun getImage(image:ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(image,0,image.size)
    }



    fun deletebook(id: Int) {
        val db = dbHelperUser.writableDatabase
        db.delete(FeedReaderContractBook.FeedEntry.TABLE_NAME,"_id = ?", arrayOf(id.toString()))

    }

    fun updatebook(bk: book){
        val db = dbHelperUser.writableDatabase
        val values = ContentValues().apply {
            put(FeedReaderContractBook.FeedEntry.COLUMN_NAME_TITLE,bk.title)
            put(FeedReaderContractBook.FeedEntry.COLUMN_NAME_AUTHOR,bk.author)
            put(FeedReaderContractBook.FeedEntry.COLUMN_NAME_DESCRIPTION,bk.description)
            put(FeedReaderContractBook.FeedEntry.COLUMN_NAME_CATEGORY,bk.category)
            put(FeedReaderContractBook.FeedEntry.COLUMN_NAME_IMAGE,bk.image)
            put(FeedReaderContractBook.FeedEntry.COLUMN_NAME_LINK,bk.link)
        }
        db?.update(FeedReaderContractBook.FeedEntry.TABLE_NAME,values,"_id=?", arrayOf(bk.id.toString()))

    }


    fun findbook(book_id:Int): book? {
        var mybook :book? = null
        val db=dbHelperUser.readableDatabase
        val projection = arrayOf(
            BaseColumns._ID,
            FeedReaderContractBook.FeedEntry.COLUMN_NAME_TITLE,
            FeedReaderContractBook.FeedEntry.COLUMN_NAME_AUTHOR,
            FeedReaderContractBook.FeedEntry.COLUMN_NAME_DESCRIPTION,
            FeedReaderContractBook.FeedEntry.COLUMN_NAME_CATEGORY,
            FeedReaderContractBook.FeedEntry.COLUMN_NAME_IMAGE,
            FeedReaderContractBook.FeedEntry.COLUMN_NAME_LINK
        )
        val sortOrder = "${FeedReaderContractBook.FeedEntry.COLUMN_NAME_TITLE} DESC"
        val cursor = db.query(
            FeedReaderContractBook.FeedEntry.TABLE_NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            "${BaseColumns._ID} = ?",              // The columns for the WHERE clause
            arrayOf(book_id.toString()),          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            sortOrder               // The sort order
        )
        if(cursor.moveToFirst()){
            var itemId =cursor.getInt(cursor.getColumnIndexOrThrow(BaseColumns._ID))
            var itemTitle = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContractBook.FeedEntry.COLUMN_NAME_TITLE))
            var itemAuthor = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContractBook.FeedEntry.COLUMN_NAME_AUTHOR))
            var itemDescription = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContractBook.FeedEntry.COLUMN_NAME_DESCRIPTION))
            var itemCategory = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContractBook.FeedEntry.COLUMN_NAME_CATEGORY))
            var itemImage = cursor.getBlob(cursor.getColumnIndexOrThrow(FeedReaderContractBook.FeedEntry.COLUMN_NAME_IMAGE))
            var itemLink = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContractBook.FeedEntry.COLUMN_NAME_LINK))
             mybook = book(itemId,itemTitle,itemAuthor,itemDescription,itemCategory,itemImage,itemLink)


        }
        return mybook

    }






}
