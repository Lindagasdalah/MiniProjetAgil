package tn.agil.miniprojet.agil;

/**
 * Created by Linda on 25/10/2017.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class AjouterClient extends AppCompatActivity {

    private EditText nom;
    private EditText prenom;
    private EditText tel;
    private Button btn;
    private EditText qte;
    private TextView indicator;
    private TextView indicator2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_client);
        nom = (EditText) findViewById(R.id.editN);
        prenom = (EditText) findViewById(R.id.editP);
        tel = (EditText) findViewById(R.id.editT);
        qte=(EditText) findViewById(R.id.editQte) ;
        btn = (Button) findViewById(R.id.btnAjout);
        indicator = (TextView) findViewById(R.id.indicator);
        btn.setOnClickListener(new OnClickListener());

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
