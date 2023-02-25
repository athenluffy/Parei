package mn.athen.test.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mn.athen.test.Class.Word;
import mn.athen.test.R;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewholder> {

    private final LayoutInflater layoutInflater;
    private List<Word> words;

    public WordListAdapter(LayoutInflater layoutInflater, List<Word> words) {
        this.layoutInflater = layoutInflater;
        this.words = words;
    }

    @NonNull
    @Override
    public WordListAdapter.WordViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= layoutInflater.inflate(R.layout.recyclerview_item,parent,false);
        return new WordViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordListAdapter.WordViewholder holder, int position) {

        if(words!=null)
        {
            Word word = words.get(position);
            holder.wordItemView.setText(word.getWord());
        }
        else
        {
            holder.wordItemView.setText("No Word");
        }
    }
    public void setWords(List<Word> words)
    {
        this.words=words;
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        if(words!=null)
            return words.size();
        return 0;
    }

    static class WordViewholder extends RecyclerView.ViewHolder
    {
        TextView wordItemView;

        public WordViewholder(@NonNull View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
        }
    }
}
