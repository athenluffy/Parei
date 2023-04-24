package mn.athen.test.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import mn.athen.test.classes.Cart;
import mn.athen.test.classes.Item;

@Dao
public interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Cart cart);

    @Query("Delete from tb_cart")
    void deleteAll();

    @Query("SELECT * from tb_cart ORDER by id ASC")
    LiveData<List<Cart>> getAllItems();

    @Query("UPDATE tb_cart SET count = count+ :counter where itemid = :item_id")
    void increase_count(int counter,int item_id);


    //minAge: Int
    @Query("SELECT * from tb_cart where id = :id ")
    LiveData<Cart> find_one(int id);

    @Query("DELETE from tb_cart where id = :id ")
    void delete_one(int id);


}
