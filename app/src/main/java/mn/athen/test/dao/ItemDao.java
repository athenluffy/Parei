package mn.athen.test.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import mn.athen.test.classes.Item;

@Dao
public interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Item item);

    @Query("Delete from tb_items")
    void deleteAll();

    @Query("SELECT * from tb_items ORDER by id ASC")
    LiveData<List<Item>> getAllItems();

    //minAge: Int
    @Query("SELECT * from tb_items where id = :id ")
    LiveData<Item> find_one(int id);


}
