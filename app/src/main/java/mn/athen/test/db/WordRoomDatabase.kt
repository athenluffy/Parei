package mn.athen.test.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import mn.athen.test.Class.Word;
import mn.athen.test.dao.WordDao;

@Database(entities = {Word.class},version = 1,exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {

    public abstract WordDao wordDao();
    private static WordRoomDatabase INSTANCE;

    private static RoomDatabase.Callback callback=
            new RoomDatabase.Callback()
            {
                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

     public static WordRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WordRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class,"word_database")
                            .addCallback(callback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }

        }
        return INSTANCE;
    }

    private static class PopulateDbAsync extends AsyncTask<Void,Void,Void> {

         private final WordDao wordDao;
         String[] words= {"Naruto","Itachi","Jiraiya"};
        public PopulateDbAsync(WordRoomDatabase instance) {
            wordDao=instance.wordDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            wordDao.deleteAll();

            for (String s : words) {
                Word word = new Word(s);
                wordDao.insert(word);
            }
            return null;
        }
    }
}



