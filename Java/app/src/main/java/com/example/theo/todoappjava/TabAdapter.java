package com.example.theo.todoappjava;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

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

        this.todoFragment.setCompletedFragment(this.completedFragment);
        //this.completedFragment.setTodoFragment(this.todoFragment);
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
