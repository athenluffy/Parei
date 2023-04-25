package mn.athen.test.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import mn.athen.test.classes.Cart
import mn.athen.test.repository.CartRepository
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.closestDI
import org.kodein.di.instance
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CartViewModel(app: Application) : AndroidViewModel(app), DIAware {

    override val di: DI by closestDI()
    private val cartRepository : CartRepository by instance()
    val items: LiveData<List<Cart>> = cartRepository.allItems
    var item: LiveData<Cart>? = null

    fun insert(item: Cart) {
        service.execute{cartRepository.insert(item)}
    }

    fun deleteall() {
        service.execute{cartRepository.delete()}
    }

    fun setItem(itemId: Int) {
        item = cartRepository.findone(itemId)

    }

    companion object {

        val service: ExecutorService = Executors.newSingleThreadExecutor()
    }



}