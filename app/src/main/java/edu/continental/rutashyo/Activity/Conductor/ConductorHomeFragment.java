package edu.continental.rutashyo.Activity.Conductor;

import android.os.Bundle;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.DefaultRetryPolicy;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.RoutingListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.tabs.TabLayout;


import com.sucho.placepicker.AddressData;
import com.sucho.placepicker.Constants;
import com.sucho.placepicker.MapType;
import com.sucho.placepicker.PlacePicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static android.content.Context.LOCATION_SERVICE;
import static android.os.Build.VERSION_CODES.M;

import dmax.dialog.SpotsDialog;
import edu.continental.rutashyo.R;
//import edu.continental.rutashyo.direcciones.FetchURL;
import edu.continental.rutashyo.Retrofit.Respuesta.RespuestaRegistro;
import edu.continental.rutashyo.Retrofit.Respuesta.RespuestaRutum;
import edu.continental.rutashyo.Retrofit.Respuesta.RespuestaTipoVehiculo;
import edu.continental.rutashyo.Retrofit.Respuesta.RespuestaVehiculo;
import edu.continental.rutashyo.Retrofit.SmartCityClient;
import edu.continental.rutashyo.Retrofit.SmartCityService;
import edu.continental.rutashyo.Retrofit.Solicitud.SolicitudCambiarEstado;
import edu.continental.rutashyo.Retrofit.Solicitud.SolicitudRuta;
import edu.continental.rutashyo.Retrofit.Solicitud.SolicitudSetLocation;
import edu.continental.rutashyo.controller.AppController;
import edu.continental.rutashyo.settings.AppConst;
import edu.continental.rutashyo.settings.ConnectionDetector;
import edu.continental.rutashyo.settings.SharedPreferencesManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class ConductorHomeFragment extends Fragment  implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleMap.OnMyLocationClickListener,
        GoogleMap.OnMyLocationButtonClickListener,
        RoutingListener{

    SmartCityService smartCityService;
    SmartCityClient smartCityClient;

    View view;
    private Marker mMarker;
    private static Context context;
    public static Activity activity;
    ConnectionDetector connectionDetector;

    ArrayList<String> tabNames = new ArrayList<String>();
    int currpos = 0;


    /** MAP **/
    public static GoogleMap mMap;
    public static Location currentLocation;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private static final int LOCATION_REQUEST_CODE = 101;

    /** GOOGLE API CLIENT **/
    private GoogleApiClient googleApiClient;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private LocationRequest locationRequest;
    private static final long UPDATE_INTERVAL = 5000, FASTEST_INTERVAL = 5000; // = 5 seconds

    View mapView;
    private LocationManager locationManager;
    private final int REQUEST_FINE_LOCATION = 1234;
    private String provider;
    private int PLACE_PICKER_REQUEST = 1;

    //    private FloatingActionButton fab;
    public static Marker currentMarker = null, destinationMarker = null
            , departMarkerReservation = null, destinationMarkerReservation = null
            , departMarkerMesRequetes = null, destinationMarkerMesRequetes = null;

    ArrayList<Marker> listMarker = new ArrayList<Marker>();
    //    private TextView commander,reserver;
    int PLACE_PICKER_REQUEST_RESERVATION_DEPART = 101;
    int PLACE_PICKER_REQUEST_RESERVATION_DESTINATION = 102;
    public static ArrayList<Location> tabLocation = new ArrayList<Location>();

    private Boolean verif = false;
    public static Polyline currentPolyline;
    PlacesClient placesClient;
    private ImageView my_location,choose_my_location,choose_my_location_destination;
    public static EditText input_text_depart;
    public static EditText input_text_arrivee;

    private static File file;
    private static RelativeLayout layout_main;
    public static BottomSheetDialogFragment bottomSheetFragmentBooking;
    private Bitmap bitmap;
    ImageView btn_my_request;
    private static String durationBicicling ="";
    private static String durationDriving="";
    private static String distance, duration;

    private RecyclerView recycler_view_ride;
    //private List<FavoriteRidePojo> albumList_ride;
    //private FavoriteRideAdapter adapter_ride;
    private ProgressBar progressBar_ride;
    private TextView nothing;
    public static AlertDialog alertDialog;


    SwitchCompat switch_statut;
    Button mButtonConnect;
    private boolean mIsConnect = false;
    private LocationRequest mLocationRequest;
    private FusedLocationProviderClient mFusedLocation;
    android.app.AlertDialog mDialog;


    List<RespuestaRutum> respuestaRutumList = new ArrayList<>();
    String idEmpresa = SharedPreferencesManager.getSomeStringValue(AppConst.PREF_IDEMPRESA);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDialog = new SpotsDialog.Builder()
                .setContext(getContext())
                .setMessage("Espere un momento")
                .setCancelable(false).build();

        setHasOptionsMenu(true);
        if (getArguments() != null)
            currpos = getArguments().getInt("tab_pos", 0);
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
                if(response.isSuccessful()){
                    //Toast.makeText(getContext(), "hay datos", Toast.LENGTH_SHORT).show();
                    LatLng m1 = new LatLng(lat1, long1);
                    LatLng m2 = new LatLng(lat2, long2);
                    LatLng m3 = new LatLng(lat3, long3);
                    LatLng m4 = new LatLng(lat4, long4);
                    LatLng m5 = new LatLng(lat5, long5);
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
                    mMap.addMarker(new MarkerOptions()
                            .position(m1)
                            .title("Inicio Ruta")
                            .draggable(true)
                            //.snippet("Inicio")
                            .draggable(true)
                    );
                    mMap.addMarker(new MarkerOptions()
                            .position(m15)
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
                            .add(m6)
                            .add(m7)
                            .add(m8)
                            .add(m9)
                            .add(m10)
                            .add(m11)
                            .add(m12)
                            .add(m13)
                            .add(m14)
                            .add(m15)
                            .color(R.color.primaryColor)
                            .width(30);
                    Polyline polyline = mMap.addPolyline(rectOptions);
                }else{
                    Toast.makeText(getContext(), "no hay datos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RespuestaRutum> call, Throwable t) {
                Toast.makeText(getContext(), "algo paso", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void retrofitInit() {

            smartCityClient = SmartCityClient.getInstance();
            smartCityService = smartCityClient.getSmartCityService();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_conductor_home, container, false);
        // Inflate the layout for this fragment



        context=getActivity();

        activity = (Activity)view.getContext();
        connectionDetector = new ConnectionDetector(context);
        switch_statut =  view.findViewById(R.id.switch_statut);

        layout_main = (RelativeLayout) view.findViewById(R.id.layout_main);
        btn_my_request =(ImageView) view.findViewById(R.id.btn_my_request);


        //Place Autocomplete
        String apikey = getResources().getString(R.string.google_maps_key);
        if(!Places.isInitialized()){
            Places.initialize(getActivity().getApplicationContext(),apikey);
        }

        placesClient = Places.createClient(context);







        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = ((SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map));
        mapView = mapFragment.getView();
        mapFragment.getMapAsync(this);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
//            return;
        }
//        fetchLastLocation();

        // Get the location manager
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        // Define the criteria how to select the locatioin provider -> use
        // default
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        if (provider != null) {
            currentLocation = locationManager.getLastKnownLocation(provider);

        }

        // we build google api client
        googleApiClient = new GoogleApiClient.Builder(context)
//                .enableAutoManage(getActivity(),0,this)
                .addApi(LocationServices.API)
//                .addApi(Places.GEO_DATA_API)
                .addConnectionCallbacks(this)
//                .addApi(Places.PLACE_DETECTION_API)
//                .enableAutoManage(getActivity(), this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).build();


        switch_statut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(switch_statut.isChecked()) {
                    mDialog.show();
                    String token = SharedPreferencesManager.getSomeStringValue(AppConst.PREF_USERTOKEN);
                    String estado = "yes";

                    SolicitudCambiarEstado solicitudCambiarEstado = new SolicitudCambiarEstado(token, estado);
                    Call<RespuestaVehiculo> call = smartCityService.doUpdateStatus(solicitudCambiarEstado);
                    call.enqueue(new Callback<RespuestaVehiculo>() {
                        @Override
                        public void onResponse(Call<RespuestaVehiculo> call, Response<RespuestaVehiculo> response) {
                            if(response.isSuccessful()){
                                mDialog.dismiss();
                                Toast.makeText(getContext(), "Activo", Toast.LENGTH_SHORT).show();

                            }else{
                                mDialog.dismiss();
                                Toast.makeText(getContext(), "algo paso", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<RespuestaVehiculo> call, Throwable t) {
                            mDialog.dismiss();
                            Toast.makeText(getActivity(), "Problemas de conexion", Toast.LENGTH_SHORT).show();

                        }
                    });

                }else {
                    mDialog.show();
                    String token = SharedPreferencesManager.getSomeStringValue(AppConst.PREF_USERTOKEN);
                    String estado = "no";
                    SolicitudCambiarEstado solicitudCambiarEstado = new SolicitudCambiarEstado(token, estado);
                    Call<RespuestaVehiculo> call = smartCityService.doUpdateStatus(solicitudCambiarEstado);
                    call.enqueue(new Callback<RespuestaVehiculo>() {
                        @Override
                        public void onResponse(Call<RespuestaVehiculo> call, Response<RespuestaVehiculo> response) {
                            if(response.isSuccessful()){
                                mDialog.dismiss();
                                Toast.makeText(getContext(), "No activo", Toast.LENGTH_SHORT).show();

                            }else{
                            mDialog.dismiss();
                            Toast.makeText(getContext(), "algo paso", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<RespuestaVehiculo> call, Throwable t) {

                        }
                    });

                }
            }
        });

        return view;
    }




    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (Build.VERSION.SDK_INT >= M) {
            if (context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
        }

        mMap.setMyLocationEnabled(true);
        if (mapView != null && mapView.findViewById(Integer.parseInt("1")) != null) {
            View locationButton = ((View) mapView.findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) locationButton.getLayoutParams();
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
            layoutParams.setMargins(0, 20, 20, 550);
            View zoomButton = ((View) mapView.findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("1"));
            RelativeLayout.LayoutParams layoutParamsZoom = (RelativeLayout.LayoutParams) zoomButton.getLayoutParams();
            layoutParamsZoom.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
            layoutParamsZoom.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
            layoutParamsZoom.setMargins(0, 20, 20, 200);
        }

//        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);






        // Initialize the location fields


            //LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());


            //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));
            if (mMarker != null) {
                mMarker.remove();
            }
            mMarker = mMap.addMarker(new MarkerOptions().position(
                    new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude())
                    )
                            .title("Tu posicion")
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_logo))
            );
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(
                    new CameraPosition.Builder()
                            .target(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()))
                            .zoom(16f)
                            .build()
            ));
            new setCurrentLocation().execute(String.valueOf(currentLocation.getLatitude()),String.valueOf(currentLocation.getLongitude()));



        rutaEmpresa();





        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
//                Toast.makeText(context, ""+latLng.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }








    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case LOCATION_REQUEST_CODE: {
                if(!isLocationEnabled(context))
                    showMessageEnabledGPS();
                return;
            }

        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.bottom_navigation_menu,menu);
    }

    private boolean isLocationEnabled(Context context){
//        String locationProviders;
        boolean enabled = false;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            int mode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE,
                    Settings.Secure.LOCATION_MODE_OFF);
            enabled = (mode != Settings.Secure.LOCATION_MODE_OFF);
        }else{
            LocationManager service = (LocationManager) context.getSystemService(LOCATION_SERVICE);
            enabled =  service.isProviderEnabled(LocationManager.GPS_PROVIDER)||service.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        }
        return enabled;
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
                        Toast.makeText(getActivity(), "se envio lat y long", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity(), "no se envio", Toast.LENGTH_SHORT).show();
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


    @Override
    public void onRoutingFailure(RouteException e) {

    }

    @Override
    public void onRoutingStart() {

    }

    @Override
    public void onRoutingSuccess(ArrayList<Route> arrayList, int i) {

    }

    @Override
    public void onRoutingCancelled() {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                &&  ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        // Permissions ok, we get last location
        currentLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        startLocationUpdates();
    }

    private void startLocationUpdates() {
        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(UPDATE_INTERVAL);
        locationRequest.setFastestInterval(FASTEST_INTERVAL);

        if (ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                &&  ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            Toast.makeText(this, "You need to enable permissions to display location !", Toast.LENGTH_SHORT).show();
        }

        //LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
    }
    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }



    @Override
    public boolean onMyLocationButtonClick() {
//        Toast.makeText(mContext, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        if(!isLocationEnabled(context))
            showMessageEnabledGPS();
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {

    }

    public void showMessageEnabledGPS(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(context.getResources().getString(R.string.this_service_requires_the_activation_of_the_gps))
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }





}