package com.example.labourmanagementsystem.SubClass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.labourmanagementsystem.Fragments.ProfileFragment;
import com.example.labourmanagementsystem.Models.CarWashModel;
import com.example.labourmanagementsystem.Models.SubModel;
import com.example.labourmanagementsystem.R;
import com.example.labourmanagementsystem.SubAdpater.CarFurAdapter;
import com.example.labourmanagementsystem.SubAdpater.CarWashAdapter;
import com.example.labourmanagementsystem.SubAdpater.CarpenterAdapter;
import com.example.labourmanagementsystem.SubAdpater.CctvAdapter;
import com.example.labourmanagementsystem.SubAdpater.CivilAdapter;
import com.example.labourmanagementsystem.SubAdpater.CookingAdapter;
import com.example.labourmanagementsystem.SubAdpater.DomesticCleaningAdapter;
import com.example.labourmanagementsystem.SubAdpater.DressMakerAdapter;
import com.example.labourmanagementsystem.SubAdpater.DryCleaningAdapter;
import com.example.labourmanagementsystem.SubAdpater.ElectricianAdapter;
import com.example.labourmanagementsystem.SubAdpater.GuardAdapter;
import com.example.labourmanagementsystem.SubAdpater.HomeTutorAdapter;
import com.example.labourmanagementsystem.SubAdpater.InteriorAdapter;
import com.example.labourmanagementsystem.SubAdpater.MakeupAdapter;
import com.example.labourmanagementsystem.SubAdpater.MechanicAdapter;
import com.example.labourmanagementsystem.SubAdpater.MenSaloonAdapter;
import com.example.labourmanagementsystem.SubAdpater.PainterAdapter;
import com.example.labourmanagementsystem.SubAdpater.PlumberAdapter;
import com.example.labourmanagementsystem.SubAdpater.ServicesAdapater;
import com.example.labourmanagementsystem.SubAdpater.SplitAcAdapter;
import com.example.labourmanagementsystem.SubAdpater.WomenSaloonAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DressMakerActivity extends AppCompatActivity implements DressMakerAdapter.GetItem {

    RecyclerView rv;
    ProgressDialog progressDialog;
    TextView ndts;
    DressMakerAdapter adapter;
    DatabaseReference database;
    ArrayList<CarWashModel> listcarwash =  new ArrayList<>();
    String userID,labourId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dress_maker);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Loading Data");
        progressDialog.setCanceledOnTouchOutside(false);
        ndts=findViewById(R.id.ndts);

        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        userID=user.getUid();

        progressDialog.show();
        rv=(RecyclerView)findViewById(R.id.dressmakerrv);
        database= FirebaseDatabase.getInstance().getReference("Labours").child("Dress Maker");

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){


                    CarWashModel carWashModel= dataSnapshot.getValue(CarWashModel.class);
                    listcarwash.add(carWashModel);
                }
                progressDialog.dismiss();
                adapter= new DressMakerAdapter(DressMakerActivity.this,listcarwash);

                rv.setLayoutManager(new LinearLayoutManager(DressMakerActivity.this));
                rv.setAdapter(adapter);
                adapter.setClickListener(DressMakerActivity.this);
                adapter.notifyDataSetChanged();

                if(listcarwash.size()>0){
                    ndts.setVisibility(View.GONE);
                }else{
                    ndts.setVisibility(View.VISIBLE);
                }


                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    @Override
    public void itemPosition(CarWashModel pos) {
        Dialog dialog = new Dialog(DressMakerActivity.this);
        dialog.setContentView(R.layout.dialog_gig_data);
        TextView name= dialog.findViewById(R.id.dialog_name);
        TextView description=dialog.findViewById(R.id.dialog_description);
        Button sendRequest=dialog.findViewById(R.id.btnsendrequest);
        Button close=dialog.findViewById(R.id.btnclose);

        labourId=pos.getUserID();
        name.setText(pos.getCompanyName());
        description.setText(pos.getDescription());
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        sendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(DressMakerActivity.this,RequestActivity.class);
                intent.putExtra("Labour Id",labourId);
                intent.putExtra("Skill","Dress Maker");
                startActivity(intent);
            }
        });


        dialog.show();
    }
}