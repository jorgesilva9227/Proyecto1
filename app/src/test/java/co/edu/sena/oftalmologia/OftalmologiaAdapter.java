package co.edu.sena.oftalmologia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.oftalmologia.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OftalmologiaAdapter extends ArrayAdapter<Oftalmologia> {
    public OftalmologiaAdapter (@NonNull Context context, @NonNull List<Oftalmologia> objects){
        super (context, R.layout.items_lista, objects);
    }
    @NonNull
    @Override
    public View getView (int position, @Nullable View convertView, @NonNull ViewGroup parent){
        Oftalmologia o = getItem(position);
        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.items_lista, null);
        }
        TextView lbNombre = convertView.findViewById(R.id.lbNombre);
        TextView lbHorario = convertView.findViewById(R.id.lbHorario);
        ImageView img = convertView.findViewById(R.id.foto);
        lbNombre.setText(o.getNombre());
        lbHorario.setText(o.getHorario());
        Picasso.get().load(o.getFoto()).resize(312,312).into(img);
        return convertView;
    }

}
