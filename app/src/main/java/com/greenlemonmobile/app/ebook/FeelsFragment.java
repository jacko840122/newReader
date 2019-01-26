package com.greenlemonmobile.app.ebook;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.common.http.NetReqUtils;
import com.common.http.data.Books_info;
import com.common.http.data.Feellist;
import com.common.kuaxue.utils.FileUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
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
    private List<Feellist.DataBean> mFeellist;
    private FeelAdapter mFeelAdapter;
    private Response.Listener<Feellist> mFeelListListener=new Response.Listener<Feellist>() {
        @Override
        public void onResponse(Feellist response) {
            if(response!=null&&response.getData()!=null&&!response.getData().isEmpty()){
                mFeellist=response.getData();
                bindFeelData();
            }
        }
    };
    private Books_info.DataBean mBook_info;

    private void bindFeelData() {
        mRvFeels.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        mFeelAdapter = new FeelAdapter(mFeellist);
        mRvFeels.setItemAnimator(new DefaultItemAnimator());
        mRvFeels.setAdapter(mFeelAdapter);
    }
    private Unbinder mUnbinder;

    public class FeelAdapter extends RecyclerView.Adapter<FeelAdapter.ViewHolder> implements View.OnClickListener {
        private List<Feellist.DataBean> list;

        public FeelAdapter(List<Feellist.DataBean> list) {
            this.list = list;
        }


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feel_item_sort2, parent, false);
            FeelAdapter.ViewHolder viewHolder = new FeelAdapter.ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(FeelAdapter.ViewHolder holder, int position) {
            String url=list.get(position).getPath()+list.get(position).getName();
            if(!TextUtils.isEmpty(url)){
                ImageLoader.getInstance().displayImage(url,holder.mIcon);
            }

            holder.mTvFeelInfo.setText(mBook_info.getB_name()+"第"+list.get(position).getChapter()+"章");
            holder.mIcon.setTag(list.get(position));
            holder.mIcon.setOnClickListener(this);

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        @Override
        public void onClick(View view) {
            Intent intent=new Intent(getContext(),FeelListActivity.class);

            intent.putExtra("BookInfo",mBook_info);
            intent.putExtra("FilePath",FileUtil.findFileByName(mBook_info.getB_name()).getPath());

            intent.putExtra("feellist", (Serializable) list);
            startActivity(intent);
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView mIcon;
            TextView mTvFeelInfo;
            ViewHolder(View itemView) {
                super(itemView);
                mIcon = itemView.findViewById(R.id.sort_icon);
                mTvFeelInfo= itemView.findViewById(R.id.tv_feel_info);
            }
        }
    }

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
        mBook_info= (Books_info.DataBean) getArguments().getSerializable("book_info");
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
