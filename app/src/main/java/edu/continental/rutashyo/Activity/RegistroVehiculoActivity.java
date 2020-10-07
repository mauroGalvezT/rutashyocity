package edu.continental.rutashyo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import edu.continental.rutashyo.R;

public class RegistroVehiculoActivity extends AppCompatActivity {

    EditText edtmarca,edtmodelo,edtcolor,edtnum_placa;
    FloatingActionButton btnRegistrar;
    private TextInputLayout input_layout_marca;
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

        btnRegistrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistroVehiculoActivity.this,InicioUserActivity.class));
            }

        });

    }
    private void enviarRegistro(){
        if (!validarMarca()) {
            return;
        }
    }
    private boolean validarMarca() {
        if (edtmarca.getText().toString().trim().isEmpty()) {
            input_layout_marca.setError(getResources().getString(R.string.ingresa_tu_nombre));
            requestFocus(edtmarca);
            return false;
        } else {
            input_layout_marca.setErrorEnabled(false);
        }

        return true;
    }
    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }


}