package edu.continental.rutashyo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import edu.continental.rutashyo.R;
import edu.continental.rutashyo.settings.AppConst;

public class RegistroUserActivity extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener{

    private String valNombre, valApelido, valTelefono, val_Email, valPass,valPassConf;
    EditText edtNombre, edtApellido, edtTelefono, edtEmail, edtPass, edtPassConf;
    FloatingActionButton btnEnviarRegistro;
    private TextInputLayout input_layout_nombre, input_layout_apellido, input_layout_telefono, input_layout_email,
            input_layout_pass, input_layout_passConf;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*
el layout no tenra limites
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
*/
        setContentView(R.layout.activity_registro_user);

        edtNombre = findViewById(R.id.edtNombre);
        edtApellido = findViewById(R.id.edtApellido);
        edtTelefono = findViewById(R.id.edtTelefono);
        edtEmail = findViewById(R.id.edtEmail);
        edtPass = findViewById(R.id.edtPass);
        edtPassConf = findViewById(R.id.edtPass_conf);
        btnEnviarRegistro = findViewById(R.id.btnEnviarRegistro);

        input_layout_nombre = findViewById(R.id.input_layout_nombre);
        input_layout_apellido = findViewById(R.id.input_layout_apellido);
        input_layout_telefono = findViewById(R.id.input_layout_telefono);
        input_layout_email = findViewById(R.id.input_layout_email);
        input_layout_pass = findViewById(R.id.input_layout_pass);
        input_layout_passConf = findViewById(R.id.input_layout_passConf);


        btnEnviarRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valNombre =edtNombre.getText().toString();
                valApelido =edtApellido.getText().toString();
                valTelefono =edtTelefono.getText().toString();
                val_Email =edtEmail.getText().toString();
                valPass =edtPass.getText().toString();
                valPassConf =edtPassConf.getText().toString();

                enviarFormularioUser();

                //enviarRegistroGET(edtNombre.toString(),edtApellido.toString(), edtTelefono.toString(), edtEmail.toString(),edtPass.toString());
                //startActivity();
            }
        });

    }
    private void enviarFormularioUser(){
        if (!validarNombre()) {
            return;
        }
        if (!validarTelefono()) {
            return;
        }
        if (!validarTelefonoValido()) {
            return;
        }
        if (!validarEmailValido()) {
            return;
        }
        if (!validarContrasena()) {
            return;
        }

        if (!validarContrasenaValida()) {
            return;
        }
        if (!validarContrasenaConf()) {
            return;
        }


        if(valPassConf.equals(valPass)){
            //progressBar_subs.setVisibility(View.VISIBLE);
            crearUser();
           // new crearUser().execute();
        }else{
            Toast.makeText(this, getResources().getString(R.string.contrasena_no_coincide), Toast.LENGTH_SHORT).show();

        }
    }

    private boolean validarNombre() {
        if (edtNombre.getText().toString().trim().isEmpty()) {
            input_layout_nombre.setError(getResources().getString(R.string.ingresa_tu_nombre));
            requestFocus(edtNombre);
            return false;
        } else {
            input_layout_nombre.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validarTelefono() {
        if (edtTelefono.getText().toString().trim().isEmpty()) {
            input_layout_telefono.setError(getResources().getString(R.string.ingresa_tu_telefono));
            requestFocus(edtTelefono);
            return false;
        } else {
            input_layout_telefono.setErrorEnabled(false);
        }

        return true;
    }
    private boolean validarTelefonoValido() {
        if (edtTelefono.getText().toString().trim().length() < 8) {
            input_layout_telefono.setError(getResources().getString(R.string.ingresa_telefono_valido));
            requestFocus(edtTelefono);
            return false;
        } else {
            input_layout_telefono.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validarEmailValido() {
        if (edtEmail.getText().toString().trim().isEmpty()) {
            input_layout_email.setError(getResources().getString(R.string.ingresa_email));
            requestFocus(edtEmail);
            return false;
        } else {
            input_layout_email.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validarContrasena() {
        if (edtPass.getText().toString().trim().isEmpty()) {
            input_layout_pass.setError(getResources().getString(R.string.ingresa_tu_contrasena));
            requestFocus(edtPass);
            return false;
        } else {
            input_layout_pass.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validarContrasenaValida() {
        if (edtPass.getText().toString().trim().length() < 8) {
            input_layout_pass.setError(getResources().getString(R.string.pass_requiere_8_caracteres));
            requestFocus(edtPass);
            return false;
        } else {
            input_layout_pass.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validarContrasenaConf() {
        if (edtPassConf.getText().toString().trim().isEmpty()) {
            input_layout_passConf.setError(getResources().getString(R.string.confirma_tu_contrasena));
            requestFocus(edtPassConf);
            return false;
        } else {
            input_layout_passConf.setErrorEnabled(false);
        }

        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    //consumir api get
    public void crearUser(){
        String url  = AppConst.Server_url+
                AppConst.Registro+
                "US_Nombres="+ valNombre+
                "&US_Apellidos="+valApelido+
                "&US_Direccion="+"null"+
                "&US_Fecha_Nacimiento="+"null"+
                "&US_Nacionalidad="+"Peru"+
                "&US_Telefono="+valTelefono+
                "&US_Email="+val_Email+
                "&US_Contrasena="+valPass+
                "&US_Tipo="+"Cliente";
        url = url.replace(" ", "%20");
        request = Volley.newRequestQueue(this);
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(this, "no registrado", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onErrorResponse(VolleyError error) {
       // progreso.hide();
        /*
        Toast.makeText(this, "No se puede guardar", Toast.LENGTH_SHORT).show();
        Log.i("Error", error.toString());

         */
        startActivity(new Intent(RegistroUserActivity.this, InicioUserActivity.class));
        finish();
        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();


    }

/*
       private class createUser extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String url = global_url+"insert_user.php";
            StringRequest jsonObjReq = new StringRequest(Request.Method.POST,
                    url,
                    new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {
                            try {
                                progressBar_subs.setVisibility(View.INVISIBLE);
                                JSONObject json = new JSONObject(response);
                                JSONObject msg = json.getJSONObject("msg");
                                String etat = msg.getString("etat");
                                if(etat.equals("1")){
                                    JSONObject user = json.getJSONObject("user");

                                    phone_subs.setText("");
                                    password_subs.setText("");
                                    password_conf.setText("");
                                    firstname_subs.setText("");
                                    email_insc.setText("");
//                                    Toast.makeText(context, "enhorabuena", Toast.LENGTH_SHORT).show();

                                    if(account_type.equals("cliente")){
                                        saveProfile(new User(user.getString("id"),user.getString("nom"),user.getString("prenom"),user.getString("phone")
                                                ,user.getString("email"),user.getString("statut"),user.getString("login_type"),user.getString("tonotify"),user.getString("device_id"),
                                                user.getString("fcm_id"),user.getString("creer"),user.getString("modifier"),user.getString("photo_path"),user.getString("user_cat"),"",user.getString("currency")
                                                ,"","" ,"","","","","",user.getString("country")));
                                    }

//                                    phone_subs.setText(phone);
//                                    Connexion.input_phone.setText(user.getString("phone"));
                                    launchHomeScreen();
                                }else if(etat.equals("2")){
                                    Toast.makeText(context, "This number already exists", Toast.LENGTH_SHORT).show();
                                    requestFocus(phone_subs);
                                    input_layout_phone_subs.setError("Enter another phone number");
                                }else
                                    Toast.makeText(context, "Failure to register", Toast.LENGTH_SHORT).show();

                            } catch (JSONException e) {

                            }
                        }
                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    progressBar_subs.setVisibility(View.INVISIBLE);
                }
            }) {

                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("firstname", val_firstname_subs);
                    params.put("phone", val_phone_subs);
                    params.put("password", val_password_subs);
                    params.put("email", val_email_subs);
                    params.put("account_type", account_type);
                    params.put("login_type", "phone");
                    params.put("tonotify", "yes");
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
        M.setCurrentFragment("",context);
        M.setCurrency(user.getCurrency(),context);
        if(!user.getUser_cat().equals("user_app"))
            M.setStatutConducteur(user.getStatut_online(),context);

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

    }

 */
}