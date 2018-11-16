package tab.com.tabscrollindicator;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;
import com.shizhefei.view.viewpager.SViewPager;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    SViewPager SViewPager;
    FixedIndicatorView FirstIndicator;
    IndicatorViewPager indicatorViewPager;
    List<Fragment> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_main_layout);
        //SViewPager是用于既有ViewPager还有indicator的页面,和ViewPager不一样
        SViewPager = (SViewPager) findViewById(R.id.firstSViewPager);
        FirstIndicator = (FixedIndicatorView) findViewById(R.id.firstIndicator);
        indicatorViewPager = new IndicatorViewPager(FirstIndicator, SViewPager);
        list = new ArrayList<Fragment>();
        SecondMainActivity secondFragment1 = new SecondMainActivity();
        list.add(secondFragment1);
        SecondMainActivity secondFragment2 = new SecondMainActivity();
        list.add(secondFragment2);
        FirstTab3Activity firstTab3Activity = new FirstTab3Activity();
        list.add(firstTab3Activity);
        SecondMainActivity secondFragment4 = new SecondMainActivity();
        list.add(secondFragment4);
        indicatorViewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        SViewPager.setCanScroll(false);//viewPager页面是否能切换
        SViewPager.setOffscreenPageLimit(4);//viewPager一次性能缓存的左右两边页面4
        FirstIndicator.setOnTransitionListener(new OnTransitionTextListener().
                setColor(Color.RED, Color.GRAY));//设置indicator的颜色样式
        //这里是没有下划线，没有背景图片，只是点击时和未被点击时的变色
    }

    public class MyAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {
        public String[] tabName = {"主页", "发现", "消息", "个人中心"};
        int[] tabIcons = {R.drawable.tab1, R.drawable.tab2, R.drawable.tab3, R.drawable.tab4};

        public MyAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return tabName.length;
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.tab_view, container, false);
            }
            TextView textView = (TextView) convertView;
            textView.setTextSize(18);
            textView.setText(tabName[position]);
            textView.setTextColor(Color.parseColor("#383838"));
            //设置图片和它位于的方位，这里是上面
            textView.setCompoundDrawablesWithIntrinsicBounds(0, tabIcons[position], 0, 0);
            return convertView;
        }

        @Override
        public Fragment getFragmentForPage(int position) {
            Bundle bundle = new Bundle();//传递数据
            bundle.putInt("position", position);
            bundle.putString("string", tabName[position]);
            list.get(position).setArguments(bundle);
            return list.get(position);
        }
    }
}
