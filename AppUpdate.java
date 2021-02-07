appUpdateManager = AppUpdateManagerFactory.create(this);
        appUpdateManager.registerListener(listener);

        appUpdateManager.getAppUpdateInfo().addOnSuccessListener(new OnSuccessListener<AppUpdateInfo>() {
            @Override
            public void onSuccess(AppUpdateInfo appUpdateInfo) {
                if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                        && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)){
                    requestUpdate(appUpdateInfo);
                    Toast.makeText(MainActivity.this, "Kindly Update the App", Toast.LENGTH_SHORT).show();
                }
                else if (appUpdateInfo.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS){
                    notifyUser();
                }
                else if (appUpdateInfo.installStatus() == InstallStatus.DOWNLOADED){
                    notifyUser();
                }
            }
        });

    InstallStateUpdatedListener listener = new InstallStateUpdatedListener() {
        @Override
        public void onStateUpdate(InstallState installState) {
            if (installState.installStatus() == InstallStatus.DOWNLOADED){

                notifyUser();
            }
            else if (installState.installStatus() == InstallStatus.INSTALLED){
                if (appUpdateManager != null){
                    appUpdateManager.unregisterListener(listener);
                }
            }
        }
    };

private void requestUpdate(AppUpdateInfo appUpdateInfo){
        try {
            appUpdateManager.startUpdateFlowForResult(appUpdateInfo,AppUpdateType.FLEXIBLE,17326);
        } catch (IntentSender.SendIntentException e) {
            e.printStackTrace();
        }
    }

private void notifyUser() {

        Snackbar snackbar =
                Snackbar.make(findViewById(android.R.id.content),
                        "An update has just been downloaded.",
                        Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("INSTALL", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (appUpdateManager != null) {
                    appUpdateManager.completeUpdate();
                }
            }
        });
        snackbar.setActionTextColor(
                getResources().getColor(android.R.color.white));
        snackbar.show();
    }
