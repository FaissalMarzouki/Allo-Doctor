package com.example.rahhal;

import static android.app.AlertDialog.*;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

class Myadapter extends FirebaseRecyclerAdapter<DoctorHelperPatient,Myadapter.Myviewholder>
{

     String email;
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public Myadapter(@NonNull FirebaseRecyclerOptions<DoctorHelperPatient> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull Myviewholder holder, @SuppressLint("RecyclerView") int position, @NonNull DoctorHelperPatient model) {
        holder.name.setText(model.getName());
        holder.age.setText(model.getAge());
        holder.cin.setText(model.getCin());


        holder.bedite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 final DialogPlus dialogPlus = DialogPlus.newDialog(holder.name.getContext())
                         .setContentHolder(new ViewHolder(R.layout.edite))
                         .setExpanded(true,1200)
                         .create();



                View view1 = dialogPlus.getHolderView();
                EditText name = view1.findViewById(R.id.name);
                EditText age = view1.findViewById(R.id.age);
                EditText numero = view1.findViewById(R.id.numero);
                EditText email = view1.findViewById(R.id.email);
                EditText cin = view1.findViewById(R.id.cin);
                EditText adress = view1.findViewById(R.id.adress);
                EditText civilite = view1.findViewById(R.id.civilite);

                Button bupdate = view1.findViewById(R.id.bupdate);

                name.setText(model.getName());
                age.setText(model.getAge());
                numero.setText(model.getNumero());
                email.setText(model.getEmail());
                cin.setText(model.getCin());
                adress.setText(model.getAdress());
                civilite.setText(model.getCivilite());

                dialogPlus.show();

                bupdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("name",name.getText().toString());
                        map.put("age",age.getText().toString());
                        map.put("numero",numero.getText().toString());
                        map.put("email",email.getText().toString());
                        map.put("cin",cin.getText().toString());
                        map.put("adress",adress.getText().toString());
                        map.put("civilite",civilite.getText().toString());




                        FirebaseDatabase.getInstance().getReference().child("Patient").child(CalenderDoctor.getCurrentDateString())
                        .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.name.getContext(), "Data update Successfully",Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();

                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.name.getContext(), "Error while updating",Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });


            }
        });


        holder.bdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.name.getContext());
                builder.setTitle("vous etes sur ?");
                builder.setMessage("Les donnees suprimees ne peuvent pas etre annulees");

                builder.setPositiveButton("Suprimer", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Patient").child(CalenderDoctor.getCurrentDateString())
                                .child(getRef(position).getKey()).removeValue();

                    }
                });
                builder.setNegativeButton("Annuler", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.name.getContext(),"cancelled ",Toast.LENGTH_SHORT).show();

                    }
                });
                builder.show();
            }
        });


        holder.brapport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.name.getContext())
                        .setContentHolder(new ViewHolder(R.layout.activity_rapport))
                        .setExpanded(true,1800)
                        .create();



                View view2 = dialogPlus.getHolderView();

                EditText rapport = view2.findViewById(R.id.rapport);

                Button benvoyer = view2.findViewById(R.id.benvoyer);


                rapport.setText(model.getRapport());

                dialogPlus.show();

                benvoyer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("rapport",rapport.getText().toString());




                        FirebaseDatabase.getInstance().getReference().child("Patient").child(CalenderDoctor.getCurrentDateString())
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.name.getContext(), "Data update Successfully",Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();

                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.name.getContext(), "Error while updating",Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });


                        Intent intent = new Intent(Intent.ACTION_SENDTO);
                        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                        intent.putExtra(Intent.EXTRA_EMAIL, model.getEmail());
                        intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
                        intent.putExtra(Intent.EXTRA_TEXT,rapport.getText().toString() );

                        view.getContext().startActivity(intent);
                    }
                });





            }





        });













    }


    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);


        return new Myviewholder(view);
    }

    class Myviewholder extends RecyclerView.ViewHolder
    {

        TextView name,age,cin;
        Button bedite,bdelete,brapport;


        public Myviewholder(@NonNull View itemView)
        {
            super(itemView);

            name=(TextView)itemView.findViewById(R.id.name);
            age=(TextView)itemView.findViewById(R.id.age);
            cin=(TextView)itemView.findViewById(R.id.cin);

            bedite = (Button) itemView.findViewById(R.id.bedit);
            bdelete = (Button) itemView.findViewById(R.id.bdelete);
            brapport = (Button) itemView.findViewById(R.id.brapport);

        }
    }
}

