package edu.continental.rutashyo.Activity.User;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.continental.rutashyo.R;
import edu.continental.rutashyo.Retrofit.Respuesta.Empresa;

public class EmpresasRecyclerViewAdapter extends RecyclerView.Adapter<EmpresasRecyclerViewAdapter.ViewHolder> {

    private Context ctx;
    private List<Empresa> mValues;

    public  EmpresasRecyclerViewAdapter(Context context,List<Empresa> items){
        mValues=items;
        ctx=context;

    }

    @NonNull
    @Override
    public EmpresasRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from((parent.getContext()))
                .inflate(R.layout.activity_empresas,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        if(mValues!=null){
            holder.mItem=mValues.get(position);

            holder.tvNombreEmpresa.setText(holder.mItem.getEMTNombre());
            holder.tvTelefonoEmpresa.setText(holder.mItem.getEMTTelefono());

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
        public Empresa mItem;


        public ViewHolder( View itemView) {

            super((itemView));
            mView=itemView;
            imageView=itemView.findViewById(R.id.imageViewProfile);
            tvNombreEmpresa=itemView.findViewById(R.id.textViewNombreEmpresa);
            tvTelefonoEmpresa=itemView.findViewById(R.id.textViewPhoneEmpresa);

        }

        @Override
        public String toString() {
            return super.toString() + "";
        }

    }
}
