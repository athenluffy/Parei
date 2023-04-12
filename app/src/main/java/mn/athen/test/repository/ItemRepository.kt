package mn.athen.test.repository

import androidx.lifecycle.LiveData
import mn.athen.test.classes.Item
import mn.athen.test.db.WordDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ItemRepository(private val db:WordDatabase)  {
    val allItems: LiveData<List<Item>> = db.ItemDao().allItems

    fun insert(item: Item?) {
        //insert to Database needs to be called async

        service.execute { db.ItemDao().insert(item) }
    }

    fun delete() {
        //delete to Database needs to be called async

        service.execute { db.ItemDao().deleteAll() }
    }

    companion object {

        val service: ExecutorService = Executors.newSingleThreadExecutor()
    }
}