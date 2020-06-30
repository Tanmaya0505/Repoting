package com.abstech.absreporting;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ReportStatisticsAdapter extends FragmentPagerAdapter {
    @StringRes
    private static final int[] TAB_TITLES =
            new int[] { R.string.title_week, R.string.title_month };
    private Context myContext;
    int totalTabs;

    public ReportStatisticsAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                WeeklyReportFragment weeklyReportFragment = new WeeklyReportFragment();
                return weeklyReportFragment;
            case 1:
                MonthlyReportFragment monthlyReportFragment = new MonthlyReportFragment();
                return monthlyReportFragment;
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
        return 2;
    }
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
