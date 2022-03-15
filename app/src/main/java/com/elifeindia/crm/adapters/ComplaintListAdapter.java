package com.elifeindia.crm.adapters;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.elifeindia.crm.R;
import com.elifeindia.crm.model.ComplaintList;
import com.elifeindia.crm.sharedpref.Constants;
import com.elifeindia.crm.sharedpref.SharedPrefsData;
import com.elifeindia.crm.utils.ViewUtils;
import com.elifeindia.crm.view.activities.ComplaintDetailsActivity;
import com.elifeindia.crm.view.activities.CustomersDetailsActivity;
import com.elifeindia.crm.view.activities.EditComplaintActivity;
import com.elifeindia.crm.view.activities.EditComplaintEmpActivity;

import java.util.List;


public class ComplaintListAdapter extends RecyclerView.Adapter<ComplaintListAdapter.MyviewHolder>{

    Context context;
    List<ComplaintList.Complaint> complaintList;
    AdapterCallback adapterCallback;
    public static String complaintId="", complaintCode="";
    public static Spinner spn_complaint_status;

    public ComplaintListAdapter(Context context, List<ComplaintList.Complaint> complaintList, AdapterCallback adapterCallback) {
        this.context = context;
        this.complaintList = complaintList;
        this.adapterCallback = adapterCallback;
    }


    @NonNull
    @Override
    public ComplaintListAdapter.MyviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_complaint_list, viewGroup, false);
        return new MyviewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyviewHolder holder,  int position) {

        try {
//            holder.acno.setText("Ac No : "+paymentReciepts.get(position).getSubscriberID());
//            holder.areaid.setText("Area Id : "+paymentReciepts.get(position).getAreaCustomerID().toString());
//            //paymentReciepts.get(position).getAreaName();
            //paymentReciepts.get(position).getCustomerID();

            //holder.invoicenumber.setText("Inv No : "+paymentReciepts.get(position).getInvoiceNumber());
            holder.txt_name.setText(complaintList.get(position).getName());
            holder.txt_complaint.setText(complaintList.get(position).getComplaintType());
            String status = complaintList.get(position).getComplaintStatus();
            holder.txt_status.setText(status);
            switch (status) {
                case "Assigned":
                    holder.txt_status.setBackgroundResource(R.color.Assign);
                    break;
                case "Closed":
                    holder.txt_status.setBackgroundResource(R.color.Closed);
                    break;
                case "Waiting":
                    //  holder.txt_status.setBackgroundResource(R.color.Closed);
                    break;
                case "Open":
                    holder.txt_status.setBackgroundResource(R.color.Open);
                    break;
                case "In Progress":
                    holder.txt_status.setBackgroundResource(R.color.InProgress);
                    break;
                case "Unassigned":
                    holder.txt_status.setBackgroundResource(R.color.Unassigned);
                    break;
                case "Reopen":
                    holder.txt_status.setBackgroundResource(R.color.Reopen);
                    break;
                case "Reassign":
                    holder.txt_status.setBackgroundResource(R.color.Reassign);
                    break;
            }



            holder.txt_assignto.setText(complaintList.get(position).getAssignTo());
            holder.subid.setText("Sub Id : "+complaintList.get(position).getSubscriberID());
            holder.txt_priority.setText("CID : "+complaintList.get(position).getAreaCustomerID());
            holder.txt_accountno.setText("Acc No : "+complaintList.get(position).getAccountNo());
            holder.txt_area.setText(complaintList.get(position).getAddress());
            try {
                holder.txt_complaintId.setText("Comp Code : "+complaintList.get(position).getComplaintCode());
            } catch (Exception e) {
                e.printStackTrace();
            }
            String cdate = null;
            try {
                cdate = ViewUtils.changeDateTimeFormat(complaintList.get(position).getComplaintDate());
            } catch (Exception e) {
                e.printStackTrace();
            }
            holder.txt_complaint_date.setText(cdate);


//            holder.txt_paymode.setText(paymentReciepts.get(position).getPaymentType());
//            holder.txt_cust_id.setText("Sub Id : "+complaintList.get(position).getSubscriberID());
//            paymentReciepts.get(position).getTitle();
//            holder.amount_to_pay.setText("T Amnt : "+paymentReciepts.get(position).getTotalAmount().toString());
//            holder.balance.setText("B Amnt : "+paymentReciepts.get(position).getBalance().toString());
//            holder.paidamount.setText("P Amnt : "+paymentReciepts.get(position).getPaidAmount().toString());
//            holder.txt_triplePlay.setText(paymentReciepts.get(position).getTriplePlay());
//            paymentReciepts.get(position).getTriplePlayID();

        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.cv_complaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPrefsData.putString(context, Constants.CustomerName, complaintList.get(position).getName(), Constants.PREF_NAME);
                SharedPrefsData.putString(context, Constants.CustomerAddress, complaintList.get(position).getAreaName(), Constants.PREF_NAME);
                SharedPrefsData.putString(context, Constants.CustomerID, complaintList.get(position).getCustomerID().toString(), Constants.PREF_NAME);
                SharedPrefsData.putString(context, Constants.AccNo, complaintList.get(position).getAccountNo().toString(), Constants.PREF_NAME);
                SharedPrefsData.putString(context, Constants.ComplaintID, complaintList.get(position).getComplaintID().toString(), Constants.PREF_NAME);
                SharedPrefsData.putString(context, Constants.PriorityID, complaintList.get(position).getPriorityID().toString(), Constants.PREF_NAME);

                SharedPrefsData.putString(context, Constants.ProductID, complaintList.get(position).getProductID().toString(), Constants.PREF_NAME);
                SharedPrefsData.putString(context, Constants.StatusID, complaintList.get(position).getComplaintStatusID().toString(), Constants.PREF_NAME);
                SharedPrefsData.putString(context, Constants.ComplaintTypeID, complaintList.get(position).getComplaintTypeID().toString(), Constants.PREF_NAME);
                SharedPrefsData.putString(context, Constants.AssignToID, complaintList.get(position).getEmployeeID().toString(), Constants.PREF_NAME);
                SharedPrefsData.putString(context, Constants.Remark, complaintList.get(position).getComment()+" "+complaintList.get(position).getDescription(), Constants.PREF_NAME);



                String Complaint_Status_ID = complaintList.get(position).getComplaintStatusID().toString();
                SharedPrefsData.putString(context, Constants.Complaint_Status_ID, Complaint_Status_ID, Constants.PREF_NAME);

                TextView  txt_name_letter, txt_name, txt_subid, txt_cid, txt_mob_no, txt_complaint, txt_created_date, txt_assignto;
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_complaint);
                Window window = dialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.WRAP_CONTENT);
                window.setGravity(Gravity.CENTER | Gravity.CENTER);
                spn_complaint_status = dialog.findViewById(R.id.spn_status);
                TextView txt_close = dialog.findViewById(R.id.txt_close);
                TextView txt_edit = dialog.findViewById(R.id.txt_edit);
                txt_assignto = dialog.findViewById(R.id.txt_assignto);
                txt_created_date = dialog.findViewById(R.id.txt_created_date);
                txt_complaint = dialog.findViewById(R.id.txt_complaint);
                txt_mob_no = dialog.findViewById(R.id.txt_mob_no);
                txt_subid = dialog.findViewById(R.id.txt_subid);
                txt_cid = dialog.findViewById(R.id.txt_cid);
                txt_name = dialog.findViewById(R.id.txt_name);
                txt_name_letter = dialog.findViewById(R.id.txt_name_letter);

                ImageView copyNumber = dialog.findViewById(R.id.copy_number);
                ImageView callNumber = dialog.findViewById(R.id.call_number);

                txt_name.setText("NAME : "+complaintList.get(position).getName());
                String custname = complaintList.get(position).getName();
                String firstLetter = custname.substring(0, 1);
                txt_name_letter.setText(firstLetter);

                txt_complaint.setText("Complaint : "+complaintList.get(position).getComplaintType()+"\n"+"Remark : "+complaintList.get(position).getComment()+"\n"+complaintList.get(position).getDescription());
                txt_subid.setText("Sub Id : "+complaintList.get(position).getSubscriberID());
                txt_cid.setText("CID : "+complaintList.get(position).getAreaCustomerID());
                txt_assignto.setText(complaintList.get(position).getAssignTo());
                txt_mob_no.setText(complaintList.get(position).getContactNo());
                String cdate = null;
                try {
                    cdate = ViewUtils.changeDateTimeFormat(complaintList.get(position).getAssignedDate());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                txt_created_date.setText(cdate);

                copyNumber.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String text = txt_mob_no.getText().toString();
                        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
                            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                            clipboard.setText(text);
                        } else {
                            android.content.ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                            android.content.ClipData clip = android.content.ClipData.newPlainText("Number Copied", text);
                            clipboard.setPrimaryClip(clip);
                            Toast.makeText(context, text + " Copied", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                callNumber.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:" + txt_mob_no.getText().toString()));
                        if (intent.resolveActivity(context.getPackageManager()) != null) {
                            context.startActivity(intent);
                        }
                    }
                });

                txt_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        //spn_complaint_status.performClick();
                        //dialog.hide();

//                        final AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(context, R.style.ListRow));
//
//                        builder.setTitle("Close complaint confirmation!")
//                                .setMessage("Are you sure you want to close this complaint")
//                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog1, int which) {
//                                        dialog.dismiss();
//                                        adapterCallback.onClickCallback(holder.cv_complaint, position, complaintList.get(position).getComplaintID().toString());
//
//                                    }
//                                })
//                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        dialog.dismiss();
//                                    }
//                                })
//
//                                .show();

                        dialog.dismiss();
                        complaintId = complaintList.get(position).getComplaintID().toString();
                        complaintCode = complaintList.get(position).getComplaintID().toString();

                        Intent intent = new Intent(context, EditComplaintEmpActivity.class);

                        intent.putExtra("DateClose", holder.txt_complaint_date.getText().toString());

                        context.startActivity(intent);

                        ComplaintDetailsActivity cd = new ComplaintDetailsActivity();
                        cd.finish();

                    }
                });
                txt_edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        complaintId = complaintList.get(position).getComplaintID().toString();
                        complaintCode = complaintList.get(position).getComplaintID().toString();

                        Intent intent = new Intent(context, EditComplaintActivity.class);

                        intent.putExtra("DateEdit", holder.txt_complaint_date.getText().toString());

                        context.startActivity(intent);

                        /*context.startActivity(new Intent(context, EditComplaintActivity.class));*/

                        ComplaintDetailsActivity cd = new ComplaintDetailsActivity();
                        cd.finish();

                    }
                });
                dialog.show();
//                String address = custemersLists.get(position).getAddress().toString();
//                SharedPrefsData.putString(context, Constants.CustomerAddress, address, Constants.PREF_NAME);
//                SharedPrefsData.putString(context, Constants.CustomerID, custemersLists.get(position).getCustomerID().toString(), Constants.PREF_NAME);
//                context.startActivity(new Intent(context, CollectionReportActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return complaintList.size();
    }

    public static class MyviewHolder extends RecyclerView.ViewHolder {
        TextView txt_complaintId, txt_name, subid, txt_priority, txt_complaint_date, txt_complaint, txt_status, txt_assignto, txt_accountno, txt_area;
        CardView cv_complaint;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);

            cv_complaint = itemView.findViewById(R.id.cv_complaint);
            txt_name = itemView.findViewById(R.id.txt_name);
            subid = itemView.findViewById(R.id.subid);
            txt_complaint_date = itemView.findViewById(R.id.txt_complaint_date);
            txt_complaint = itemView.findViewById(R.id.txt_complaint);
            txt_status = itemView.findViewById(R.id.txt_status);
            txt_assignto =itemView.findViewById(R.id.txt_assignto);
            txt_accountno = itemView.findViewById(R.id.txt_accountno);
            txt_area = itemView.findViewById(R.id.txt_area);
            txt_priority = itemView.findViewById(R.id.txt_priority);
            txt_complaintId = itemView.findViewById(R.id.txt_complaintId);


        }

    }

}
