package edu.illinois.finalproject;

import android.content.Context;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import edu.illinois.finalproject.PlayerGuides.Guide;

import static android.content.ContentValues.TAG;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("edu.illinois.finalproject", appContext.getPackageName());
    }


    @Test
    public void getGuides() throws Exception {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("champions/LeeSin/guides/");

        System.out.println("reading.....................");
        Log.d(TAG, "getGuides: ");
        final CountDownLatch writeSignal = new CountDownLatch(1);
        // Add all polls in ref as rows
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot guideSnapshot : snapshot.getChildren()) {
                        Guide guide = new Guide();

                        String intro = (String) guideSnapshot.child("introduction").getValue();
                        List startingItems = (List) guideSnapshot.child("startingItems").getValue();

                        guide.setIntroduction(intro);
                        guide.setStartingItems(startingItems);

                        System.out.println(guide.getIntroduction()+" "+guide.getStartingItems());
                        System.out.println("read*****************values");
                        writeSignal.countDown();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        writeSignal.await(10, TimeUnit.SECONDS);


    }
}
