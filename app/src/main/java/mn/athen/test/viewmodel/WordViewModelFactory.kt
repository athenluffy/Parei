package mn.athen.test.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mn.athen.test.repository.WordRepository

class WordViewModelFactory( private val wordRepository: WordRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WordViewModel(wordRepository) as T
    }
}