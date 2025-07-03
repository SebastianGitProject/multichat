package com.example.nonloso
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class Database(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

        private var MAX_ROWS = 7
    // below is the method for creating a database by a sqlite query
    override fun onCreate(db: SQLiteDatabase) {
        val enableForeignKeySupport = "PRAGMA foreign_keys = ON;"  //attivo il foreign key che di default è su off
        db.execSQL(enableForeignKeySupport)

        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USERNAME_COl + " TEXT," +
                EMAIL_COL + " INTEGER," +
                PASSWORD_COL + " TEXT," +
                ID_AVATAR_COL + " INTEGER" + ")")

        db.execSQL(query)
        val query2 = ("CREATE TABLE " + TABLE_NAME2 + " ("
                + ID_COL2 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USERNAME_COl2 + " TEXT," +
                TEMPO_COL + " INTEGER," +
                NUMMES_COL + " INTEGER," +
                NOMEMOD_COL + " TEXT," +
                ID_AVATAR_COL2 + " INTEGER," +
                SELEZIONATO + " INTEGER," +
                "FOREIGN KEY ($USERNAME_COl2) REFERENCES $TABLE_NAME($USERNAME_COl)," +
                "FOREIGN KEY ($ID_COL2) REFERENCES $TABLE_NAME($ID_COL)," +
                "FOREIGN KEY ($ID_AVATAR_COL2) REFERENCES $TABLE_NAME($ID_AVATAR_COL)" +")")


        db.execSQL(query2)
        val query3 = ("CREATE TABLE " + TABLE_NAME3 + " ("
                + ID_COL3 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USERNAME_COl3 + " TEXT," +
                NUMFILE_COL + " INTEGER," +
                SELEZIONATO2 + " INTEGER," +
                "FOREIGN KEY ($USERNAME_COl3) REFERENCES $TABLE_NAME2($USERNAME_COl2)," +
                "FOREIGN KEY ($ID_COL3) REFERENCES $TABLE_NAME2($ID_COL2)," +
                "FOREIGN KEY ($SELEZIONATO2) REFERENCES $TABLE_NAME2($SELEZIONATO)" +")")
        db.execSQL(query3)
        val query4 = ("CREATE TABLE " + TABLE_NAME4 + " ("
                + ID_COL4 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USERNAME_COl4 + " TEXT," +
                SELEZIONATO3 + " INTEGER," +
                DATAORA_COL2 + " INTEGER," +
                "FOREIGN KEY ($USERNAME_COl4) REFERENCES $TABLE_NAME3($USERNAME_COl3)," +
                "FOREIGN KEY ($ID_COL4) REFERENCES $TABLE_NAME3($ID_COL3)," +
                "FOREIGN KEY ($SELEZIONATO3) REFERENCES $TABLE_NAME3($SELEZIONATO2)" +")")
        db.execSQL(query4)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // this method is to check if table already exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2)
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME3)
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME4)
        onCreate(db)
    }

    fun deleteTable4(): Int {
        val db = this.writableDatabase
        // Eseguire la query di cancellazione
        return db.delete(TABLE_NAME4, null, null)
    }

    @SuppressLint("Range")
    fun logTableNames() {
        val db = this.writableDatabase
        val cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null)

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                Log.v("Helper", "Table name: " + cursor.getString(cursor.getColumnIndex("name")))
                cursor.moveToNext()
            }
        }

        cursor.close()
    }
    
    @SuppressLint("Recycle")
    fun addName(username : String, email : String, password : String ): Boolean {
        val db = this.writableDatabase
        val cursor = db.rawQuery(/* sql = */ "INSERT INTO $TABLE_NAME($USERNAME_COl,$EMAIL_COL,$PASSWORD_COL,$ID_AVATAR_COL)VALUES('$username','$email','$password',0)" , /* selectionArgs = */ null)
        if(cursor.moveToFirst()){
            return true
        }
        return false
    }
    fun updateAvatar(ID_AVATAR : Int, username: String){
        val db = this.writableDatabase
        db.execSQL("UPDATE $TABLE_NAME SET $ID_AVATAR_COL = $ID_AVATAR WHERE $USERNAME_COl = '$username';")
    }

    @SuppressLint("Recycle", "SuspiciousIndentation")
    fun addModel(username: String,tempo : Int, nummes : Int, nomemod: String, idvatar: Int): Boolean {
        val db = this.writableDatabase
        val cursor2 = db.rawQuery("SELECT count(*) FROM $TABLE_NAME2 WHERE $USERNAME_COl2 = '$username'; ", null)
        cursor2.moveToFirst()
        val rowCount = cursor2.getInt(0)
        cursor2.close()

        if (rowCount >= MAX_ROWS) { //se la tabella ha già 4 righe con lo stesso username, si limita ha fare l'update delle righe esistenti con i nuovi dati
            db.execSQL(/* sql = */ "UPDATE $TABLE_NAME2 SET $TEMPO_COL = $tempo WHERE $ID_AVATAR_COL2 = $idvatar AND $USERNAME_COl2 = '$username'; ")
            db.execSQL(/* sql = */ "UPDATE $TABLE_NAME2 SET $NUMMES_COL = $nummes WHERE $ID_AVATAR_COL2 = $idvatar AND $USERNAME_COl2 = '$username'; ")
            return false
        } else {
        val cursor = db.rawQuery(/* sql = */ "INSERT INTO $TABLE_NAME2($USERNAME_COl2,$TEMPO_COL,$NUMMES_COL,$NOMEMOD_COL,$ID_AVATAR_COL2,$SELEZIONATO)VALUES('$username',$tempo,$nummes,'$nomemod',$idvatar,0)" , /* selectionArgs = */ null)
            if(cursor.moveToFirst()){
                return true
            }
            return false
        }


    }

    fun addConvers(username: String, numfile: Int, selezionato: Int): Boolean{ //su selezionato non impostarlo subito a 0, ma prenderlo da GlobalVars dalla tabella2
        val db = this.writableDatabase
        val cursor2 = db.rawQuery("SELECT count(*) FROM $TABLE_NAME3 WHERE $USERNAME_COl3 = '$username' AND $SELEZIONATO2 = $selezionato ; ", null)
        cursor2.moveToFirst()
        val rowCount = cursor2.getInt(0)
        cursor2.close()

        if (rowCount >= 1) {
            db.execSQL("UPDATE $TABLE_NAME3 SET $NUMFILE_COL = $numfile WHERE $USERNAME_COl3 = '$username' AND $SELEZIONATO2 = $selezionato ;")
            return false
        }else {
            val cursor = db.rawQuery("INSERT INTO $TABLE_NAME3($USERNAME_COl3,$NUMFILE_COL,$SELEZIONATO2)VALUES('$username',$numfile,$selezionato)", null)
            if (cursor.moveToFirst()) {
                return true
            }
            return false
        }


    }

    fun addInfoConv(username: String, selezionato: Int, dataora: String): Boolean{
        val db = this.writableDatabase
        val cursor = db.rawQuery(/* sql = */ "INSERT INTO $TABLE_NAME4($USERNAME_COl4,$SELEZIONATO3,$DATAORA_COL2)VALUES('$username',$selezionato,'$dataora')" , /* selectionArgs = */ null)
        if(cursor.moveToFirst()){
            return true
        }
        return false
    }

    @SuppressLint("Range")
    fun getInfoConv(username: String, selezionato: Int): List<String>? {
        val db = this.readableDatabase
        val data = mutableListOf<String>()
        // Eseguire la query
        val cursor = db.rawQuery(/* sql = */ "SELECT $DATAORA_COL2 FROM $TABLE_NAME4 WHERE $USERNAME_COl4 = '$username' AND $SELEZIONATO3 = $selezionato;", /* selectionArgs = */
            null)

        if (cursor.moveToFirst()) {
            do{
                data.add(cursor.getString(cursor.getColumnIndex(DATAORA_COL2)))
            }while (cursor.moveToNext())
        }
        cursor.close()
        return data
    }



    fun aggiornaConvers(username: String, numfile: Int, selezionato: Int){
        val db = this.writableDatabase
        db.execSQL("UPDATE $TABLE_NAME3 SET $NUMFILE_COL = $numfile WHERE $USERNAME_COl3 = '$username' AND $SELEZIONATO2 = $selezionato ;")
    }

    data class NumFile(val numfile: Int)

    @SuppressLint("Range")
    fun getNumFile(username: String, selezionato: Int): NumFile? {
        val db = this.readableDatabase
        var file: NumFile? = null
        val cursor = db.rawQuery("SELECT $NUMFILE_COL FROM $TABLE_NAME3 WHERE $USERNAME_COl3 = '$username' AND $SELEZIONATO2 = $selezionato ;", null)
        if (cursor.moveToFirst()) {
            var numfile = 0
            do{
                numfile = cursor.getInt(cursor.getColumnIndex(NUMFILE_COL))
            }while (cursor.moveToNext())

            file = NumFile(numfile)
        }
        cursor.close()
        return file
    }

    fun aggiornaSelezionato(selezionato: Int, idavatar: Int, username: String) {
        val db = this.writableDatabase
        db.execSQL(/* sql = */ "UPDATE $TABLE_NAME2 SET $SELEZIONATO = $selezionato WHERE $ID_AVATAR_COL2 = $idavatar AND $USERNAME_COl2 = '$username'; ")
    }



    fun getEmail(username: String, password: String): Boolean {
        val db = this.readableDatabase
        var emailFound = false
        // Eseguire la query
        val cursor = db.rawQuery(/* sql = */ "SELECT $EMAIL_COL FROM $TABLE_NAME WHERE $USERNAME_COl = $username AND $PASSWORD_COL = $password", /* selectionArgs = */
            null)

        // Verificare se il cursore contiene dati e impostare emailFound su true se presente
        if (cursor.moveToFirst()) {
            emailFound = true
        }
        return emailFound
    }


    @SuppressLint("Range")
    fun getEmailValoreString(username: String, password: String): String? {
        val db = this.readableDatabase
        var email: String? = null
        // Eseguire la query
        val cursor = db.rawQuery(/* sql = */ "SELECT $EMAIL_COL FROM $TABLE_NAME WHERE $USERNAME_COl = $username AND $PASSWORD_COL = $password;", /* selectionArgs = */
            null)

        if (cursor.moveToFirst()) {
            do{
                email = cursor.getString(cursor.getColumnIndex(EMAIL_COL))
            }while (cursor.moveToNext())
        }
        cursor.close()
        return email
    }


    data class User(val username: String, val password: String)


    @SuppressLint("SuspiciousIndentation", "Range")
    fun getDimenticata(email: String): User? {
        val db = this.readableDatabase
        var user: User? = null
        // Eseguire la query
        val cursor = db.rawQuery(/* sql = */ "SELECT $USERNAME_COl,$PASSWORD_COL FROM $TABLE_NAME WHERE $EMAIL_COL = $email", /* selectionArgs = */
            null)

        // Verificare se il cursore contiene dati
        var username = ""
        var password = ""
        if (cursor.moveToFirst()) {
            do{
                username = cursor.getString(cursor.getColumnIndex(USERNAME_COl))
                password = cursor.getString(cursor.getColumnIndex(PASSWORD_COL))
            }while (cursor.moveToNext())

            user = User(username, password)
        }
        cursor.close()
        return user
    }

    data class Modello(val tempo: Int, val nummes: Int, val nomemod: String)
    data class Tempo(val tempo: Int, val nummes: Int)
    data class Avatar(val avatar: Int)

    @SuppressLint("SuspiciousIndentation", "Range")
    fun getModelloInfo(username: String,idavatar: Int): Modello? {
        val db = this.readableDatabase
        var modello: Modello? = null
        // Eseguire la query
        val cursor = db.rawQuery(/* sql = */ "SELECT $USERNAME_COl2,$TEMPO_COL,$NUMMES_COL,$NOMEMOD_COL FROM $TABLE_NAME2 WHERE $ID_AVATAR_COL2 = $idavatar AND $USERNAME_COl2 = '$username'; ", /* selectionArgs = */
            null)
        var tempo = 0
        var nummes = 0
        var nomemod = ""
        if (cursor.moveToFirst()) {
            do{
                tempo = cursor.getInt(cursor.getColumnIndex(TEMPO_COL))
                nummes = cursor.getInt(cursor.getColumnIndex(NUMMES_COL))
                nomemod = cursor.getString(cursor.getColumnIndex(NOMEMOD_COL))
            }while (cursor.moveToNext())

            modello = Modello(tempo, nummes, nomemod)
        }
        cursor.close()
        return modello
    }


    @SuppressLint("Range")
    fun getTempo(idavatar: Int, username: String): Tempo? {
        val db = this.readableDatabase
        var tempos: Tempo? = null
        // Eseguire la query
        val cursor = db.rawQuery(/* sql = */ "SELECT $TEMPO_COL,$NUMMES_COL FROM $TABLE_NAME2 WHERE $ID_AVATAR_COL2 = $idavatar AND $USERNAME_COl2 = '$username'; ",
            null)
        var tempo = 0
        var nummes = 0
        if (cursor.moveToFirst()) {
            do{
                tempo = cursor.getInt(cursor.getColumnIndex(TEMPO_COL))
                nummes = cursor.getInt(cursor.getColumnIndex(NUMMES_COL))
            }while (cursor.moveToNext())

            tempos = Tempo(tempo, nummes)
        }
        cursor.close()
        return tempos
    }

    fun setTempo(idavatar: Int, username: String, tempo: Int){
        val db = this.writableDatabase
        // Eseguire la query
        db.execSQL("UPDATE $TABLE_NAME2 SET $TEMPO_COL = $tempo WHERE $ID_AVATAR_COL2 = $idavatar AND $USERNAME_COl2 = '$username';")
    }

    fun setNumMes(idavatar: Int, username: String, mes: Int){
        val db = this.writableDatabase
        // Eseguire la query
        db.execSQL("UPDATE $TABLE_NAME2 SET $NUMMES_COL = $mes WHERE $ID_AVATAR_COL2 = $idavatar AND $USERNAME_COl2 = '$username';")
    }

    fun getRighe(username: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT COUNT(*) FROM $TABLE_NAME2 WHERE $USERNAME_COl2 = '$username'; ", null)
        cursor.moveToFirst()
        val rowCount = cursor.getInt(0)
        cursor.close()

        if (rowCount >= MAX_ROWS) {
            return true
        }
        return false
    }


    fun getRigheConv(username: String, selezionato: Int): Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT COUNT(*) FROM $TABLE_NAME3 WHERE $USERNAME_COl3 = '$username' AND $SELEZIONATO2 = $selezionato; ", null)
        cursor.moveToFirst()
        val rowCount = cursor.getInt(0)
        cursor.close()

        if (rowCount >= 1) {
            return true
        }
        return false
    }



    @SuppressLint("Range")
    fun getAvatarID(username: String): Int? {
        val db = this.readableDatabase
        var avatarID: Int? = null
        // Eseguire la query
        val cursor = db.rawQuery(/* sql = */ "SELECT $ID_AVATAR_COL FROM $TABLE_NAME WHERE $USERNAME_COl = '$username'; ",
            null)
        if (cursor.moveToFirst()) {
            do{
                avatarID = cursor.getInt(cursor.getColumnIndex(ID_AVATAR_COL))
            }while (cursor.moveToNext())
        }
        cursor.close()
        return avatarID
    }


    @SuppressLint("Range")
    fun printAllData() {
        val db = this.readableDatabase

        // Eseguire la query per ottenere tutti i dati
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        // Verificare se il cursore contiene dati
        if (cursor.moveToFirst()) {
            do {
                // Ottenere i dati da ogni colonna
                val id = cursor.getInt(cursor.getColumnIndex(ID_COL))
                val username = cursor.getString(cursor.getColumnIndex(USERNAME_COl))
                val email = cursor.getString(cursor.getColumnIndex(EMAIL_COL))
                val password = cursor.getString(cursor.getColumnIndex(PASSWORD_COL))
                val idAvatar = cursor.getInt(cursor.getColumnIndex(ID_AVATAR_COL))

                // Stampare i dati utilizzando Log.v
                Log.v("DatabaseData", "ID: $id, Username: $username, Email: $email, Password: $password, ID Avatar: $idAvatar")
            } while (cursor.moveToNext())
        }

        // Chiudere il cursore
        cursor.close()
    }


    @SuppressLint("Range")
    fun printAllDataModello() {
        val db = this.readableDatabase

        // Eseguire la query per ottenere tutti i dati
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME2", null)

        // Verificare se il cursore contiene dati
        if (cursor.moveToFirst()) {
            do {
                // Ottenere i dati da ogni colonna
                val id = cursor.getInt(cursor.getColumnIndex(ID_COL2))
                val nomes = cursor.getString(cursor.getColumnIndex(USERNAME_COl2))
                val tempo = cursor.getString(cursor.getColumnIndex(TEMPO_COL))
                val nummes = cursor.getString(cursor.getColumnIndex(NUMMES_COL))
                val nomemod = cursor.getString(cursor.getColumnIndex(NOMEMOD_COL))
                val idAvatar = cursor.getInt(cursor.getColumnIndex(ID_AVATAR_COL2))

                // Stampare i dati utilizzando Log.v
                Log.v("DatabaseDataModello", "ID: $id,Nome: ${nomes}, Tempo: $tempo, nummes: $nummes, nomemod: $nomemod, ID Avatar: $idAvatar")
            } while (cursor.moveToNext())
        }

        // Chiudere il cursore
        cursor.close()
    }

    @SuppressLint("Range")
    fun printAllDataConvers() {
        val db = this.readableDatabase

        // Eseguire la query per ottenere tutti i dati
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME3", null)

        // Verificare se il cursore contiene dati
        if (cursor.moveToFirst()) {
            do {
                // Ottenere i dati da ogni colonna
                val id = cursor.getInt(cursor.getColumnIndex(ID_COL3))
                val nomes = cursor.getString(cursor.getColumnIndex(USERNAME_COl3))
                val numfiles = cursor.getInt(cursor.getColumnIndex(NUMFILE_COL))
                val selezionatos = cursor.getInt(cursor.getColumnIndex(SELEZIONATO2))

                // Stampare i dati utilizzando Log.v
                Log.v("DatabaseDataConvers", "ID: $id,Nome: ${nomes}, NumFile: $numfiles, selezionato: $selezionatos")
            } while (cursor.moveToNext())
        }

        // Chiudere il cursore
        cursor.close()
    }

    @SuppressLint("Range")
    fun printAllDataInfoConvers() {
        val db = this.readableDatabase

        // Eseguire la query per ottenere tutti i dati
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME4", null)

        // Verificare se il cursore contiene dati
        if (cursor.moveToFirst()) {
            do {
                // Ottenere i dati da ogni colonna
                val id = cursor.getInt(cursor.getColumnIndex(ID_COL4))
                val nomes = cursor.getString(cursor.getColumnIndex(USERNAME_COl4))
                val selezionatos = cursor.getInt(cursor.getColumnIndex(SELEZIONATO3))
                val numfiles = cursor.getString(cursor.getColumnIndex(DATAORA_COL2))


                // Stampare i dati utilizzando Log.v
                Log.v("DatabaseDataInfoConvers", "ID: $id,Nome: ${nomes}, DataOra: $numfiles, Selezionato: $selezionatos")
            } while (cursor.moveToNext())
        }

        // Chiudere il cursore
        cursor.close()
    }





    companion object{
        // here we have defined variables for our database

        // below is variable for database name
        private val DATABASE_NAME = "Chatbot"

        // below is the variable for database version
        private val DATABASE_VERSION = 6 //se si apporta una modifica al database(es: si aggiunge una tabella in più), si deve incrementare la versione del db(esegue il opUpgrade che a sua volta esegue onCreate con la nuova tabella)

        // below is the variable for table name
        val TABLE_NAME = "login"
        val ID_COL = "id"
        val USERNAME_COl = "username"
        var EMAIL_COL = "email"
        val PASSWORD_COL = "password"
        val ID_AVATAR_COL = "id_avatar"
        val TABLE_NAME2 = "model"
        val ID_COL2 = "id"
        val TEMPO_COL = "tempo"
        val NUMMES_COL = "numero_mes"
        val NOMEMOD_COL = "nome_modello"
        val ID_AVATAR_COL2 = "id_avatar"
        val SELEZIONATO = "avatar_selezionato"
        val USERNAME_COl2 = "username"
        val TABLE_NAME3 = "conversazioni"
        val ID_COL3 = "id"
        val USERNAME_COl3 = "username"
        var NUMFILE_COL = "numero_file"
        val SELEZIONATO2 = "avatar_selezionato"
        val DATAORA_COL = "data_e_ora"
        val TABLE_NAME4 = "infoconv"
        val ID_COL4 = "id"
        val USERNAME_COl4 = "username"
        val SELEZIONATO3 = "avatar_selezionato"
        val DATAORA_COL2 = "data_e_ora"
    }
}
