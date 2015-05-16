package se.cadash.cadash;

import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;

import se.cadash.cadash.model.IModel;
import se.cadash.cadash.model.Model;
import se.cadash.cadash.view.ListViewActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private IModel model = Model.getInstance();
    /* Request code used to invoke sign in user interactions. */
    private static final int RC_SIGN_IN = 0;
    private boolean loginIntentInProgress = false;
    private boolean mSignInClicked = false;

    public static final String PREFS_NAME = "FirstLaunchPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GoogleApiClient googleApi = model.getGoogleApiClient();

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        boolean firstTime = settings.getBoolean("firstTime", true);

        googleApi = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(Plus.SCOPE_PLUS_LOGIN)
                .build();

        model.setGoogleApiClient(googleApi);

        // Initialize the UI components
        findViewById(R.id.sign_in_button).setOnClickListener(this);

        if (!firstTime) {
            model.getGoogleApiClient().connect();
        }

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
    }

    @Override
    protected void onStop() {
        super.onStop();
        model.getGoogleApiClient().disconnect();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.sign_in_button && !model.getGoogleApiClient().isConnecting()) {
            mSignInClicked = true;
            model.getGoogleApiClient().connect();
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        mSignInClicked = false;
        Toast.makeText(this, "User: " + model.getUserEmail(), Toast.LENGTH_LONG).show();
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("firstTime", false);
        editor.commit();
        goToNextScreen();
    }

    @Override
    public void onConnectionSuspended(int i) {
        model.getGoogleApiClient().connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (!loginIntentInProgress && connectionResult.hasResolution()) {
            try {
                loginIntentInProgress = true;
                connectionResult.startResolutionForResult(this, RC_SIGN_IN);
            } catch (IntentSender.SendIntentException e) {
                // The intent was canceled before it was sent.  Return to the default
                // state and attempt to connect to get an updated ConnectionResult.
                loginIntentInProgress = false;
                model.getGoogleApiClient().connect();
            }
        }
    }

    private void goToNextScreen() {
        Intent intent = new Intent(getBaseContext(), ListViewActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent intent) {
        if (requestCode == RC_SIGN_IN) {
            if (responseCode != RESULT_OK) {
                mSignInClicked = false;
            }

            loginIntentInProgress = false;

            if (!model.getGoogleApiClient().isConnected()) {
                model.getGoogleApiClient().reconnect();
            }
        }
    }
}
