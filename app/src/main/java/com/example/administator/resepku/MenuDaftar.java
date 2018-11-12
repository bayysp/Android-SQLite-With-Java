package com.example.administator.resepku;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MenuDaftar extends AppCompatActivity {

    DatabaseHelper db;

    Button btnRegister,btnData,btnBack;
    EditText etNama,etUsername,etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_daftar);

        btnRegister = findViewById(R.id.btn_confirm_register);
        btnData = findViewById(R.id.btn_show_data);
        btnBack = findViewById(R.id.btn_back);
        etNama = findViewById(R.id.et_nama);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);

        db = new DatabaseHelper(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean inserted = db.insertRegister(etNama.getText().toString(),etUsername.getText().toString(),
                        etPassword.getText().toString());

                if(inserted == true){
                    Toast.makeText(MenuDaftar.this,"Terimakasih Telah Mendaftar",Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(MenuDaftar.this , MainActivity.class);
                    startActivity(intent);

                }else
                    Toast.makeText(MenuDaftar.this,"Gagal  Mendaftar",Toast.LENGTH_SHORT).show();

            }
        });

        btnData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor result = db.getAllData();

                if(result.getCount() == 0){
                    ShowMessage("ERROR","NO DATA FOUND!");
                    return ;
                }

                StringBuffer stringBuffer = new StringBuffer();
                while (result.moveToNext()){
                    stringBuffer.append("Nama\t: "+ result.getString(0)+"\n");
                    stringBuffer.append("Username\t: "+ result.getString(1)+"\n");
                    stringBuffer.append("Password\t: "+ result.getString(2)+"\n");

                }

                ShowMessage("DATA", stringBuffer.toString());
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuDaftar.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public void ShowMessage(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
