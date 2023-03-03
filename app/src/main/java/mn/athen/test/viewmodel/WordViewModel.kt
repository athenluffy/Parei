package mn.athen.test.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import mn.athen.test.Class.Word
import mn.athen.test.repository.WordRepository

class WordViewModel(private val wordRepository: WordRepository) : ViewModel() {

    val words: LiveData<List<Word>> = wordRepository.allWords

    fun insert(word: Word?) {
        wordRepository.insert(word)
    }

    fun deleteword(word: Word?) {
        wordRepository.delete(word)
    }
}