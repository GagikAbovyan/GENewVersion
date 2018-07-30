package vtc.room.a101.gagasexpressnewversion.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import vtc.room.a101.gagasexpressnewversion.R;
import vtc.room.a101.gagasexpressnewversion.activity.InfoActivity;
import vtc.room.a101.gagasexpressnewversion.constant.Constant;
import vtc.room.a101.gagasexpressnewversion.module.Product;
import vtc.room.a101.gagasexpressnewversion.provider.DataProvider;


public class AllProductAdapter extends RecyclerView.Adapter<AllProductAdapter.ProductViewHolder>  implements Filterable{

    private final Context context;
    private final List<Product> list;
    private final boolean store;
    private List<Product> listSearch;

    public AllProductAdapter(final Context context, final List<Product> list, final boolean store) {
        this.context = context;
        this.list = list;
        this.store = store;
        listSearch = list;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.all_product_item, parent, false);
        if (store){
            view = LayoutInflater.from(context).inflate(R.layout.layout_store_item, parent, false);
        }
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, final int position) {
        final Product product = listSearch.get(position);
        holder.imageProduct.setImageResource(product.getImageArrayId()[0]);
        holder.title.setText(product.getTitle());
        holder.descr.setText(product.getDescription());
        if (!store) {holder.price.setText(product.getPrice() + " $");}
        holder.favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFavorite(holder, product, position);
            }
        });
        if (store){holder.ratingBar.setRating(product.getStar());}

        holder.storeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isStore(holder, product, position);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(holder.itemView.getContext(), InfoActivity.class);
                intent.putExtra(Constant.MY_KEY, position);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listSearch.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String searchText = String.valueOf(constraint);
                if (searchText.isEmpty()) {
                    listSearch = list;
                } else {
                    List<Product> newList = new ArrayList<>();
                    for (int i = 0; i < listSearch.size(); i++) {
                        if (listSearch.get(i).getTitle().toLowerCase().contains(searchText)) {
                            newList.add(listSearch.get(i));
                        }
                    }
                    listSearch = newList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = listSearch;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                listSearch = (List<Product>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    class ProductViewHolder extends RecyclerView.ViewHolder{
        ImageView imageProduct;
        TextView title;
        TextView descr;
        TextView price;
        ImageButton favoriteButton;
        ImageButton storeButton;
        RatingBar ratingBar;
        ProductViewHolder(View itemView) {
            super(itemView);
            imageProduct = (ImageView) itemView.findViewById(R.id.image_item);
            title = (TextView) itemView.findViewById(R.id.title_item);
            descr = (TextView) itemView.findViewById(R.id.description_item);
            price = (TextView) itemView.findViewById(R.id.price_item);
            favoriteButton = (ImageButton) itemView.findViewById(R.id.favorite_item);
            storeButton = (ImageButton) itemView.findViewById(R.id.store_item);
            if (store) {
                ratingBar = (RatingBar) itemView.findViewById(R.id.rating_Bar);
                imageProduct = (ImageView) itemView.findViewById(R.id.image_store_item);
                title = (TextView) itemView.findViewById(R.id.title_store_item);
                descr = (TextView) itemView.findViewById(R.id.description_store_item);
                favoriteButton = (ImageButton) itemView.findViewById(R.id.favorite_store_item);
                storeButton = (ImageButton) itemView.findViewById(R.id.store_store_item);
            }
        }
    }

    private void isStore(final ProductViewHolder productHolder, final Product product, final int position){
        if (product.isStore()){
            DataProvider.getList().get(position).setStore(false);
            productHolder.storeButton.setColorFilter(context.getResources().getColor(R.color.colorPassive));
        }else {
            DataProvider.getList().get(position).setStore(true);
            productHolder.storeButton.setColorFilter(context.getResources().getColor(R.color.colorTextApp));
        }
    }

    private void isFavorite(final ProductViewHolder productHolder, final Product product, final int position){
        if (product.isFavorite()){
            DataProvider.getList().get(position).setFavorite(false);
            productHolder.favoriteButton.setColorFilter(context.getResources().getColor(R.color.colorPassive));
        }else {
            DataProvider.getList().get(position).setFavorite(true);
            productHolder.favoriteButton.setColorFilter(context.getResources().getColor(R.color.colorTextApp));
        }
    }

}
