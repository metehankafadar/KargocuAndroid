package com.example.projezaferhoca;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.EncodedPolyline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class harita extends FragmentActivity implements OnMapReadyCallback {
    /*


           ------------> M U T L A K A    O K U   <----------------

    implementation 'com.google.maps:google-maps-services:0.2.11'
    implementation 'org.slf4j:slf4j-nop:1.7.25'

    Bunları grandle(module:app) ye ekle aksi takdirde çalişmaz

   */
    private GoogleMap mMap;
    private String TAG = "so47492459";
    private static final int location_req = 500;
    List<String> liste;
    List locationlist=new ArrayList();
    //ArrayList<String> litem={"41.216614,32.659168",};
    LocationManager locationManager;
    static double longitude;
    static double latitude;
    Bitmap bitmap;
    String provider;
    static LatLng myhome;
    static Location str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);




        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        locationlist = Arrays.asList(liste);



    }
/*
    public void changeToLoctionVariables(){



        locationlist.add("41.216614,32.659168");
        locationlist.add("41.217362, 32.663164");
        locationlist.add("41.206615, 32.659991");

        while (!locationlist.isEmpty()){

            locationlist.remove(1);
            onMapReady(mMap);



        }



    }

 */

    private Location onLocationChanged(Location location) {


        return  location;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        final String takelat = getIntent().getExtras().getString("lat", "defaultKey");
        final String takelon=getIntent().getExtras().getString("lon", "defaultKey");
        double lgsol=Double.parseDouble(takelat); //veri çekilip lgsol a alınacak
        double lgsag=Double.parseDouble(takelon); //veri çekilip lgsol a alınacak


        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, location_req);
            return;
        }
        mMap.setMyLocationEnabled(true);


        final Location location = locationManager.getLastKnownLocation(provider);
        if (location != null) {
            str= onLocationChanged(location);
            myhome = new LatLng(str.getLatitude(),str.getLongitude());
            mMap.addMarker(new MarkerOptions().position(myhome).title("my hme"));
        } else {
            Toast.makeText(this,"bulunamadı",Toast.LENGTH_SHORT).show();

        }


        LatLng baskalokasyon = new LatLng(lgsol, lgsag);
        mMap.addMarker(new MarkerOptions().position(baskalokasyon).title("istediğin yer"));


        List<LatLng> path = new ArrayList();

        //String langstr_myhome = String.valueOf(myhome.latitude);
        //String longstr_myhome = String.valueOf(myhome.latitude);

        //Execute Directions API request


        String str1=lgsol+","+lgsag;
        double lon=str.getLongitude();
        double lan=str.getLatitude();

        String lanlonchange= lan+","+lon;

        // String str=locationlist.get(1);
        // String str1=locationlist.get(2);

        //LatLng latLng=new LatLng(str);

        GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyAZe2l_t6v5q8SSuaU07-3GG-tAYtmq9vE").build();

        DirectionsApiRequest req = DirectionsApi.getDirections(context,lanlonchange,str1);
        try {
            DirectionsResult res = req.await();

            //Loop through legs and steps to get encoded polylines of each step
            if (res.routes != null && res.routes.length > 0) {
                DirectionsRoute route = res.routes[0];

                if (route.legs !=null) {
                    for(int i=0; i<route.legs.length; i++) {
                        DirectionsLeg leg = route.legs[i];
                        if (leg.steps != null) {
                            for (int j=0; j<leg.steps.length;j++){
                                DirectionsStep step = leg.steps[j];
                                if (step.steps != null && step.steps.length >0) {
                                    for (int k=0; k<step.steps.length;k++){
                                        DirectionsStep step1 = step.steps[k];
                                        EncodedPolyline points1 = step1.polyline;
                                        if (points1 != null) {
                                            //Decode polyline and add points to list of route coordinates
                                            List<com.google.maps.model.LatLng> coords1 = points1.decodePath();
                                            for (com.google.maps.model.LatLng coord1 : coords1) {
                                                path.add(new LatLng(coord1.lat, coord1.lng));
                                            }
                                        }
                                    }
                                } else {
                                    EncodedPolyline points = step.polyline;
                                    if (points != null) {
                                        //Decode polyline and add points to list of route coordinates
                                        List<com.google.maps.model.LatLng> coords = points.decodePath();
                                        for (com.google.maps.model.LatLng coord : coords) {
                                            path.add(new LatLng(coord.lat, coord.lng));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch(Exception ex) {
            Log.e(TAG, ex.getLocalizedMessage());
        }

        //Draw the polyline
        if (path.size() > 0) {
            PolylineOptions opts = new PolylineOptions().addAll(path).color(Color.BLUE).width(5);
            mMap.addPolyline(opts);
        }

        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(baskalokasyon,15));


        //changeToLoctionVariables();
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case location_req :
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    mMap.setMyLocationEnabled(true);
                }

                break;
        }
    }
}
