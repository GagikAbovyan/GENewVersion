package vtc.room.a101.gagasexpressnewversion.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import vtc.room.a101.gagasexpressnewversion.R;
import vtc.room.a101.gagasexpressnewversion.adapter.AllProductAdapter;
import vtc.room.a101.gagasexpressnewversion.constant.Constant;
import vtc.room.a101.gagasexpressnewversion.fragment.AllProductFragment;
import vtc.room.a101.gagasexpressnewversion.provider.DataProvider;

public class ExpressActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private AllProductFragment allProductFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_express);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FloatingActionButton openFavoriteButton = (FloatingActionButton) findViewById(R.id.open_favorites);
        FloatingActionButton openStoresButton = (FloatingActionButton) findViewById(R.id.open_store);
        openFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllProductFragment.setList(DataProvider.returnFavorites());
                allProductFragment.updateList(new AllProductAdapter(ExpressActivity.this, AllProductFragment.getList(), false));
            }
        });
        openStoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllProductFragment.setList(DataProvider.returnStores());
                allProductFragment.updateList(new AllProductAdapter(ExpressActivity.this, AllProductFragment.getList(), true));
            }
        });

        DataProvider.initData();
        /*Collections.shuffle(DataProvider.getList());*/
        AllProductFragment.setList(DataProvider.getList());
        //Collections.shuffle(AllProductFragment.getList());
        allProductFragment = new AllProductFragment();
        loadFragment(allProductFragment);

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
        getMenuInflater().inflate(R.menu.menu_info, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                allProductFragment.allProductAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                allProductFragment.allProductAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id){
            case R.id.nav_all:
                AllProductFragment.setList(DataProvider.getList());
                allProductFragment.updateList(new AllProductAdapter(ExpressActivity.this, AllProductFragment.getList(), false));
                break;
            case R.id.nav_smart_phone:
                AllProductFragment.setList(DataProvider.updateList(Constant.CATEGORY_SMART_PHONE));
                allProductFragment.updateList(new AllProductAdapter(ExpressActivity.this, AllProductFragment.getList(), false));
                break;
            case R.id.nav_notebook:
                AllProductFragment.setList(DataProvider.updateList(Constant.CATEGORY_NOTEBOOK));
                allProductFragment.updateList(new AllProductAdapter(ExpressActivity.this, AllProductFragment.getList(), false));
                break;
            case R.id.nav_watch:
                AllProductFragment.setList(DataProvider.updateList(Constant.CATEGORY_WATCH));
                allProductFragment.updateList(new AllProductAdapter(ExpressActivity.this, AllProductFragment.getList(), false));
                break;
            case R.id.nav_favorite:
                AllProductFragment.setList(DataProvider.returnFavorites());
                allProductFragment.updateList(new AllProductAdapter(ExpressActivity.this, AllProductFragment.getList(), false));
                break;
            case R.id.nav_store:
                AllProductFragment.setList(DataProvider.returnStores());
                allProductFragment.updateList(new AllProductAdapter(ExpressActivity.this, AllProductFragment.getList(), true));
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadFragment(final Fragment fragment){
        getSupportFragmentManager().beginTransaction().add(R.id.container_for_fragment, fragment).commit();
    }

}
