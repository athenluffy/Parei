package mn.athen.test.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mn.athen.test.classes.Item
import mn.athen.test.repository.ItemRepository
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.closestDI
import org.kodein.di.instance
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ItemViewModel (app: Application) : AndroidViewModel(app), DIAware {

    private var item: Item? = null
    override val di: DI by closestDI()
    private val itemRepository : ItemRepository by instance()




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
