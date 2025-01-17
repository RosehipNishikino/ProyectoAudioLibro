package net.ivanvega.audiolibros2020;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SelectorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectorFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recycler;
    private GridLayoutManager layoutManager;
    MainActivity mainActivity;
    Context context;
    public SelectorFragment() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SelectorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SelectorFragment newInstance(String param1, String param2) {
        SelectorFragment fragment = new SelectorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        if(context instanceof MainActivity){
            mainActivity = (MainActivity) context;
        }else {
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_selector,
                container, false);
        recycler =   (RecyclerView)v.findViewById(R.id.recyclerView);
        layoutManager = new GridLayoutManager(getActivity(),2);
        recycler.setLayoutManager(layoutManager);
        AdaptadorLibros adaptadorLibros =
                new AdaptadorLibros(getActivity() , Libro.ejemploLibros());
            adaptadorLibros.setOnclickListener(
                    vl -> {
                        Toast.makeText(getActivity(),
                        "Elemento seleccionado: "
                                + recycler.getChildAdapterPosition(vl) ,
                                Toast.LENGTH_LONG).show();
                        mainActivity.mostrarDetalle(recycler.getChildAdapterPosition(vl));
                    }
            );
            adaptadorLibros.setOnLongClickListener(View -> {
                AlertDialog.Builder cuadroDialogo = new AlertDialog.Builder(context);
                cuadroDialogo.setTitle("Seleccionar la opcion");
                cuadroDialogo.setItems(new String[]{"Compartir","Eliminar","Agregar"},
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getActivity(),
                                        "Opcion seleccionado: "
                                                + i ,
                                        Toast.LENGTH_LONG).show();
                                switch (i){
                                    default:
                                }
                            }
                        });
                //cuadroDialogo.setMessage("Este es un cuadro de dialog");
                //cuadroDialogo.setPositiveButton("OK", ((dialogInterface, i) -> {
                //}));
                cuadroDialogo.create().show();
                return false;
            });
        recycler.setAdapter(adaptadorLibros);
        return v;
    }
}