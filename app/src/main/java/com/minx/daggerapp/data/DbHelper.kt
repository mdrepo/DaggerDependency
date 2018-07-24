package com.minx.daggerapp.data

import android.content.ContentValues
import android.content.Context
import android.content.res.Resources
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by Mayur on 13/3/18.
 */
@Singleton
class DbHelper @Inject
constructor(context: Context,
            dbName: String,
            version: Int) : SQLiteOpenHelper(context, dbName, null, version) {

    private val currentTimeStamp: String
        get() = (System.currentTimeMillis() / 1000).toString()

    override fun onCreate(db: SQLiteDatabase) {
        tableCreateStatements(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME)
        onCreate(db)
    }

    private fun tableCreateStatements(db: SQLiteDatabase) {
        try {
            db.execSQL(
                    "CREATE TABLE IF NOT EXISTS "
                            + USER_TABLE_NAME + "("
                            + USER_COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + USER_COLUMN_USER_NAME + " VARCHAR(20), "
                            + USER_COLUMN_USER_ADDRESS + " VARCHAR(50), "
                            + USER_COLUMN_USER_CREATED_AT + " VARCHAR(10) DEFAULT " + currentTimeStamp + ", "
                            + USER_COLUMN_USER_UPDATED_AT + " VARCHAR(10) DEFAULT " + currentTimeStamp + ")"
            )

        } catch (e: SQLException) {
            e.printStackTrace()
        }

    }

    @Throws(Resources.NotFoundException::class, NullPointerException::class)
    fun getUser(userId: Long): User {
        var cursor: Cursor? = null
        try {
            val db = this.readableDatabase
            cursor = db.rawQuery(
                    ("SELECT * FROM "
                            + USER_TABLE_NAME
                            + " WHERE "
                            + USER_COLUMN_USER_ID
                            + " = ? "),
                    arrayOf((userId).toString() + ""))

            if (cursor.count > 0) {
                cursor.moveToFirst()
                val user = User()
                user.id = cursor.getLong(cursor.getColumnIndex(USER_COLUMN_USER_ID))
                user.name = cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_NAME))
                user.address = cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_ADDRESS))
                user.createdAt = cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_CREATED_AT))
                user.updatedAt = cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_UPDATED_AT))
                return user
            } else {
                throw Resources.NotFoundException("User with id $userId does not exists")
            }
        } catch (e: NullPointerException) {
            e.printStackTrace()
            throw e
        } finally {
                cursor?.close()
        }
    }

    @Throws(Exception::class)
    fun insertUser(user: User): Long? {
        try {
            val db = this.writableDatabase
            val contentValues = ContentValues()
            contentValues.put(USER_COLUMN_USER_NAME, user.name)
            contentValues.put(USER_COLUMN_USER_ADDRESS, user.address)
            return db.insert(USER_TABLE_NAME, null, contentValues)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }

    }

    companion object {

        //USER TABLE
        val USER_TABLE_NAME = "users"
        val USER_COLUMN_USER_ID = "id"
        val USER_COLUMN_USER_NAME = "usr_name"
        val USER_COLUMN_USER_ADDRESS = "usr_add"
        val USER_COLUMN_USER_CREATED_AT = "created_at"
        val USER_COLUMN_USER_UPDATED_AT = "updated_at"
    }
}