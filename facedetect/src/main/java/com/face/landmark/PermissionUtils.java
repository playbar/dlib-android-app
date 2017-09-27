package com.face.landmark;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;


public class PermissionUtils {

    private static final int REQUEST_CODE_PERMISSION = 2;
    private static String[] PERMISSIONS_REQ = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };

    public static boolean verifyPermissions(Activity activity) {
        // Check if we have write permission
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if( currentapiVersion >= Build.VERSION_CODES.M ) {
            int write_permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int read_persmission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int camera_permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);

            if (write_permission != PackageManager.PERMISSION_GRANTED ||
                    read_persmission != PackageManager.PERMISSION_GRANTED ||
                    camera_permission != PackageManager.PERMISSION_GRANTED) {
                // We don't have permission so prompt the user
                ActivityCompat.requestPermissions(activity, PERMISSIONS_REQ, REQUEST_CODE_PERMISSION);
                return false;
            } else {
                return true;
            }
        }
        return false;
    }


    /**
     * 检查单个权限
     * @param activity
     * @param permission
     * @param code
     * @return
     */
    public static boolean  checkPermission(Activity activity,String permission,int code){
        return checkPermission(activity,new String[]{permission},code);
    }

    /**
     * 申请没有的权限,已申请的会被过滤,
     * 如果没有过滤,会导致已申请的权限再次申请
     * 返回申请失败,不执行相应的方法
     * @param activity
     * @param permission
     * @param code
     * @return
     */
    public static boolean  checkPermission(Activity activity,String[] permission,int code){
        if(Build.VERSION.SDK_INT >Build.VERSION_CODES.M) {
            ArrayList<String> permissions=new ArrayList<>();
            for (String per :permission){
                if (ContextCompat.checkSelfPermission(activity, per)
                        != PackageManager.PERMISSION_GRANTED) {
                    permissions.add(per);
                }
            }
            if(permissions.size()>0){
                activity.requestPermissions(permissions.toArray(new String[permissions.size()]),code);
                return false;
            }
        }
        return  true;
    }




}
