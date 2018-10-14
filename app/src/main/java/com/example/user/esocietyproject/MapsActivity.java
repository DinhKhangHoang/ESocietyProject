package com.example.user.esocietyproject;

import android.app.AlertDialog;
import android.app.Dialog;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.DiemToaDo;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    Marker marker;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    GoogleMap mMap;
    ProgressDialog dialog;
    ArrayList<DiemToaDo> dsDiem;
    ArrayList<Diem> dsTemp;
    ArrayAdapter<String> adapterStr;
    ArrayList<Integer> area = new ArrayList<>();
    ArrayList<String> arrStr = new ArrayList<>();

    private double distance (double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;
        return (dist*600);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts decimal degrees to radians             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts radians to decimal degrees             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    ProgressDialog dialogUpload;
    ProgressDialog dialogDownload;
    Map<String, Integer> mMarkers = new HashMap<String, Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        addControls();
        addEvents();
    }

    private void addEvents() {

    }
    private void addControls() {
        dsDiem = new ArrayList<>();
//        ReceiveTask receiveTask = new ReceiveTask();
//        receiveTask.execute();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        dsDiem.clear();
        dsDiem.add(new DiemToaDo("1","Ô nhiễm nước","",10.862005,106.71624,"4"));
        dsDiem.add(new DiemToaDo("2","Ô nhiễm nước","",10.863132,106.715811,"4"));
        dsDiem.add(new DiemToaDo("3","Ô nhiễm nước","",10.864626,106.714047,"4"));
        dsDiem.add(new DiemToaDo("4","Ô nhiễm nước","",10.865657,106.716983,"4"));
        dsDiem.add(new DiemToaDo("5","Ô nhiễm nước","",10.864796,106.716291,"4"));
        dsDiem.add(new DiemToaDo("6","Ô nhiễm nước","",10.860753,106.714741,"4"));
        dsDiem.add(new DiemToaDo("7","Ô nhiễm đất","",10.792061,106.62562,"1"));
        dsDiem.add(new DiemToaDo("8","Ô nhiễm đất","",10.795019,106.626738,"1"));
        dsDiem.add(new DiemToaDo("9","Ô nhiễm đất","",10.793061,106.625878,"1"));
        dsDiem.add(new DiemToaDo("10","Ô nhiễm đất","",10.79224,106.627039,"1"));
        dsDiem.add(new DiemToaDo("11","Ô nhiễm đất","",10.794198,106.625867,"1"));
        dsDiem.add(new DiemToaDo("12","Ô nhiễm đất","",10.792983,106.626012,"1"));
        dsDiem.add(new DiemToaDo("13","Ô nhiễm đất","",10.792361,106.624698,"1"));
        dsDiem.add(new DiemToaDo("14","Ô nhiễm rác thải","",10.834771,106.666291,"3"));
        dsDiem.add(new DiemToaDo("15","Ô nhiễm rác thải","",10.835792,106.667222,"3"));
        dsDiem.add(new DiemToaDo("16","Ô nhiễm rác thải","",10.835013,106.667983,"3"));
        dsDiem.add(new DiemToaDo("17","Ô nhiễm rác thải","",10.836729,106.665927,"3"));
        dsDiem.add(new DiemToaDo("18","Ô nhiễm rác thải","",10.835502,106.667372,"3"));
        dsDiem.add(new DiemToaDo("19","Ô nhiễm không khí","",10.804907,106.670519,"2"));
        dsDiem.add(new DiemToaDo("20","Ô nhiễm không khí","",10.805822,106.671551,"2"));
        dsDiem.add(new DiemToaDo("21","Ô nhiễm không khí","",10.805159,106.673326,"2"));
        dsDiem.add(new DiemToaDo("22","Ô nhiễm không khí","",10.804017,106.672122,"2"));
        dsDiem.add(new DiemToaDo("23","Ô nhiễm không khí","",10.806875,106.669854,"2"));

        for (DiemToaDo diem:dsDiem)
        {
            MarkerOptions mo = new MarkerOptions()
                    .icon(BitmapDescriptorFactory.defaultMarker(getColorForType(diem.getTittle())))
                    .position(new LatLng(diem.getLattitude(),diem.getLongitude()))
                    .flat(true)
                    .snippet(diem.getDescription())
                    .rotation(0)
                    .title(diem.getTittle());
            Marker mkr = mMap.addMarker(mo);
            mMarkers.put(mkr.getId(), Integer.parseInt(diem.getID()));
        }

        int counter = 0;
        for(int i = 0; i < dsDiem.size()-1 ; i++){
            if(dsDiem.get(i).getAreaID() == dsDiem.get(i+1).getAreaID()) {
                counter++;
                continue;
            }
            else {
                area.add(counter + 1);
                counter = 0;
            }
        }

        area.add(counter+1);

        //int j = 0;
        int countArea = 0;



        for(int count = 0 ; count < area.size() ; count++) {
            //PolygonOptions rectOptions = new PolygonOptions();
            //Polygon polygon;
            double latMin = dsDiem.get(countArea).getLattitude();
            double longMin = dsDiem.get(countArea).getLongitude();
            double latMax = dsDiem.get(countArea).getLattitude();
            double longMax = dsDiem.get(countArea).getLongitude();

            for(int i = 0; i < area.get(count) ; i++) {
                //countArea++;
                if(dsDiem.get(countArea).getLattitude() > latMax){ latMax = dsDiem.get(countArea).getLattitude();};
                if(dsDiem.get(countArea).getLongitude() > longMax){ longMax = dsDiem.get(countArea).getLongitude();};
                if(dsDiem.get(countArea).getLattitude() < latMin){ latMin = dsDiem.get(countArea).getLattitude();};
                if(dsDiem.get(countArea).getLongitude() < longMin){ longMin = dsDiem.get(countArea).getLongitude();};

                countArea++;
            }


            double R = distance(latMin,longMin,latMax,longMax);
            double latR = (latMax + latMin) /2;
            double longR = (longMax + longMin)/2;
            CircleOptions circleOptions = new CircleOptions().center(new LatLng(latR,longR)).radius(R).strokeWidth(2).strokeColor(Color.RED).fillColor(Color.TRANSPARENT);
            Circle circle = mMap.addCircle(circleOptions);


            //j++;

        }



        LatLng moveTo = new LatLng(dsDiem.get(1).getLattitude(),dsDiem.get(1).getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(moveTo,15.0f));
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                if (marker!=null) marker.remove();
                MarkerOptions mo = new MarkerOptions()
                        .icon(BitmapDescriptorFactory.defaultMarker())
                        .position(latLng)
                        .snippet("Click here for adding new problem.")
                        .title("Bạn muốn nhập điểm mới ?");
                marker = mMap.addMarker(mo);
            }
        });
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(final Marker marker) {
                String tittle = marker.getTitle();
                if (tittle.equals("Bạn muốn nhập điểm mới ?"))
                {
                    final Dialog dialog = new Dialog(MapsActivity.this);
                    dialog.setContentView(R.layout.nhap);
                    dialog.setTitle("Nhap");
                    Spinner spinner = dialog.findViewById(R.id.spTittle);
                    final TextView txtDescription = dialog.findViewById(R.id.txtDescription);
                    final Button btnOK = dialog.findViewById(R.id.btnOK);
                    final Button btnHuy = dialog.findViewById(R.id.btnHuy);
                    arrStr.clear();
                    List<String> arr = Arrays.asList((getResources().getStringArray(R.array.dsTittle)));
                    for (String str:arr
                         ) {
                        arrStr.add(str);
                    }
                    adapterStr = new ArrayAdapter<>(
                            MapsActivity.this,
                            android.R.layout.simple_spinner_item,arrStr
                    );
                    adapterStr.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                    spinner.setAdapter(adapterStr);
                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                            btnOK.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    DiemToaDo diem = new DiemToaDo();
                                    diem.setID(dsDiem.size()+1+"");
                                    diem.setTittle(arrStr.get(position));
                                    diem.setDescription(txtDescription.getText().toString());
                                    diem.setLattitude(marker.getPosition().latitude);
                                    diem.setLongitude(marker.getPosition().longitude);
//                                    diem.setAreaID(setAreaID());
                                    dsDiem.add(diem);

                                    MarkerOptions mo = new MarkerOptions()
                                            .icon(BitmapDescriptorFactory.defaultMarker(getColorForType(diem.getTittle())))
                                            .position(new LatLng(diem.getLattitude(),diem.getLongitude()))
                                            .flat(true)
                                            .snippet(diem.getDescription())
                                            .rotation(0)
                                            .title(diem.getTittle());
                                    Marker mkr = mMap.addMarker(mo);
                                    mMarkers.put(mkr.getId(), Integer.parseInt(diem.getID()));
                                    dialog.dismiss();
                                    marker.remove();
                                }
                            });
                            btnHuy.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    marker.remove();
                                }
                            });
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    dialog.setCancelable(false);
                    Window window = dialog.getWindow();
                    window.setLayout(700,300);
                    dialog.show();
                }
            }
        });
//        SendInfoTask sendTask = new SendInfoTask();
//        sendTask.execute();
    }

    private float getColorForType(String tittle) {
        float type;
        switch(tittle)
        {
            case("Ô nhiễm nước") : type = BitmapDescriptorFactory.HUE_BLUE;break;
            case("Ô nhiễm đất") : type = BitmapDescriptorFactory.HUE_ORANGE;break;
            case("Ô nhiễm rác thải") : type = BitmapDescriptorFactory.HUE_MAGENTA;break;
            case("Ô nhiễm không khí") : type = BitmapDescriptorFactory.HUE_AZURE;break;
            default: type = BitmapDescriptorFactory.HUE_RED;break;
        }
        return type;
    }
//    class ReceiveTask extends AsyncTask<Void,Void,Void> {
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            dsDiem.clear();
//            Log.d("diem", "fetch database");
//            db.collection("databases")
//                    .get()
//                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                        @Override
//                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                            Log.d("diem", "completed");
//                            if (task.isSuccessful()) {
//                                for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
//                                    Diem gt = document.toObject(Diem.class);
//                                    dsTemp.add(gt);
//                                    }
//                            } else {
//                                Log.d("Loi", "Error getting documents: ", task.getException());
//                            }
//                            dialogDownload.dismiss();
//                            for (Diem temp : dsTemp)
//                            {
//                                DiemToaDo diemToaDo = new DiemToaDo();
//                                diemToaDo.setID(temp.ID);
//                                diemToaDo.setAreaID(temp.AreaID);
//                                diemToaDo.setTittle(temp.Tittle);
//                                diemToaDo.setLattitude(temp.Lattitude);
//                                diemToaDo.setLongitude(temp.Longitude);
//                                dsDiem.add(diemToaDo);
//                            }
//
//                            Toast.makeText(MapsActivity.this, "Upload thanh cong", Toast.LENGTH_SHORT).show();
//
//                        }
//                    });
//            return null;
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            dialogDownload = new ProgressDialog(MapsActivity.this);
//            dialogDownload.setTitle("Thông báo");
//            dialogDownload.setCancelable(false);
//            dialogDownload.setMessage("Đang nhận dữ liệu từ hệ thống.");
//            dialogDownload.show();
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//
//        }
//
//        @Override
//        protected void onProgressUpdate(Void... values) {
//            super.onProgressUpdate(values);
//        }
//    }
    class SendInfoTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids)
        {

            for (int i = 0; i < dsDiem.size(); i++) {
                DiemToaDo diemCu = dsDiem.get(i);
                Diem diem = new Diem(diemCu.getID(), diemCu.getTittle(),
                        diemCu.getDescription(), diemCu.getLattitude(), diemCu.getLongitude(), "");
                db.collection("databases").document(diemCu.getID()).set(diem);
            }
            return null;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialogUpload = new ProgressDialog(MapsActivity.this);
            dialogUpload.setTitle("Thông báo");
            dialogUpload.setCancelable(false);
            dialogUpload.setMessage("Đang gửi dữ liệu lên hệ thống.");
            dialogUpload.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialogUpload.dismiss();

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
