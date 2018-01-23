package com.example.joshuayingwhat.pigsyloan;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joshuayingwhat.pigsyloan.credit.CreditFragment;
import com.example.joshuayingwhat.pigsyloan.loan.LoanFragment;
import com.example.joshuayingwhat.pigsyloan.mine.MineFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 八戒借贷首页
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 贷款页面布局
     */
    @BindView(R.id.loan_layout)
    View LoanLayout;

    /**
     * 信用卡页面布局
     */
    @BindView(R.id.credit_layout)
    View CreditLayout;

    /**
     * 我的页面布局
     */
    @BindView(R.id.mine_layout)
    View MineLayout;

    /**
     * 在Tab上用于设置显示贷款图标的控件
     */
    @BindView(R.id.loan_image)
    ImageView LoanImage;
    @BindView(R.id.loan_text)
    TextView LoadText;

    /**
     * 在Tab上用于设置显示信用卡图标的控件
     */
    @BindView(R.id.credit_image)
    ImageView CreditImage;
    @BindView(R.id.credit_text)
    TextView CreditText;

    /**
     * 在Tab上用于显示我的图标控件
     */
    @BindView(R.id.mine_image)
    ImageView MineImage;
    @BindView(R.id.mine_text)
    TextView MineText;
    private FragmentManager fragmentManager;

    /**
     * 这个是贷款界面的Fragment布局样式
     */
    public Fragment LoanModules;

    /**
     * 这个是信用卡界面的Framgment布局样式
     */
    public Fragment CreditModules;

    /**
     * 这个是我的界面的Fragment布局样式
     */
    public Fragment MineModules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        //隐藏标题栏
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        ButterKnife.bind(this);

        fragmentManager = getSupportFragmentManager();
        //打开app时，显示就显示贷款的Fragment布局
        setTabSelect(0);
        LoanImage.setImageResource(R.mipmap.loan_blue);
        LoadText.setTextColor(this.getResources().getColor(R.color.material_blue));
        LoanLayout.setOnClickListener(this);
        CreditLayout.setOnClickListener(this);
        MineLayout.setOnClickListener(this);
    }

    //沉浸式状态栏
//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
//            View decorView = getWindow().getDecorView();
//            decorView.setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
//        }
//    }

    @Override
    public void onClick(View view) {
        //点击之前将所有的图标和文字normal
        setTitleImageTextColor();
        switch (view.getId()) {
            case R.id.loan_layout:
                LoanImage.setImageResource(R.mipmap.loan_blue);
                LoadText.setTextColor(this.getResources().getColor(R.color.material_blue));
                setTabSelect(0);
                break;
            case R.id.credit_layout:
                CreditImage.setImageResource(R.mipmap.credit_blue);
                CreditText.setTextColor(this.getResources().getColor(R.color.material_blue));
                setTabSelect(1);
                break;
            case R.id.mine_layout:
                MineImage.setImageResource(R.mipmap.mine_blue);
                MineText.setTextColor(this.getResources().getColor(R.color.material_blue));
                setTabSelect(2);
                break;
        }
    }


    /**
     * 根据传入的index值来设置选中的tab页面
     *
     * @param index 0,
     */
    private void setTabSelect(int index) {
        //开启一个事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //先全部隐藏所有的Fragment
        hideFragments(transaction);
        switch (index) {
            case 0:
                if (LoanModules == null) {
                    //如果LoanModules为空，则创建一个并添加到界面上
                    LoanModules = new LoanFragment();
                    transaction.add(R.id.content_fragment, LoanModules);
                } else {
                    //如果LoanModules不为空，则将它直接显示出来
                    transaction.show(LoanModules);
                }
                break;
            case 1:
                if (CreditModules == null) {
                    //当CreditModules为空时，则创建一个并添加到界面上
                    CreditModules = new CreditFragment();
                    transaction.add(R.id.content_fragment, CreditModules);
                } else {
                    //如果CreditModules不为空时，则将它直接显示出来
                    transaction.show(CreditModules);
                }
                break;
            case 2:
            default:
                if (MineModules == null) {
                    //如果MineModules为空时，则创建一个并添加到界面上
                    MineModules = new MineFragment();
                    transaction.add(R.id.content_fragment, MineModules);
                } else {
                    //当MineModules不为空时，则将它直接显示出来
                    transaction.show(MineModules);
                }
                break;
        }
        //将Fragment事务提交
        transaction.commit();
    }

    /**
     * 将所有的事务都设置为隐藏
     *
     * @param transaction 对Fragment操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (LoanModules != null) {
            transaction.hide(LoanModules);
        }

        if (CreditModules != null) {
            transaction.hide(CreditModules);
        }

        if (MineModules != null) {
            transaction.hide(MineModules);
        }
    }


    //在点击title之前将所有的title normal
    public void setTitleImageTextColor() {
        LoanImage.setImageResource(R.mipmap.loan_normal);
        CreditImage.setImageResource(R.mipmap.credit_normal);
        MineImage.setImageResource(R.mipmap.mine_normal);

        LoadText.setTextColor(android.graphics.Color.parseColor("#8A8A8A"));
        CreditText.setTextColor(android.graphics.Color.parseColor("#8A8A8A"));
        MineText.setTextColor(android.graphics.Color.parseColor("#8A8A8A"));
    }
}
