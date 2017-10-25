package tn.agil.miniprojet.agil;

/**
 * Created by Linda on 25/10/2017.
 */


import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import org.apache.http.client.HttpClient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class AjouterClient extends AppCompatActivity {

    private EditText nom;
    private EditText prenom;
    private EditText tel;
    private Button btn;
    private EditText qte;
    private Spinner spinner;
    private TextView indicator;
    private TextView indicator2;

    ArrayList<String> listItems =new ArrayList<>();
    ArrayAdapter<String> adapter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_client);
        nom = (EditText) findViewById(R.id.editN);
        prenom = (EditText) findViewById(R.id.editP);
        tel = (EditText) findViewById(R.id.editT);
        spinner = (Spinner) findViewById(R.id.prodList);
        adapter=new ArrayAdapter<String>(this, R.layout.spinner_layout, R.id.txt, listItems);
        spinner.setAdapter(adapter);
        qte=(EditText) findViewById(R.id.editQte) ;
        btn = (Button) findViewById(R.id.btnAjout);
        indicator = (TextView) findViewById(R.id.indicator);
        btn.setOnClickListener(new OnClickListener());

    }

    protected void onStart()
    {
        super.onStart();
        BackTask bt= new BackTask();
        bt.execute();
    }

    private class BackTask extends AsyncTask< Void , Void , Void >
    {
        ArrayList<String> list ;
        protected  void  onPreExecute()
        {
            super.onPreExecute();
            list= new ArrayList<>();

        }

        protected Void doInBackground (Void...params)
        {
            InputStream is = null;
            String result="";
            try{
                HttpClient httpClient;
                httpClient = new DefaultHttpClient();
                HttpPost httpPost= new HttpPost("http://192.168.1.5/test/all.php ");
                HttpResponse response=httpClient.execute(httpPost);
                HttpEntity entity= response.getEntity();
                is=entity.getContent();

            }catch (Exception e){
                e.printStackTrace();
            }
            // convert the response to string
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is,"utf-8"));
                String line="";
                while ((line=bufferedReader.readLine())!=null){
                    result+=line;
                }
                is.close();
            }catch (IOException e){e.printStackTrace(); }

            // Parse json String
            try{
                JSONArray jsonArray =new JSONArray(result);
                for (int i=0; i<jsonArray.length();i++)
                {
                    JSONObject jsonObject= jsonArray.getJSONObject(i);
                    list.add(jsonObject.getString("iname"));
                }
            }catch (JSONException e){e.printStackTrace();}
            return null;
        }

        protected  void  onPostExecute (Void result) {
            listItems.addAll(list);
            adapter.notifyDataSetChanged();

        }
    }





    private class OnClickListener implements View.OnClickListener
    {
        public void onClick (View v)
        {
            if((nom.getText().length()==0) && (prenom.getText().length()==0) && (tel.getText().length()==0)&& (qte.getText().length()==0))
            {
                indicator.setText(" * Champs Obligatoire ");
            }
            else if((nom.getText().length()==0) )
            {
                indicator.setText(" * Inseret le Nom  ");
            }
            else if((prenom.getText().length()==0) )
            {
                indicator.setText(" * Inseret le Prenom  ");
            }
            else if((tel.getText().length()==0) )
            {
                indicator.setText(" * Inseret le N°tel  ");
            }


            else if((qte.getText().length()==0) )
            {
                indicator.setText(" * Inseret la quantité   ");
            }

            else
            {
                indicator.setText(" !! ");
            }
        }
    }
}
