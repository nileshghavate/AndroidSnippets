package in.dbit.tryjsonws;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // ArrayList for person names, email Id's and mobile numbers
    ArrayList<String> personNames = new ArrayList<>();
    ArrayList<String> emailIds = new ArrayList<>();
    ArrayList<String> mobileNumbers = new ArrayList<>();

    RecyclerView recyclerView;
    //#2
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    //private String url = "https://run.mocky.io/v3/f933c888-d376-4888-b03b-492b23decf51";
    private String url = "http://domainaa07a6.stackstaging.com/api.php";
    private AppCompatButton btnRequest;
    private static final String TAG = MainActivity.class.getName();
    private String res="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // get the reference of RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);


        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(),"Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen

                try {
                    // get JSONObject from JSON file
                    JSONObject obj = new JSONObject(response.toString());

                    // fetch JSONArray named users
                    JSONArray userArray = obj.getJSONArray("users");

                    // implement for loop for getting users list data
                    for (int i = 0; i < userArray.length(); i++)
                    {
                        // create a JSONObject for fetching single user data
                        JSONObject userDetail = userArray.getJSONObject(i);

                        // fetch email and name and store it in arraylist
                        personNames.add(userDetail.getString("name"));
                        emailIds.add(userDetail.getString("email"));

                        // create a object for getting contact data from JSONObject
                        JSONObject contact = userDetail.getJSONObject("contact");

                        // fetch mobile number and store it in arraylist
                        mobileNumbers.add(contact.getString("mobile"));
                    }


                    //  call the constructor of CustomAdapter to send the reference and data to Adapter
                    CustomAdapter customAdapter = new CustomAdapter(MainActivity.this, personNames, emailIds, mobileNumbers);

                    // set the Adapter to RecyclerView
                    recyclerView.setAdapter(customAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i(TAG,"Error :" + error.toString());
            }
        });

        mRequestQueue.add(mStringRequest);



    }
}