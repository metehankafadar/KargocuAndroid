package com.example.projezaferhoca;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import androidx.appcompat.app.AppCompatActivity;

public class SendqrActivity extends AppCompatActivity  {
    String TAG="Qr Generate";
    //SignInden veri çekilecek
    QRGEncoder qrgEncoder;
    Bitmap bitmap;
    ImageView ivseQR;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendqr);
        ivseQR=findViewById(R.id.iv_seQR);
        final String takeun = getIntent().getExtras().getString("gonderilen", "defaultKey");
        final String girilendeger=takeun;

        if (girilendeger.length() > 0) {
            WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
            Display display = manager.getDefaultDisplay();
            Point point = new Point();
            display.getSize(point);
            int width = point.x;
            int height = point.y;
            int smallerdimension = width < height ? width : height; //if width<height is true, width is returned, else height is returned
            smallerdimension = smallerdimension * 2 / 4;
            qrgEncoder = new QRGEncoder(girilendeger, null, QRGContents.Type.TEXT, smallerdimension);
            try {
                bitmap = qrgEncoder.encodeAsBitmap();
                ivseQR.setImageBitmap(bitmap);
            } catch (WriterException e) {
                Log.v(TAG, e.toString());
            }

        } else {
            Toast.makeText(this,"Hataaa",Toast.LENGTH_SHORT).show();
        }
    }
}
