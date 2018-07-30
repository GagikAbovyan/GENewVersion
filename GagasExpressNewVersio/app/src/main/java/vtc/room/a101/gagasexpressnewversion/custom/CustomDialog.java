package vtc.room.a101.gagasexpressnewversion.custom;

import android.app.Activity;
import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.widget.VideoView;

import vtc.room.a101.gagasexpressnewversion.R;
import vtc.room.a101.gagasexpressnewversion.provider.DataProvider;

/**
 * Created by HP on 7/29/2018.
 */

public class CustomDialog extends Dialog {


    private Activity c;
    private int position;


    public CustomDialog(final Activity a, final int position) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.position = position;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        final VideoView video = (VideoView) findViewById(R.id.video_info);
        video.setVideoURI(Uri.parse(DataProvider.getList().get(position).getVideoUrl()));
        video.start();
    }

}
