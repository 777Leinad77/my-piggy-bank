package com.example.projetbudget.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.example.projetbudget.ui.main.PlaceholderFragment;
import com.example.projetbudget.ui.main.PlaceholderFragment1;
import com.example.projetbudget.ui.main.PlaceholderFragment2;
import com.example.projetbudget.R;
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3};
    private final Context mContext;
    PlaceholderFragment placeholderFragment;
    PlaceholderFragment1 placeholderFragment1;
    PlaceholderFragment2 placeholderFragment2;
    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;

    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        if (position == 0) {
            return placeholderFragment.newInstance(0);
        } else if (position == 1) {
            return placeholderFragment1.newInstance(1);
        }
        return placeholderFragment2.newInstance(2);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }
}
