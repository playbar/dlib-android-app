package com.face.landmark;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceView;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    static {
        System.loadLibrary("opencv_java3");
        System.loadLibrary("manipulation-lib");
        System.loadLibrary("jni-linker");
    }

    private CameraHandler mCameraHandler;
    private FaceTracker mFaceTracker;
    private DrawingSurfaceView mDrawingSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PermissionUtils.verifyPermissions(this);

        copyAssetFilesToSdcard();

        mFaceTracker = new FaceTracker();

        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.camera_surface_view);
        mDrawingSurfaceView = (DrawingSurfaceView) findViewById(R.id.drawing_surface_view);
        if (mDrawingSurfaceView != null) {
            mDrawingSurfaceView.setZOrderOnTop(true);
        }
        mCameraHandler = new CameraHandler(surfaceView, getWindowManager().getDefaultDisplay().getRotation(), mFaceTracker);
        mCameraHandler.setCameraStateListener(mCameraStateListener);
    }

    private void copyAssetFilesToSdcard() {
        byte[] buffer = new byte[1024];
        FileUtils.copyAssetFile(this, "lbpcascade_frontalface.xml", getFilesDir().getAbsolutePath() + "/lbpcascade_frontalface.xml", buffer);
        FileUtils.copyAssetFile(this, "haarcascade_frontalface_alt2.xml", getFilesDir().getAbsolutePath() + "/haarcascade_frontalface_alt2.xml", buffer);
        FileUtils.copyAssetFile(this, "haarcascade_frontalface_alt.xml", getFilesDir().getAbsolutePath() + "/haarcascade_frontalface_alt.xml", buffer);
        FileUtils.copyAssetFile(this, "haarcascade_frontalface_alt_tree.xml", getFilesDir().getAbsolutePath() + "/haarcascade_frontalface_alt_tree.xml", buffer);
//        FileUtils.copyAssetFile(this, "LBF.model", getFilesDir().getAbsolutePath() + "/LBF.model", buffer);
//        FileUtils.copyAssetFile(this, "Regressor.model", getFilesDir().getAbsolutePath() + "/Regressor.model", buffer);
        FileUtils.copyAssetFile(this, "model.bin", getFilesDir().getAbsolutePath() + "/model.bin", buffer);
    }

    public void onClickResetButton(View view) {
        mFaceTracker.reset();
    }

    private CameraHandler.CameraStateListener mCameraStateListener = new CameraHandler.CameraStateListener() {
        @Override
        public void onReadyCamera(boolean success, int width, int height) {
            String trackerFilePath = getFilesDir().getAbsolutePath();
            String detectorFilePath = getFilesDir().getAbsolutePath() + "/haarcascade_frontalface_alt.xml";
            mCameraHandler.startFaceTracking(trackerFilePath, detectorFilePath);
        }

        @Override
        public void onFoundFace(float[] faces) {
            // TODO
            mDrawingSurfaceView.setFacePoints(faces);
        }

        @Override
        public void onUpdateFaceFoundState(boolean foundFace) {

        }
    };
}
