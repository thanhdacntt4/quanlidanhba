package com.example.baiktra1;

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

public class NhanVienActivity extends AppCompatActivity {

    EditText edtMaNhanVien, edtHoTen, edtChucVu, edtEmailNhanVien, edtSoDienThoaiNhanVien, edtAnhDaiDien, edtMaDonVi;
    Button btnInsertNhanVien, btnUpdateNhanVien, btnDeleteNhanVien, btnQueryNhanVien;
    ListView lvNhanVien;
    ArrayList<String> nhanVienList;
    ArrayAdapter<String> nhanVienAdapter;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_vien);

        edtMaNhanVien = findViewById(R.id.edtMaNhanVien);
        edtHoTen = findViewById(R.id.edtHoTen);
        edtChucVu = findViewById(R.id.edtChucVu);
        edtEmailNhanVien = findViewById(R.id.edtEmailNhanVien);
        edtSoDienThoaiNhanVien = findViewById(R.id.edtSoDienThoaiNhanVien);
        edtAnhDaiDien = findViewById(R.id.edtAnhDaiDien);
        edtMaDonVi = findViewById(R.id.edtMaDonVi);

        btnInsertNhanVien = findViewById(R.id.btnInsertNhanVien);
        btnUpdateNhanVien = findViewById(R.id.btnUpdateNhanVien);
        btnDeleteNhanVien = findViewById(R.id.btnDeleteNhanVien);
        btnQueryNhanVien = findViewById(R.id.btnQueryNhanVien);

        lvNhanVien = findViewById(R.id.lvNhanVien);

        nhanVienList = new ArrayList<>();
        nhanVienAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nhanVienList);
        lvNhanVien.setAdapter(nhanVienAdapter);

        DBHelper dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();

        lvNhanVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Lấy dữ liệu của mục được chọn từ danh sách
                String selectedItem = nhanVienList.get(position);

                // Phân tích dữ liệu và hiển thị trên các EditText
                String[] parts = selectedItem.split("\n");
                for (String part : parts) {
                    String[] keyValue = part.split(": ");
                    if (keyValue.length == 2) {
                        String key = keyValue[0];
                        String value = keyValue[1];
                        switch (key) {
                            case "Ma Nhan Vien":
                                edtMaNhanVien.setText(value);
                                break;
                            case "Ho Ten":
                                edtHoTen.setText(value);
                                break;
                            case "Chuc Vu":
                                edtChucVu.setText(value);
                                break;
                            case "Email":
                                edtEmailNhanVien.setText(value);
                                break;
                            case "So Dien Thoai":
                                edtSoDienThoaiNhanVien.setText(value);
                                break;
                            case "Anh Dai Dien":
                                edtAnhDaiDien.setText(value);
                                break;
                            case "Ma Don Vi":
                                edtMaDonVi.setText(value);
                                break;
                        }
                    }
                }
            }
        });

        btnInsertNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maNhanVien = edtMaNhanVien.getText().toString();
                String hoTen = edtHoTen.getText().toString();
                String chucVu = edtChucVu.getText().toString();
                String email = edtEmailNhanVien.getText().toString();
                String soDienThoai = edtSoDienThoaiNhanVien.getText().toString();
                String anhDaiDien = edtAnhDaiDien.getText().toString();
                String maDonVi = edtMaDonVi.getText().toString();

                ContentValues values = new ContentValues();
                values.put("maNhanVien", maNhanVien);
                values.put("hoTen", hoTen);
                values.put("chucVu", chucVu);
                values.put("email", email);
                values.put("soDienThoai", soDienThoai);
                values.put("anhDaiDien", anhDaiDien);
                values.put("maDonVi", maDonVi);

                String msg;
                if (db.insert("tblNhanVien", null, values) == -1) {
                    msg = "Fail to Insert Record!";
                } else {
                    msg = "Insert record Successfully";
                    updateListView();
                }
                Toast.makeText(NhanVienActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

        btnUpdateNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maNhanVien = edtMaNhanVien.getText().toString();
                String hoTen = edtHoTen.getText().toString();
                String chucVu = edtChucVu.getText().toString();
                String email = edtEmailNhanVien.getText().toString();
                String soDienThoai = edtSoDienThoaiNhanVien.getText().toString();
                String anhDaiDien = edtAnhDaiDien.getText().toString();
                String maDonVi = edtMaDonVi.getText().toString();

                ContentValues values = new ContentValues();
                values.put("hoTen", hoTen);
                values.put("chucVu", chucVu);
                values.put("email", email);
                values.put("soDienThoai", soDienThoai);
                values.put("anhDaiDien", anhDaiDien);
                values.put("maDonVi", maDonVi);

                String msg = "";
                int result = db.update("tblNhanVien", values, "maNhanVien = ?", new String[]{maNhanVien});
                if (result == 0) {
                    msg = "No record to Update";
                } else {
                    msg = result + " record is updated";
                    updateListView();
                }
                Toast.makeText(NhanVienActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

        btnDeleteNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maNhanVien = edtMaNhanVien.getText().toString();
                String msg = "";
                int result = db.delete("tblNhanVien", "maNhanVien = ?", new String[]{maNhanVien});
                if (result == 0) {
                    msg = "No record to Delete";
                } else {
                    msg = result + " record is deleted";
                    updateListView();
                }
                Toast.makeText(NhanVienActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

        btnQueryNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateListView();
            }
        });
    }

    private void updateListView() {
        nhanVienList.clear();
        Cursor c = db.query("tblNhanVien", null, null, null, null, null, null);

        if (c != null && c.moveToFirst()) {
            int maNhanVienIndex = c.getColumnIndex("maNhanVien");
            int hoTenIndex = c.getColumnIndex("hoTen");
            int chucVuIndex = c.getColumnIndex("chucVu");
            int emailIndex = c.getColumnIndex("email");
            int soDienThoaiIndex = c.getColumnIndex("soDienThoai");
            int anhDaiDienIndex = c.getColumnIndex("anhDaiDien");
            int maDonViIndex = c.getColumnIndex("maDonVi");

            do {
                String maNhanVien = maNhanVienIndex != -1 ? c.getString(maNhanVienIndex) : "";
                String hoTen = hoTenIndex != -1 ? c.getString(hoTenIndex) : "";
                String chucVu = chucVuIndex != -1 ? c.getString(chucVuIndex) : "";
                String email = emailIndex != -1 ? c.getString(emailIndex) : "";
                String soDienThoai = soDienThoaiIndex != -1 ? c.getString(soDienThoaiIndex) : "";
                String anhDaiDien = anhDaiDienIndex != -1 ? c.getString(anhDaiDienIndex) : "";
                String maDonVi = maDonViIndex != -1 ? c.getString(maDonViIndex) : "";

                String data = "Ma Nhan Vien: " + maNhanVien + "\n" +
                        "Ho Ten: " + hoTen + "\n" +
                        "Chuc Vu: " + chucVu + "\n" +
                        "Email: " + email + "\n" +
                        "So Dien Thoai: " + soDienThoai + "\n" +
                        "Anh Dai Dien: " + anhDaiDien + "\n" +
                        "Ma Don Vi: " + maDonVi;
                nhanVienList.add(data);
            } while (c.moveToNext());
        }
        if (c != null) {
            c.close();
        }
        nhanVienAdapter.notifyDataSetChanged();
    }

}

