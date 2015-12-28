package com.example.caobotao.festivalmsm;

import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnHoverListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.caobotao.festivalmsm.bean.FestivalLab;
import com.example.caobotao.festivalmsm.bean.Msg;
import com.example.caobotao.festivalmsm.fragment.FestivalCategoryFragment;

public class ChooseMsgActivity extends AppCompatActivity {
    private ListView mLvMsgs;
    private FloatingActionButton mFabToSend;
    private ArrayAdapter<Msg> mAdapter;
    private int mFestivalId;
    private LayoutInflater mInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_msg);
        mFestivalId = getIntent().getIntExtra(FestivalCategoryFragment.FESTIVAL_ID,-1);
        mInflater = LayoutInflater.from(this);
        initViews();
        initEvents();
    }

    private void initEvents() {
        mFabToSend.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SendMsgActivity.toActivity(ChooseMsgActivity.this,mFestivalId,-1);
            }
        });


    }

    private void initViews() {
        mLvMsgs = (ListView) findViewById(R.id.id_lv_msgs);
        mFabToSend = (FloatingActionButton) findViewById(R.id.id_fab_toSend);
        mLvMsgs.setAdapter(mAdapter = new ArrayAdapter<Msg>(this,-1, FestivalLab.getInstance().getMsgsByFestivalId(mFestivalId)){
            @Override
            public View getView( final int position, View convertView, ViewGroup parent) {
                ViewHolder viewHolder = null;
                if (convertView == null) {
                    viewHolder = new ViewHolder();
                    convertView = mInflater.inflate(R.layout.item_msg, parent, false);
                    viewHolder.content = (TextView) convertView.findViewById(R.id.id_tv_content);
                    viewHolder.toSend = (Button) convertView.findViewById(R.id.id_btn_toSend);
                    convertView.setTag(viewHolder);
                } else {
                    viewHolder = (ViewHolder) convertView.getTag();
                }
                viewHolder.content.setText("\t\t" + getItem(position).getContent());
                viewHolder.toSend.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SendMsgActivity.toActivity(ChooseMsgActivity.this,mFestivalId,getItem(position).getId());
                    }
                });


                return convertView;
            }
        });
    }

    class ViewHolder {
        TextView content;
        Button toSend;
    }
}
