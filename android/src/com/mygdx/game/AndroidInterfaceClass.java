package com.mygdx.game;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.mygdx.game.controller.LoginController;
import com.mygdx.game.model.User;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.UUID;

public class AndroidInterfaceClass implements FireBaseInterface{

    FirebaseDatabase database;

    public AndroidInterfaceClass() {
        // Write a message to the database
        this.database = FirebaseDatabase.getInstance("https://tdt4220-g17-2022-default-rtdb.europe-west1.firebasedatabase.app");
    }

    public FirebaseDatabase getDatabase() {
        return database;
    }

    @Override
    public void SetOnValueChangedListener(String target) {

        // Read from the database
        getDatabase().getReference(target).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    @Override
    public void SetValueInDBb(String target, Object value) {
        getDatabase().getReference(target).setValue(value);
        Log.d(TAG, "Target is: " + target);
        Log.d(TAG, "Value is: " + value.toString());
    }

    @Override
    public void retrieveUserFromCredentials(String uname, String pwd) {
        getDatabase().getReference("users/").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    for(DataSnapshot child: task.getResult().getChildren()){
                        if (child.child("username").getValue().toString().equals(uname) &&
                                child.child("password").getValue().toString().equals(pwd)) {
                            Log.d("firebase", "User " + uname + " was found!");
                            User usr = new User(uname, pwd, child.getKey());
                            LoginController.getInstance().getUserSession().setUser(usr);
                            return;
                        }
                    }
                    // should only reach here if no user is found
                    Log.d("firebase", "No user with username "+uname+" and password "+pwd+" was found...");
                }
            }
        });

    }
}
