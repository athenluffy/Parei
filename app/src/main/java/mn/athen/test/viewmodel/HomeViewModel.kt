package mn.athen.test.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import mn.athen.test.classes.Item
import mn.athen.test.classes.Word
import mn.athen.test.repository.ItemRepository
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.closestDI
import org.kodein.di.instance
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class HomeViewModel(app:Application) : AndroidViewModel(app), DIAware {

    override val di:DI by closestDI()
    private val itemRepository : ItemRepository by instance()
    val items: LiveData<List<Item>> = itemRepository.allItems
    var item: LiveData<Item>? = null

    fun insert(item: Item) {
        service.execute{itemRepository.insert(item)}
    }

    fun deleteall() {
        service.execute{itemRepository.delete()}
    }

    fun setItem(itemId: Int) {
        item = itemRepository.findone(itemId)

    }

    companion object {

        val service: ExecutorService = Executors.newSingleThreadExecutor()
    }



}