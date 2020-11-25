package edu.continental.rutashyo.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import edu.continental.rutashyo.Retrofit.Respuesta.Empresa;

public class EmpresasViewModel extends AndroidViewModel {
    private EmpresaRepository empresaRepository;
    private LiveData<List<Empresa>> empresas;

    public EmpresasViewModel(@NonNull Application application){
        super(application);
        empresaRepository=new EmpresaRepository();
        empresas=empresaRepository.getAllEmpresas();
    }

    public LiveData<List<Empresa>> getEmpresas(){

        return empresas;
    }
    public LiveData<List<Empresa>> getNewEmpresas(){

        empresas=empresaRepository.getAllEmpresas();
        return empresas;
    }


}
