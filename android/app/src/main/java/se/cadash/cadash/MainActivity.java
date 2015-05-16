package se.cadash.cadash;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import se.cadash.cadash.View.ListViewActivity;
import se.cadash.cadash.model.IModel;
import se.cadash.cadash.model.Model;
import se.cadash.cadash.model.SignInListener;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, SignInListener {

    private IModel model = Model.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the UI components
        findViewById(R.id.sign_in_button).setOnClickListener(this);


        model.initialize();
        model.addSignInListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    protected void onStart() {
        super.onStart();
        model.connect();


    }

    @Override
    protected void onStop() {
        super.onStop();
        model.disconnect();
    }

    @Override
    public void onClick(View view) {
        model.signIn();

    }

    @Override
    public void signInCompleted() {
        Intent intent = new Intent(getBaseContext(), ListViewActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void signInFailed() {

    }
}
