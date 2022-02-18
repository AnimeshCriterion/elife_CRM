package com.elifeindia.crm.networking;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetCheck {

    public static boolean isInternetConnection(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connMgr.getActiveNetworkInfo();
        if (activeNetworkInfo != null) { // connected to the internet
            // connected to the mobile provider's data plan
            if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
                return true;
            } else return activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
        }
        return false;
    }

    /*public static void showErrorDialog(final Context context, final String dialogTitle, final String dialogMessage, final boolean isOutsideCancalabel, final APIHandler iDialogCallback, final int requestCode) {
        if (context instanceof Activity) {
            ((Activity) context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle(dialogTitle);
                    builder.setMessage(dialogMessage);

                    builder.setPositiveButton(context.getString(R.string.ok_txt),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (iDialogCallback != null) {
                                        iDialogCallback.onPositiveButtonPress(requestCode);
                                    }
                                    // positive button logic
                                    dialog.dismiss();

                                }
                            });

                    AlertDialog dialog = builder.create();
                    dialog.setCanceledOnTouchOutside(isOutsideCancalabel);
                    dialog.show();
                }
            });

        }
    }*/

}
