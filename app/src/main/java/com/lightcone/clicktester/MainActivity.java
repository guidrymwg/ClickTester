package com.lightcone.clicktester;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;

public class MainActivity extends AppCompatActivity implements OnClickListener, OnLongClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View view;

        // Set click and long-click listeners

        View button1 = findViewById(R.id.button1);
        button1.setOnClickListener(this);
        button1.setOnLongClickListener(this);

        View redblock = findViewById(R.id.redblock);
        redblock.setOnClickListener(this);
        redblock.setOnLongClickListener(this);

        View greenblock = findViewById(R.id.greenblock);
        greenblock.setOnClickListener(this);
        greenblock.setOnLongClickListener(this);

        View yellowblock = findViewById(R.id.yellowblock);
        yellowblock.setOnClickListener(this);
        yellowblock.setOnLongClickListener(this);

        View blueblock = findViewById(R.id.blueblock);
        blueblock.setOnClickListener(this);
        blueblock.setOnLongClickListener(this);
    }

    // Handle short clicks
    @Override
    public void onClick(View v) {
        String whichOne = whichWidget(v);
        Toast.makeText(this, getString(R.string.shortclick) + " "+whichOne,
                Toast.LENGTH_SHORT).show();
    }

    // Handle long clicks
    @Override
    public boolean onLongClick(View v) {
        String whichOne = whichWidget(v);
        // If it wasn't the button
        if(whichOne != getString(R.string.buttonString)){
            processMenu(v);
        // If it was the button
        } else {
            Toast.makeText(this, getString(R.string.longclick) +" "+ whichOne,
                    Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    // Method to determine which widget was pressed
    private String whichWidget(View v){
        String whichOne = "";

        switch(v.getId()){
            case R.id.button1:
                whichOne = getString(R.string.buttonString);
                break;
            case R.id.redblock:
                whichOne = getString(R.string.redString);
                break;
            case R.id.greenblock:
                whichOne = getString(R.string.greenString);
                break;
            case R.id.yellowblock:
                whichOne = getString(R.string.yellowString);
                break;
            case R.id.blueblock:
                whichOne = getString(R.string.blueString);
                break;
        }
        return whichOne;
    }

    // Menu with list of color options
    private void doMenu(View v, int index){
        switch(index){
            case 0:     // Android White
                v.setBackgroundColor(Color.WHITE);
                break;
            case 1:     // Android Black
                v.setBackgroundColor(Color.BLACK);
                break;
            case 2:     // Android Gray
                v.setBackgroundColor  (Color.GRAY);
                break;
            case 3:    // Original red, user-defined in colors.xml
                v.setBackgroundColor(Color.parseColor(getString(R.color.redback)));
                break;
            case 4:    // Original green, user-defined in colors.xml
                v.setBackgroundColor(Color.parseColor(getString(R.color.greenback)));
                break;
        }
    }

    // Open AlertDialog holding color options menu and process with anonymous inner class
    private void processMenu(final View v){
        new AlertDialog.Builder(this).setTitle(R.string.processMenu_title)
                .setItems(R.array.colornames,
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialoginterface, int i){
                                doMenu(v, i);
                            }
                        }).show();
    }

}
