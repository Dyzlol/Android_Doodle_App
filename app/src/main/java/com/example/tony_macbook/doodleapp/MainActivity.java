package com.example.tony_macbook.doodleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.ImageButton;
import java.io.File;
import java.io.FileOutputStream;
import android.graphics.Bitmap;
import android.widget.SeekBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.ContextWrapper;
import android.content.Context;



public class MainActivity extends AppCompatActivity {

    private DoodleView canvasView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        canvasView = (DoodleView) findViewById(R.id.draw_pane);
        Button red_button = (Button) findViewById(R.id.red_col);
        Button blue_button = (Button) findViewById(R.id.blue_col);
        Button yellow_button = (Button) findViewById(R.id.yellow_col);
        Button black_button = (Button) findViewById(R.id.black_col);
        Button purple_button = (Button) findViewById(R.id.purple_col);
        Button orange_button = (Button) findViewById(R.id.orange_col);
        Button green_button = (Button) findViewById(R.id.green_col);
        Button white_button = (Button) findViewById(R.id.white_col);
        ImageButton clear_page_button = (ImageButton) findViewById(R.id.new_page);
        ImageButton save_image_button = (ImageButton) findViewById(R.id.save_page);
        SeekBar brush_size = (SeekBar) findViewById(R.id.brush_size);

        red_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                canvasView.setColor("#ff0000");
            }
        });

        purple_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                canvasView.setColor("#9101ff");
            }
        });

        black_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                canvasView.setColor("#000000");
            }
        });

        blue_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                canvasView.setColor("#002fff");
            }
        });

        orange_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                canvasView.setColor("#ff6200");
            }
        });

        white_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                canvasView.setColor("#ffffff");
            }
        });

        green_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                canvasView.setColor("#26e200");
            }
        });

        yellow_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                canvasView.setColor("#fff200");
            }
        });

        clear_page_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
               canvasView.reset_canvas();
            }
        });

        brush_size.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChanged = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChanged = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                canvasView.setStroke(progressChanged);
            }
        });

        //Save functionality
        save_image_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder saveDialog = new AlertDialog.Builder(MainActivity.this);
                saveDialog.setTitle("Save drawing");
                saveDialog.setMessage("Save drawing to device Gallery?");

                saveDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                saveDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                //saveDialog.show();
                Bitmap b = canvasView.getBitmap();

                ContextWrapper cw = new ContextWrapper(getApplicationContext());
                // path to /data/data/yourapp/app_data/imageDir
                File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
                // Create imageDir
                File mypath=new File(directory,"profile.jpg");

                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(mypath);
                    // Use the compress method on the BitMap object to write image to the OutputStream
                    b.compress(Bitmap.CompressFormat.PNG, 100, fos);
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {

                }
            }

        });

    }



}
