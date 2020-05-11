package com.example.volley;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    // RequestQueue Volley
    private RequestQueue queue;

    // mi componentes
    TextView res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // my process
        res  = (TextView) findViewById(R.id.result);
        res.setText("Mi message");

        queue = Volley.newRequestQueue(this);

        // Llamada a la función que ejecutará nuestra funcion obtenerDatos()
        ejecutar();
        // cambio en la rama_volley
    }

    private void ejecutar(){
        final Handler handler= new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                obtenerDatos(); //llamamos nuestro metodo
                handler.postDelayed(this,10000);//se ejecutara cada 10 segundos
            }
        },5000);//empezara a ejecutarse después de 5 milisegundos
    }

    private void obtenerDatos() {
        //String url = "https://api.androidhive.info/contacts/";
        String url = "http://marisol.orgfree.com/read_data.php";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String data = response.getString("valorTemperatura");
                    res.setText(data);
                }catch (JSONException e){
                    e.printStackTrace();
                    res.setText("error al decodificar");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);

    }



}
