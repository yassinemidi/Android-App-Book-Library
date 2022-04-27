package com.example.milolibrairy.controller

import android.provider.BaseColumns

object FeedReaderContractUser {
    object FeedEntry : BaseColumns {
        const val TABLE_NAME = "user"
        const val COLUMN_NAME_NOM="nom"
        const val COLUMN_NAME_PRENOM="prenom"
        const val COLUMN_NAME_EMAIL="email"
        const val COLUMN_NAME_PASSWORD="password"
        const val COLUMN_NAME_TYPE="type"
    }
    public const val SQL_CREATE_ENTRIES =
        "CREATE TABLE ${FeedEntry.TABLE_NAME} ("+
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${FeedEntry.COLUMN_NAME_NOM} TEXT," +
                "${FeedEntry.COLUMN_NAME_PRENOM} TEXT," +
                "${FeedEntry.COLUMN_NAME_EMAIL} TEXT,"+
                "${FeedEntry.COLUMN_NAME_PASSWORD} TEXT,"+
                "${FeedEntry.COLUMN_NAME_TYPE} INTEGER)"

    public const val SQL_DELETE_ENTRIES ="DROP TABLE IF EXISTS ${FeedEntry.TABLE_NAME}"
}