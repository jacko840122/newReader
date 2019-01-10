package com.greenlemonmobile.app.ebook;

import android.os.Bundle;
import android.os.PersistableBundle;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;

public class BookActivity extends AppCompatActivity implements Response.ErrorListener{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.book_main_layout);
        ButterKnife.bind(this);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }
}
