package com.abstech.absreporting;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyTaskAdapter extends FragmentPagerAdapter {
    @StringRes
    private static final int[] TAB_TITLES =
            new int[] { R.string.title_todaytask, R.string.title_uptask,R.string.title_comptask };
    private Context myContext;
    int totalTabs;
    public MyTaskAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                TodayTaskFragment todayTaskFragment = new TodayTaskFragment();
                return todayTaskFragment;
            case 1:
                UpcomingTaskFragment upcomingTaskFragment = new UpcomingTaskFragment();
                return upcomingTaskFragment;
            case 2:
                CompletedTaskFragment completedTaskFragment = new CompletedTaskFragment();
                return completedTaskFragment;
            default:
                return null;
        }
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return myContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 3;
    }
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
