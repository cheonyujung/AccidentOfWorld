package com.example.cheonyujung.accidentofworld.parallaxviewpage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.SparseArrayCompat;
import android.util.Log;
import android.view.ViewGroup;

/**
 * Created by ohyongtaek on 16. 5. 26..
 */
public class CustomFragmentPagerAdapter extends FragmentPagerAdapter {
    private SparseArrayCompat<ScrollTabHolder> mScrollTabHolders;
    private int mNumFragments;
    public CustomFragmentPagerAdapter(FragmentManager fm, int fragmentCount) {
        super(fm);
        this.mScrollTabHolders = new SparseArrayCompat<>();
        this.mNumFragments = fragmentCount;
    }

    @Override
    public Fragment getItem(int position) {
        return (Fragment) mScrollTabHolders.valueAt(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object object = super.instantiateItem(container, position);
        mScrollTabHolders.put(position,(ScrollTabHolder) object);

        return  object;
    }

    @Override
    public int getCount() {
        return mNumFragments;
    }

    public SparseArrayCompat<ScrollTabHolder> getmScrollTabHolders() {
        return mScrollTabHolders;
    }

}
