package fem.miw.upm.es.ejemploficheros;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private static final String MI_FICHERO = "FICHERO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("MiW", "Creado");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.opcionAbrir:
                abrirFichero();
                break;
            case R.id.opcionGuardar:
                guardarFichero();
                break;
        }
        return true;
    }

    private void abrirFichero() {
        try{
            TextView textView = (TextView) findViewById(R.id.textView);
            BufferedReader fin = new BufferedReader(new InputStreamReader(openFileInput(MI_FICHERO)));
            String linea = fin.readLine();
            while (linea != null){
                textView.append(linea + '\n');
                linea = fin.readLine();
            }
            fin.close();
            Toast.makeText(this, "El fichero se ha abierto", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void guardarFichero() {
        try {
            EditText editText = (EditText) findViewById(R.id.editText);
            FileOutputStream fos = openFileOutput(MI_FICHERO, Context.MODE_APPEND);
            fos.write(editText.getText().toString().getBytes());
            fos.write('\n');
            fos.close();
            Toast.makeText(this, "El fichero ha sido guardado", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
