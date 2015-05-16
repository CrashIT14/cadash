package se.cadash.cadash.model;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Alexander Håkansson
 */
public class BackendConnetion {

    private static String BACKEND_URL = "http://192.168.43.158:8080/";

    private SERVER_REQUEST requestType;

    private BackendSyncListener syncListener;

    public enum SERVER_REQUEST {
        REGISTER {
            @Override
            String getName() {
                return "register";
            }
        }, SYNC {
            @Override
            String getName() {
                return "sync";
            }
        }, NEW_DEBT {
            @Override
            String getName() {
                return "newDebt";
            }
        }, REMOVE {
            @Override
            String getName() {
                return "remove";
            }
        };

        abstract String getName();
    }

    public void register(String email) {
        this.requestType = SERVER_REQUEST.REGISTER;
        String param = "?email="+email;
        new BackendConnect().execute(param);
    }

    public void newDebt(String to, String from, int amount) {
        this.requestType = SERVER_REQUEST.NEW_DEBT;
        String param = "?amount="+amount+"&user1="+from+"&user2="+to;
        new BackendConnect().execute(param);
    }

    public void sync(BackendSyncListener listener, String user) {
        this.requestType = SERVER_REQUEST.SYNC;
        String param = "?user="+user;
        this.syncListener = listener;
        new BackendConnect().execute(param);
    }


    public BackendConnetion(SERVER_REQUEST requestType) {
        this.requestType = requestType;
    }

    private class BackendConnect extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {

            InputStream is = null;

            try {
                URL url = new URL(BACKEND_URL + requestType.getName() + strings[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000); /* milliseconds */
                conn.setConnectTimeout(15000); /* milliseconds */
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                // Starts the query
                conn.connect();
                int response = conn.getResponseCode();

                // HTTP response 200 = OK
                if (response == 200) {
                    is = conn.getInputStream();

                    /// read the output from the server
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line = null;

                    while ((line = reader.readLine()) != null)
                    {
                        line = line.replace(',', '.');
                        stringBuilder.append(line);
                    }

                    String result = stringBuilder.toString();

                    if (result.trim().toLowerCase().equals("true")) {
                        return "done";
                    } else {
                        return result;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return "done";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            List<Contact> contacts = new ArrayList<Contact>();
            if (!result.equals("done")) {
                if (requestType == SERVER_REQUEST.SYNC) {
                    String[] debts = result.split(";");
                    for (String debt : debts) {
                        String[] bleh = debt.split(":");
                        String u1 = bleh[0];
                        String u2 = bleh[1];
                        int amount = Integer.parseInt(bleh[2]);
                        Contact newContact = new Contact("First", "Last", u1, amount);
                        contacts.add(newContact);
                    }
                    for (Contact c : contacts) {
                        System.out.println(c.getEmail() + " | " + c.getDebt());
                    }
                    syncListener.onContactsSynced(contacts);
                }
            }
        }


    }
}
