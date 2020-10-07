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
    public String enviarRegistroGET(String nombre, String apellido, String telefono, String email, String pass){
        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder result = null; // recibira la data

        try {
            url = new  URL(AppConst.Server_url+
                    AppConst.Registro+
                    "US_Nombres="+ nombre+
                    "&US_Apellidos="+apellido+
                    "&US_Direccion="+"null"+
                    "&US_Fecha_Nacimiento="+"null"+
                    "&US_Nacionalidad="+"Peru"+
                    "&US_Telefono="+telefono+
                    "&US_Email="+email+
                    "&US_Contrasena="+pass+
                    "&US_Tipo="+"Cliente");
            HttpURLConnection conection = (HttpURLConnection) url.openConnection();
            respuesta = conection.getResponseCode();//respuesta de 200
            result=new StringBuilder();
            if(respuesta == HttpURLConnection.HTTP_OK){
                InputStream in = new BufferedInputStream(conection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                while ((linea = reader.readLine()) != null){
                    result.append(linea);
                }
            }
        }catch (Exception e){
            return result.toString();
        }
    return null;

    }

 */
}