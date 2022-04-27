package com.example.milolibrairy.controller

import android.provider.BaseColumns

object FeedReaderContractBook {
    object FeedEntry : BaseColumns {
        const val TABLE_NAME = "book"
        const val COLUMN_NAME_TITLE ="title"
        const val COLUMN_NAME_AUTHOR="author"
        const val COLUMN_NAME_DESCRIPTION="description"
        const val COLUMN_NAME_CATEGORY="category"
        const val COLUMN_NAME_IMAGE="image"
        const val COLUMN_NAME_LINK="link"


    }
    const val SQL_CREATE_ENTRIES =
        "CREATE TABLE ${FeedEntry.TABLE_NAME} ("+
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${FeedEntry.COLUMN_NAME_TITLE} TEXT," +
                "${FeedEntry.COLUMN_NAME_AUTHOR} TEXT,"+
                "${FeedEntry.COLUMN_NAME_DESCRIPTION} TEXT,"+
                "${FeedEntry.COLUMN_NAME_CATEGORY} TEXT,"+
                "${FeedEntry.COLUMN_NAME_IMAGE} BLOB,"+
                "${FeedEntry.COLUMN_NAME_LINK} TEXT)"

    const val SQL_DELETE_ENTRIES ="DROP TABLE IF EXISTS ${FeedEntry.TABLE_NAME}"
}