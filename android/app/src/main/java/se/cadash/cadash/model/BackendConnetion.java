package se.cadash.cadash.model;

import android.os.AsyncTask;

/**
 * @author Alexander Håkansson
 */
public class BackendConnetion {

    private static String BACKEND_URL = "http://192.168.43.158:8080/";

    private SERVER_REQUEST requestType;

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
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}
