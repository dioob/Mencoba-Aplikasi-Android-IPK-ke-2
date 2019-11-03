package dioobanu.yahoo.appipk;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class MainActivity extends AppCompatActivity {

    private EditText mNamaMakul, mJumlahSKS, mNilai;
    private Button btnSimpanArray, btnTampilData, btnHitungIP;
    private TextView TotalSKS, NilaiIP;

    ArrayList<Integer> mtsks= new ArrayList<>();
    ArrayList<Integer> tip= new ArrayList<>();
    ArrayList<String> tambahArray = new ArrayList<>();
    ListView tampil;
    int counter=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNamaMakul = findViewById(R.id.namamatakuliah);
        mJumlahSKS = findViewById(R.id.jumlahsks);
        mNilai = findViewById(R.id.nilai);

        btnSimpanArray = findViewById(R.id.button);
        btnTampilData = findViewById(R.id.button1);
        btnHitungIP = findViewById(R.id.button2);

        TotalSKS = findViewById(R.id.totalsks);
        NilaiIP = findViewById(R.id.nilaiip);

        tampil = findViewById(R.id.makul_list);

        btnSimpanArray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nilaiAngka = 0;
                final String makul = mNamaMakul.getText().toString();
                final String sks = mJumlahSKS.getText().toString();
                final String nilai = mNilai.getText().toString();
                final int hsks = Integer.parseInt(mJumlahSKS.getText().toString());

                //Keterangan bobot: A = 4, B = 3, C = 2, D = 1, E = 0
                switch (nilai) {
                    case "A":
                    case "a":
                        nilaiAngka = 4;
                        break;
                    case "B":
                    case "b":
                        nilaiAngka = 3;
                        break;
                    case "C":
                    case "c":
                        nilaiAngka = 2;
                        break;
                    case "D":
                    case "d":
                        nilaiAngka = 1;
                        break;
                    case "E":
                    case "e":
                        nilaiAngka = 0;
                        break;
                }


                if (tambahArray.contains(makul+"\t"+sks+"\t"+nilai)){
                    mtsks.contains(hsks);
                    tip.contains(nilaiAngka);
                    Toast.makeText(getBaseContext(), "Berhasil menyimpan ke dalam bentuk Array.", Toast.LENGTH_LONG).show();
                }
                else if ((makul.equals("") && sks.equals("") && nilai.equals("")) || makul.equals("") || sks.equals("") || nilai.equals("")){
                    Toast.makeText(getBaseContext(), "Mohon isi semua kolom inputan.", Toast.LENGTH_LONG).show();
                }
                else {
                    btnTampilData.setOnClickListener(new View.OnClickListener() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onClick(View v) {
                            tambahArray.add(makul+"\t   "+sks+" SKS\t   Nilai "+nilai);
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, tambahArray);
                            tampil.setAdapter(adapter);
                            btnTampilData.setEnabled(false);
                            btnHitungIP.setEnabled(true);
                        }
                    });


                    final int finalNilaiAngka = nilaiAngka;
                    btnHitungIP.setOnClickListener(new View.OnClickListener() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onClick(View v) {
                            int sum=0;
                            double ip=0;
                            double totalip = 0;
                            mtsks.add(hsks);
                            tip.add(finalNilaiAngka);


                            //Hitung total jumlah SKS
                            for (int i=0; i<mtsks.size(); i++){
                                sum += mtsks.get(i);
                                TotalSKS.setText("Total SKS : "+sum);

                                for (int n = 0; n < tip.size(); n++) {
                                    totalip += tip.get(n);
                                if(counter>=2){
                                        totalip = totalip / 2;
                                        DecimalFormat df = new DecimalFormat("#.##"); //program pembulatan decimal
                                        NilaiIP.setText("Nilai IP : " + totalip);
                                }else{counter++;NilaiIP.setText("Nilai IP : "+df.format(totalip));}} //mengubah ke df.format(totalip)

                                btnHitungIP.setEnabled(false);
                                btnTampilData.setEnabled(false);
                                btnSimpanArray.setEnabled(true);
                            }


                            //Hitung : IP = ((2 x 4) + (3 x 2) + (2 x 3) ) / (2 + 3 + 2)
                            //IP = (8 + 6 + 6) / 7
                            //IP = 20 / 7
                            //IP = 2,86

                        }
                    });
                    ((EditText)findViewById(R.id.namamatakuliah)).setText("");
                    ((EditText)findViewById(R.id.jumlahsks)).setText("");
                    ((EditText)findViewById(R.id.nilai)).setText("");
                    btnHitungIP.setEnabled(false);
                    btnTampilData.setEnabled(true);
                    btnSimpanArray.setEnabled(false);
                }
            }
        });


    }

}
