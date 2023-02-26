package mn.athen.test.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import mn.athen.test.Class.Word;

@Dao
public interface WordDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Word word);

    @Query("Delete from word_table")
    void deleteAll();

    @Query("SELECT * from word_table ORDER by word ASC")
    LiveData<List<Word>> getAllWords();
    @Delete(entity = Word.class)
    void deleteword(Word word);


}
