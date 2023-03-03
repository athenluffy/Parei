package mn.athen.test.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import mn.athen.test.Class.Word
import mn.athen.test.repository.WordRepository
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class WordViewModel(private val wordRepository: WordRepository) : ViewModel() {

    val words: LiveData<List<Word>> = wordRepository.allWords

    fun insert(word: Word?) {
        service.execute{wordRepository.insert(word)}
    }

    fun deleteword(word: Word?) {
       service.execute{wordRepository.delete(word)}
    }
    companion object {

        val service: ExecutorService = Executors.newSingleThreadExecutor()
    }
}