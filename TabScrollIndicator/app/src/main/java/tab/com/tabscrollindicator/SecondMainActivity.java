package tab.com.tabscrollindicator;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.shizhefei.fragment.LazyFragment;
import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.slidebar.ScrollBar;
import java.util.ArrayList;
import java.util.List;

public class SecondMainActivity extends LazyFragment {
    ViewPager secondViewPager;
    FixedIndicatorView secondIndicator;
    List<Fragment> list = null;
    IndicatorViewPager indicatorViewPager;
    String tabName;
    int position;

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        position = getArguments().getInt("position") + 1;
        tabName = getArguments().getString("string");
        setContentView(R.layout.second_main_layout);
        //这个页面是indicator+viewpager组成的一个fragment
        secondIndicator = (FixedIndicatorView) findViewById(R.id.secondIndicator);
        secondViewPager = (ViewPager) findViewById(R.id.secondViewPager);
        list = new ArrayList<Fragment>();
        list.clear();
        switch (position) {
            case 1:
                SecondIndex1Fragment secondIndex1Fragment = new SecondIndex1Fragment();
                list.add(secondIndex1Fragment);
                SecondIndex2Fragment secondIndex2Fragment = new SecondIndex2Fragment();
                list.add(secondIndex2Fragment);
                SecondIndex3Fragment secondIndex3Fragment = new SecondIndex3Fragment();
                list.add(secondIndex3Fragment);
                SecondIndex4Fragment secondIndex4Fragment = new SecondIndex4Fragment();
                list.add(secondIndex4Fragment);
                break;
            case 2:
                SecondFind1Fragment secondFind1Fragment = new SecondFind1Fragment();
                list.add(secondFind1Fragment);
                SecondFind2Fragment secondFind2Fragment = new SecondFind2Fragment();
                list.add(secondFind2Fragment);
                SecondFind3Fragment secondFind3Fragment = new SecondFind3Fragment();
                list.add(secondFind3Fragment);
                SecondFind4Fragment secondFind4Fragment = new SecondFind4Fragment();
                list.add(secondFind4Fragment);
                break;
            case 4:
                SecondMe1Fragment secondMe1Fragment = new SecondMe1Fragment();
                list.add(secondMe1Fragment);
                SecondMe2Fragment secondMe2Fragment = new SecondMe2Fragment();
                list.add(secondMe2Fragment);
                SecondMe3Fragment secondMe3Fragment = new SecondMe3Fragment();
                list.add(secondMe3Fragment);
                SecondMe4Fragment secondMe4Fragment = new SecondMe4Fragment();
                list.add(secondMe4Fragment);
                break;
        }
        indicatorViewPager = new IndicatorViewPager(secondIndicator, secondViewPager);
        //设置indicator的样式，这里是下划线，还有颜色高度方位
        secondIndicator.setScrollBar(new ColorBar(getContext(),
                Color.parseColor("#7EC0EE"), 5, ScrollBar.Gravity.BOTTOM));
        secondViewPager.setOffscreenPageLimit(4);
        //在fragment中用getChild
        indicatorViewPager.setAdapter(new MyAdapter(getChildFragmentManager()));
    }


    public class MyAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {
        public MyAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.tab_view, container, false);
            }
            TextView textView = (TextView) convertView;
            textView.setText(tabName + (position + 1));
            textView.setTextSize(16);
            textView.setTextColor(Color.parseColor("#787878"));
            return convertView;
        }

        @Override
        public Fragment getFragmentForPage(int position) {
            return list.get(position);
        }
    }
}
