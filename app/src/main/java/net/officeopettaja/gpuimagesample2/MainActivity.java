package net.officeopettaja.gpuimagesample2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {
    private final int REQ_CAMERA = 1;       //カメラのリクエストコード

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //インテントの生成
        Intent intent = new Intent();
        //インテントのアクションを指定
        intent.setAction("android.media.action.IMAGE_CAPTURE");
        //標準カメラアプリのアクティビティを起動
        startActivityForResult(intent, REQ_CAMERA);
    }

    //標準カメラアプリからの戻り
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        Log.d("onActivityResult", "start");
        Log.d("onActivityResult", "requestCode=" + requestCode);
        switch(requestCode) {
            case REQ_CAMERA:		//インテントカメラから戻ってきた場合
                Log.d("onActivityResult","case REQ_CAMERA");
                if(resultCode== Activity.RESULT_OK) {
                    Bitmap bitmap = (Bitmap) intent.getExtras().get("data");

                    ImageView imgMain = (ImageView)findViewById(R.id.imgMain);
                    imgMain.setImageBitmap(bitmap);

                    //取得したBitmapをbyteArrayに変換
//                    ByteArrayOutputStream byteos = new ByteArrayOutputStream();
//                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteos);
//                    byte[] data = byteos.toByteArray();

                    // Start FilterActivity
//                    Intent activity = new Intent(MainActivity.this, FilterActivity.class);
//                    activity.putExtra("bmp", data);
//                    startActivityForResult(activity, REQ_EDIT);		//FilterActivityの起動
                } else {
                    Log.d("onActivityResult","error : code=" + resultCode);
                    finish();
                }
                break;
//            case REQ_EDIT:		//FilterActivityから戻ってきた場合
//                Log.d("onActivityResult","case REQ_EDIT");
            default:			//その他（共通）
                Log.d("onActivityResult", "case default");
                finish();		//Activityの終了
        }
    }
}
