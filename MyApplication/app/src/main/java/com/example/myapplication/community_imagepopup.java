package com.example.myapplication;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONException;

public class community_imagepopup extends Activity implements View.OnClickListener {
    private Context mContext = null;
    private final int imgWidth = 300;
    private final int imgHeight = 372;
    protected Bitmap bmp;
    protected String imgPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fragment_image_popup);
        mContext = this;

        /** 전송메시지 */
        Intent i = getIntent();
        Bundle extras = i.getExtras();
        imgPath = extras.getString("filename");

        /** 완성된 이미지 보여주기  */
//        BitmapFactory.Options bfo = new BitmapFactory.Options();
        ImageView iv = (ImageView) findViewById(R.id.imageView);
//        Bitmap bm = BitmapFactory.decodeFile(imgPath, bfo);
//        Bitmap resized = Bitmap.createScaledBitmap(bm, imgWidth, imgHeight, true);
//        iv.setScaleType(ImageView.ScaleType.CENTER);
//        iv.setImageBitmap(bm); //bm -> resized\
        Glide.with(mContext).load(imgPath).into(iv);


        /** 리스트로 가기 버튼*/
        Button btn = (Button) findViewById(R.id.btn_back);
        btn.setOnClickListener(this);
    }

    /* (non-Javadoc)
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:

//                //여기서 디비의 개인 갤러리에 사진을 추가함
//                Intent i = getIntent();
//                Bundle extras = i.getExtras();
//                String imgPath = extras.getString("filename");
//                Log.d("filename",imgPath);
//                Intent intent = new Intent(community_imagepopup.this, community_write_activity.class);
//                intent.putExtra("filepath",imgPath);
//
//                SharedPreferences sf = getSharedPreferences("email",MODE_PRIVATE);
//                String email = sf.getString("email","No email");


                this.finish();
                break;
        }
    }
    @Override
    public void finish(){
        Intent intent = new Intent(this, community_write_activity.class);
        Bundle bundle = new Bundle();
        Encoding encoding = new Encoding();
         BitmapFactory.Options bfo = new BitmapFactory.Options();

        Bitmap bmp = BitmapFactory.decodeFile(imgPath, bfo);

        String image = encoding.EncodingOneImage(bmp);
        if(!(image.isEmpty())){
            Log.d("image_popup",image);
            bundle.putString("bitmap_image", image);
            intent.putExtras(bundle);
            setResult(RESULT_OK, intent);
        }
        else{
            setResult(RESULT_CANCELED, intent);
        }
        super.finish();
    }


}
