package com.example.caobotao.festivalmsm.fragment;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.caobotao.festivalmsm.R;
import com.example.caobotao.festivalmsm.bean.SendedMsg;
import com.example.caobotao.festivalmsm.db.SmsProvider;
import com.example.caobotao.festivalmsm.view.FlowLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by caobotao on 15/12/28.
 */
public class SmsHistoryFragment extends ListFragment {
    private static final int LOADER_ID = 1;
    private LayoutInflater mInflater;
    private CursorAdapter mCursorAdapter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("CBT", "onViewCreated-->");
        mInflater = LayoutInflater.from(getActivity());
        initLoader();
        setupListAdapter();
    }

    private void setupListAdapter() {
        mCursorAdapter = new CursorAdapter(getActivity(), null, false) {

            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                Log.e("CBT", "newView-->");
                View view = mInflater.inflate(R.layout.item_sended_msg, parent, false);
                return view;
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                Log.e("CBT", "bindView-->");
                TextView msg = (TextView) view.findViewById(R.id.id_tv_msg);
                FlowLayout fl = (FlowLayout) view.findViewById(R.id.id_fl_contacts);
                TextView fes = (TextView) view.findViewById(R.id.id_tv_fes);
                TextView date = (TextView) view.findViewById(R.id.id_tv_date);

                msg.setText(cursor.getString(cursor.getColumnIndex(SendedMsg.COLUMN_MSG)));
                fes.setText(cursor.getString(cursor.getColumnIndex(SendedMsg.COLUMN_FES_NAME)));

                long dateVal = cursor.getLong(cursor.getColumnIndex(SendedMsg.COLUMN_DATE));
                date.setText(parseDate(dateVal));

                String names = cursor.getString(cursor.getColumnIndex(SendedMsg.COLUMN_NAMES));
                Log.e("CBT","namesBefore-->"+names);
                if (TextUtils.isEmpty(names)) {
                    return;
                }
                Log.e("CBT","namesAfter-->"+names);
                fl.removeAllViews();
                for (String name : names.split(":")) {
                    addTag(name,fl);
                }
            }
        };
        setListAdapter(mCursorAdapter);
    }

    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private String parseDate(long dateVal) {
        return df.format(dateVal);
    }

    private void addTag(String name, FlowLayout fl) {
        TextView tv = (TextView) mInflater.inflate(R.layout.tag, fl, false);
        tv.setText(name);
        fl.addView(tv);
    }

    private void initLoader() {

        getLoaderManager().initLoader(LOADER_ID, null, new LoaderCallbacks<Cursor>(){

            @Override
            public Loader<Cursor> onCreateLoader(int id, Bundle args) {
                Log.e("CBT","onCreateLoader-->");
                CursorLoader loader = new CursorLoader(getActivity(), SmsProvider.URI_SMS_ALL, null, null,null,null);
                return loader;
            }

            @Override
            public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
                Log.e("CBT","onLoadFinished-->");
                if (loader.getId() == LOADER_ID) {
                    Log.e("CBT","swapCursor()-->");
                    mCursorAdapter.swapCursor(data);
                }
            }

            @Override
            public void onLoaderReset(Loader<Cursor> loader) {
                mCursorAdapter.swapCursor(null);
            }
        });
    }
}
