package com.example.eva1

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "appDB.db"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE Usuarios (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, contraseña TEXT, isAdmin INTEGER DEFAULT 0)"
        db?.execSQL(createTable)


        val insertAdmin = "INSERT INTO Usuarios (nombre, contraseña, isAdmin) VALUES ('admin', 'admin123', 1)"
        db?.execSQL(insertAdmin)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS Usuarios")
        onCreate(db)
    }


    fun validateUser(username: String, password: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Usuarios WHERE nombre=? AND contraseña=?", arrayOf(username, password))

        return if (cursor.moveToFirst()) {
            cursor.close()
            db.close()
            true
        } else {
            cursor.close()
            db.close()
            false
        }
    }


    fun userExists(nombre: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Usuarios WHERE nombre=?", arrayOf(nombre))
        val exists = cursor.moveToFirst()
        cursor.close()
        db.close()
        return exists
    }


    fun addUser(nombre: String, contrasena: String): Long {
        if (userExists(nombre)) {
            return -1 // Usuario ya existe
        }

        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put("nombre", nombre)
            put("contraseña", contrasena)
        }
        val result = db.insert("Usuarios", null, contentValues)
        db.close()
        return result
    }


    fun updateUser(nombre: String, nuevaContrasena: String): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put("contraseña", nuevaContrasena)
        }
        val result = db.update("Usuarios", contentValues, "nombre=?", arrayOf(nombre))
        db.close()
        return result
    }


    fun deleteUser(nombre: String): Int {
        val db = this.writableDatabase

        if (nombre == "admin") {
            db.close()
            return 0
        }

        val result = db.delete("Usuarios", "nombre=?", arrayOf(nombre))
        db.close()
        return result
    }
}
