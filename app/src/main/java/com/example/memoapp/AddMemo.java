package com.example.memoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class AddMemo extends AppCompatActivity implements View.OnClickListener {
    private EditText mEdtTilte;
    private EditText mEdtContent;
    private Button btnSave;
    final DBManager dbManager = new DBManager(this);
    private List<MemoItem> memoItems;

    public AddMemo() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_memo);
        initWidget();
        memoItems = dbManager.getAllMemo();
        btnSave.setOnClickListener(this);
    }

    private void initWidget() {
        mEdtTilte = findViewById(R.id.memo_tilte_edt);
        mEdtContent = findViewById(R.id.memo_content_edt);
        btnSave = findViewById(R.id.memo_save_btn);

    }


    @Override
    public void onClick(View v) {
        MemoItem memoItem = createMemoItem();
        if (memoItem != null) {
            dbManager.addMemo(memoItem);
        }
        memoItems.clear();
        memoItems.addAll(dbManager.getAllMemo());

        Intent intent = new Intent();

        setResult(2014, intent);
        finish();
    }

    public MemoItem createMemoItem() {

        String tilte = mEdtTilte.getText().toString();
        String content = mEdtContent.getText().toString();

        MemoItem memoItem = new MemoItem(tilte, content);
        return memoItem;
    }

    public interface ViDu {
        void HaiDay();
    }
}
