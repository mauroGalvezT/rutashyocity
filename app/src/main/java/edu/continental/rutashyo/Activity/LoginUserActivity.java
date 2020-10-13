package edu.continental.rutashyo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.continental.rutashyo.R;
import edu.continental.rutashyo.model.user;
import edu.continental.rutashyo.settings.AppConst;

public class LoginUserActivity extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener {

    EditText edtEmailLogin, edtPassLogin;
    String valEmailLogin, valPassLogin;
    FloatingActionButton btnIniciarSesion;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
*/
        setContentView(R.layout.activity_login_user);

        edtEmailLogin = findViewById(R.id.edtEmailLogin);
        edtPassLogin = findViewById(R.id.edtPassLogin);
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);

        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valEmailLogin=edtEmailLogin.getText().toString();
                valPassLogin=edtPassLogin.getText().toString();
                Login();
            }
        });
    }
    public void OpenSignupPage(View view) {
        startActivity(new Intent(LoginUserActivity.this,RegistroUserActivity.class));
    }
    void Login(){
        String url  = AppConst.Server_url_user+
                AppConst.Login+
                "US_Email="+valEmailLogin+
                "&US_Contrasena="+valPassLogin;


        url = url.replace(" ", "%20");
        request = Volley.newRequestQueue(this);
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);



        Toast.makeText(this, "Resultado", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onErrorResponse(VolleyError error) {

        Toast.makeText(this,"No se pudo consultar"+ error.toString(), Toast.LENGTH_SHORT).show();
        Log.i("Error ", error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {

        Toast.makeText(this, "Mensaje " + response, Toast.LENGTH_SHORT).show();
        user miUsuario = new user();
        JSONArray json = response.optJSONArray("usuario");
        JSONObject jsonObject = null;
        try {
            jsonObject = json.getJSONObject(0);
            miUsuario.setNombres(jsonObject.optString("US_Nombres"));
            miUsuario.setContrasena(jsonObject.optString("US_Contrasena"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        edtEmailLogin.setText(miUsuario.getNombres());
        edtPassLogin.setText(miUsuario.getContrasena());
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}


    /*
    *     private class loginUser extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String url = global_url+"user_login.php";
            StringRequest jsonObjReq = new StringRequest(Request.Method.POST,
                    url,
                    new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {
                            try {
                                progressBar_login.setVisibility(View.INVISIBLE);
                                JSONObject json = new JSONObject(response);
                                JSONObject msg = json.getJSONObject("msg");
                                String etat = msg.getString("etat");
                                if(etat.equals("1")){
                                    JSONObject user = json.getJSONObject("user");
                                    if(user.getString("user_cat").equals("conducteur")){
                                        if(user.getString("statut_vehicule").equals("no")){
                                            Intent intent = new Intent(LoginActivity.this, DriverVehicleActivity.class);
                                            intent.putExtra("id_driver",user.getString("id"));
                                            startActivity(intent);
                                        }else if(user.getString("photo").equals("")){
                                            Intent intent = new Intent(LoginActivity.this, ChoosePhotoActivity.class);
                                            intent.putExtra("id_driver",user.getString("id"));
                                            startActivity(intent);
                                        }else if(user.getString("statut_nic").equals("no")){
                                            Intent intent = new Intent(LoginActivity.this, ChooseNIActivity.class);
                                            intent.putExtra("id_driver",user.getString("id"));
                                            startActivity(intent);
                                        }else if(user.getString("statut_licence").equals("no")){
                                            Intent intent = new Intent(LoginActivity.this, ChooseLicenceActivity.class);
                                            intent.putExtra("id_driver",user.getString("id"));
                                            startActivity(intent);
                                        }else{
                                            saveProfile(new User(user.getString("id"),user.getString("nom"),user.getString("prenom"),user.getString("phone")
                                                    ,user.getString("email"),user.getString("statut"),user.getString("login_type"),user.getString("tonotify"),user.getString("device_id"),
                                                    user.getString("fcm_id"),user.getString("creer"),user.getString("modifier"),user.getString("photo_path"),user.getString("user_cat"),user.getString("online"),user.getString("currency")
                                                    ,user.getString("statut_licence"),user.getString("statut_nic"),user.getString("brand"),user.getString("model"),user.getString("color"),user.getString("numberplate"),user.getString("statut_vehicule"),user.getString("country")));

                                            input_phone.setText("");
                                            password.setText("");
                                            launchHomeScreen();
                                        }
                                    }else{
                                        saveProfile(new User(user.getString("id"),user.getString("nom"),user.getString("prenom"),user.getString("phone")
                                                ,user.getString("email"),user.getString("statut"),user.getString("login_type"),user.getString("tonotify"),user.getString("device_id"),
                                                user.getString("fcm_id"),user.getString("creer"),user.getString("modifier"),user.getString("photo_path"),
                                                user.getString("user_cat"),"",user.getString("currency")
                                                ,"","","","","","","",user.getString("country")));

                                        input_phone.setText("");
                                        password.setText("");
                                        launchHomeScreen();
                                    }
                                }else if(etat.equals("0")){
                                    Toast.makeText(context, context.getResources().getString(R.string.this_account_does_not_exist), Toast.LENGTH_SHORT).show();
                                    requestFocus(input_phone);
                                    input_layout_phone.setError(context.getResources().getString(R.string.enter_another_phone_number));
                                }else if(etat.equals("2")){
                                    Toast.makeText(context, context.getResources().getString(R.string.incorrect_password), Toast.LENGTH_SHORT).show();
                                    requestFocus(password);
                                    input_layout_password.setError(context.getResources().getString(R.string.enter_another_password));
                                }else {
                                    requestFocus(input_phone);
                                    Toast.makeText(context, R.string.this_account_is_inactive, Toast.LENGTH_LONG).show();
                                }

                            } catch (JSONException e) {

                            }
                        }
                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    progressBar_login.setVisibility(View.INVISIBLE);
                }
            }) {

                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("phone", val_phone);
                    params.put("mdp", val_password);
                    return params;
                }

            };
            AppController.getInstance().addToRequestQueue(jsonObjReq);
            jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(
                    10000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

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

    private void saveProfile(User user){
        M.setNom(user.getNom(),context);
        M.setPrenom(user.getPrenom(),context);
        M.setPhone(user.getPhone(),context);
        M.setEmail(user.getEmail(),context);
        M.setID(user.getId(),context);
        M.setlogintype(user.getLogin_type(),context);
        M.setUsername(user.getNom(),context);
        M.setUserCategorie(user.getUser_cat(),context);
//        M.setCoutByKm(user.getCost(),context);
        M.setStatutConducteur(user.getStatut_online(),context);
        M.setCurrentFragment("",context);
        M.setCurrency(user.getCurrency(),context);
        M.setPhoto(user.getPhoto(),context);

        M.setVehicleBrand(user.getVehicle_brand(),context);
        M.setVehicleColor(user.getVehicle_color(),context);
        M.setVehicleModel(user.getVehicle_model(),context);
        M.setVehicleNumberPlate(user.getVehicle_numberplate(),context);
        M.setCountry(user.getCountry(),context);
        if(user.getTonotify().equals("yes"))
            M.setPushNotification(true, context);
        else
            M.setPushNotification(false, context);

        updateFCM(M.getID(context));
    }
    * */
