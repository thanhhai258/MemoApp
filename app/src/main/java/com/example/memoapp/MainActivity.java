package com.example.memoapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;


import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, AddMemo.ViDu {
    private Button mBtAdd;
    private Switch mOnOff;
    private ListView mListViewMemo;
    private final String tag = "DatabaseManager";

    //flag switch on off
    protected static int ONFF;
    private List<MemoItem> mMemoItems;
    public static final int REQUEST_CODE_A = 1;
    private CustomAdapter customAdapter;
    public DBManager dbManager = new DBManager(this);


    public ListView getmListViewMemo() {
        return mListViewMemo;
    }
//123
    //cherrypick
    public List<MemoItem> getmMemoItems() {
        return mMemoItems;
    }

    public void setmMemoItems(List<MemoItem> mMemoItems) {
        this.mMemoItems = mMemoItems;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();
        setAdapter();
        mOnOff.setOnCheckedChangeListener(this);
        mBtAdd.setOnClickListener(this);
        mListViewMemo.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                MemoItem memoItem = mMemoItems.get(position);
                int result = dbManager.deleteStudent(memoItem.getmId());
                if (result > 0) {
                    mMemoItems.clear();
                    mMemoItems.addAll(dbManager.getAllMemo());
                    customAdapter.notifyDataSetChanged();


                }
                return false;
            }
        });
        mListViewMemo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MemoItem memoItem = mMemoItems.get(position);
                String setTitle = memoItem.getmTilte();
                String setContent = memoItem.getmContent();
                Intent intent = new Intent(MainActivity.this, SetMemo.class);
                intent.putExtra("x", memoItem.getmId());
                intent.putExtra("title", setTitle);
                intent.putExtra("content", setContent);
                Toast.makeText(MainActivity.this, position + "", Toast.LENGTH_SHORT).show();
                startActivityForResult(intent, REQUEST_CODE_A);
            }
        });
    }
//67899
    private void anhXa() {
        mOnOff = findViewById(R.id.tatmo);
        mBtAdd = findViewById(R.id.addMemo);
        mListViewMemo = findViewById(R.id.lv_memo);
        mMemoItems = dbManager.getAllMemo();
    }

    private void setAdapter() {
        if (customAdapter == null) {
            customAdapter = new CustomAdapter(this, R.layout.item_list_memo, mMemoItems);
            mListViewMemo.setAdapter(customAdapter);
        } else {
            customAdapter.notifyDataSetChanged();
            mListViewMemo.setSelection(customAdapter.getCount() - 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_A) {
            if (resultCode == Activity.RESULT_OK) {
                customAdapter.notifyDataSetChanged();
                //khoi tao lai activity
                reload();
                Log.d(tag, "setMemo tra ve");
            }
            if (resultCode == 2014) {
                customAdapter.notifyDataSetChanged();
                //khoi tao lai activity
                reload();
                Log.d(tag, "AddMemo tra ve");
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //khoi tao lai activity
    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addMemo:
                Intent intent = new Intent(MainActivity.this, AddMemo.class);
                startActivityForResult(intent, REQUEST_CODE_A);
                break;
            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.tatmo:
                if (isChecked) {
                    ONFF = 1;
                } else {
                    ONFF = 2;
                }
        }
    }
//cherrypic2
    //cherrypic3

    @Override
    public void HaiDay() {

    }
}
