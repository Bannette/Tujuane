package com.moringaschool.tujuane;

import android.content.Context;
import android.widget.ArrayAdapter;

public class MyFriendsArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mFriends;
    private String[] mDescription;

    public MyFriendsArrayAdapter(Context mContext, int resource, String[] mFriends, String[] mDescription){
        super(mContext, resource);
        this.mContext = mContext;
        this.mDescription = mDescription;
        this.mFriends = mFriends;
    }

    @Override
    public Object getItem(int position){
        String friend = mFriends[position];
        String description = mDescription[position];
        return String.format("%s \nServes great: %s", friend, description);
    }

    @Override
    public int getCount(){
        return mDescription.length;
    }
}
