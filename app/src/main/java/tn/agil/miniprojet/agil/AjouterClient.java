package tn.agil.miniprojet.agil;

/**
 * Created by Linda on 25/10/2017.
 */


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class AjouterClient extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener  {

    private EditText cin;
    private EditText nom;
    private EditText prenom;
    private EditText tel;
    private Button btnajouter;
    private EditText qte;

    Spinner spinner;
    RequestQueue requestQueue;
   /* //// array list for spinner adapter
    InputStream is = null;
    String result="";
    String line=null;
    String[] name;
    ArrayList<String> listItems =new ArrayList<>();
    ArrayAdapter<String> adapter;*/

    String insertUrl="http://192.168.1.5/5edma/insertion.php";
    //String affichUrl="http://192.168../5edma/affiche.php";

    String[] produit = { "TANIX 4WD SAE 15W50", "ENI I-SINT 5W40", "ENI I-SINT 10W40", "TANIX SUPER 1100 SAE 20W50"
            , "TANIX SUPER 700 SAE 20W50"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_client);
        cin = (EditText) findViewById(R.id.editCin);
        nom = (EditText) findViewById(R.id.editN);
        prenom = (EditText) findViewById(R.id.editP);
        tel = (EditText) findViewById(R.id.editT);
        spinner = (Spinner) findViewById(R.id.prodList);// define spinner
        spinner.setOnItemSelectedListener(this);
        //Creating the ArrayAdapter instance having the produit list
        ArrayAdapter<String> aa = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,produit);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        qte = (EditText) findViewById(R.id.editQte);
        btnajouter = (Button) findViewById(R.id.btnAjout);
        btnajouter.setOnClickListener(new OnClickListener());

        //Setting the ArrayAdapter data on the Spinner
        spinner.setAdapter(aa);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        Toast.makeText(getApplicationContext(),produit[i] ,Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    private class OnClickListener implements View.OnClickListener
    {
        public void onClick (View v)
        {
            String cinStr= cin.getText().toString();
            String nomStr= nom.getText().toString();
            String prenomStr= prenom.getText().toString();
            String telStr= tel.getText().toString();
            String prodStr= spinner.getAccessibilityClassName().toString();
            String qteStr= qte.getText().toString();


            if ((cinStr.isEmpty())||(nomStr.isEmpty())||(prenomStr.isEmpty())||(telStr.isEmpty())||
                    (prodStr.isEmpty())||(qteStr.isEmpty()))
            {
                Toast.makeText(AjouterClient.this,"Champs Vides !!",Toast.LENGTH_SHORT).show();
            }
            else{

                insertDataOnline();}

        }

        private void insertDataOnline() {
            StringRequest request = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }

            })
            {
                protected Map<String,String> getParam() throws AuthFailureError {
                    Map<String,String> parametres = new HashMap<String, String>();
                    parametres.put("Cin",cin.getText().toString());
                    parametres.put("Nom",nom.getText().toString());
                    parametres.put("Prenom",prenom.getText().toString());
                    parametres.put("Produit",spinner.getAccessibilityClassName().toString());
                    parametres.put("tel",tel.getText().toString());
                    parametres.put("qte",qte.getText().toString());
                    return parametres;


                }
            };
            requestQueue.add(request);
        }
    }
}
