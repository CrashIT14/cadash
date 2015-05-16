package se.cadash.cadash.view;

import android.app.Activity;
import android.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import java.util.ArrayList;
import java.util.List;

import se.cadash.cadash.R;
import se.cadash.cadash.model.Contact;
import se.cadash.cadash.model.Debt;
import se.cadash.cadash.model.IModel;
import se.cadash.cadash.model.Model;

public class ListViewActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private IModel model = Model.getInstance();

    Contact c1 = new Contact("Carl", "Albertsson", null, new Debt(29));
    Contact c2 = new Contact("jsjd", "Alberddsddtsson", null, new Debt(33));
    Contact c3 = new Contact("dasdsa", "sdsdsd", null, new Debt(93));
    Contact c4 = new Contact("fdsfs", "as", null, new Debt(123));
    Contact c5 = new Contact("dsdsda", "dsffg", null, new Debt(97));
    List<Contact> conList = new ArrayList<Contact>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        conList.add(c1);
        conList.add(c2);
        conList.add(c3);
        conList.add(c4);
        conList.add(c5);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        //mAdapter = new ListAdapter(model.getContacts());
        mAdapter = new ListAdapter(conList);
        mRecyclerView.setAdapter(mAdapter);

        SwipeRefreshLayout swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorSchemeColors(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_list_view, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_add_new_contact:
                //Metod för nya kontakter
                return true;
            case R.id.action_settings:
                //setingsshit
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onRefresh() {

    }
}
