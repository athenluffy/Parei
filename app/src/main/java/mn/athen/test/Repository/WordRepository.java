package mn.athen.test.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import mn.athen.test.Class.Word;
import mn.athen.test.dao.WordDao;
import mn.athen.test.db.WordRoomDatabase;

public class WordRepository {

    private static WordDao wordDao;
    private final LiveData<List<Word>> allWords;

    public WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.Companion.invoke(application);
        wordDao = db.wordDao();
        allWords= wordDao.getAllWords();
    }

    public LiveData<List<Word>> getAllWords()
    {
        return  allWords;
    }

    public void insert(Word word)
    {
        //insert to Database needs to be called async
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(()->
                wordDao.insert(word));

    }
    public void delete(Word word)
    {
        //insert to Database needs to be called async
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(()->
                wordDao.deleteword(word));

    }
}
