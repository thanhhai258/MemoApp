package com.example.memoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class SetMemo extends AppCompatActivity implements View.OnClickListener {
    private EditText mSetTitle, mSetContent;
    private List<MemoItem> mMemoItems;
    int vitri;
    Button mLuu;
    DBManager dbManager = new DBManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_memo);
        mMemoItems = dbManager.getAllMemo();
        anhxa();
        getData();
        mLuu.setOnClickListener(this);
    }
//lay du lieu gui sang tu MainActivity
    private void getData() {
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");
        vitri=intent.getIntExtra("x",123);
        mSetTitle.setText(title);
        mSetContent.setText(content);
    }
    private void anhxa() {
        mSetTitle = findViewById(R.id.memo_tilte_edt_set);
        mLuu = findViewById(R.id.memo_save_btn_set);
        mSetContent = findViewById(R.id.memo_content_edt_set);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.memo_save_btn_set:
                MemoItem memoItem=new MemoItem();
                memoItem.setmId(vitri);
                memoItem.setmTilte(mSetTitle.getText() + " ");
                memoItem.setmContent(mSetContent.getText() + " ");
                int result = dbManager.updateMemo(memoItem);
                if (result > 0) {
                    mMemoItems.clear();
                    mMemoItems.addAll(dbManager.getAllMemo());
                }
                Intent intent = new Intent();
                setResult(RESULT_OK,intent);
                finish();
                break;
        }
    }
}
