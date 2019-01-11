package com.greenlemonmobile.app.ebook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.common.http.NetReqUtils;
import com.common.http.data.Books_info;
import com.common.http.data.Feellist;

import java.io.Serializable;
import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.android.volley.Request.Method.POST;
import static com.common.http.NetReqUtils.ACTION_GET_FEEL_LIST;

public class FeelsFragment extends Fragment implements Response.ErrorListener {


    @BindView(R.id.rv_feels)
    RecyclerView mRvFeels;
    private String mBook_ID;
    private Feellist mFeellist;
    private Response.Listener<Feellist> mFeelListListener=new Response.Listener<Feellist>() {
        @Override
        public void onResponse(Feellist response) {
            mFeellist=response;
        }
    };
    private Unbinder mUnbinder;

    private void getData(){
        try{
            HashMap<String, Object> extraParams =new HashMap<>();
            extraParams.put("bid",Integer.valueOf(mBook_ID));
            NetReqUtils.addGsonRequest(getContext(),POST,this,null,extraParams,
                    ACTION_GET_FEEL_LIST,Feellist.class,mFeelListListener,this);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBook_ID=getArguments().getString("book_id");
        getData();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.main_feel_layout, container,false);
        mUnbinder =ButterKnife.bind(this,root);
        return root;
    }

    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        if(getContext()!=null)
            Toast.makeText(getContext(),"获取数据有异常!",Toast.LENGTH_SHORT).show();
        error.printStackTrace();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mUnbinder!=null){
            mUnbinder.unbind();
        }

    }
}
