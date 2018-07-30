package vtc.room.a101.gagasexpressnewversion.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import vtc.room.a101.gagasexpressnewversion.R;
import vtc.room.a101.gagasexpressnewversion.activity.InfoActivity;
import vtc.room.a101.gagasexpressnewversion.custom.CustomDialogViewPager;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.InfoViewHolder>{

   private final Context context;
   private final int[] imageArray;
   private final int positionMod;

    public InfoAdapter(final Context context, final int[] imageArray, final int positionMod) {
        this.context = context;
        this.imageArray = imageArray;
        this.positionMod = positionMod;
    }

    @Override
    public InfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_info_item, parent, false);
        return new InfoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final InfoViewHolder holder, final int position) {
        holder.infoImage.setImageResource(imageArray[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*CustomDialogViewPager cdvp = new CustomDialogViewPager(context, positionModel);
                cdvp.show();*/
                /*final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.layout_view_pager);
                ViewPagerAdapter adapter = new ViewPagerAdapter(context, imageArray);
                final ViewPager viewPager = (ViewPager) dialog.findViewById(R.id.view_pager);
                viewPager.setAdapter(adapter);
                dialog.show();*/
                final CustomDialogViewPager cdvp = new CustomDialogViewPager(context, positionMod, position);
                final android.support.v4.app.FragmentManager fragmentManager = ((InfoActivity)context).getSupportFragmentManager();
                cdvp.show(fragmentManager, "Tag");
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageArray.length;
    }

    class InfoViewHolder extends RecyclerView.ViewHolder{
        ImageView infoImage;
        InfoViewHolder(View itemView) {
            super(itemView);
            infoImage = (ImageView) itemView.findViewById(R.id.image_info_item);
        }
    }
}
