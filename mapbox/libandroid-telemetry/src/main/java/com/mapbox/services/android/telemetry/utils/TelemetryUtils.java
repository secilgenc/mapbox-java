package com.mapbox.services.android.telemetry.utils;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.res.Configuration;
import android.location.Location;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.mapbox.services.android.telemetry.constants.TelemetryConstants;
import com.mapbox.services.android.telemetry.permissions.PermissionsManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Static utilities to complete the event data.
 */
public class TelemetryUtils {

  private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ",
    TelemetryConstants.DEFAULT_LOCALE);

  public static String generateCreateDate() {
    return dateFormat.format(new Date());
  }

  public static Location buildLocation(double longitude, double latitude) {
    Location location = new Location(TelemetryUtils.class.getSimpleName());
    location.setLongitude(longitude);
    location.setLatitude(latitude);
    return location;
  }

  public static SharedPreferences getSharedPreferences(Context context) {
    return context.getSharedPreferences(
      TelemetryConstants.MAPBOX_SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE);
  }

  public static String getApplicationIdentifier(Context context) {
    try {
      PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
      return String.format(TelemetryConstants.DEFAULT_LOCALE, "%s/%s/%s", context.getPackageName(),
        packageInfo.versionName, packageInfo.versionCode);
    } catch (Exception exception) {
      return "";
    }
  }

  public static String getOrientation(Context context) {
    switch (context.getResources().getConfiguration().orientation) {
      case Configuration.ORIENTATION_LANDSCAPE:
        return "Landscape";
      case Configuration.ORIENTATION_PORTRAIT:
        return "Portrait";
      default:
        return "";
    }
  }

  public static String getApplicationState(Context context) {
    ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
    List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
    if (appProcesses == null) {
      return "";
    }

    String packageName = context.getPackageName();
    for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
      if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
        && appProcess.processName.equals(packageName)) {
        return "Foreground";
      }
    }

    return "Background";
  }

  public static float getAccesibilityFontScaleSize(Context context) {
    // Small = 0.85; Normal = 1.0; Large = 1.15; Huge = 1.3
    return context.getResources().getConfiguration().fontScale;
  }

  public static String getCellularCarrier(Context context) {
    TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    String carrierName = manager.getNetworkOperatorName();
    if (TextUtils.isEmpty(carrierName)) {
      carrierName = "";
    }

    return carrierName;
  }

  public static String getCellularNetworkType(Context context) {
    TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    switch (manager.getNetworkType()) {
      case TelephonyManager.NETWORK_TYPE_1xRTT:
        return "1xRTT";
      case TelephonyManager.NETWORK_TYPE_CDMA:
        return "CDMA";
      case TelephonyManager.NETWORK_TYPE_EDGE:
        return "EDGE";
      case TelephonyManager.NETWORK_TYPE_EHRPD:
        return "EHRPD";
      case TelephonyManager.NETWORK_TYPE_EVDO_0:
        return "EVDO_0";
      case TelephonyManager.NETWORK_TYPE_EVDO_A:
        return "EVDO_A";
      case TelephonyManager.NETWORK_TYPE_EVDO_B:
        return "EVDO_B";
      case TelephonyManager.NETWORK_TYPE_GPRS:
        return "GPRS";
      case TelephonyManager.NETWORK_TYPE_HSDPA:
        return "HSDPA";
      case TelephonyManager.NETWORK_TYPE_HSPA:
        return "HSPA";
      case TelephonyManager.NETWORK_TYPE_HSPAP:
        return "HSPAP";
      case TelephonyManager.NETWORK_TYPE_HSUPA:
        return "HSUPA";
      case TelephonyManager.NETWORK_TYPE_IDEN:
        return "IDEN";
      case TelephonyManager.NETWORK_TYPE_LTE:
        return "LTE";
      case TelephonyManager.NETWORK_TYPE_UMTS:
        return "UMTS";
      case TelephonyManager.NETWORK_TYPE_UNKNOWN:
        return "Unknown";
      default:
        return "";
    }
  }

  /**
   * Check whether we're connected to wifi. This requires android.permission.ACCESS_WIFI_STATE,
   * we'll fail silently if we don't have access to it.
   */
  public static Boolean getConnectedToWifi(Context context) {
    Boolean isConnectedToWifi = false;

    try {
      WifiManager wifiMgr = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
      if (PermissionsManager.isPermissionGranted(context, Manifest.permission.ACCESS_WIFI_STATE)
        && wifiMgr.isWifiEnabled()) {

        //noinspection MissingPermission
        WifiInfo wifiInfo = wifiMgr.getConnectionInfo();
        if (wifiInfo.getNetworkId() != -1) {
          isConnectedToWifi = true;
        }
      }
    } catch (Exception exception) {
      // Assume false if we don't have access to state
      isConnectedToWifi = false;
    }

    return isConnectedToWifi;
  }

}
