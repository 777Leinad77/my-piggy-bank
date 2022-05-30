package com.example.projetbudget.operation;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.projetbudget.R;

public class SectionsPagerGestionDepense extends FragmentPagerAdapter {


    private final Context mContext;

    public SectionsPagerGestionDepense(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;

    }

    @Override
    public Fragment getItem(int position) {

       /* if (position == 0) {
            return AjoutDepense.newInstance(0);
        }*/
       return GainDepense.newInstance(1);
    }

    @Override
    public int getCount() {
        return 1;
    }
}
