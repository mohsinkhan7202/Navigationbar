package com.example.navigationbar;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
public class NoticeActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private Button choosebtn, savebtn,showbtn;
    private EditText editText;
    private ImageView imageView;
    private RecyclerView listView;
    private ArrayList<Notice> notices;
    private NoticeAdapter adapter;

    private FirebaseDatabase database;
    private DatabaseReference myDatabase;
    private StorageReference mstorage;
    private StorageTask mUploadTask;

    private Uri mimageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_notice );

        imageView = findViewById( R.id.image_notice_Id );
        editText = findViewById( R.id.textId );
        savebtn = findViewById( R.id.btn_notice_Save );
        choosebtn = findViewById( R.id.btn_notice_Choose );
        listView = findViewById( R.id.listViewId );



        database = FirebaseDatabase.getInstance();
        mstorage = FirebaseStorage.getInstance().getReference( "notice" );
        myDatabase = FirebaseDatabase.getInstance().getReference().child( "Notice" );

        choosebtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        } );
        savebtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText( NoticeActivity.this, "Upload in progress", Toast.LENGTH_SHORT ).show();
                } else {
                    saveData();
                }
            }
        } );
    }


    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType( "image/*" );
        intent.setAction( Intent.ACTION_GET_CONTENT );
        startActivityForResult( intent, PICK_IMAGE_REQUEST );
    }


    private String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType( contentResolver.getType( uri ) );
    }

    private void saveData() {
        if (mimageUri != null) {
            final StorageReference reference = mstorage.child( System.currentTimeMillis() + "." + getFileExtension( mimageUri ) );
            mUploadTask = reference.putFile( mimageUri )
                    .addOnSuccessListener( new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Handler handler = new Handler();
                            handler.postDelayed( new Runnable() {
                                @Override
                                public void run() {
                                }
                            }, 500 );


                            reference.getDownloadUrl().addOnSuccessListener( new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    Toast.makeText( NoticeActivity.this, "Successful...", Toast.LENGTH_SHORT ).show();
                                    String noticeId,name,imageUrl;
                                    String noticId = myDatabase.push().getKey();
                                    name = editText.getText().toString();
                                    imageUrl = uri.toString();


                                    Notice notice = new Notice( name, imageUrl );
                                    myDatabase.child( noticId ).setValue( notice );

                                }
                            } );
                        }
                    } ).addOnFailureListener( new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText( NoticeActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT ).show();

                        }
                    } );
        } else {
            Toast.makeText( NoticeActivity.this, "Please select image", Toast.LENGTH_SHORT ).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult( requestCode, resultCode, data );

        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mimageUri = data.getData();
            Picasso.with( this ).load( mimageUri ).into( imageView );

        }
    }



    private void show() {
        DatabaseReference myRef = database.getReference( "Notice" );
        myRef.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                notices = new ArrayList<>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Notice notice = dataSnapshot1.getValue( Notice.class );
                    notices.add( notice );
                }
                adapter = new NoticeAdapter( NoticeActivity.this, notices );
                listView.setAdapter( adapter );
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w( "TAG", "Failed to read value.", error.toException() );
            }
        } );
    }

    }

