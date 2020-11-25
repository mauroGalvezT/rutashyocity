package edu.continental.rutashyo.Activity.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import edu.continental.rutashyo.R;
import edu.continental.rutashyo.Retrofit.Respuesta.Empresa;
import edu.continental.rutashyo.data.EmpresasViewModel;

import static com.google.android.material.internal.ContextUtils.getActivity;

public class EmpresasActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EmpresasRecyclerViewAdapter adapter;
    List<Empresa> empresaList;
    EmpresasViewModel empresasViewModel;

    SwipeRefreshLayout swipeRefreshLayout;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresas_list);
        empresasViewModel=new ViewModelProvider(this).get(EmpresasViewModel.class);
        recyclerView = findViewById(R.id.list);
        swipeRefreshLayout=findViewById(R.id.swiperefreshlayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                swipeRefreshLayout.setRefreshing(true);
                loadNewData();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter=new EmpresasRecyclerViewAdapter(this,empresaList);
        recyclerView.setAdapter(adapter);
        loadEmpresasData();
    }

    private void loadEmpresasData() {
        empresasViewModel.getEmpresas().observe(this, new Observer<List<Empresa>>() {
            @Override
            public void onChanged(List<Empresa> empresas) {
                empresaList=empresas;
                swipeRefreshLayout.setRefreshing(false);

                adapter.setData(empresaList);
            }
        });
    }
    private void loadNewData() {
        empresasViewModel.getEmpresas().observe(this, new Observer<List<Empresa>>() {
            @Override
            public void onChanged(List<Empresa> empresas) {
                empresaList=empresas;
                swipeRefreshLayout.setRefreshing(false);

                adapter.setData(empresaList);
                empresasViewModel.getNewEmpresas().removeObserver(this);
            }
        });
    }




}