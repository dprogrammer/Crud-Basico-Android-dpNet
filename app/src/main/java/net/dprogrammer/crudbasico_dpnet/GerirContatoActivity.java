package net.dprogrammer.crudbasico_dpnet;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class GerirContatoActivity extends AppCompatActivity {

    private Contato contato;

    TextInputLayout inputLayoutNome, inputLayoutSobrenome, inputLayoutEmail;
    EditText edNome, edSobrenome, edEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerir_contato);

        inputLayoutNome = (TextInputLayout) findViewById(R.id.input_layout_nome);
        inputLayoutSobrenome = (TextInputLayout) findViewById(R.id.input_layout_sobrenome);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);

        edNome = (EditText) findViewById(R.id.nome);
        edSobrenome = (EditText) findViewById(R.id.sobrenome);
        edEmail = (EditText) findViewById(R.id.email);

        Intent intent = getIntent();
        contato = (Contato) intent.getSerializableExtra("contato");

        if (contato != null) {
            setTitle("Alterar Contato");

            edNome.setText(contato.getNome());
            edSobrenome.setText(contato.getSobrenome());
            edEmail.setText(contato.getEmail());

        } else {
            setTitle("Novo Contato");
        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    public void salvar (View v){

        if (contato != null) {

            contato.setNome(edNome.getText().toString());
            contato.setSobrenome(edSobrenome.getText().toString());
            contato.setEmail(edEmail.getText().toString());

            ContatosAdapter.getInstance(this).UpdateContato(contato);

        } else {

            contato = new Contato();

            contato.setNome(edNome.getText().toString());
            contato.setSobrenome(edSobrenome.getText().toString());
            contato.setEmail(edEmail.getText().toString());

            ContatosAdapter.getInstance(this).InsertContato(contato);
        }

        setResult(MainActivity.REQUEST_SALVOU);

        finish();

    }

}
