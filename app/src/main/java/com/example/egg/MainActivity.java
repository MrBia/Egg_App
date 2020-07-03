package com.example.egg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {
    TextView Ngay;
    TextView Gio;
    TextView TongSoNgayAp;
    TextView NhietDoHienTai;
    TextView SoTrungDaNo;
    TextView SoTrungAp;
    Button OK;

    // music
    private SoundManager soundManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get id
        Ngay = (TextView) findViewById(R.id.tvNgay);
        Gio = (TextView) findViewById(R.id.tvGio);
        TongSoNgayAp = (TextView) findViewById(R.id.tvTongsongay);
        NhietDoHienTai = (TextView) findViewById(R.id.tvNhietdo);
        SoTrungDaNo = (TextView) findViewById(R.id.tvSoluongtrungno);
        SoTrungAp = (TextView) findViewById(R.id.tvSotrungap);
        OK = (Button) findViewById(R.id.buttonOK);

        // firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myref = database.getReference("Data");
        final DatabaseReference reference = database.getReference("history");

        // music
        soundManager = new SoundManager();
        soundManager.initSound(getBaseContext());
        soundManager.addSound(1, R.raw.coin);

        myref.child("NumberEgg").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(int.class);
                SoTrungAp.setText(value+"");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        myref.child("Sum_Day").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                TongSoNgayAp.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        myref.child("date").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Ngay.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        myref.child("numberEggHatches").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(int.class);
                SoTrungDaNo.setText(value+"");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        myref.child("temperature").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                float value = dataSnapshot.getValue(float.class);
                double temperature = Math.round(value*100.0)/100.0;
                NhietDoHienTai.setText(temperature+"");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        myref.child("time").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Gio.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data data = new Data();
                data.setDay(Ngay.getText()+"");
                data.setTime(Gio.getText()+"");
                data.setTemperature(NhietDoHienTai.getText()+"");
                data.setNumberEgg(SoTrungAp.getText()+"");
                data.setNumberEggHatches(SoTrungDaNo.getText()+"");

                String ngay = Ngay.getText().toString();
                String gio = Gio.getText().toString();
                String path = ngay.replaceAll("/","-")
                        + "-" + gio.replaceAll(":", "-");
                reference.child(path).setValue(data);

                // playsound
                //soundManager.playSound(1);
            }
        });
    }
}
