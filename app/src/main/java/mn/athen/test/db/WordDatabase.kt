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
        var items = arrayOf(
            "https://www.bigbasket.com/media/uploads/p/xxl/40173098_3-eastern-chicken-pickle.jpg",
            "https://m.media-amazon.com/images/I/61i8XttB1aL._SY679_.jpg",
            "https://cdn.shopify.com/s/files/1/0636/4892/3869/products/NEOriginsSmokedChickenPicklePR01.jpg?v=1680069610&width=1946",
            "https://mirchi.com/os/cdn/content/images/chicken%20pickle%20boovi%20foods_medium_0898657.webp",
            "https://i0.wp.com/tressiafoods.com/wp-content/uploads/2021/01/CHICKEN-PICKLE_20220207_03.jpg?fit=1500%2C1500&ssl=1"
        )


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
                val item = Item(i,"Chicken Pickle$i", items[i-1], 3.5F)
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