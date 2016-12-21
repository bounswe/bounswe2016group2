package com.example.bounswegroup2.eatright;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.example.bounswegroup2.Utils.OnBackPressedListener;

/**
 * Created by yigitozgumus on 12/20/16.
 */
public class BaseBackPressedListener implements OnBackPressedListener {
    private final FragmentActivity activity;

    /**
     * Instantiates a new Base back pressed listener.
     *
     * @param activity the activity
     */
    public BaseBackPressedListener(FragmentActivity activity) {
        this.activity = activity;
    }

    @Override
    public void doBack() {
        activity.getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

    }
}

