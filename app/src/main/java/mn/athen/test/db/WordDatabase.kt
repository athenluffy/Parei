package mn.athen.test.db

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import mn.athen.test.classes.Item
import mn.athen.test.classes.Word
import mn.athen.test.dao.ItemDao
import mn.athen.test.dao.WordDao

@Database(entities = [Word::class,Item::class], version = 2, exportSchema = false)
abstract class WordDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
    abstract fun itemDao(): ItemDao
    private class PopulateDbAsync(instance: WordDatabase?) : AsyncTask<Void?, Void?, Void?>() {
        private val wordDao: WordDao
        private val itemDao: ItemDao
        var words = arrayOf("Naruto", "Itachi", "Jiraiya")
        var items = Item(1,"Chicken Pickle","https://goo.gl/gEgYUd",4.9F)


        init {
            wordDao = instance!!.wordDao()
            itemDao = instance.itemDao()
        }

        @Deprecated("Deprecated in Java")
        override fun doInBackground(vararg p0: Void?): Void? {
            wordDao.deleteAll()
            for (s in words) {
                val word = Word(s)
                wordDao.insert(word)
            }
            for (i in 1..5)
            {
                val item = Item(i,"Chicken Pickle$i","https://goo.gl/gEgYUd", 3.5F)
                itemDao.insert(item)

            }
            return null
        }
    }

    companion object {
        private var instance: WordDatabase? = null
        private val LOCK = Any()
        private val callback: Callback = object : Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                PopulateDbAsync(instance).execute()
            }
        }

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDb(context).also {
                instance = it
            }
        }

        @JvmStatic
        private fun buildDb(context: Context) =

            Room.databaseBuilder(
                context.applicationContext,
                WordDatabase::class.java, "word_database"
            )
                .addCallback(callback)
                .fallbackToDestructiveMigration()

                .build()

    }
}