package net.dprogrammer.crudbasico_dpnet;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

    /*

    Exemplo criando em 14/12/2015 por Paulo Miranda
    Site: http://dprogrammer.net

    O objetivo desse exemplo é mostrar o funcionamento de um CRUD basico
    com apenas três campos. Contém duas telas, uma para listar o conteúdo da tabela
    e outra para inserir um novo regitro ou editá-lo.

    Você também vai aprender a implementar os eventos onItemClick e onItemLongClick no RecyclerView.

    */

public class MainActivity extends AppCompatActivity {

    private List<Contato> contatos = new ArrayList<>();
    private ContatosViewAdapter contatoViewAdapter;

    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;

    private static final int REQUEST_EDICAO = 0;
    public static final int REQUEST_SALVOU = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.contatos_recycler_view);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {

                    @Override
                    public void onItemClick(View view, int position) {
                        // TODO Handle item click
                        Intent intent = new Intent(MainActivity.this, GerirContatoActivity.class);
                        intent.putExtra("contato", contatos.get(position));
                        startActivityForResult(intent, REQUEST_EDICAO);
                    }

                    @Override
                    public void onItemLongClick(View view, final int position) {
                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case DialogInterface.BUTTON_POSITIVE:
                                        if (ContatosAdapter.getInstance(MainActivity.this).DeleteGrupo(contatos.get(position))) {
                                            Toast.makeText(MainActivity.this, "Registro excluído com sucesso", Toast.LENGTH_LONG).show();
                                            CarregarDados();
                                        } else {
                                            Toast.makeText(MainActivity.this, "Não foi possível excluir o registro", Toast.LENGTH_LONG).show();
                                        }
                                        break;
                                    case DialogInterface.BUTTON_NEGATIVE:
                                        // No button clicked
                                        break;
                                }
                            }
                        };

                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("Confirmar a exclusão do contato? " + contatos.get(position).getNome())
                                .setPositiveButton("Sim", dialogClickListener)
                                .setNegativeButton("Não", dialogClickListener).show();

                        Log.e("TAG", "Long Click Position:" + position);
                    }
                })
        );

        CarregarDados();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, GerirContatoActivity.class);
                startActivityForResult(intent, REQUEST_EDICAO);

            }
        });
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

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == REQUEST_EDICAO) {
            if (resultCode == REQUEST_SALVOU) {
                CarregarDados();
            }
        }
    }

    private void CarregarDados() {
        Cursor gruposRegistros =  ContatosAdapter.getInstance(this).getAllRows();

        contatos = ContatosAdapter.getInstance(this).getAllList(gruposRegistros);

        contatoViewAdapter = new ContatosViewAdapter(this, contatos);

        recyclerView.setAdapter(contatoViewAdapter);

    }

}
