//
// DO NOT EDIT THIS FILE.
// Generated using AndroidAnnotations 4.0.0.
// 
// You can create a larger work that contains this file and distribute that work under terms of your choice.
//


package com.tzutalin.dlibtest;

import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import com.dexafree.materialList.card.Card;
import com.dexafree.materialList.view.MaterialListView;
import com.face.landmark.R;

import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.builder.ActivityIntentBuilder;
import org.androidannotations.api.builder.PostActivityStarter;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class MainActivity_
    extends MainActivity
    implements HasViews, OnViewChangedListener
{
    private final OnViewChangedNotifier onViewChangedNotifier_ = new OnViewChangedNotifier();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        OnViewChangedNotifier previousNotifier = OnViewChangedNotifier.replaceNotifier(onViewChangedNotifier_);
        init_(savedInstanceState);
        super.onCreate(savedInstanceState);
        OnViewChangedNotifier.replaceNotifier(previousNotifier);
        setContentView(R.layout.activity_main);
    }

    private void init_(Bundle savedInstanceState) {
        OnViewChangedNotifier.registerOnViewChangedListener(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    @Override
    public void setContentView(View view, LayoutParams params) {
        super.setContentView(view, params);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    public static MainActivity_.IntentBuilder_ intent(Context context) {
        return new MainActivity_.IntentBuilder_(context);
    }

    public static MainActivity_.IntentBuilder_ intent(android.app.Fragment fragment) {
        return new MainActivity_.IntentBuilder_(fragment);
    }

    public static MainActivity_.IntentBuilder_ intent(android.support.v4.app.Fragment supportFragment) {
        return new MainActivity_.IntentBuilder_(supportFragment);
    }

    @Override
    public void onViewChanged(HasViews hasViews) {
        this.mListView = ((MaterialListView) hasViews.findViewById(R.id.material_listview));
        this.mFabActionBt = ((FloatingActionButton) hasViews.findViewById(R.id.fab));
        this.mFabCamActionBt = ((FloatingActionButton) hasViews.findViewById(R.id.fab_cam));
        this.mToolbar = ((Toolbar) hasViews.findViewById(R.id.toolbar));
        if (this.mFabActionBt!= null) {
            this.mFabActionBt.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View view) {
                    MainActivity_.this.launchGallery();
                }
            }
            );
        }
        if (this.mFabCamActionBt!= null) {
            this.mFabCamActionBt.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View view) {
                    MainActivity_.this.launchCameraPreview();
                }
            }
            );
        }
        setupUI();
    }

    @Override
    protected void addCardListView(final List<Card> cardrets) {
        UiThreadExecutor.runTask("", new Runnable() {

            @Override
            public void run() {
                MainActivity_.super.addCardListView(cardrets);
            }
        }
        , 0L);
    }

    @Override
    protected void showDiaglog() {
        UiThreadExecutor.runTask("", new Runnable() {

            @Override
            public void run() {
                MainActivity_.super.showDiaglog();
            }
        }
        , 0L);
    }

    @Override
    protected void dismissDialog() {
        UiThreadExecutor.runTask("", new Runnable() {

            @Override
            public void run() {
                MainActivity_.super.dismissDialog();
            }
        }
        , 0L);
    }

    @NonNull
    @Override
    protected void runDetectAsync(
        @NonNull
        final String imgPath) {
        BackgroundExecutor.execute(new BackgroundExecutor.Task("", 0L, "") {

            @Override
            public void execute() {
                try {
                    MainActivity_.super.runDetectAsync(imgPath);
                } catch (final Throwable e) {
                    Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), e);
                }
            }
        }
        );
    }

    public static class IntentBuilder_
        extends ActivityIntentBuilder<MainActivity_.IntentBuilder_>
    {
        private android.app.Fragment fragment_;
        private android.support.v4.app.Fragment fragmentSupport_;

        public IntentBuilder_(Context context) {
            super(context, MainActivity_.class);
        }

        public IntentBuilder_(android.app.Fragment fragment) {
            super(fragment.getActivity(), MainActivity_.class);
            fragment_ = fragment;
        }

        public IntentBuilder_(android.support.v4.app.Fragment fragment) {
            super(fragment.getActivity(), MainActivity_.class);
            fragmentSupport_ = fragment;
        }

        @Override
        public PostActivityStarter startForResult(int requestCode) {
            if (fragmentSupport_!= null) {
                fragmentSupport_.startActivityForResult(intent, requestCode);
            } else {
                if (fragment_!= null) {
                    fragment_.startActivityForResult(intent, requestCode, lastOptions);
                } else {
                    if (context instanceof Activity) {
                        Activity activity = ((Activity) context);
                        ActivityCompat.startActivityForResult(activity, intent, requestCode, lastOptions);
                    } else {
                        context.startActivity(intent, lastOptions);
                    }
                }
            }
            return new PostActivityStarter(context);
        }
    }
}
