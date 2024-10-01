
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

        // Insertar un administrador predeterminado
        val insertAdmin = "INSERT INTO Usuarios (nombre, contraseña, isAdmin) VALUES ('admin', 'admin123', 1)"
        db?.execSQL(insertAdmin)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS Usuarios")
        onCreate(db)
    }

    // Método para validar usuario y contraseña
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
}
