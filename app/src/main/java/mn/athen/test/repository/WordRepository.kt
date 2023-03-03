package mn.athen.test.repository

import android.app.Application
import androidx.lifecycle.LiveData
import mn.athen.test.Class.Word
import mn.athen.test.dao.WordDao
import mn.athen.test.db.WordDatabase.Companion.invoke
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class WordRepository(application: Application?)  {
    val allWords: LiveData<List<Word>>

    init {
        val db = invoke(application!!)
        wordDao = db.wordDao()
        allWords = wordDao.allWords
    }

    fun insert(word: Word?) {
        //insert to Database needs to be called async

        service.execute { wordDao.insert(word) }
    }

    fun delete(word: Word?) {
        //delete to Database needs to be called async

        service.execute { wordDao.deleteword(word) }
    }

    companion object {
        private lateinit var wordDao: WordDao
        val service: ExecutorService = Executors.newSingleThreadExecutor()
    }
}