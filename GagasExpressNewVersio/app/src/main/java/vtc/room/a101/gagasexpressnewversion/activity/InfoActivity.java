package vtc.room.a101.gagasexpressnewversion.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import vtc.room.a101.gagasexpressnewversion.R;
import vtc.room.a101.gagasexpressnewversion.adapter.InfoAdapter;
import vtc.room.a101.gagasexpressnewversion.constant.Constant;
import vtc.room.a101.gagasexpressnewversion.custom.CustomDialog;
import vtc.room.a101.gagasexpressnewversion.module.Product;
import vtc.room.a101.gagasexpressnewversion.provider.DataProvider;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final int position = getIntent().getExtras().getInt(Constant.MY_KEY);
        TextView allDescription = (TextView) findViewById(R.id.decr_info);
        ImageView imageInfo = (ImageView) findViewById(R.id.image_info);
        ImageView videoImage = (ImageView) findViewById(R.id.video_image);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rec_info);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        final int[] imageArray = DataProvider.getList().get(position).getImageArrayId();
        InfoAdapter infoAdapter = new InfoAdapter(this, imageArray, position);
        Product product = DataProvider.getList().get(position);
        allDescription.setText(product.getAllDescription());
        imageInfo.setImageResource(imageArray[0]);
        videoImage.setImageResource(imageArray[0]);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(infoAdapter);
        videoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog cd = new CustomDialog(InfoActivity.this, position);
                cd.show();
            }
        });
    }


}
