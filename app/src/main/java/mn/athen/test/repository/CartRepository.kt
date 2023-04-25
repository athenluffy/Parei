package mn.athen.test.repository

import androidx.lifecycle.LiveData
import mn.athen.test.classes.Cart
import mn.athen.test.classes.Item
import mn.athen.test.db.WordDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CartRepository(private val db:WordDatabase)  {
    val allItems: LiveData<List<Cart>> = db.cartDao().allItems

    fun insert(item: Cart?) {
        //insert to Database needs to be called async

        service.execute { db.cartDao().insert(item) }
    }

    fun findone(id:Int): LiveData<Cart>? {
        //insert to Database needs to be called async

        return db.cartDao().find_one(id)
    }
    fun deleteone(id:Int) {
        //insert to Database needs to be called async

        service.execute{db.cartDao().delete_one(id)}
    }

    fun change_count(counter:Int,item_id:Int){
        //insert to Database needs to be called async

        service.execute{ db.cartDao().increase_count(counter,item_id)}
    }



    fun delete() {
        //delete to Database needs to be called async

        service.execute { db.cartDao().deleteAll() }
    }

    companion object {

        val service: ExecutorService = Executors.newSingleThreadExecutor()
    }
}