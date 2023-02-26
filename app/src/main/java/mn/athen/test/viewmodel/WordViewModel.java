package mn.athen.test.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import mn.athen.test.Class.Word;
import mn.athen.test.Repository.WordRepository;

public class WordViewModel extends AndroidViewModel {
    private final WordRepository repository;
    private final LiveData<List<Word>> words;

    public WordViewModel(@NonNull Application application) {
        super(application);
        repository=new WordRepository(application);
        words=repository.getAllWords();
    }

    public LiveData<List<Word>> getWords() { return words;}

    public void insert(Word word) { repository.insert(word);}
    public void deleteword(Word word) { repository.delete(word);}
}
