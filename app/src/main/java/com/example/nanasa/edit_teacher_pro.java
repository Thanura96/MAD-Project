package com.example.nanasa;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class edit_teacher_pro extends AppCompatActivity {

    private ImageView pImageView;
    private EditText pfName, pLname, pEmail, pPassword, pRePassword;
    Button updateInfo;
    private Uri imageUri;
    private static String teacherEmail;

    private String id, fName, lName, email, password, rePassword;
    private DatabaseHelperMKASG dbHelper;

    private static final int CAMERA_REQUEST_CODE = 100;
    private static final int STORAGE_REQUEST_CODE = 101;

    private static final int IMAGE_PICK_CAMER_CODE = 102;
    private static final int IMAGE_PICK_GALLERY_CODE = 103;

    private String[] cameraPermissions;
    private String[] storagePermissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //remove titel bar
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_edit_teacher_pro);

        //initiate database object in main funtion
        dbHelper = new DatabaseHelperMKASG(this);

        pImageView = findViewById(R.id.personImage);
        pfName = findViewById(R.id.fName);
        pLname = findViewById(R.id.lName);
        pEmail = findViewById(R.id.email);
        pPassword = findViewById(R.id.newPassword);
        pRePassword = findViewById(R.id.renewPassword);



        teacherEmail = getIntent().getStringExtra("emailT");
        String teacherfname = getIntent().getStringExtra("fname");
        String teacherlname = getIntent().getStringExtra("lname");
        String teacherpassword = getIntent().getStringExtra("password");

        Cursor cursor = dbHelper.getUserData(teacherEmail);


        updateInfo = findViewById(R.id.updateBTN);

        pfName.setText(teacherfname);
        pLname.setText(teacherlname);
        pEmail.setText(teacherEmail);
        pPassword.setText(teacherpassword);


        if (cursor.moveToFirst()) {
            do {
                Model_TR model = new Model_TR(

                        "" + cursor.getInt(cursor.getColumnIndex(ConstantsMKASG.T_ID)),
                        "" + cursor.getString(cursor.getColumnIndex(ConstantsMKASG.T_IMAGE)),
                        "" + cursor.getString(cursor.getColumnIndex(ConstantsMKASG.T_F_NAME)),
                        "" + cursor.getString(cursor.getColumnIndex(ConstantsMKASG.T_L_NAME)),
                        "" + cursor.getString(cursor.getColumnIndex(ConstantsMKASG.T_EMAIL)),
                        "" + cursor.getString(cursor.getColumnIndex(ConstantsMKASG.T_PASSWORD)),
                        "" + cursor.getString(cursor.getColumnIndex(ConstantsMKASG.T_ADD_TIMESTAMP)),
                        "" + cursor.getString(cursor.getColumnIndex(ConstantsMKASG.T_UPDATE_TIMESTAMP))

                );
                //Adding user record to list
                pImageView.setImageURI(Uri.parse(model.t_image));

            } while (cursor.moveToNext());
        }



        cameraPermissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};


        pImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagePickDialog();

            }
        });

        updateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //click the save button insert data to db
                editDialog();
            }
        });

    }


    private void getData() {

        fName = "" + pfName.getText().toString().trim();
        lName = "" + pLname.getText().toString().trim();
        email = "" + pEmail.getText().toString().trim();
        password = "" + pPassword.getText().toString().trim();
        rePassword = "" + pRePassword.getText().toString().trim();

        //System.out.println("re__"+checkUN);

        if (email.isEmpty() || fName.isEmpty() || lName.isEmpty() || password.isEmpty() || rePassword.isEmpty()) {
            Toast.makeText(this, "Please fill all the information", Toast.LENGTH_SHORT).show();
        } else if (!password.equals(rePassword)) {
            Toast.makeText(this, "password mismatch", Toast.LENGTH_SHORT).show();
        } else {

            dbHelper.updateInfoTable_3(
                    "" + fName,
                    "" + lName,
                    "" + email,
                    "" + password,
                    "" + imageUri,
                    "" + getDateTime(),
                    "" + getDateTime()
            );

            Intent intent = new Intent(edit_teacher_pro.this, profile.class);
            intent.putExtra("emailT", email);
            startActivity(intent);
            Toast.makeText(edit_teacher_pro.this, "Update Successfull", Toast.LENGTH_SHORT).show();
        }

    }



    private void editDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit");
        builder.setMessage("Are you want to edit ?");
        builder.setCancelable(false);
        builder.setIcon(R.drawable.editicon);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                getData();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();

            }
        });

        builder.create().show();
    }





    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }


    private void imagePickDialog() {

        String[] options = {"Camera", "Gallery"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Select for Image");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (which == 0) {
                    //if 0 then open camera and check the permision
                    if (!checkCmaraPermission()) {
                        //if permission is not granted, request for camera permission
                        requestCameraPermission();
                    } else {
                        pickFromCamera();
                    }

                } else if (which == 1) {
                    if (!checkStoragePermission()) {
                        requestStoragePermission();
                    } else {
                        pickFromStorage();
                    }

                }

            }
        });

        builder.create().show();
    }


    private void pickFromStorage() {
        //get image from gallary
        Intent gallaryIntent = new Intent(Intent.ACTION_PICK);
        gallaryIntent.setType("image/*");
        startActivityForResult(gallaryIntent, IMAGE_PICK_GALLERY_CODE);

        System.out.println("image__" + imageUri);

    }

    private void pickFromCamera() {

        //get image from camera
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "Image Title");
        values.put(MediaStore.Images.Media.DESCRIPTION, "Image Description");

        imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(cameraIntent, IMAGE_PICK_CAMER_CODE);
        System.out.println("image__" + imageUri);
    }


    private boolean checkStoragePermission() {
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == (PackageManager.PERMISSION_GRANTED);
        return result;

    }

    private void requestStoragePermission() {

        ActivityCompat.requestPermissions(this, storagePermissions, STORAGE_REQUEST_CODE);

    }

    private boolean checkCmaraPermission() {

        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == (PackageManager.PERMISSION_GRANTED);

        boolean resulti = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == (PackageManager.PERMISSION_GRANTED);

        return result && resulti;
    }

    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(this, cameraPermissions, CAMERA_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case CAMERA_REQUEST_CODE: {

                if (grantResults.length > 0) {

                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean storageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if (cameraAccepted && storageAccepted) {
                        pickFromCamera();
                    } else {
                        Toast.makeText(this, "Camera permission required", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            break;

            case STORAGE_REQUEST_CODE: {

                if (grantResults.length > 0) {
                    boolean storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                    if (storageAccepted) {
                        pickFromStorage();
                    } else {
                        Toast.makeText(this, "storage permission requried", Toast.LENGTH_SHORT).show();
                    }
                }

            }

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (resultCode == RESULT_OK) {
            if (requestCode == IMAGE_PICK_GALLERY_CODE) {
                CropImage.activity(data.getData())
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1, 1)
                        .start(this);

            } else if (requestCode == IMAGE_PICK_CAMER_CODE) {
                CropImage.activity(imageUri)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1, 1)
                        .start(this);
            } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {

                CropImage.ActivityResult result = CropImage.getActivityResult(data);

                if (resultCode == RESULT_OK) {
                    Uri resultUri = result.getUri();
                    imageUri = resultUri;
                    pImageView.setImageURI(resultUri);
                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    Exception error = result.getError();
                    Toast.makeText(this, "" + error, Toast.LENGTH_SHORT).show();
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    public void clickBack(View view) {
        Intent intentback = new Intent(this, profile.class);
        intentback.putExtra("emailT",teacherEmail);
        startActivity(intentback);
    }
}