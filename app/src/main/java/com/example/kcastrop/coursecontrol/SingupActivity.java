package com.example.kcastrop.coursecontrol;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import entities.User;

public class SingupActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener{

    //defining firebaseauth object
    private FirebaseAuth firebaseAuth;
    private DatabaseReference database;

    //defining view objects
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonSignup;
    private TextView textViewSignin;
    //private ProgressBar progressBar;


    //----------------------------------------- Google OAuth -----------------------------------------//

    private SignInButton buttonSignInGoogle;
    private static final String TAG = "SingUpActivity";
    private static final int RC_SIGN_IN = 9001;
    private GoogleApiClient mGoogleApiClient;

    //------------------------------------- END Google OAuth -----------------------------------------//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();


        database = FirebaseDatabase.getInstance().getReference();

        //if getCurrentUser does not returns null
        if(firebaseAuth.getCurrentUser() != null){
            //that means user is already logged in
            //so close this activity
            finish();

            //and open profile activity
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        }

        //initializing views
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        buttonSignup = (Button) findViewById(R.id.buttonSignup);

        textViewSignin = (TextView) findViewById(R.id.textViewSignin);

        //progressBar = (ProgressBar) findViewById(R.id.registration_progress);

        //attaching listener to button
        buttonSignup.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);

        //----------------------------------------- Google OAuth -----------------------------------------//

        buttonSignInGoogle = (SignInButton) findViewById(R.id.login_with_google);
        buttonSignInGoogle.setOnClickListener(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, (GoogleApiClient.OnConnectionFailedListener) this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        //------------------------------------- END Google OAuth -----------------------------------------//

    }

    public void registerUser(){
        //getting email and password from edit texts
        String email = editTextEmail.getText().toString().trim();
        String password  = editTextPassword.getText().toString().trim();

        //checking if email and passwords are empty
        if(TextUtils.isEmpty(email)){
            finish();
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Por favor ingrese la contraseña",Toast.LENGTH_LONG).show();
            return;
        }

        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){
                            //display some message here
                            User u = new User(firebaseAuth.getCurrentUser().getUid());
                            String e = firebaseAuth.getCurrentUser().getEmail();
                            String getUsername = e.substring(0, e.length() - 4);
                            u.setUsername(getUsername);
                            database.child("users/" + u.getFirebaseId()).setValue(u);
                            finish();
                            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        }else{
                            //display some message here
                            Toast.makeText(SingupActivity.this,"Error de registro",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    //----------------------------------------- Google OAuth -----------------------------------------//

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            final FirebaseUser user = firebaseAuth.getCurrentUser();
                            DatabaseReference userRef = database.child("users/");
                            System.out.println(userRef);
                            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if(!dataSnapshot.child(user.getUid()).exists()){
                                        Log.d("Exist", "User: " + user.getEmail() + " does not exists in database. Adding ...");
                                        User u = new User(firebaseAuth.getCurrentUser().getUid());
                                        String e = firebaseAuth.getCurrentUser().getEmail();
                                        String getUsername = e.substring(0, e.length() - 4);
                                        u.setUsername(getUsername);
                                        database.child("users/" + u.getFirebaseId()).setValue(u);
                                    }
                                    else{
                                        Log.d("Exist", "User: " + user.getEmail() + " exists");
                                    }
                                    finish();
                                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                                }
                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    Log.d("Database error", "The read failed: " + databaseError.getCode());
                                }
                            });
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(SingupActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                Log.d("GoogleSignIn", "Google Sign In was successful, authenticate with Firebase");
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                Log.d("GoogleSignIn", "Google Sign In Failed");
            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }


    //------------------------------------- END Google OAuth -----------------------------------------//



    @Override
    public void onClick(View view) {

        if(view == buttonSignup){
            registerUser();
        }

        if(view == textViewSignin){
            startActivity(new Intent(this, LoginActivity.class));
        }

        //----------------------------------------- Google OAuth -----------------------------------------//

        if (view == buttonSignInGoogle) {
            signIn();
        }

        //------------------------------------- END Google OAuth -----------------------------------------//

    }
}
