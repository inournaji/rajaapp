package com.rajateck.wael.raja.controllers.inAppViews;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.nineoldandroids.animation.Animator;
import com.rajateck.wael.raja.R;
import com.rajateck.wael.raja.controllers.inAppViews.baseActivityPackage.DropDownAnimation;
import com.rajateck.wael.raja.controllers.inAppViews.baseActivityPackage.Menu_adapter;
import com.rajateck.wael.raja.controllers.inAppViews.contactUs.ContactsFragment;
import com.rajateck.wael.raja.controllers.inAppViews.home.HomeFragment;
import com.rajateck.wael.raja.controllers.inAppViews.mobilesScreens.MobileDetailsFragment;
import com.rajateck.wael.raja.controllers.inAppViews.offers.OffersFragment;
import com.rajateck.wael.raja.controllers.inAppViews.otherServices.OtherServiceFragment;
import com.rajateck.wael.raja.controllers.inAppViews.rajaList.RagaListFragment;
import com.rajateck.wael.raja.controllers.inAppViews.searchScreen.SearchFragment;
import com.rajateck.wael.raja.controllers.inAppViews.termsConditions.TermsFragment;
import com.rajateck.wael.raja.controllers.inAppViews.warrantyCheck.WarrantyCheckFragment;
import com.rajateck.wael.raja.delegates.PermissionDelegate;
import com.rajateck.wael.raja.enums.FragmentTags;
import com.rajateck.wael.raja.models.Mobile;
import com.rajateck.wael.raja.models.News;
import com.rajateck.wael.raja.utils.RajaApp;
import com.rajateck.wael.raja.utils.cacheUtils.RajaCacheUtils;

import java.util.ArrayList;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fr.arnaudguyon.tabstacker.TabStacker;

public class BaseActivity extends FragmentActivity implements View.OnClickListener {
    private SearchFragment searchFragment;
    private PermissionDelegate permissionDelegate;
    private FrameLayout fragmentHolderFull;
    private FrameLayout Warranty_frame;
    private FrameLayout Offers_frame;
    private TabStacker tabStacker;
    private TabStacker tabStackerFull;
    private RelativeLayout hardwareRelative;
    private RelativeLayout extentionsRelative;
    private RelativeLayout mobilesRelative;
    private RelativeLayout homeRelative;
    private RelativeLayout otherServiceRelative;
    private DrawerLayout mDrawerLayout;
    private RelativeLayout menu_icon_container;
    private RelativeLayout close_icon_container;
    private RelativeLayout mainView;
    private ListView explore_list;
    private ArrayList<String> s_secondLevels_explore;
    private FrameLayout explore_frame;
    private RelativeLayout search_icon_container;
    private TextView home_icon;
    private TextView mobile_icon;
    private TextView extentions_icon;
    private TextView hardware_icon;
    private TextView other_icon;
    private LinearLayout exp_expand;
    private DropDownAnimation dropDownAnimation;
    private Boolean is_exp = false;
    private FrameLayout home_frame;
    private FrameLayout Feedback_frame;
    private FrameLayout Branches_frame;
    private FrameLayout Term_frame;
    private FrameLayout Join_frame;
    private TextView english;
    private TextView arabic;


    public static Intent getOpenFacebookIntent(Context context) {
        //https://www.facebook.com/Raja-Tec-%D8%B1%D8%AC%D8%A7-%D8%AA%D9%83-291299507950476/
        try {
            context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
//            return new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/<Raja-Tec-%D8%B1%D8%AC%D8%A7-%D8%AA%D9%83-291299507950476>"));
//            return new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/<Raja-Tec-%D8%B1%D8%AC%D8%A7-%D8%AA%D9%83-291299507950476>"));
            return new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/291299507950476"));
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/Raja-Tec-%D8%B1%D8%AC%D8%A7-%D8%AA%D9%83-291299507950476/"));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        findViews();
        initMenuItems();
        initialize_drawer_open_close();
        validateLanguageUI();
        validatePackage();

        if (savedInstanceState != null) {
            tabStacker.restoreInstance(savedInstanceState);
            String selectedTab = tabStacker.getCurrentTabName();
            // do something like highlight the selected tab...
        }

        homeRelative.performClick();
    }

    public void validatePackage() {
        ArrayList<News> newsArrayList = RajaCacheUtils.getCachedNewsList();
        if (newsArrayList != null &&
                newsArrayList.size() > 0) {
            if (newsArrayList.get(0).getMax_width() != null &&
                    newsArrayList.get(0).getMax_width()) {
                otherServiceRelative.setVisibility(View.VISIBLE);
            } else {
                otherServiceRelative.setVisibility(View.GONE);
                System.out.println("BaseActivity.validatePackage: it's null or false ");
            }
        } else {
            otherServiceRelative.setVisibility(View.GONE);
        }
    }


    private void validateLanguageUI() {
        String local = RajaApp.getSharePrefrenceLocale(BaseActivity.this);
        if (local.equalsIgnoreCase("en")) {
            english.setBackgroundResource(R.drawable.rounded_white_home_color);
            english.setTextColor(Color.WHITE);

            arabic.setBackgroundColor(Color.WHITE);


            english.setClickable(false);
            arabic.setClickable(true);
        } else if (local.equalsIgnoreCase("ar")) {
            arabic.setBackgroundResource(R.drawable.rounded_white_home_color);
            arabic.setTextColor(Color.WHITE);

            english.setBackgroundColor(Color.WHITE);

            arabic.setClickable(false);
            english.setClickable(true);
        }
    }

    private void initMenuItems() {
        final Menu_adapter explore_adapter = new Menu_adapter(getApplicationContext(), s_secondLevels_explore, 0);
        explore_list.setAdapter(explore_adapter);
        explore_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    onClickOnTab(FragmentTags.MobileFragment);
                    ChangeHeaderView(FragmentTags.MobileFragment);
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                } else if (position == 1) {
                    onClickOnTab(FragmentTags.ExtensionsFragment);
                    ChangeHeaderView(FragmentTags.ExtensionsFragment);
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                } else if (position == 2) {
                    onClickOnTab(FragmentTags.ExtensionsFragment);
                    ChangeHeaderView(FragmentTags.ExtensionsFragment);
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                } else if (position == 3) {
                    onClickOnTab(FragmentTags.HardWareFragment);
                    ChangeHeaderView(FragmentTags.HardWareFragment);
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                }
            }
        });
    }

    private void findViews() {
        Warranty_frame = (FrameLayout) findViewById(R.id.Warranty_frame);
        Offers_frame = (FrameLayout) findViewById(R.id.Offers_frame);
        search_icon_container = (RelativeLayout) findViewById(R.id.search_icon_container);
        home_icon = (TextView) findViewById(R.id.home_icon);
        mobile_icon = (TextView) findViewById(R.id.mobile_icon);
        extentions_icon = (TextView) findViewById(R.id.extentions_icon);
        hardware_icon = (TextView) findViewById(R.id.hardware_icon);
        other_icon = (TextView) findViewById(R.id.other_icon);
        tabStacker = new TabStacker(getSupportFragmentManager(), R.id.fragmentHolder);
        tabStackerFull = new TabStacker(getSupportFragmentManager(), R.id.fragmentHolderFull);
        hardwareRelative = (RelativeLayout) findViewById(R.id.hardware_relative);
        extentionsRelative = (RelativeLayout) findViewById(R.id.extentions_relative);
        mobilesRelative = (RelativeLayout) findViewById(R.id.mobiles_relative);
        homeRelative = (RelativeLayout) findViewById(R.id.home_relative);
        otherServiceRelative = (RelativeLayout) findViewById(R.id.other_service_relative);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        menu_icon_container = (RelativeLayout) findViewById(R.id.menu_icon_container);
        close_icon_container = (RelativeLayout) findViewById(R.id.close_icon_container);
        mainView = (RelativeLayout) findViewById(R.id.content_frame);
        explore_list = (ListView) findViewById(R.id.explore_list);
        explore_frame = (FrameLayout) findViewById(R.id.explore_frame);
        fragmentHolderFull = (FrameLayout) findViewById(R.id.fragmentHolderFull);
        exp_expand = (LinearLayout) findViewById(R.id.exp_expand);
        dropDownAnimation = new DropDownAnimation(exp_expand);
        home_frame = (FrameLayout) findViewById(R.id.home_frame);
        Feedback_frame = (FrameLayout) findViewById(R.id.Feedback_frame);
        Branches_frame = (FrameLayout) findViewById(R.id.Branches_frame);
        Term_frame = (FrameLayout) findViewById(R.id.Term_frame);
        Join_frame = (FrameLayout) findViewById(R.id.Join_frame);
        english = (TextView) findViewById(R.id.English);
        arabic = (TextView) findViewById(R.id.Arabic);

        s_secondLevels_explore = new ArrayList<>();
        s_secondLevels_explore.add(getString(R.string.mobiles));
        s_secondLevels_explore.add(getString(R.string.accessories));
//        s_secondLevels_explore.add(getString(R.string.smartAccessort));
//        s_secondLevels_explore.add(getString(R.string.hardware));

        hardwareRelative.setOnClickListener(this);
        extentionsRelative.setOnClickListener(this);
        mobilesRelative.setOnClickListener(this);
        homeRelative.setOnClickListener(this);
        otherServiceRelative.setOnClickListener(this);
        search_icon_container.setOnClickListener(this);
        Warranty_frame.setOnClickListener(this);
        Offers_frame.setOnClickListener(this);
        home_frame.setOnClickListener(this);
        Feedback_frame.setOnClickListener(this);
        Branches_frame.setOnClickListener(this);
        Term_frame.setOnClickListener(this);
        Join_frame.setOnClickListener(this);
        english.setOnClickListener(this);
        arabic.setOnClickListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // Keep this first
        if (tabStacker != null) {
            tabStacker.saveInstance(outState);
        }

        if (tabStackerFull != null) {
            tabStackerFull.saveInstance(outState);
        }


        super.onSaveInstanceState(outState);
    }

    public void restoreView(Fragment fragment, View view) {
        tabStacker.restoreView(fragment, view);
        tabStackerFull.restoreView(fragment, view);
    }

    private void onClickOnTab(FragmentTags fragmentTag) {
        if (!tabStacker.switchToTab(fragmentTag.getFragmentName())) {    // Try to switch to the TAB STACK
            // no fragment yet on this stack -> push the 1st fragment of the stack
            switch (fragmentTag) {

                case HomeFragment:
                    HomeFragment homeFragment = HomeFragment.newInstance("", "");
                    tabStacker.replaceFragment(homeFragment, null);  // no animation

                    break;

                case MobileFragment:

                    RagaListFragment mobileListFragment = RagaListFragment.newInstance("", "", fragmentTag);
                    tabStacker.replaceFragment(mobileListFragment, null);  // no animation

                    break;

                case ExtensionsFragment:

                    RagaListFragment extensionsFragment = RagaListFragment.newInstance("", "", fragmentTag);
                    tabStacker.replaceFragment(extensionsFragment, null);  // no animation

                    break;

                case HardWareFragment:

                    RagaListFragment hardwareFragment = RagaListFragment.newInstance("", "", fragmentTag);
                    tabStacker.replaceFragment(hardwareFragment, null);  // no animation

                    break;

                case OTHERFragment:

                    OtherServiceFragment otherServiceFragment = new OtherServiceFragment();
                    tabStacker.replaceFragment(otherServiceFragment, null);  // no animation

                    break;

                case ApplicationFragment:

                    RagaListFragment applicationsFragment = RagaListFragment.newInstance("", "", fragmentTag);
                    tabStacker.replaceFragment(applicationsFragment, null);  // no animation

                    break;

            }
        }
    }

    private void updateHeaderInformation(FragmentTags fragmentTag) {


        if (fragmentTag.equals(FragmentTags.MobileFragment)) {

            hardwareRelative.setBackgroundResource(R.drawable.rounded_red_gray);
            extentionsRelative.setBackgroundResource(R.drawable.rounded_red_gray);
            mobilesRelative.setBackgroundResource(R.drawable.rounded_red_color);
            homeRelative.setBackgroundResource(R.drawable.rounded_red_gray);
            otherServiceRelative.setBackgroundResource(R.drawable.rounded_red_gray);

//            home_icon.setTextColor(Color.parseColor("#ffffff"));
//            mobile_icon.setTextColor(Color.parseColor("#ed1c24"));
//            animateSelection(mobile_icon);
//            extentions_icon.setTextColor(Color.parseColor("#ffffff"));
//            hardware_icon.setTextColor(Color.parseColor("#ffffff"));
//            other_icon.setTextColor(Color.parseColor("#ffffff"));

            home_icon.setTextColor(Color.parseColor("#ed1c24"));
            mobile_icon.setTextColor(Color.parseColor("#ffffff"));
            animateSelection(mobile_icon);
            extentions_icon.setTextColor(Color.parseColor("#ed1c24"));
            hardware_icon.setTextColor(Color.parseColor("#ed1c24"));
            other_icon.setTextColor(Color.parseColor("#ed1c24"));

        } else if (fragmentTag.equals(FragmentTags.HomeFragment)) {

            hardwareRelative.setBackgroundResource(R.drawable.rounded_red_gray);
            extentionsRelative.setBackgroundResource(R.drawable.rounded_red_gray);
            mobilesRelative.setBackgroundResource(R.drawable.rounded_red_gray);
            homeRelative.setBackgroundResource(R.drawable.rounded_red_color);
            otherServiceRelative.setBackgroundResource(R.drawable.rounded_red_gray);

//            home_icon.setTextColor(Color.parseColor("#ed1c24"));
//            animateSelection(home_icon);
//            mobile_icon.setTextColor(Color.parseColor("#ffffff"));
//            extentions_icon.setTextColor(Color.parseColor("#ffffff"));
//            hardware_icon.setTextColor(Color.parseColor("#ffffff"));
//            other_icon.setTextColor(Color.parseColor("#ffffff"));


            home_icon.setTextColor(Color.parseColor("#ffffff"));
            animateSelection(home_icon);
            mobile_icon.setTextColor(Color.parseColor("#ed1c24"));
            extentions_icon.setTextColor(Color.parseColor("#ed1c24"));
            hardware_icon.setTextColor(Color.parseColor("#ed1c24"));
            other_icon.setTextColor(Color.parseColor("#ed1c24"));

        } else if (fragmentTag.equals(FragmentTags.HardWareFragment)) {

            hardwareRelative.setBackgroundResource(R.drawable.rounded_red_color);
            extentionsRelative.setBackgroundResource(R.drawable.rounded_red_gray);
            mobilesRelative.setBackgroundResource(R.drawable.rounded_red_gray);
            homeRelative.setBackgroundResource(R.drawable.rounded_red_gray);
            otherServiceRelative.setBackgroundResource(R.drawable.rounded_red_gray);


//            home_icon.setTextColor(Color.parseColor("#ffffff"));
//            mobile_icon.setTextColor(Color.parseColor("#ffffff"));
//            extentions_icon.setTextColor(Color.parseColor("#ffffff"));
//            hardware_icon.setTextColor(Color.parseColor("#ed1c24"));
//            animateSelection(hardware_icon);
//            other_icon.setTextColor(Color.parseColor("#ffffff"));

            home_icon.setTextColor(Color.parseColor("#ed1c24"));
            mobile_icon.setTextColor(Color.parseColor("#ed1c24"));
            extentions_icon.setTextColor(Color.parseColor("#ed1c24"));
            hardware_icon.setTextColor(Color.parseColor("#ffffff"));
            animateSelection(hardware_icon);
            other_icon.setTextColor(Color.parseColor("#ed1c24"));

        } else if (fragmentTag.equals(FragmentTags.ExtensionsFragment)) {


            hardwareRelative.setBackgroundResource(R.drawable.rounded_red_gray);
            extentionsRelative.setBackgroundResource(R.drawable.rounded_red_color);
            mobilesRelative.setBackgroundResource(R.drawable.rounded_red_gray);
            homeRelative.setBackgroundResource(R.drawable.rounded_red_gray);
            otherServiceRelative.setBackgroundResource(R.drawable.rounded_red_gray);

//            home_icon.setTextColor(Color.parseColor("#ffffff"));
//            mobile_icon.setTextColor(Color.parseColor("#ffffff"));
//            extentions_icon.setTextColor(Color.parseColor("#ed1c24"));
//            animateSelection(extentions_icon);
//            hardware_icon.setTextColor(Color.parseColor("#ffffff"));
//            other_icon.setTextColor(Color.parseColor("#ffffff"));


            home_icon.setTextColor(Color.parseColor("#ed1c24"));
            mobile_icon.setTextColor(Color.parseColor("#ed1c24"));
            extentions_icon.setTextColor(Color.parseColor("#ffffff"));
            animateSelection(extentions_icon);
            hardware_icon.setTextColor(Color.parseColor("#ed1c24"));
            other_icon.setTextColor(Color.parseColor("#ed1c24"));


        } else {
            hardwareRelative.setBackgroundResource(R.drawable.rounded_red_gray);
            extentionsRelative.setBackgroundResource(R.drawable.rounded_red_gray);
            mobilesRelative.setBackgroundResource(R.drawable.rounded_red_gray);
            homeRelative.setBackgroundResource(R.drawable.rounded_red_gray);
            otherServiceRelative.setBackgroundResource(R.drawable.rounded_red_color);

//            home_icon.setTextColor(Color.parseColor("#ffffff"));
//            mobile_icon.setTextColor(Color.parseColor("#ffffff"));
//            extentions_icon.setTextColor(Color.parseColor("#ffffff"));
//            hardware_icon.setTextColor(Color.parseColor("#ffffff"));
//            other_icon.setTextColor(Color.parseColor("#ed1c24"));
//            animateSelection(other_icon);

            home_icon.setTextColor(Color.parseColor("#ed1c24"));
            mobile_icon.setTextColor(Color.parseColor("#ed1c24"));
            extentions_icon.setTextColor(Color.parseColor("#ed1c24"));
            hardware_icon.setTextColor(Color.parseColor("#ed1c24"));
            other_icon.setTextColor(Color.parseColor("#ffffff"));
            animateSelection(other_icon);
        }
    }

    private void animateSelection(final TextView view) {
//        YoYo.with(Techniques.ZoomOut)
//                .duration(300)
//                .withListener(new Animator.AnimatorListener() {
//                    @Override
//                    public void onAnimationStart(Animator animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        YoYo.with(Techniques.ZoomIn)
//                                .duration(300)
//                                .playOn(view);
//                    }
//
//                    @Override
//                    public void onAnimationCancel(Animator animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationRepeat(Animator animation) {
//
//                    }
//                })
//                .interpolate(new AccelerateInterpolator())
//                .playOn(view);
        view.animate().alpha(0.5f).setDuration(300).setListener(new android.animation.Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(android.animation.Animator animation) {

            }

            @Override
            public void onAnimationEnd(android.animation.Animator animation) {
                view.animate().alpha(1).setDuration(300).start();
            }

            @Override
            public void onAnimationCancel(android.animation.Animator animation) {

            }

            @Override
            public void onAnimationRepeat(android.animation.Animator animation) {

            }
        }).start();

    }

    @Override
    public void onClick(View view) {
        if (view == hardwareRelative) {
            onClickOnTab(FragmentTags.HardWareFragment);
            ChangeHeaderView(FragmentTags.HardWareFragment);
        } else if (view == extentionsRelative) {
            onClickOnTab(FragmentTags.ExtensionsFragment);
            ChangeHeaderView(FragmentTags.ExtensionsFragment);
        } else if (view == mobilesRelative) {
            onClickOnTab(FragmentTags.MobileFragment);
            ChangeHeaderView(FragmentTags.MobileFragment);
        } else if (view == homeRelative) {
            onClickOnTab(FragmentTags.HomeFragment);
            ChangeHeaderView(FragmentTags.HomeFragment);
        } else if (view == otherServiceRelative) {
            onClickOnTab(FragmentTags.ApplicationFragment);
//            ChangeHeaderView(FragmentTags.OTHERFragment);
            ChangeHeaderView(FragmentTags.ApplicationFragment);

        } else if (view == search_icon_container) {
            openSearchScreen();
        } else if (view == Warranty_frame) {
            openWarrentyScreen();
        } else if (view == Offers_frame) {
            openOffersScreen();
        } else if (view == home_frame) {
            mDrawerLayout.closeDrawer(Gravity.LEFT);
            onClickOnTab(FragmentTags.HomeFragment);
            ChangeHeaderView(FragmentTags.HomeFragment);
        } else if (view == Feedback_frame) {
            mDrawerLayout.closeDrawer(Gravity.LEFT);
            openMailforFeedback();

        } else if (view == Branches_frame) {
            mDrawerLayout.closeDrawer(Gravity.LEFT);
            openbrached();

        } else if (view == Term_frame) {
            mDrawerLayout.closeDrawer(Gravity.LEFT);
            openTermsScreen();

        } else if (view == Join_frame) {
            mDrawerLayout.closeDrawer(Gravity.LEFT);
            openFacebookPage();
        } else if (view == english) {
            changeLanguage(true);
        } else if (view == arabic) {
            changeLanguage(false);
        }
    }

    private void openTermsScreen() {
        mDrawerLayout.closeDrawer(Gravity.LEFT);
        fragmentHolderFull.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.SlideInUp).interpolate(new AccelerateInterpolator()).duration(400).playOn(fragmentHolderFull);
        TermsFragment termsFragment = new TermsFragment();
        tabStackerFull.addFragment(termsFragment, null);  // no animation
    }

    private void changeLanguage(boolean toEnglish) {
        if (toEnglish) {
            RajaApp.setSharePrefrenceLocale(BaseActivity.this, "en");
            RajaApp.setLocale(BaseActivity.this, "en");

            Intent refresh = getBaseContext().getPackageManager()
                    .getLaunchIntentForPackage(getBaseContext().getPackageName());
            refresh.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(refresh);
            finish();
        } else {
            RajaApp.setSharePrefrenceLocale(BaseActivity.this, "ar");
            RajaApp.setLocale(BaseActivity.this, "ar");

            Intent refresh = getBaseContext().getPackageManager()
                    .getLaunchIntentForPackage(getBaseContext().getPackageName());
            refresh.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(refresh);
            finish();
        }

    }

    private void openFacebookPage() {
        mDrawerLayout.closeDrawer(Gravity.LEFT);
        Intent intent = getOpenFacebookIntent(BaseActivity.this);
        startActivity(intent);
    }

    private void openbrached() {
        Toast.makeText(BaseActivity.this, "Coming soom", Toast.LENGTH_SHORT).show();
    }

    private void openMailforFeedback() {
        mDrawerLayout.closeDrawer(Gravity.LEFT);
        System.out.println("BaseActivity.openSearchScreen :  here to open search.");
        fragmentHolderFull.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.SlideInUp).interpolate(new AccelerateInterpolator()).duration(400).playOn(fragmentHolderFull);
        ContactsFragment contactsFragment = new ContactsFragment();
        tabStackerFull.addFragment(contactsFragment, null);  // no animation
    }

    private void openSearchScreen() {
        System.out.println("BaseActivity.openSearchScreen :  here to open search.");
        fragmentHolderFull.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.SlideInUp).interpolate(new AccelerateInterpolator()).duration(400).playOn(fragmentHolderFull);
        searchFragment = new SearchFragment();
        tabStackerFull.addFragment(searchFragment, null);  // no animation
    }

    private void openWarrentyScreen() {
        mDrawerLayout.closeDrawer(Gravity.LEFT);
        fragmentHolderFull.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.SlideInUp).interpolate(new AccelerateInterpolator()).duration(400).playOn(fragmentHolderFull);
        WarrantyCheckFragment warrantyCheckFragment = new WarrantyCheckFragment();
        tabStackerFull.addFragment(warrantyCheckFragment, null);  // no animation
    }

    private void openOffersScreen() {
        mDrawerLayout.closeDrawer(Gravity.LEFT);
        fragmentHolderFull.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.SlideInUp).interpolate(new AccelerateInterpolator()).duration(400).playOn(fragmentHolderFull);
        OffersFragment offersFragment = new OffersFragment();
        tabStackerFull.addFragment(offersFragment, null);  // no animation
    }

    private void ChangeHeaderView(FragmentTags fragmentTag) {
        updateHeaderInformation(fragmentTag);
    }

    private void initialize_drawer_open_close() {
        menu_icon_container.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                } else {
                    mDrawerLayout.openDrawer(Gravity.LEFT);
                }
                return true;
            }
        });

        // Listener for 'X' container icon
        close_icon_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.closeDrawer(Gravity.LEFT);
            }
        });


        // Make shadow when open menu
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        // For make menu push view when open
        mDrawerLayout.addDrawerListener(new ActionBarDrawerToggle(this, mDrawerLayout, null, R.string.app_name, R.string.app_name) {
            public void onDrawerClosed(View view) {
                supportInvalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                mainView.setTranslationX(slideOffset * drawerView.getWidth());
                mDrawerLayout.bringChildToFront(drawerView);
                mDrawerLayout.requestLayout();

            }
        });


        explore_frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (is_exp) {
//                    exp.setTextColor(Color.parseColor("#3c3c3c"));
                    System.out.println("BaseActivity.onClick 1");
                    dropDownAnimation.collapse();
                    is_exp = false;

                } else {
                    System.out.println("BaseActivity.onClick 2");

                    dropDownAnimation.expand();
                    is_exp = true;
                }

            }
        });
    }

    public void addMobileDetails(Mobile mobile) {

        fragmentHolderFull.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.SlideInUp).interpolate(new AccelerateInterpolator()).duration(400).playOn(fragmentHolderFull);
        MobileDetailsFragment otherServiceFragment = new MobileDetailsFragment(mobile);
        tabStackerFull.addFragment(otherServiceFragment, null);  // no animation

    }

    @Override
    public void onBackPressed() {
        try {
            if (JCVideoPlayer.backPress()) {
                return;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (tabStackerFull != null &&
                tabStackerFull.getCurrentTabSize() > 0) {

            if (tabStackerFull.getCurrentTabSize() == 1) {
                System.out.println("BaseActivity.onBackPressed: here to close.");
                tabStackerFull.clearTabStack();
                YoYo.with(Techniques.SlideOutDown).interpolate(new AccelerateInterpolator()).withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        fragmentHolderFull.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                }).duration(400).playOn(fragmentHolderFull);
            } else {
                System.out.println("BaseActivity.onBackPressed: here to remove the top fragment");
                tabStackerFull.onBackPressed();
            }


        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            JCVideoPlayer.releaseAllVideos();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void addMobileDetailsFromSearch(Mobile mobile) {
        MobileDetailsFragment otherServiceFragment = new MobileDetailsFragment(mobile);
        tabStackerFull.addFragment(otherServiceFragment, null);  // no animation
    }

    public PermissionDelegate getPermissionDelegate() {
        return permissionDelegate;
    }

    public void setPermissionDelegate(PermissionDelegate permissionDelegate) {
        this.permissionDelegate = permissionDelegate;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        System.out.println("Permission: BaseActivity.onRequestPermissionsResult here is the result");
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            permissionDelegate.onAllowPermission();
        } else {
            permissionDelegate.onDenyPermission();
        }
    }

    public void closeLayout() {
        System.out.println("BaseActivity.onBackPressed: here to close.");
        tabStackerFull.clearTabStack();
        YoYo.with(Techniques.SlideOutDown).interpolate(new AccelerateInterpolator()).withListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                fragmentHolderFull.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).duration(400).playOn(fragmentHolderFull);
    }
}
