package com.example.theo.todoappjava;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.theo.todoappjava.TabFragments.CompletedFragment;
import com.example.theo.todoappjava.TabFragments.TodoFragment;

public class TabAdapter extends FragmentPagerAdapter {
    private Context gContext;
    int totalTabs;

    public TabAdapter(Context context, FragmentManager fm, int totalTabs){
        super(fm);
        gContext = context;
        this.totalTabs = totalTabs;
    }

    @Override
    public Fragment getItem(int pos){
        switch (pos){
            case 0:
                TodoFragment todoFragment = new TodoFragment();
                return todoFragment;
            case 1:
                CompletedFragment completedFragment = new CompletedFragment();
                return completedFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount(){
        return totalTabs;
    }
}
