package com.donnipra.dicodingsub4.util;

/*
 * Created by donni.
 * Last modified 6/12/18 10:21 AM.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.donnipra.dicodingsub4.MainActivity;
import com.donnipra.dicodingsub4.ui.main.FavoriteFragment;
import com.donnipra.dicodingsub4.ui.main.NowPlayingFragment;
import com.donnipra.dicodingsub4.ui.main.UpcomingFragment;

public class ATabPager extends FragmentPagerAdapter {
    private static final int NUM_ITEMS = 3;

    public ATabPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new NowPlayingFragment();

            case 1:
                return new UpcomingFragment();

            case 2:
                return new FavoriteFragment();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }
}
