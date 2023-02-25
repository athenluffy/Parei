package mn.athen.test.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import mn.athen.test.Class.Word;
import mn.athen.test.dao.WordDao;
import mn.athen.test.db.WordRoomDatabase;

public class WordRepository {

    private static WordDao wordDao;
    private LiveData<List<Word>> allWords;

    public WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        wordDao = db.wordDao();
        allWords= wordDao.getAllWords();
    }

    public LiveData<List<Word>> getAllWords()
    {
        return  allWords;
    }

    public void insert(Word word)
    {
        new insertAsyncTask(wordDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<Word,Void,Void> {
        private WordDao aSyncTaskdao;
        public insertAsyncTask(WordDao wordDao) {
            aSyncTaskdao=wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.insert(words[0]);
            return null;
        }
    }
}
