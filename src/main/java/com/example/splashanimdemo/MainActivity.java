package com.example.splashanimdemo;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import com.prolificinteractive.parallaxpager.OnViewCreatedListener;
import com.prolificinteractive.parallaxpager.ParallaxContainer;
import com.prolificinteractive.parallaxpager.ParallaxContextWrapper;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    @Override
    protected void attachBaseContext(Context newBase) {
        //ParallaxPager and Calligraphy don't seem to play nicely together
        //The solution was to add a listener for view creation events so that we can hook up
        // Calligraphy to our view creation calls instead.
//        ParallaxPager的似乎不好好一起玩
//        解决方案是为视图创建事件添加侦听器，以便我们可以将视差挂在视图创建调用中。
        super.attachBaseContext(
                new ParallaxContextWrapper(newBase, new OnViewCreatedListener() {
                    @Override
                    public View onViewCreated(View view, Context context, AttributeSet attrs) {
                        return view;
                    }
                })
        );
    }

    IndicatorView mIndicatorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIndicatorView = (IndicatorView) findViewById(R.id.indicatorView);

        ParallaxContainer parallaxContainer =
                (ParallaxContainer) findViewById(R.id.parallax_container);

        //是否循环
        parallaxContainer.setLooping(false);

        parallaxContainer.setupChildren(getLayoutInflater(),
                R.layout.parallax_view_0,
                R.layout.parallax_view_1,
                R.layout.parallax_view_2,
                R.layout.parallax_view_3,
                R.layout.parallax_view_4,
                R.layout.parallax_view_5,
                R.layout.parallax_view_6);

        parallaxContainer.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (positionOffset > 0.5) {
            mIndicatorView.setSelect(position + 1);
        } else {
            mIndicatorView.setSelect(position);
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
