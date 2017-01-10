package idoldistionary.com.distionaryidol;

import android.support.v7.app.ActionBar;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import java.lang.annotation.Annotation;

public class MainActivity extends AppCompatActivity implements ActionBar.NavigationMode {

    private DBConnector mDbConnector;
    private RecyclerView mRecyclerViewshow;
    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Connection sqlite
        mDbConnector = new DBConnector(getApplicationContext());

        //RecyclerView
        mRecyclerViewshow = (RecyclerView)findViewById(R.id.recyclerViewShow);
        mRecyclerViewAdapter = new RecyclerViewAdapter();
        mRecyclerViewAdapter.setmRecyclerAdapterListener(new RecyclerAdapterListener() {
            @Override
            public void onItemClicked(String definition) {
                gotopage(definition);
            }
        });
        mRecyclerViewshow.setAdapter(mRecyclerViewAdapter);
        mLinearLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerViewshow.setLayoutManager(mLinearLayoutManager);
        search("");


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bar, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView)item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Log.d("Search", "onTextChanged " + s);
                search(s);
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    private void gotopage(String definition) {
        Intent intent = new Intent(getApplicationContext(), DetailWordActivity.class);
        intent.putExtra("definition",definition);
        startActivity(intent);
    }
    private void search(String word) {
        String[] selectionArgs = { word + "%"};
        cursor = mDbConnector.getReadableDatabase().rawQuery("SELECT * FROM Dictionary WHERE word LIKE ? LIMIT 100", selectionArgs);
        mRecyclerViewAdapter.setCursor(cursor);

//        mWordAdapter.setCursor(mCursor);
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
