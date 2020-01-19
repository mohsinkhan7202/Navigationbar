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

public class Class_Routine_Activity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private Button choosebtn, savebtn,showbtn;
    private EditText editText;
    private ImageView imageView;
    private RecyclerView listView;
    private ArrayList<Routine> routines;
    private RoutineAdapter adapter;

    private FirebaseDatabase database;
    private DatabaseReference myDatabase;
    private StorageReference mstorage;
    private StorageTask mUploadTask;

    private Uri mimageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_class__routine_ );

        imageView = findViewById( R.id.image_routine_Id );
        editText = findViewById( R.id.txtId );
        savebtn = findViewById( R.id.btnSave );
        choosebtn = findViewById( R.id.btnChoose );
        listView = findViewById( R.id.listViewId );
        showbtn=findViewById( R.id. btnShow_Notice);


        database = FirebaseDatabase.getInstance();


        mstorage = FirebaseStorage.getInstance().getReference( "routine" );
        myDatabase = FirebaseDatabase.getInstance().getReference().child( "Routine" );

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
                    Toast.makeText( Class_Routine_Activity.this, "Upload in progress", Toast.LENGTH_SHORT ).show();
                } else {
                    saveData();
                }
            }
        } );
       showbtn.setOnClickListener( new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(Class_Routine_Activity.this,Class_Routine_Detail_Activity.class);
               startActivity( intent );
               show();
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

                           /* Toast.makeText( Class_Routine_Activity.this, "Upload Successful", Toast.LENGTH_SHORT ).show();//extra

                            Routine routine = new Routine( editText.getText().toString().trim(), taskSnapshot.getMetadata()
                                    .getReference().getDownloadUrl().toString() );
                            String routineId = myDatabase.push().getKey();
                            myDatabase.child( routineId ).setValue( routine );
*/
                            reference.getDownloadUrl().addOnSuccessListener( new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    Toast.makeText( Class_Routine_Activity.this, "Successful...", Toast.LENGTH_SHORT ).show();
                                    String routineId,name,imageUrl;
                                    String routinId = myDatabase.push().getKey();
                                    name = editText.getText().toString();
                                    imageUrl = uri.toString();


                                    Routine routine = new Routine( name, imageUrl );
                                    myDatabase.child( routinId ).setValue( routine );

                                }
                            } );
                        }
                    } ).addOnFailureListener( new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText( Class_Routine_Activity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT ).show();

                        }
                    } );
        } else {
            Toast.makeText( Class_Routine_Activity.this, "Please select image", Toast.LENGTH_SHORT ).show();
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
        DatabaseReference myRef = database.getReference( "Routine" );
        myRef.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                routines = new ArrayList<>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Routine routine = dataSnapshot1.getValue( Routine.class );
                    routines.add( routine );
                }
                adapter = new RoutineAdapter( Class_Routine_Activity.this, routines );
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
