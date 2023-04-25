package mn.athen.test.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.Nullable;

import java.util.List;

import mn.athen.test.classes.Item;
import mn.athen.test.Interface.ItemClickListener;
import mn.athen.test.R;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ItemViewholder> {

    private final LayoutInflater layoutInflater;
    private List<Item> items;
    ItemClickListener itemClickListener;
    final Context context;

    public ItemListAdapter(LayoutInflater layoutInflater, List<Item> items,ItemClickListener itemClickListener,Context context) {
        this.layoutInflater = layoutInflater;
        this.items = items;
        this.itemClickListener = itemClickListener;
        this.context = context;

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
            Glide.with(context).load(item.getImg()).into(holder.img);
            holder.star.setRating(item.getStar());
            holder.price.setText(item.getPrice());
            holder.itemView.setOnClickListener((l)->
                    itemClickListener.onclick(position,items.get(position)));

        }

    }

    @Override
    public int getItemCount() {
        if(items!=null)
            return items.size();
        return 0;
    }

    public void setItems(@Nullable List<Item> it) {
        this.items=it;
        notifyDataSetChanged();
    }

    static class ItemViewholder extends RecyclerView.ViewHolder
    {
        TextView name;
        TextView price;
        ImageView img;
        RatingBar star;

        public ItemViewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_name);
            img = itemView.findViewById(R.id.item_img);
            star = itemView.findViewById(R.id.item_star);

            price=itemView.findViewById(R.id.tv_itemPrice);
        }
    }
}
