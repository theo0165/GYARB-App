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

    private TodoFragment todoFragment;
    private CompletedFragment completedFragment;

    public TabAdapter(Context context, FragmentManager fm, int totalTabs){
        super(fm);
        gContext = context;
        this.totalTabs = totalTabs;

        this.todoFragment = new TodoFragment();
        this.completedFragment = new CompletedFragment();

        this.todoFragment.setCompletedFragment((this.completedFragment));

    }

    @Override
    public Fragment getItem(int pos){
        switch (pos){
            case 0:
                //this.todoFragment.getTodoListAdapter().setCompletedFragment(this.completedFragment);
                return todoFragment;
            case 1:
                return completedFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount(){
        return totalTabs;
    }

    public TodoFragment getTodoFragment() {
        return todoFragment;
    }
}
