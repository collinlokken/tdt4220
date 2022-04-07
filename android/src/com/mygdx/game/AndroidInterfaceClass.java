package com.mygdx.game;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;

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
    public void SetValueInDBb(String target, String value) {
        getDatabase().getReference(target).setValue(value);
    }
}
