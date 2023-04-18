package mn.athen.test.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import mn.athen.test.classes.Item
import mn.athen.test.classes.Word
import mn.athen.test.repository.ItemRepository
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class HomeViewModel(private val itemRepository: ItemRepository) : ViewModel() {

    val items: LiveData<List<Item>> = itemRepository.allItems

    fun insert(item: Item) {
        service.execute{itemRepository.insert(item)}
    }

    fun deleteall() {
        service.execute{itemRepository.delete()}
    }
    companion object {

        val service: ExecutorService = Executors.newSingleThreadExecutor()
    }

}