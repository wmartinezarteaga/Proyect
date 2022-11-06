
package com.example.proyect.app.ui.context;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.example.proyect.R;
import com.example.proyect.app.ui.views.Menu;
import com.example.proyect.core.DataBase.DataBaseHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //boton para enlasar el inicio con el menu
    public void Men(View View){
        DataBaseHelper baseDeDatos = new DataBaseHelper(MainActivity.this);
        SQLiteDatabase sqLiteDatabase = baseDeDatos.getReadableDatabase();
         boolean  isbase =   sqLiteDatabase.isOpen();
            if(isbase) {
                Intent Men = new Intent(this, Menu.class);
                startActivity(Men);
            }
    }
}