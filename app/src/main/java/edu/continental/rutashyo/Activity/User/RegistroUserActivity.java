package edu.continental.rutashyo.Activity.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


import dmax.dialog.SpotsDialog;
import edu.continental.rutashyo.R;
import edu.continental.rutashyo.Retrofit.Respuesta.RespuestaRegistro;
import edu.continental.rutashyo.Retrofit.SmartCityClient;
import edu.continental.rutashyo.Retrofit.SmartCityService;
import edu.continental.rutashyo.Retrofit.Solicitud.SolicitarRegistro;
import edu.continental.rutashyo.model.Client;
import edu.continental.rutashyo.providers.AuthProvider;
import edu.continental.rutashyo.providers.ClientProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegistroUserActivity extends AppCompatActivity{
    AuthProvider mAuthProvider;
    ClientProvider mClientProvider;
    private String valNombre, valApelido, valTelefono, val_Email, valPass,valPassConf;
    EditText edtNombre, edtApellido, edtTelefono, edtEmail, edtPass, edtPassConf;
    FloatingActionButton btnEnviarRegistro;
    private TextInputLayout input_layout_nombre, input_layout_apellido, input_layout_telefono, input_layout_email,
            input_layout_pass, input_layout_passConf;
    TextView irALoginUi;
    SmartCityService smartCityService;
    SmartCityClient smartCityClient;

    AlertDialog mDialog;

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

        mDialog = new SpotsDialog.Builder()
                .setContext(this)
                .setMessage("Espere un momento")
                .setCancelable(false).build();


        retrofitInit();
        findViews();
        events();





    }

    private void retrofitInit() {
        smartCityClient = SmartCityClient.getInstance();
        smartCityService = smartCityClient.getSmartCityService();

    }

    private void events() {
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
        irALoginUi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegistroUserActivity.this, LoginUserActivity.class);
                startActivity(i);
            }
        });
    }

    private void findViews() {

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
        irALoginUi = findViewById(R.id.irALoginUi);
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
            mDialog.dismiss();
            input_layout_nombre.setError(getResources().getString(R.string.ingresa_tu_nombre));
            //requestFocus(edtNombre);
            return false;
        } else {
            input_layout_nombre.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validarTelefono() {
        if (edtTelefono.getText().toString().trim().isEmpty()) {
            mDialog.dismiss();
            input_layout_telefono.setError(getResources().getString(R.string.ingresa_tu_telefono));
            requestFocus(edtTelefono);
            return false;
        } else {
            input_layout_telefono.setErrorEnabled(false);
        }

        return true;
    }
    private boolean validarTelefonoValido() {
        mDialog.dismiss();
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
        mDialog.dismiss();
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
        mDialog.dismiss();
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
        mDialog.dismiss();
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
        mDialog.dismiss();
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


    public void crearUser(){
        //mDialog.show();
        final String name = edtNombre.getText().toString();
        final String apellido = edtApellido.getText().toString();
        final String telefono = edtTelefono.getText().toString();
        final String email = edtEmail.getText().toString();

        String direccion = "null";
        String nacimiento = "null";
        final String pass = edtPass.getText().toString();
        String userTipo = "Cliente";
        String nacionalidad = "PERU";

        mDialog.show();
        register(name, apellido,telefono,email, pass);


/*
            SolicitarRegistro solicitarRegistro =new SolicitarRegistro(name,apellido,direccion,nacimiento,nacionalidad,telefono, email, pass, userTipo);
            Call<RespuestaRegistro> call = smartCityService.doSignUp(solicitarRegistro);
            call.enqueue(new Callback<RespuestaRegistro>() {
                @Override
                public void onResponse(Call<RespuestaRegistro> call, Response<RespuestaRegistro> response) {
                    if(response.isSuccessful()){
                        mDialog.dismiss();
                        Toast.makeText(RegistroUserActivity.this, "Registrado correctamente", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(RegistroUserActivity.this, InicioUserActivity.class);
                        startActivity(i);
                        finish();
                    }else{
                        mDialog.dismiss();
                        Toast.makeText(RegistroUserActivity.this, "Revise sus datos de registro", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<RespuestaRegistro> call, Throwable t) {
                    mDialog.dismiss();
                    Toast.makeText(RegistroUserActivity.this, "Problemas de conexion", Toast.LENGTH_SHORT).show();
                }
            });
*/
    }
    void register(final String name, final String apellido, final String telefono, final String email, String password) {
        mAuthProvider.register(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                mDialog.hide();
                if (task.isSuccessful()) {
                    String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    Client client = new Client(id, name,apellido ,telefono ,email);
                    create(client);
                }
                else {
                    Toast.makeText(RegistroUserActivity.this, "No se pudo registrar el usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void create(Client client) {
        mClientProvider.create(client).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(RegistroUserActivity.this, InicioUserActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(RegistroUserActivity.this, "No se pudo crear el cliente", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



}