package com.example.kcastrop.coursecontrol;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import entities.User;

public class BaseActivity extends AppCompatActivity{

    //firebase auth object
    private FirebaseAuth firebaseAuth;
    private DatabaseReference database;

    //view objects
    private TextView textViewUsername;
    private Button buttonLogout;


    private static String TAG = BaseActivity.class.getSimpleName();

    ListView mDrawerList;
    RelativeLayout mDrawerPane;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baseactivity);

        database = FirebaseDatabase.getInstance().getReference();

        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();

        //initializing views
        textViewUsername = (TextView) findViewById(R.id.userName);
        //buttonLogout = (Button) findViewById(R.id.buttonLogout);

        //adding listener to button
        //buttonLogout.setOnClickListener(this);


        mNavItems.add(new NavItem("Inicio", "Menu principal", R.drawable.ic_home));
        //falta incluir los iconos: ic_book y ic_assignment
        //mNavItems.add(new NavItem("Mis cursos", "Cursos inscritos", R.drawable.ic_book));
        mNavItems.add(new NavItem("Mis cursos", "Cursos inscritos", R.drawable.ic_home));
        //mNavItems.add(new NavItem("Administrar", "Crear o administrar cursos", R.drawable.ic_assignment));
        mNavItems.add(new NavItem("Administrar", "Crear o administrar cursos", R.drawable.ic_home));
        mNavItems.add(new NavItem("Preferencias", "Ajustes de cuentas", R.drawable.ic_settings));
        mNavItems.add(new NavItem("Acerca", "Informacion sobre nosotros", R.drawable.ic_info));
        mNavItems.add(new NavItem("Cerrar sesion", "Salir de la cuenta", R.drawable.ic_logout));

        // DrawerLayout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        // Populate the Navigtion Drawer with options
        mDrawerPane = (RelativeLayout) findViewById(R.id.drawerPane);
        mDrawerList = (ListView) findViewById(R.id.navList);
        DrawerListAdapter adapter = new DrawerListAdapter(this, mNavItems);
        mDrawerList.setAdapter(adapter);

        // Drawer Item click listeners
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItemFromDrawer(position);
            }
        });

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                Log.d(TAG, "onDrawerClosed: " + getTitle());

                invalidateOptionsMenu();
            }
        };

        mDrawerLayout.addDrawerListener(mDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loadBaseInfo();
    }

    public void loadBaseInfo(){

        //if the user is not logged in
        //that means current user will return null
        if(firebaseAuth.getCurrentUser() == null){
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));
        }

        //getting current user
        FirebaseUser user = firebaseAuth.getCurrentUser();
        DatabaseReference userRef = database.child("users/"+user.getUid());

        //displaying logged in user name
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                textViewUsername.setText(dataSnapshot.getValue(User.class).getUsername());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    public void logoutUser(){
        Log.d("Cerrar sesion", "Exitoso");
        //logging out the user
        firebaseAuth.signOut();
        //closing activity
        finish();
        //starting login activity
        startActivity(new Intent(this, LoginActivity.class));
    }

    /*
    * Called when a particular item from the navigation drawer
    * is selected.
    * */
    private void selectItemFromDrawer(int position) {

        switch(position) {
            case 0 :
                Log.d("Item selected", Integer.toString(position));
                Intent home = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(home);
                break;
            case 1 :
                Log.d("Item selected", Integer.toString(position));
                break;
            case 2 :
                Log.d("Item selected", Integer.toString(position));
                Intent managecourses = new Intent(getApplicationContext(), CreateCourseActivity.class);
                startActivity(managecourses);
                break;
            case 3 :
                Log.d("Item selected", Integer.toString(position));
                Intent settings = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(settings);
                break;
            case 4 :
                Log.d("Item selected", Integer.toString(position));
                Intent about = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(about);
                break;
            case 5 :
                Log.d("Item selected", Integer.toString(position));
                logoutUser();
                break;
            default :
                Log.d("Item selected", "invalid");
        }

        /* Posible uso de fragments
        Fragment fragment = new PreferencesFragment();

        Bundle args = new Bundle();
        args.putInt("ARG_ITEM_SELECTED", position);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.mainContent, fragment)
                .commit();
        */


        mDrawerList.setItemChecked(position, true);
        //setTitle(mNavItems.get(position).mTitle);

        // Close the drawer
        mDrawerLayout.closeDrawer(mDrawerPane);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle
        // If it returns true, then it has handled
        // the nav drawer indicator touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    class NavItem {
        String mTitle;
        String mSubtitle;
        int mIcon;

        public NavItem(String title, String subtitle, int icon) {
            mTitle = title;
            mSubtitle = subtitle;
            mIcon = icon;
        }
    }

    class DrawerListAdapter extends BaseAdapter {

        Context mContext;
        ArrayList<NavItem> mNavItems;

        public DrawerListAdapter(Context context, ArrayList<NavItem> navItems) {
            mContext = context;
            mNavItems = navItems;
        }

        @Override
        public int getCount() {
            return mNavItems.size();
        }

        @Override
        public Object getItem(int position) {
            return mNavItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.drawer_item, null);
            }
            else {
                view = convertView;
            }

            TextView titleView = (TextView) view.findViewById(R.id.title);
            TextView subtitleView = (TextView) view.findViewById(R.id.subTitle);
            ImageView iconView = (ImageView) view.findViewById(R.id.icon);

            titleView.setText( mNavItems.get(position).mTitle );
            subtitleView.setText( mNavItems.get(position).mSubtitle );
            iconView.setImageResource(mNavItems.get(position).mIcon);

            return view;
        }
    }

}
