package com.elifeindia.crm.utils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.content.ContentValues.TAG;

public class ViewUtils {

    AlertDialog.Builder alertDialog;
    //Toast
    public void toast(Context ctx, String msg){
        Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
    }

    //Snack bar

    //Progress bar
    public void showAlertDialog(Context context, String title, String msg) {
        alertDialog = new AlertDialog.Builder(context);
        alertDialog.setCancelable(false);//you can cancel it by pressing back button
        alertDialog.setTitle(title);
        alertDialog.setMessage(msg);

        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = alertDialog.create();
        dialog.show();
    }

    public static String changeDateFormat(String inputDate){

        String DateTime1 = inputDate.substring(0,10);

        SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        SimpleDateFormat EEEddMMMyyyy = new SimpleDateFormat("dd MMM yyyy", Locale.US);
        String outputDateStr = "";
        outputDateStr = parseDate(DateTime1, ymdFormat, EEEddMMMyyyy);
        return outputDateStr;
    }

    public static String changeDateTimeFormat(String inputDate){
        String outputDateStr = "";
        if(inputDate!=null){
            String Date = inputDate.substring(0,10);
            String Time = inputDate.substring(11,16);

            SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            SimpleDateFormat EEEddMMMyyyy = new SimpleDateFormat("dd MMM yyyy", Locale.US);

            outputDateStr = parseDate(Date, ymdFormat, EEEddMMMyyyy)+" "+Time;
        }



        return outputDateStr;
    }


    public static String changeDateTimeFormatForPayment(String inputDate){
        String outputDateStr = "";
        if(inputDate!=null){
            String Date = inputDate.substring(0,10);
            String Time = inputDate.substring(11,16);

            SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            SimpleDateFormat EEEddMMMyyyy = new SimpleDateFormat("dd MMM yyyy", Locale.US);

            outputDateStr = parseDate(Date, ymdFormat, EEEddMMMyyyy);
        }



        return outputDateStr;
    }



    public static Date parseDateTime(String dateString) {
        if (dateString == null) return null;
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
           // dateString = dateString.substring(0, dateString.lastIndexOf(':')) + dateString.substring(dateString.lastIndexOf(':')+1);
        try {
            Date date =  fmt.parse(dateString);
            return date;
        }
        catch (ParseException e) {
            Log.e(TAG, "Could not parse datetime: " + dateString);
            return null;
        }
    }


    public static String parseDate(String inputDateString, SimpleDateFormat inputDateFormat, SimpleDateFormat outputDateFormat) {
        Date date = null;
        String outputDateString = null;
        try {
            date = inputDateFormat.parse(inputDateString);
            outputDateString = outputDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outputDateString;
    }
    //Internet not available dialog

}

