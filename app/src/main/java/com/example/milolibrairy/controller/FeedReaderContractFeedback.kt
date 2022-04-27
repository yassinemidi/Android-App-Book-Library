package com.example.milolibrairy.controller

import android.provider.BaseColumns

object FeedReaderContractFeedback {
    object FeedEntry : BaseColumns {
        const val TABLE_NAME = "feedback"
        const val COLUMN_NAME_ID_BOOK ="id_book"
        const val COLUMN_NAME_ID_USER="id_user"
        const val COLUMN_NAME_NOTE ="note"
        const val COLUMN_NAME_COMMENT="comment"
    }
    public const val SQL_CREATE_ENTRIES =
        "CREATE TABLE ${FeedEntry.TABLE_NAME} ("+
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${FeedEntry.COLUMN_NAME_ID_BOOK} INTEGER," +
                "${FeedEntry.COLUMN_NAME_ID_USER} INTEGER,"+
                "${FeedEntry.COLUMN_NAME_NOTE} INTEGER,"+
                "${FeedEntry.COLUMN_NAME_COMMENT} TEXT)"

    public const val SQL_DELETE_ENTRIES ="DROP TABLE IF EXISTS ${FeedEntry.TABLE_NAME}"
}