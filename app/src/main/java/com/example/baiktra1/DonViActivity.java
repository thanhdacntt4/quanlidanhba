package com.example.baiktra1;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class DonViActivity extends AppCompatActivity {

    EditText edtMaDonVi, edtTenDonVi, edtEmailDonVi, edtWebsiteDonVi, edtLogoDonVi, edtDiaChiDonVi, edtSoDienThoaiDonVi, edtMaDonViCha;
    Button btnInsertDonVi, btnQueryDonVi, btnUpdateDonVi, btnDeleteDonVi;
    ListView lvDonVi;
    ArrayList<String> donViList;
    ArrayAdapter<String> donViAdapter;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_vi);

        edtMaDonVi = findViewById(R.id.edtMaDonVi);
        edtTenDonVi = findViewById(R.id.edtTenDonVi);
        edtEmailDonVi = findViewById(R.id.edtEmailDonVi);
        edtWebsiteDonVi = findViewById(R.id.edtWebsiteDonVi);
        edtLogoDonVi = findViewById(R.id.edtLogoDonVi);
        edtDiaChiDonVi = findViewById(R.id.edtDiaChiDonVi);
        edtSoDienThoaiDonVi = findViewById(R.id.edtSoDienThoaiDonVi);
        edtMaDonViCha = findViewById(R.id.edtMaDonViCha);

        btnInsertDonVi = findViewById(R.id.btnInsertDonVi);
        btnQueryDonVi = findViewById(R.id.btnQueryDonVi);
        btnUpdateDonVi = findViewById(R.id.btnUpdateDonVi);
        btnDeleteDonVi = findViewById(R.id.btnDeleteDonVi);

        lvDonVi = findViewById(R.id.lvDonVi);

        donViList = new ArrayList<>();
        donViAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, donViList);
        lvDonVi.setAdapter(donViAdapter);

        DBHelper dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();
        lvDonVi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Lấy dữ liệu của mục được chọn từ danh sách
                String selectedItem = donViList.get(position);

                // Phân tích dữ liệu và hiển thị trên các EditText
                String[] parts = selectedItem.split("\n");
                for (String part : parts) {
                    String[] keyValue = part.split(": ");
                    if (keyValue.length == 2) {
                        String key = keyValue[0];
                        String value = keyValue[1];
                        switch (key) {
                            case "Ma Don Vi":
                                edtMaDonVi.setText(value);
                                break;
                            case "Ten Don Vi":
                                edtTenDonVi.setText(value);
                                break;
                            case "Email":
                                edtEmailDonVi.setText(value);
                                break;
                            case "Website":
                                edtWebsiteDonVi.setText(value);
                                break;
                            case "Logo":
                                edtLogoDonVi.setText(value);
                                break;
                            case "Dia Chi":
                                edtDiaChiDonVi.setText(value);
                                break;
                            case "So Dien Thoai":
                                edtSoDienThoaiDonVi.setText(value);
                                break;
                            case "Ma Don Vi Cha":
                                edtMaDonViCha.setText(value);
                                break;
                        }
                    }
                }
            }
        });

        btnInsertDonVi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maDonVi = edtMaDonVi.getText().toString();
                String tenDonVi = edtTenDonVi.getText().toString();
                String email = edtEmailDonVi.getText().toString();
                String website = edtWebsiteDonVi.getText().toString();
                String logo = edtLogoDonVi.getText().toString();
                String diaChi = edtDiaChiDonVi.getText().toString();
                String soDienThoai = edtSoDienThoaiDonVi.getText().toString();
                String maDonViCha = edtMaDonViCha.getText().toString();

                ContentValues values = new ContentValues();
                values.put("maDonVi", maDonVi);
                values.put("tenDonVi", tenDonVi);
                values.put("email", email);
                values.put("website", website);
                values.put("logo", logo);
                values.put("diaChi", diaChi);
                values.put("soDienThoai", soDienThoai);
                values.put("maDonViCha", maDonViCha);

                String msg = "";
                if (db.insert("tblDonVi", null, values) == -1) {
                    msg = "Fail to Insert Record!";
                } else {
                    msg = "Insert record Successfully";
                    updateListView();
                }
                Toast.makeText(DonViActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

        btnUpdateDonVi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maDonVi = edtMaDonVi.getText().toString();
                String tenDonVi = edtTenDonVi.getText().toString();
                String email = edtEmailDonVi.getText().toString();
                String website = edtWebsiteDonVi.getText().toString();
                String logo = edtLogoDonVi.getText().toString();
                String diaChi = edtDiaChiDonVi.getText().toString();
                String soDienThoai = edtSoDienThoaiDonVi.getText().toString();
                String maDonViCha = edtMaDonViCha.getText().toString();

                ContentValues values = new ContentValues();
                values.put("tenDonVi", tenDonVi);
                values.put("email", email);
                values.put("website", website);
                values.put("logo", logo);
                values.put("diaChi", diaChi);
                values.put("soDienThoai", soDienThoai);
                values.put("maDonViCha", maDonViCha);

                String msg = "";
                int result = db.update("tblDonVi", values, "maDonVi = ?", new String[]{maDonVi});
                if (result == 0) {
                    msg = "No record to Update";
                } else {
                    msg = result + " record is updated";
                    updateListView();
                }
                Toast.makeText(DonViActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

        btnDeleteDonVi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maDonVi = edtMaDonVi.getText().toString();
                String msg = "";
                int result = db.delete("tblDonVi", "maDonVi = ?", new String[]{maDonVi});
                if (result == 0) {
                    msg = "No record to Delete";
                } else {
                    msg = result + " record is deleted";
                    updateListView();
                }
                Toast.makeText(DonViActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

        btnQueryDonVi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateListView();
            }
        });
    }

    private void updateListView() {
        donViList.clear();
        Cursor c = db.query("tblDonVi", null, null, null, null, null, null);

        if (c != null && c.moveToFirst()) {
            int maDonViIndex = c.getColumnIndex("maDonVi");
            int tenDonViIndex = c.getColumnIndex("tenDonVi");
            int emailIndex = c.getColumnIndex("email");
            int websiteIndex = c.getColumnIndex("website");
            int logoIndex = c.getColumnIndex("logo");
            int diaChiIndex = c.getColumnIndex("diaChi");
            int soDienThoaiIndex = c.getColumnIndex("soDienThoai");
            int maDonViChaIndex = c.getColumnIndex("maDonViCha");

            do {
                String maDonVi = maDonViIndex != -1 ? c.getString(maDonViIndex) : "";
                String tenDonVi = tenDonViIndex != -1 ? c.getString(tenDonViIndex) : "";
                String email = emailIndex != -1 ? c.getString(emailIndex) : "";
                String website = websiteIndex != -1 ? c.getString(websiteIndex) : "";
                String logo = logoIndex != -1 ? c.getString(logoIndex) : "";
                String diaChi = diaChiIndex != -1 ? c.getString(diaChiIndex) : "";
                String soDienThoai = soDienThoaiIndex != -1 ? c.getString(soDienThoaiIndex) : "";
                String maDonViCha = maDonViChaIndex != -1 ? c.getString(maDonViChaIndex) : "";

                String data = "Ma Don Vi: " + maDonVi + "\n" +
                        "Ten Don Vi: " + tenDonVi + "\n" +
                        "Email: " + email + "\n" +
                        "Website: " + website + "\n" +
                        "Logo: " + logo + "\n" +
                        "Dia Chi: " + diaChi + "\n" +
                        "So Dien Thoai: " + soDienThoai + "\n" +
                        "Ma Don Vi Cha: " + maDonViCha;
                donViList.add(data);
            } while (c.moveToNext());
        }
        if (c != null) {
            c.close();
        }
    }

}
