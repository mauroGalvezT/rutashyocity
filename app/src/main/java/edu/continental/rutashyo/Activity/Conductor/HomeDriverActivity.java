package edu.continental.rutashyo.Activity.Conductor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.database.ValueEventListener;

import edu.continental.rutashyo.R;
import edu.continental.rutashyo.Retrofit.Respuesta.RespuestaRutum;
import edu.continental.rutashyo.Retrofit.Respuesta.RespuestaVehiculo;
import edu.continental.rutashyo.Retrofit.SmartCityClient;
import edu.continental.rutashyo.Retrofit.SmartCityService;
import edu.continental.rutashyo.Retrofit.Solicitud.SolicitudCambiarEstado;
import edu.continental.rutashyo.Retrofit.Solicitud.SolicitudRuta;
import edu.continental.rutashyo.Retrofit.Solicitud.SolicitudSetLocation;
import edu.continental.rutashyo.includes.MyToolbar;
import edu.continental.rutashyo.settings.AppConst;
import edu.continental.rutashyo.settings.SharedPreferencesManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeDriverActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private SupportMapFragment mMapFragment;




    private LocationRequest mLocationRequest;
    private FusedLocationProviderClient mFusedLocation;

    private final static int LOCATION_REQUEST_CODE = 1;
    private final static int SETTINGS_REQUEST_CODE = 2;

    private Marker mMarker;

    private Button mButtonConnect;
    private boolean mIsConnect = false;

    private LatLng mCurrentLatLng;

    private ValueEventListener mListener;
    SmartCityService smartCityService;
    SmartCityClient smartCityClient;
    String idEmpresa = SharedPreferencesManager.getSomeStringValue(AppConst.PREF_IDEMPRESA);
    String token = SharedPreferencesManager.getSomeStringValue(AppConst.PREF_USERTOKEN);

    LocationCallback mLocationCallback = new LocationCallback() {

        @Override
        public void onLocationResult(LocationResult locationResult) {
            for (Location location : locationResult.getLocations()) {
                if (getApplicationContext() != null) {

                    mCurrentLatLng = new LatLng(location.getLatitude(), location.getLongitude());

                    if (mMarker != null) {
                        mMarker.remove();
                    }

                    mMarker = mMap.addMarker(new MarkerOptions().position(
                            new LatLng(location.getLatitude(), location.getLongitude())
                            )
                                    .title("Tu posicion")
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_icone))
                    );
                    // OBTENER LA LOCALIZACION DEL USUARIO EN TIEMPO REAL
                    mMap.moveCamera(CameraUpdateFactory.newCameraPosition(
                            new CameraPosition.Builder()
                                    .target(new LatLng(location.getLatitude(), location.getLongitude()))
                                    .zoom(16f)
                                    .build()
                    ));

                    new setCurrentLocation().execute(String.valueOf(location.getLatitude()),String.valueOf(location.getLongitude()));

                    Log.d("ENTRO", "ACTUALIZANDO PSOICIN");
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_driver);
        MyToolbar.show(this, "Conductor", false);

        mFusedLocation = LocationServices.getFusedLocationProviderClient(this);

        mMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mMapFragment.getMapAsync(this);

        mButtonConnect = findViewById(R.id.btnConnect);
        mButtonConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mIsConnect) {
                    disconnect();
                    String estado = "no";
                    SolicitudCambiarEstado solicitudCambiarEstado = new SolicitudCambiarEstado(token, estado);
                    Call<RespuestaVehiculo> call = smartCityService.doUpdateStatus(solicitudCambiarEstado);
                    call.enqueue(new Callback<RespuestaVehiculo>() {
                        @Override
                        public void onResponse(Call<RespuestaVehiculo> call, Response<RespuestaVehiculo> response) {
                            if(response.isSuccessful()){
                                //mDialog.dismiss();
                                //Toast.makeText(HomeDriverActivity.this, "no activo", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<RespuestaVehiculo> call, Throwable t) {
                            //mDialog.dismiss();
                            Toast.makeText(HomeDriverActivity.this, "Problemas de conexion", Toast.LENGTH_SHORT).show();
                            //Toast.makeText(getActivity(), "Problemas de conexion", Toast.LENGTH_SHORT).show();

                        }
                    });
                } else {
                    startLocation();
                    String estado = "yes";
                    SolicitudCambiarEstado solicitudCambiarEstado = new SolicitudCambiarEstado(token, estado);
                    Call<RespuestaVehiculo> call = smartCityService.doUpdateStatus(solicitudCambiarEstado);
                    call.enqueue(new Callback<RespuestaVehiculo>() {
                        @Override
                        public void onResponse(Call<RespuestaVehiculo> call, Response<RespuestaVehiculo> response) {
                            if(response.isSuccessful()){
                                //mDialog.dismiss();
                                //Toast.makeText(HomeDriverActivity.this, "activo", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<RespuestaVehiculo> call, Throwable t) {
                            //mDialog.dismiss();
                            Toast.makeText(HomeDriverActivity.this, "Problemas de conexion", Toast.LENGTH_SHORT).show();
                            //Toast.makeText(getActivity(), "Problemas de conexion", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        });

        //generateToken();
        //isDriverWorking();
        retrofitInit();
    }

    private void rutaEmpresa() {
        SolicitudRuta solicitudRuta = new SolicitudRuta(idEmpresa);
        Call<RespuestaRutum> call = smartCityService.doRutas(solicitudRuta);
        call.enqueue(new Callback<RespuestaRutum>() {
            @Override
            public void onResponse(Call<RespuestaRutum> call, Response<RespuestaRutum> response) {
                Double lat1 = Double.valueOf(response.body().getLatInicio1());
                Double long1 = Double.valueOf(response.body().getLongInicio1());
                Double lat2 = Double.valueOf(response.body().getLatInicio2());
                Double long2 = Double.valueOf(response.body().getLongInicio2());
                Double lat3 = Double.valueOf(response.body().getLatInicio3());
                Double long3 = Double.valueOf(response.body().getLongInicio3());
                Double lat4 = Double.valueOf(response.body().getLatInicio4());
                Double long4 = Double.valueOf(response.body().getLongInicio4());
                Double lat5 = Double.valueOf(response.body().getLatInicio5());
                Double long5 = Double.valueOf(response.body().getLongInicio5());
                /*
                Double lat6 = Double.valueOf(response.body().getLatInicio6());
                Double long6 = Double.valueOf(response.body().getLongInicio6());
                Double lat7 = Double.valueOf(response.body().getLatInicio7());
                Double long7 = Double.valueOf(response.body().getLongInicio7());
                Double lat8 = Double.valueOf(response.body().getLatInicio8());
                Double long8 = Double.valueOf(response.body().getLongInicio8());
                Double lat9 = Double.valueOf(response.body().getLatInicio9());
                Double long9 = Double.valueOf(response.body().getLongInicio9());
                Double lat10 = Double.valueOf(response.body().getLatInicio10());
                Double long10 = Double.valueOf(response.body().getLongInicio10());
                Double lat11 = Double.valueOf(response.body().getLatInicio11());
                Double long11 = Double.valueOf(response.body().getLongInicio11());
                Double lat12 = Double.valueOf(response.body().getLatInicio12());
                Double long12 = Double.valueOf(response.body().getLongInicio12());
                Double lat13 = Double.valueOf(response.body().getLatInicio13());
                Double long13 = Double.valueOf(response.body().getLongInicio13());
                Double lat14 = Double.valueOf(response.body().getLatInicio14());
                Double long14 = Double.valueOf(response.body().getLongInicio14());
                Double lat15 = Double.valueOf(response.body().getLatInicio15());
                Double long15 = Double.valueOf(response.body().getLongInicio15());
                */
                if(response.isSuccessful()){
                    //Toast.makeText(getContext(), "hay datos", Toast.LENGTH_SHORT).show();
                    LatLng m1 = new LatLng(lat1, long1);
                    LatLng m2 = new LatLng(lat2, long2);
                    LatLng m3 = new LatLng(lat3, long3);
                    LatLng m4 = new LatLng(lat4, long4);
                    LatLng m5 = new LatLng(lat5, long5);
                    /*
                    LatLng m6 = new LatLng(lat6, long6);
                    LatLng m7 = new LatLng(lat7, long7);
                    LatLng m8 = new LatLng(lat8, long8);
                    LatLng m9 = new LatLng(lat9, long9);
                    LatLng m10 = new LatLng(lat10, long10);
                    LatLng m11 = new LatLng(lat11, long11);
                    LatLng m12 = new LatLng(lat12, long12);
                    LatLng m13 = new LatLng(lat13, long13);
                    LatLng m14 = new LatLng(lat14, long14);
                    LatLng m15 = new LatLng(lat15, long15);
                    */
                    mMap.addMarker(new MarkerOptions()
                            .position(m1)
                            .title("Inicio Ruta")
                            .draggable(true)
                            //.snippet("Inicio")
                            .draggable(true)
                    );
                    mMap.addMarker(new MarkerOptions()
                            .position(m5)
                            .title("Fin de la ruta")
                            .draggable(true)
                            //.snippet("Final de ruta")
                            .draggable(true)
                    );
                    PolylineOptions rectOptions = new PolylineOptions()
                            .add(m1)
                            .add(m2)
                            .add(m3)
                            .add(m4)
                            .add(m5)
                            //.add(m6)
                            //.add(m7)
                            //.add(m8)
                            //.add(m9)
                            //.add(m10)
                            //.add(m11)
                            //.add(m12)
                            //.add(m13)
                            //.add(m14)
                            //.add(m15)
                            .color(R.color.primaryColor)
                            .width(30);
                    Polyline polyline = mMap.addPolyline(rectOptions);
                }else{
                    Toast.makeText(HomeDriverActivity.this, "no hay datros", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RespuestaRutum> call, Throwable t) {
                Toast.makeText(HomeDriverActivity.this, "algo paso", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private class setCurrentLocation extends AsyncTask<String, Void, String> {
        String token = SharedPreferencesManager.getSomeStringValue(AppConst.PREF_USERTOKEN);


        @Override
        protected String doInBackground(String... params) {
            final String latitude = params[0];
            final String longitude = params[1];
            //final String latitude = String.valueOf(currentLocation.getLatitude());
            //final String longitude = String.valueOf(currentLocation.getLongitude());
            SolicitudSetLocation solicitudSetLocation = new SolicitudSetLocation(token, latitude, longitude);
            Call<RespuestaVehiculo> call = smartCityService.doSetLocation(solicitudSetLocation);
            call.enqueue(new Callback<RespuestaVehiculo>() {
                @Override
                public void onResponse(Call<RespuestaVehiculo> call, Response<RespuestaVehiculo> response) {
                    if(response.isSuccessful()){
                        //Toast.makeText(HomeDriverActivity.this, "se envio lat y long", Toast.LENGTH_SHORT).show();
                    }else{
                        //Toast.makeText(HomeDriverActivity.this, "no se envio", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<RespuestaVehiculo> call, Throwable t) {

                }
            });
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            //to add spacing between cards
            if (this != null) {

            }

        }

        @Override
        protected void onPreExecute() {

        }
    }
    private void retrofitInit() {

        smartCityClient = SmartCityClient.getInstance();
        smartCityService = smartCityClient.getSmartCityService();

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        //LatLng hyo = new LatLng(-12.0668271, -75.2141605);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-12.0668271, -75.2141605), 10));
        //mMap.getUiSettings().setZoomControlsEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(false);

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setSmallestDisplacement(5);
        rutaEmpresa();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    if (gpsActived()) {
                        mFusedLocation.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
                    } else {
                        showAlertDialogNOGPS();
                    }
                } else {
                    checkLocationPermissions();
                }
            } else {
                checkLocationPermissions();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SETTINGS_REQUEST_CODE && gpsActived()) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            mFusedLocation.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
        }
        else {
            showAlertDialogNOGPS();
        }
    }

    private void showAlertDialogNOGPS() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Por favor activa tu ubicacion para continuar")
                .setPositiveButton("Configuraciones", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), SETTINGS_REQUEST_CODE);
                    }
                }).create().show();
    }

    private boolean gpsActived() {
        boolean isActive = false;
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            isActive = true;
        }
        return isActive;
    }


    private void disconnect() {

        if (mFusedLocation != null) {
            mButtonConnect.setText("Conectarse");
            mIsConnect = false;
            mFusedLocation.removeLocationUpdates(mLocationCallback);

        }
        else {
            Toast.makeText(this, "No te puedes desconectar", Toast.LENGTH_SHORT).show();
        }
    }

    private void startLocation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                if (gpsActived()) {
                    mButtonConnect.setText("Desconectarse");
                    mIsConnect = true;
                    mFusedLocation.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
                }
                else {
                    showAlertDialogNOGPS();
                }
            }
            else {
                checkLocationPermissions();
            }
        } else {
            if (gpsActived()) {
                mFusedLocation.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
            }
            else {
                showAlertDialogNOGPS();
            }
        }
    }

    private void checkLocationPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                new AlertDialog.Builder(this)
                        .setTitle("Proporciona los permisos para continuar")
                        .setMessage("Esta aplicacion requiere de los permisos de ubicacion para poder utilizarse")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCompat.requestPermissions(HomeDriverActivity.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
                            }
                        })
                        .create()
                        .show();
            }
            else {
                ActivityCompat.requestPermissions(HomeDriverActivity.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
            }
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.driver_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


}