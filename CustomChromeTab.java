private String sPackageNameToUse;
private String packageName = null;
packageName = getPackageNameToUse();

private void openFile() {
try {
        if (packageName != null) {
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            CustomTabColorSchemeParams params = new CustomTabColorSchemeParams.Builder()
                    .setNavigationBarColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary))
                    .setToolbarColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryDark))
                    .setSecondaryToolbarColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary))
                    .build();
            builder.setColorSchemeParams(CustomTabsIntent.COLOR_SCHEME_DARK, params);
            builder.setStartAnimations(MainActivity.this, R.anim.slide_in_right, R.anim.slide_out_left);
            builder.setExitAnimations(MainActivity.this, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            CustomTabsIntent customTabsIntent = builder.build();
            customTabsIntent.intent.setPackage(packageName);
            customTabsIntent.launchUrl(MainActivity.this, Uri.parse(url));
        } else {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        }
    } catch (Exception ignored) {
        Toast.makeText(MainActivity.this, "Cant Open file at this Moment!", Toast.LENGTH_SHORT).show();
    }
}
private String getPackageNameToUse() {
        if (sPackageNameToUse != null) return sPackageNameToUse;

        PackageManager pm = getPackageManager();
        Intent activityIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.BASE_URL));
        ResolveInfo defaultViewHandlerInfo = pm.resolveActivity(activityIntent, 0);
        String defaultViewHandlerPackageName = null;
        if (defaultViewHandlerInfo != null) {
            defaultViewHandlerPackageName = defaultViewHandlerInfo.activityInfo.packageName;
        }

        List<ResolveInfo> resolvedActivityList = pm.queryIntentActivities(activityIntent, 0);
        List<String> packagesSupportingCustomTabs = new ArrayList<>();
        for (ResolveInfo info : resolvedActivityList) {
            Intent serviceIntent = new Intent();
            serviceIntent.setAction("android.support.customtabs.action.CustomTabsService");
            serviceIntent.setPackage(info.activityInfo.packageName);
            if (pm.resolveService(serviceIntent, 0) != null) {
                packagesSupportingCustomTabs.add(info.activityInfo.packageName);
            }
        }

        if (packagesSupportingCustomTabs.isEmpty()) {
            sPackageNameToUse = null;
        } else if (packagesSupportingCustomTabs.size() == 1) {
            sPackageNameToUse = packagesSupportingCustomTabs.get(0);
        } else if (!TextUtils.isEmpty(defaultViewHandlerPackageName)
                && !hasSpecializedHandlerIntents(this, activityIntent)
                && packagesSupportingCustomTabs.contains(defaultViewHandlerPackageName)) {
            sPackageNameToUse = defaultViewHandlerPackageName;
        } else if (packagesSupportingCustomTabs.contains("com.android.chrome")) {
            sPackageNameToUse = "com.android.chrome";
        } else if (packagesSupportingCustomTabs.contains("com.chrome.beta")) {
            sPackageNameToUse = "com.chrome.beta";
        } else if (packagesSupportingCustomTabs.contains("com.chrome.dev")) {
            sPackageNameToUse = "com.chrome.dev";
        } else if (packagesSupportingCustomTabs.contains("com.google.android.apps.chrome")) {
            sPackageNameToUse = "com.google.android.apps.chrome";
        }
        return sPackageNameToUse;
    }

    private boolean hasSpecializedHandlerIntents(Context context, Intent intent) {
        try {
            PackageManager pm = context.getPackageManager();
            List<ResolveInfo> handlers = pm.queryIntentActivities(
                    intent,
                    PackageManager.GET_RESOLVED_FILTER);
            if (handlers.size() == 0) {
                return false;
            }
            for (ResolveInfo resolveInfo : handlers) {
                IntentFilter filter = resolveInfo.filter;
                if (filter == null) continue;
                if (filter.countDataAuthorities() == 0 || filter.countDataPaths() == 0) continue;
                if (resolveInfo.activityInfo == null) continue;
                return true;
            }
        } catch (RuntimeException ignored) { }
        return false;
    }

<?xml version="1.0" encoding="utf-8"?>
<!-- left -->
<set xmlns:android="http://schemas.android.com/apk/res/android">
    <translate android:fromXDelta="0" android:toXDelta="-50%p"
        android:duration="@android:integer/config_mediumAnimTime"/>
    <alpha android:fromAlpha="1.0" android:toAlpha="0.0"
        android:duration="@android:integer/config_mediumAnimTime" />
</set>

<?xml version="1.0" encoding="utf-8"?>
<!-- right -->
<set xmlns:android="http://schemas.android.com/apk/res/android">
    <translate android:fromXDelta="50%p" android:toXDelta="0"
        android:duration="@android:integer/config_mediumAnimTime"/>
    <alpha android:fromAlpha="0.0" android:toAlpha="1.0"
        android:duration="@android:integer/config_mediumAnimTime" />
</set>
