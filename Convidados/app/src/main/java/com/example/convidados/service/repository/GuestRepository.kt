package com.example.convidados.service.repository

import android.content.ContentValues
import android.content.Context
import android.text.Selection
import com.example.convidados.service.constants.DataBaseConstants
import com.example.convidados.service.model.GuestModel
import java.lang.Exception

//fechamos o construtor da classe deixando privado ai ninguem vai instanciar
class GuestRepository private constructor(context: Context) {

    //Padrao Singleton -> que so pode ter uma instancia da classe Repository
    //Só vai ter uma conexão com o banco de dados, evita concorrencia com o banco
    //e evita ler alguma informação nao atualizada etc.

    //Inicia a conexão com o banco
    private var mGuestDataBaseHelper: GuestDataBaseHelper = GuestDataBaseHelper(context)

    companion object {
        //cria essa variavel para ficar com a instancia da classe
        private lateinit var repository: GuestRepository

        //somente o getInstance que passar o repositorio mais nenhuma e somente 1 repositorio
        //para nao ficar instanciando um monte em nas classes assim concluindo o SINGLETON
        fun getInstance(context: Context): GuestRepository {
            //verifica se o repositorio ja foi inicializado na variavel
            if (!::repository.isInitialized) {
                //se nao inicializa ele
                repository = GuestRepository(context)
            }
            //retorna o repositorio
            return repository
        }
    }

    fun save(guest: GuestModel): Boolean {
        return try {
            val db = mGuestDataBaseHelper.writableDatabase

            val contentValues = ContentValues()
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest.presence)

            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, contentValues)
            true
        } catch (e: Exception) {
            false
        }

    }

    fun update(guest: GuestModel): Boolean {
        return try {
            val db = mGuestDataBaseHelper.writableDatabase

            val contentValues = ContentValues()
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest.presence)

            //FIZEMOS UMA STRING COM ID = A ?(SENDO A ? UM DOS ARGUMENTOS)
            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            //CRIAMOS O ARGS QUE SAO OS ARGUMENTOS É UM ARRAY SERIA UM PRA CADA ? DO SELECTION
            val args = arrayOf(guest.id.toString())

            db.update(DataBaseConstants.GUEST.TABLE_NAME, contentValues, selection, args)
            true
        } catch (e: Exception) {
            false

        }

    }

    fun delete(id: Int): Boolean {
        return try {
            val db = mGuestDataBaseHelper.writableDatabase

            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())

            db.delete(DataBaseConstants.GUEST.TABLE_NAME, selection, args)
            true
        } catch (e: Exception) {
            false

        }
    }

    fun get(id: Int): GuestModel? {

        var guest: GuestModel? = null
        return try {
            val db = mGuestDataBaseHelper.readableDatabase


            //RawQuery da pra fazer diretamente o comando SQL, porém é menos seguro pq tem que fazer
            //tudo manual se der ruim pode dar erro, mas é mais limpo
//            db.rawQuery("SELECT * FROM Guest WHERE id = $id", null)

            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE
            )

            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())

            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME, projection, selection, args,
                null, null, null
            )

            if (cursor != null && cursor.count > 0) {
                //Tem que colocar move to first pq mesmo tendo somente uma linha ele seleciona
                //tudo como se tivesse mais de uma linha ai mandamos o move to first pra ir pra coluna
                //algo assim
                cursor.moveToFirst()

                //pegando o INDEX do NAME que é o nome e passando pra variavel name
                val name =
                    cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                //presence é um int mas no nosso guestModel ele é um Boolean
                //Assim verificamos na hora se for == 1 vira true
                val presence =
                    (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                guest = GuestModel(id, name, presence)

            }

            //liberamos o cursor da memoria para nao usarmos mais
            cursor?.close()
            guest
        } catch (e: Exception) {
            guest
        }

    }

    fun getAll(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()

        return try {
            val db = mGuestDataBaseHelper.readableDatabase

            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE
            )

            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME, projection, null, null,
                null, null, null
            )

            if (cursor != null && cursor.count > 0) {
                //vai lendo e passando pro proximo cursor enquanto o moveToNext nao dar false
                while (cursor.moveToNext()){
                    //pega os dados e adiciona em uma variavel e adiciona na lista
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence = (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                    val guest = GuestModel(id, name, presence)
                    list.add(guest)
                }
            }

            //liberamos o cursor da memoria para nao usarmos mais
            cursor?.close()
            list
        } catch (e: Exception) {
            list
        }
    }

    fun getPresent(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()

        return try {
            val db = mGuestDataBaseHelper.readableDatabase

            //USANDO RAWQUERY
            val cursor = db.rawQuery("SELECT id, name, presence FROM Guest WHERE presence = 1", null)

//            val projection = arrayOf(
//                DataBaseConstants.GUEST.COLUMNS.ID,
//                DataBaseConstants.GUEST.COLUMNS.NAME,
//                DataBaseConstants.GUEST.COLUMNS.PRESENCE
//            )
//
//            val cursor = db.query(
//                DataBaseConstants.GUEST.TABLE_NAME, projection, null, null,
//                null, null, null
//            )

            if (cursor != null && cursor.count > 0) {
                //vai lendo e passando pro proximo cursor enquanto o moveToNext nao dar false
                while (cursor.moveToNext()){
                    //pega os dados e adiciona em uma variavel e adiciona na lista
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence = (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                    val guest = GuestModel(id, name, presence)
                    list.add(guest)
                }
            }

            //liberamos o cursor da memoria para nao usarmos mais
            cursor?.close()
            list
        } catch (e: Exception) {
            list
        }
    }

    fun getAbsent(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()

        return try {
            val db = mGuestDataBaseHelper.readableDatabase

            //USANDO RAWQUERY
            val cursor = db.rawQuery("SELECT id, name, presence FROM Guest WHERE presence = 0", null)

//            val projection = arrayOf(
//                DataBaseConstants.GUEST.COLUMNS.ID,
//                DataBaseConstants.GUEST.COLUMNS.NAME,
//                DataBaseConstants.GUEST.COLUMNS.PRESENCE
//            )
//
//            val cursor = db.query(
//                DataBaseConstants.GUEST.TABLE_NAME, projection, null, null,
//                null, null, null
//            )

            if (cursor != null && cursor.count > 0) {
                //vai lendo e passando pro proximo cursor enquanto o moveToNext nao dar false
                while (cursor.moveToNext()){
                    //pega os dados e adiciona em uma variavel e adiciona na lista
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence = (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                    val guest = GuestModel(id, name, presence)
                    list.add(guest)
                }
            }

            //liberamos o cursor da memoria para nao usarmos mais
            cursor?.close()
            list
        } catch (e: Exception) {
            list
        }
    }


}