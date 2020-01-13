package id.ac.poliban.mi.andre.mycrud;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private EditText etNama;
    private EditText etPosisi;
    private EditText etGaji;

    private Button btTambahPegawai;
    private Button btTampilPegawai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNama = findViewById(R.id.et_nama);
        etPosisi = findViewById(R.id.et_posisi);
        etGaji = findViewById(R.id.et_gaji);

        btTambahPegawai = findViewById(R.id.bt_insert_pegawai);
        btTampilPegawai = findViewById(R.id.bt_get_all_pegawai);

        btTambahPegawai.setOnClickListener(v -> {
            addEmployee();
        });

        btTampilPegawai.setOnClickListener(v -> {
            startActivity(new Intent(this, TampilSemuaPgw.class));
        });
    }

    private void addEmployee() {
        final String name = etNama.getText().toString().trim();
        final String posisi = etPosisi.getText().toString().trim();
        final String gaji = etGaji.getText().toString().trim();

        class AddEmployee extends AsyncTask<Void, Void, String> {

            private ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this,
                        "Menambahkan...", "Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(MainActivity.this, "apa nih?"+s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Configuration.KEY_EMP_NAMA, name);
                params.put(Configuration.KEY_EMP_POSISI, posisi);
                params.put(Configuration.KEY_EMP_GAJI, gaji);

                RequestHandler rh = new RequestHandler();
                return rh.sendPostRequest(Configuration.URL_ADD, params);
            }
        }
        AddEmployee ae = new AddEmployee();
        ae.execute();
    }
}
