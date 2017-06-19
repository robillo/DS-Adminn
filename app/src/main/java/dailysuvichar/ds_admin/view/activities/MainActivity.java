package dailysuvichar.ds_admin.view.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

import dailysuvichar.ds_admin.R;
import dailysuvichar.ds_admin.model.Guru;
import dailysuvichar.ds_admin.view.adapters.RVPendingGurus;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MA";
    RecyclerView rv;
    static RVPendingGurus rvPendingGurus;
    static ArrayList<Guru> gurus;

    private DatabaseReference mDatabase;
    private static DatabaseReference mDatabaseGuruOfficial;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private StorageReference mStorageReferenceGovid, mStorageReferenceSpecid;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        gurus = new ArrayList<>();

        rv= (RecyclerView) findViewById(R.id.rv);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
        mStorageReferenceGovid = FirebaseStorage.getInstance().getReference("gurus").child("pending").child("govid");
        mStorageReferenceSpecid = FirebaseStorage.getInstance().getReference("gurus").child("pending").child("specid");

        mDatabase = FirebaseDatabase.getInstance().getReference("gurus");

        Log.d(TAG, "onCreate: "+mDatabase);

        mDatabase.child("pending").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "onDataChange: ANDJBDAJKBDAKFKA");
                Log.d(TAG, "onDataChange: "+dataSnapshot.getChildrenCount());
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Guru guru = postSnapshot.getValue(Guru.class);
                    guru.setImgGov(mStorageReferenceGovid.child(guru.getUid())); //ERROR CHILD NAME Cant be empty
                    guru.setImgSpec(mStorageReferenceSpecid.child(guru.getUid()));
                    guru.setDbRef(postSnapshot.getRef());
                    Log.d(TAG, "onDataChange: "+guru.getUid());
                    gurus.add(guru);

                    rvPendingGurus.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: " + databaseError.getMessage());
            }
        });

        rvPendingGurus = new RVPendingGurus(this, gurus);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(rvPendingGurus);
        rvPendingGurus.notifyDataSetChanged();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();
            }
        });
    }

    private void refreshContent(){
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                mDatabase.child("pending").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Log.d(TAG, "onDataChange: ANDJBDAJKBDAKFKA");
                        Log.d(TAG, "onDataChange: "+dataSnapshot.getChildrenCount());
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            Guru guru = postSnapshot.getValue(Guru.class);
                            guru.setImgGov(mStorageReferenceGovid.child(guru.getUid()));
                            guru.setImgSpec(mStorageReferenceSpecid.child(guru.getUid()));
                            guru.setDbRef(postSnapshot.getRef());
                            Log.d(TAG, "onDataChange: "+guru.getUid());
                            gurus.add(guru);

                            rvPendingGurus.notifyDataSetChanged();
                            rvPendingGurus = new RVPendingGurus(getApplicationContext(), gurus);
                            rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            rv.setAdapter(rvPendingGurus);
                            rvPendingGurus.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d(TAG, "onCancelled: " + databaseError.getMessage());
                    }
                });
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                       swipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static void deleteGuru(Guru guru, DatabaseReference dbRef){
        if(dbRef!=null){
            Log.i(TAG, "deleteSupplier: "+dbRef);
            dbRef.setValue(null);
            gurus.remove(guru);
        }else{
            Log.i(TAG, "deleteSupplier: dBRef is "+dbRef);
        }
        rvPendingGurus.notifyDataSetChanged();
    }


    public static void confirmAsGuru(Guru guru, DatabaseReference dbRef){

        mDatabaseGuruOfficial = FirebaseDatabase.getInstance().getReference("gurus").child("official");

        if(dbRef!=null){
            Log.i(TAG, "deleteSupplier: "+dbRef);
            dbRef.setValue(null);
            gurus.remove(guru);

            String guruId = mDatabaseGuruOfficial.push().getKey();

            mDatabaseGuruOfficial.child(guruId).setValue(guru);

        }else{
            Log.i(TAG, "deleteSupplier: dBRef is "+dbRef);
        }
        rvPendingGurus.notifyDataSetChanged();
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
