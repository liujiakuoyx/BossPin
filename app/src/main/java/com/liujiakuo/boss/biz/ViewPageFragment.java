package com.liujiakuo.boss.biz;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.liujiakuo.boss.R;
import com.liujiakuo.boss.base.BaseFragment;
import com.liujiakuo.boss.biz.position.PositionListFragment;
import com.liujiakuo.boss.biz.profile.ProfileFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 佳阔 on 2019/3/12.
 */

public class ViewPageFragment extends BaseFragment implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private ViewPager mViewPager;
    private View mTab1, mTab2, mTab3, mTab4;
    private ImageView mTabImage1, mTabImage2, mTabImage3, mTabImage4;
    private TextView mTabTextView1, mTabTextView2, mTabTextView3, mTabTextView4;

    private MyPagerAdapter mPagerAdapter;

    @Override

    protected int getContentView() {
        return R.layout.main_page_fragment;
    }

    @Override
    protected void initViews(View view) {
        mViewPager = view.findViewById(R.id.my_view_page);
        mTab1 = view.findViewById(R.id.tab_layout1);
        mTab2 = view.findViewById(R.id.tab_layout2);
        mTab3 = view.findViewById(R.id.tab_layout3);
        mTab4 = view.findViewById(R.id.tab_layout4);
        mTabImage1 = view.findViewById(R.id.tab_image1);
        mTabImage2 = view.findViewById(R.id.tab_image2);
        mTabImage3 = view.findViewById(R.id.tab_image3);
        mTabImage4 = view.findViewById(R.id.tab_image4);
        mTabTextView1 = view.findViewById(R.id.tab_text1);
        mTabTextView2 = view.findViewById(R.id.tab_text2);
        mTabTextView3 = view.findViewById(R.id.tab_text3);
        mTabTextView4 = view.findViewById(R.id.tab_text4);
        mTab1.setOnClickListener(this);
        mTab2.setOnClickListener(this);
        mTab3.setOnClickListener(this);
        mTab4.setOnClickListener(this);
        initViewPager();
    }

    private void initViewPager() {
        mPagerAdapter = new MyPagerAdapter(getActivity().getSupportFragmentManager());
        List<BaseFragment> fragments = new ArrayList<>(4);
        fragments.add(new PositionListFragment());
        fragments.add(new PositionListFragment());
        fragments.add(new PositionListFragment());
        fragments.add(new ProfileFragment());
        mPagerAdapter.setFragments(fragments);
        mViewPager.setAdapter(mPagerAdapter);
        mPagerAdapter.notifyDataSetChanged();
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setCurrentItem(0);
    }

    private void onSelected(int position) {
        resetTab();
        switch (position) {
            case 0:
                mTabImage1.setImageResource(R.drawable.position_selected);
                mTabTextView1.setTextColor(getResources().getColor(R.color.selected_text_color));
                break;
            case 1:
                mTabImage2.setImageResource(R.drawable.qiuzhi_selected);
                mTabTextView2.setTextColor(getResources().getColor(R.color.selected_text_color));
                break;
            case 2:
                mTabImage3.setImageResource(R.drawable.toudi_selected);
                mTabTextView3.setTextColor(getResources().getColor(R.color.selected_text_color));
                break;
            case 3:
                mTabImage4.setImageResource(R.drawable.me_selected);
                mTabTextView4.setTextColor(getResources().getColor(R.color.selected_text_color));
                break;
        }
    }

    private void resetTab() {
        mTabImage1.setImageResource(R.drawable.position_no_selected);
        mTabImage2.setImageResource(R.drawable.qiuzhi_no_selected);
        mTabImage3.setImageResource(R.drawable.toudi_no_selected);
        mTabImage4.setImageResource(R.drawable.me_no_selected);
        mTabTextView1.setTextColor(getResources().getColor(R.color.not_selected_text_color));
        mTabTextView2.setTextColor(getResources().getColor(R.color.not_selected_text_color));
        mTabTextView3.setTextColor(getResources().getColor(R.color.not_selected_text_color));
        mTabTextView4.setTextColor(getResources().getColor(R.color.not_selected_text_color));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tab_layout1:
                mViewPager.setCurrentItem(0);
                onSelected(0);
                break;
            case R.id.tab_layout2:
                mViewPager.setCurrentItem(1);
                onSelected(1);
                break;
            case R.id.tab_layout3:
                mViewPager.setCurrentItem(2);
                onSelected(2);
                break;
            case R.id.tab_layout4:
                mViewPager.setCurrentItem(3);
                onSelected(3);
                break;
        }
    }


    class MyPagerAdapter extends FragmentPagerAdapter {
        List<BaseFragment> fragments = new ArrayList<>();

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void setFragments(List<BaseFragment> fragments) {
            this.fragments.clear();
            this.fragments.addAll(fragments);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        onSelected(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
