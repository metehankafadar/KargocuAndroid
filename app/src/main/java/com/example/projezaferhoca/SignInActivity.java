package com.example.projezaferhoca;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity implements LocationListener {
    String TAG = "QR CODE OLUŞTUR";
    ImageView ivsiQR;
    EditText etsiSender;
    String girilendeger,provider,burdaki;
    Button btnsichangeloc,btnsitakeqr;
    TextView tvsiWelcome;
    LocationManager locationManager;
    static double longitude;
    static double latitude;
    QRGEncoder qrgEncoder;
    Bitmap bitmap;
    connectionClass connection = new connectionClass();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        ivsiQR=findViewById(R.id.iv_seQR);
        etsiSender=findViewById(R.id.et_siSender);
        btnsichangeloc=findViewById(R.id.btn_sichangeloc);
        btnsitakeqr=findViewById(R.id.btn_sitakeqr);
        tvsiWelcome=findViewById(R.id.Tv_goster);



        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        //final Location location = locationManager.getLastKnownLocation(provider);
               final Location location=locationManager.getLastKnownLocation(provider);
        if (location != null) {
            onLocationChanged(location);
        } else {
           tvsiWelcome.setText("bulunamadi");

        }

            // buraya final yazılması lazım
         final String takeun = getIntent().getExtras().getString("un", "defaultKey");
        //tvqAl.setText("Merhaba " + takeun + " QR kodun :");
        //final String take=takeun;

        //Qr oluşturma kodu
         girilendeger = takeun.trim();
        if (girilendeger.length() > 0) {
            WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
            Display display = manager.getDefaultDisplay();
            Point point = new Point();
            display.getSize(point);
            int width = point.x;
            int height = point.y;
            int smallerdimension = width < height ? width : height;
            smallerdimension = smallerdimension * 2 / 4;
            qrgEncoder = new QRGEncoder(girilendeger, null, QRGContents.Type.TEXT, smallerdimension);
            try {
                bitmap = qrgEncoder.encodeAsBitmap();
                ivsiQR.setImageBitmap(bitmap);
            } catch (WriterException e) {
                Log.v(TAG, e.toString());
            }

        } else {
            tvsiWelcome.setError("Required");
        }

        btnsichangeloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lat=Double.toString(latitude);
                String lon=Double.toString(longitude);
               tvsiWelcome.setText(lat+" "+lon+" "+girilendeger);
               connection.update(girilendeger,lat,lon);
            }
        });

        btnsitakeqr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                burdaki=etsiSender.getText().toString();
                String deliverid = new connectionClass().kargo(girilendeger,burdaki);
                Intent gotosenqr=new Intent(SignInActivity.this,SendqrActivity.class);
                gotosenqr.putExtra("gonderilen",deliverid);
                startActivity(gotosenqr);
            }
        });

    }
    @Override
    public void onLocationChanged(Location location) {
        longitude=location.getLongitude();
        latitude=location.getLatitude();
        tvsiWelcome.setText(String.valueOf(latitude)+""+String.valueOf(longitude)); // Kişinin anlık location çekiyor
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onResume() {
        super.onResume();
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(provider, 100, 1, this);
    }
    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
    }
}
