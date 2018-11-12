package com.example.administator.resepku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity{

    @BindView(R.id.btn_login) Button btnLogin;
    @BindView(R.id.btn_register) Button btnRegister;
    @BindView(R.id.et_username) EditText etUsername;
    @BindView(R.id.et_password) EditText etPassword;
    DatabaseHelper myDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        myDb = new DatabaseHelper(this);

    }

    @OnClick(R.id.btn_login)
    public void btn_login(View view){

        if(etUsername.getText().toString().equals("") || etPassword.getText().toString().equals("")){
            if(etUsername.getText().toString().equals("")){
                etUsername.setError("Harap Isi Username Anda");
            }

            if(etPassword.getText().toString().equals("")){
                etPassword.setError("Harap Isi Password Anda");
            }

        }else if(myDb.LoginAkun(etUsername.getText().toString(),etUsername.getText().toString())){
            Intent moveToMenuUtama = new Intent(MainActivity.this, MenuUtama.class);
            startActivity(moveToMenuUtama);
        }else{
            Toast.makeText(getApplicationContext(),"Username atau Password Anda Salah",Toast.LENGTH_SHORT).show();
        }

    }

    @OnClick(R.id.btn_register)
        public void btn_register(){

        Intent moveToDaftar = new Intent(MainActivity.this,MenuDaftar.class);
        startActivity(moveToDaftar);

        }

    public boolean cekAkun(String user,String pass){

        if(user.equals("bayu") && pass.equals("bayu")){
            return true;
        }else
            return false;

    }
}
