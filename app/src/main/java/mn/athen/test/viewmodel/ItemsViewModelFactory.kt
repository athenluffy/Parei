package mn.athen.test.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mn.athen.test.repository.ItemRepository
import mn.athen.test.repository.WordRepository

class ItemsViewModelFactory(private val itemRepository: ItemRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(itemRepository) as T
    }
}