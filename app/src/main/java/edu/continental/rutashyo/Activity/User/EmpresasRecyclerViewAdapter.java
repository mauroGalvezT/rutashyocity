package edu.continental.rutashyo.Activity.User;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.continental.rutashyo.Activity.Fragments.HomeFragment;
import edu.continental.rutashyo.R;
import edu.continental.rutashyo.Retrofit.Respuesta.Empresa;
import edu.continental.rutashyo.settings.AppConst;
import edu.continental.rutashyo.settings.MyApp;
import edu.continental.rutashyo.settings.SharedPreferencesManager;

public class EmpresasRecyclerViewAdapter extends RecyclerView.Adapter<EmpresasRecyclerViewAdapter.ViewHolder> {

    private Context ctx;
    private List<Empresa> mValues;
    //ConstraintLayout irMapa;
    String nombre;
    public Button btnVerRutas;

    public  EmpresasRecyclerViewAdapter(Context context,List<Empresa> items){
        mValues=items;
        ctx=context;

    }

    @NonNull
    @Override
    public EmpresasRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from((parent.getContext()))
                .inflate(R.layout.activity_empresas,parent,false);
        //irMapa = view.findViewById(R.id.irMapa);

        btnVerRutas=view.findViewById(R.id.buttonVerRutas);



        /*irMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ctx, "ir mapa "+nombre, Toast.LENGTH_SHORT).show();
            }
        });*/
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        if(mValues!=null){
            holder.mItem=mValues.get(position);



            nombre=holder.mItem.getIDEmpresaTransp();
            holder.tvNombreEmpresa.setText(holder.mItem.getEMTNombre());
            holder.tvTelefonoEmpresa.setText(holder.mItem.getEMTTelefono());
            holder.idRut.setText(holder.mItem.getIDEmpresaTransp());

            btnVerRutas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(ctx, "idEmpresa: "+holder.mItem.getIDEmpresaTransp(), Toast.LENGTH_SHORT).show();
                    //System.out.println("idEmpresa: "+holder.mItem.getIDEmpresaTransp());
                    Intent i=new Intent(MyApp.getContext(), InicioUserActivity.class);
                    i.putExtra("idEmpresa",holder.mItem.getIDEmpresaTransp());

                    ctx.startActivity(i);

                }
            });

        }
    }

    public void setData(List<Empresa> empresaList){
        this.mValues=empresaList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mValues!= null)
            return mValues.size();
        else return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public final ImageView imageView;
        public final TextView tvNombreEmpresa;
        public final TextView tvTelefonoEmpresa;
        //public final Button btnVerRutas;
        public final TextView idRut;
        public Empresa mItem;



        public ViewHolder(final View itemView) {

            super((itemView));
            mView=itemView;
            imageView=itemView.findViewById(R.id.imageViewProfile);
            tvNombreEmpresa=itemView.findViewById(R.id.textViewNombreEmpresa);
            tvTelefonoEmpresa=itemView.findViewById(R.id.textViewPhoneEmpresa);
            idRut=itemView.findViewById(R.id.textViewIdRuta);




        }

        @Override
        public String toString() {
            return super.toString() + "";
        }

    }
}
