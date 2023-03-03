package mn.athen.test.repository

import androidx.lifecycle.LiveData
import mn.athen.test.Class.Word
import mn.athen.test.db.WordDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class WordRepository(private val db:WordDatabase)  {
    val allWords: LiveData<List<Word>> = db.wordDao().allWords

    fun insert(word: Word?) {
        //insert to Database needs to be called async

        service.execute { db.wordDao().insert(word) }
    }

    fun delete(word: Word?) {
        //delete to Database needs to be called async

        service.execute { db.wordDao().deleteword(word) }
    }

    companion object {

        val service: ExecutorService = Executors.newSingleThreadExecutor()
    }
}