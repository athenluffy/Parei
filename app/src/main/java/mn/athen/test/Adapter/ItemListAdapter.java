package mn.athen.test.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mn.athen.test.Class.Item;
import mn.athen.test.R;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ItemViewholder> {

    private final LayoutInflater layoutInflater;
    private List<Item> items;

    public ItemListAdapter(LayoutInflater layoutInflater, List<Item> items) {
        this.layoutInflater = layoutInflater;
        this.items = items;
    }

    @NonNull
    @Override
    public ItemListAdapter.ItemViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= layoutInflater.inflate(R.layout.pickle_item,parent,false);
        return new ItemViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemListAdapter.ItemViewholder holder, int position) {

        if(items!=null)
        {
            Item item = items.get(position);
            holder.name.setText(item.getName());
            holder.img.setImageResource(item.getImg());
            holder.star.setRating(item.getStar());

        }
    }
    public void setWords(List<Item> items)
    {
        this.items=items;
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        if(items!=null)
            return items.size();
        return 0;
    }

    static class ItemViewholder extends RecyclerView.ViewHolder
    {
        TextView name;
        ImageView img;
        RatingBar star;

        public ItemViewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_name);
            img = itemView.findViewById(R.id.item_img);
            star = itemView.findViewById(R.id.item_star);
        }
    }
}
