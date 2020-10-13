package edu.continental.rutashyo.Activity.Conductor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import edu.continental.rutashyo.Activity.User.InicioUserActivity;
import edu.continental.rutashyo.R;

public class RegistroVehiculoActivity extends AppCompatActivity {

    private String valmarca,valmodelo,valcolor,valplaca;
    EditText edtmarca,edtmodelo,edtcolor,edtnum_placa;
    FloatingActionButton btnRegistrar;
    private TextInputLayout input_layout_marca,input_layout_modelo,input_layout_color,input_layout_placa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_vehiculo);
        edtmarca=findViewById(R.id.edtMarca);
        edtmodelo=findViewById(R.id.edtModelo);
        edtcolor=findViewById(R.id.edtColor);
        edtnum_placa=findViewById(R.id.edtNum_placa);
        btnRegistrar=findViewById(R.id.btnRegistrar);

        input_layout_marca=findViewById(R.id.input_layout_marca);
        input_layout_modelo=findViewById(R.id.input_layout_modelo);
        input_layout_color=findViewById(R.id.input_layout_color);
        input_layout_placa=findViewById(R.id.input_layout_placa);

        btnRegistrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                valmarca=edtmarca.getText().toString();
                valmodelo=edtmodelo.getText().toString();
                valcolor=edtcolor.getText().toString();
                valplaca=edtnum_placa.getText().toString();
                enviarRegistro();
            }

        });

    }
    private void enviarRegistro(){
        if (!validarMarca()) {
            return;
        }
        if (!validarModelo()) {
            return;
        }
        if (!validarColor()) {
            return;
        }
        if (!validarPlaca()) {
            return;
        }
        registrarVehiculo();
    }
    private boolean validarMarca() {
        if (edtmarca.getText().toString().trim().isEmpty()) {
            input_layout_marca.setError(getResources().getString(R.string.campo_vacio));
            requestFocus(edtmarca);
            return false;
        } else {
            input_layout_marca.setErrorEnabled(false);
        }

        return true;
    }
    private boolean validarModelo() {
        if (edtmodelo.getText().toString().trim().isEmpty()) {
            input_layout_modelo.setError(getResources().getString(R.string.campo_vacio));
            requestFocus(edtmodelo);
            return false;
        } else {
            input_layout_modelo.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validarColor() {
        if (edtcolor.getText().toString().trim().isEmpty()) {
            input_layout_color.setError(getResources().getString(R.string.campo_vacio));
            requestFocus(edtcolor);
            return false;
        } else {
            input_layout_color.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validarPlaca() {
        if (edtnum_placa.getText().toString().trim().isEmpty()) {
            input_layout_placa.setError(getResources().getString(R.string.campo_vacio));
            requestFocus(edtnum_placa);
            return false;
        } else {
            input_layout_placa.setErrorEnabled(false);
        }

        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public void registrarVehiculo(){

        startActivity(new Intent(RegistroVehiculoActivity.this, InicioUserActivity.class));
    }


}