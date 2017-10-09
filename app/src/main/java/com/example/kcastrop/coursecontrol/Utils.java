package com.example.kcastrop.coursecontrol;

import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import entities.User;

/**
 * Created by AnDresLoPz on 10/8/2017.
 */

public class Utils {

    private DatabaseReference database;
    private FirebaseAuth firebaseAuth;
    private String currentUser;
    private static Utils instance = null;

    private Utils (){

    }

    public static Utils getInstance(){
        if (instance == null){
            instance = new Utils();
        }
        return instance;
    }

}
