package com.elifeindia.crm.networking;

import com.elifeindia.crm.UpdateCustomerPojo;
import com.elifeindia.crm.UpdateSubscription.UpdateINternetSubscriptionActivityForUpdateSubscription;
import com.elifeindia.crm.model.AlacarteModel;
import com.elifeindia.crm.model.AreaResponse;
import com.elifeindia.crm.model.BillTypeModel;
import com.elifeindia.crm.model.BouquetModel;
import com.elifeindia.crm.model.BoxAlacarteList;
import com.elifeindia.crm.model.BoxBouquetList;
import com.elifeindia.crm.model.BoxTypeModel;
import com.elifeindia.crm.model.CableBoxWithSubscription;
import com.elifeindia.crm.model.ChangePasswordModel;
import com.elifeindia.crm.model.ComplaintList;
import com.elifeindia.crm.model.ComplaintStatusList;
import com.elifeindia.crm.model.ComplaintTypeList;
import com.elifeindia.crm.model.CustemersCableBoxData;
import com.elifeindia.crm.model.CustemersList;
import com.elifeindia.crm.model.CustomerData;
import com.elifeindia.crm.model.CustomerInvoice;
import com.elifeindia.crm.model.CustomerStatusResponse;
import com.elifeindia.crm.model.CustomerSubscribeList;
import com.elifeindia.crm.model.CustomersInternetBoxData;
import com.elifeindia.crm.model.EmployeeList;
import com.elifeindia.crm.model.FooterModel;
import com.elifeindia.crm.model.GetInvoiceModel;
import com.elifeindia.crm.model.HeaderModel;
import com.elifeindia.crm.model.InsertPayment;
import com.elifeindia.crm.model.InternetBoxWithSubscription;
import com.elifeindia.crm.model.InternetPackageModel;
import com.elifeindia.crm.model.InternetSubscriptionDetails;
import com.elifeindia.crm.model.Login;
import com.elifeindia.crm.model.PaymentReciept;
import com.elifeindia.crm.model.PaymentRecieptList;
import com.elifeindia.crm.model.PaymentStatusModel;
import com.elifeindia.crm.model.PaymentTypeList;
import com.elifeindia.crm.model.PriorityList;
import com.elifeindia.crm.model.ProductList;
import com.elifeindia.crm.model.RolewiseAccess;
import com.elifeindia.crm.model.UpdateBox;
import com.elifeindia.crm.model.generateinvoice.InsertInvoiceModel;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiService {

    @GET("Values/Login?")
    Observable<Login> getLogin(@Query("Uname") String username, @Query("Upass") String password);

    @GET("Values/GetCustomerList?")
    Observable<CustemersList> getCustemersList(
            @Query("Company_ID") String Company_ID,
            @Query("User_ID") String User_ID,
            @Query("Employee_ID") String Employee_ID,
            @Query("Value") String Value,
            @Query("selectCount") String selectCount,
            @Query("page_no") String page_no);

    @GET("Values/GetCustomerListdatewise?")
    Observable<CustemersList> getCustemersListDateWise(
            @Query("Company_ID") String Company_ID,
            @Query("User_ID") String User_ID,
            @Query("Employee_ID") String Employee_ID,
            @Query("Area_ID") String Area_ID,
            @Query("Status_ID") String Status_ID,
            @Query("selectCount") String selectCount,
            @Query("page_no") String page_no,
            @Query("Value") String Value,
            @Query("Field_Value") String field_value,
            @Query("Field_Name") String field_name,
            @Query("From_Date") String From_Date,
            @Query("To_Date") String To_Date);

    @GET("Values/GetArea?")
    Observable<AreaResponse> getArea(@Query("Company_ID") String Company_ID, @Query("Employee_ID") String Employee_ID);


    @GET("Values/GetCustomer?")
    Observable<CustomerData> getCustomerData(@Query("Customer_ID") String Customer_ID);

   @GET("Values/GetCustomer?")
    Call<CustomerData> getCustomerDataForUpdate(@Query("Customer_ID") String Customer_ID);

    @GET("Values/GetCableboxList?")
    Observable<CustemersCableBoxData> getCustemersCableBoxData(@Query("Customer_ID") String Customer_ID);

    @GET("Values/GetInternetboxList?")
    Observable<CustomersInternetBoxData> getCustemersinternetBoxData(@Query("Customer_ID") String Customer_ID);

    @GET("Values/GetBoxalacartelistList?")
    Observable<BoxAlacarteList> getBoxalacarteList(@Query("Cable_Box_ID") String Cable_Box_ID);

    @GET("Values/GetBoxBouquetlistList?")
    Observable<BoxBouquetList> getBoxBouquetList(@Query("Cable_Box_ID") String Cable_Box_ID);

    @GET("Values/GetBoxPackageList?")
    Observable<InternetSubscriptionDetails> getInternetSubscriptiondetails(@Query("Internet_Box_ID") String Internet_Box_ID);

    @GET("Values/GetCustomerSubscribeList?")
    Observable<CustomerSubscribeList> getCustomerSubscribeList(@Query("Customer_ID") String Customer_ID);

    @GET("Values/GetCustomerInvoice?")
    Observable<CustomerInvoice> getCustomerInvoice(@Query("Customer_ID") String Customer_ID, @Query("Triple_play_ID") String Triple_play_ID);

    @GET("Values/GetpaymenttypeList?")
    Observable<PaymentTypeList> getPaymentTypeList(@Query("Company_ID") String Customer_ID);

    @GET("Values/GetaymentReciept?")
    Observable<PaymentReciept> getPaymentReciept(@Query("Payment_Id") String Payment_Id);
 @POST("Values/Update_Customer")
    Observable<PaymentReciept> updateCustomer(@Body UpdateCustomerPojo updateCustomerPojo);

//    @Headers("Content-Type: application/json")
//    @POST("Values/InsertPayment?")
//    Observable<InsertPayment> insertPayment(@Body JSONObject jsonObject);

    @FormUrlEncoded
    @POST("Values/InsertPayment?")
    Observable<InsertPayment> insertPayment(
            @Field("Customer_ID") String Customer_ID,
            @Field("paymentType_Id") String paymentType_Id,
            @Field("invoice_ID") String invoice_ID,
            @Field("date") String date,
            @Field("total_amount") String total_amount,
            @Field("paid_Amount") String paid_Amount,
            @Field("balance") String balance,
            @Field("transaction_No") String transaction_No,
            @Field("company_ID") String company_ID,
            @Field("user_ID") String user_ID,
    @Field("discount") String Discount);


    @GET("Values/GetEmployeeList?")
    Observable<EmployeeList> getEmployeeList(
            @Query("Company_ID") String Company_ID,
            @Query("Employee_ID") String ID,
            @Query("Role_Type") String Role_Type);

    @GET("Values/GetaymentRecieptList?")
    Observable<PaymentRecieptList> getPaymentRecieptList(
            @Query("Company_ID") String Company_ID,
            @Query("Customer_ID") String Customer_ID,
            @Query("From_Date") String From_Date,
            @Query("To_Date") String To_Date,
            @Query("Triple_play_ID") String Triple_play_ID,
            @Query("Value") String Value,
            @Query("Employee_ID") String Employee_ID,
            @Query("Area_ID") String Area_ID);

    @GET("Values/GetComplainttypelist?")
    Observable<ComplaintTypeList> getComplaintTypeList(
            @Query("Company_ID") String Company_ID,
            @Query("Complaint_Type_ID") String ID);

    @GET("Values/GetProductlist?")
    Observable<ProductList> getProductList(
            @Query("Company_ID") String Company_ID,
            @Query("Product_ID") String ID);

    @GET("Values/GeComplaintStatuslist?")
    Observable<ComplaintStatusList> getComplaintStatusList(
            @Query("Company_ID") String Company_ID,
            @Query("Complaint_Status_ID") String ID);


    @GET("Values/Geprioritylist?")
    Observable<PriorityList> getPriorityList(
            @Query("Company_ID") String Company_ID,
            @Query("Priority_ID") String Priority_ID);

    @FormUrlEncoded
    @POST("Values/InsertComplaint?")
    Observable<InsertPayment> insertComplaint(
            @Field("Complaint_ID") String Complaint_ID,
            @Field("Company_ID") String Company_ID,
            @Field("Customer_ID") String paymentType_Id,
            @Field("Complaint_Code") String Complaint_Code,
            @Field("Complaint_Date") String Complaint_Date,
            @Field("Complaint_Type_ID") String Complaint_Type_ID,
            @Field("Description") String Description,
            @Field("Product_ID") String balance,
            @Field("Assigned_Date") String Assigned_Date,
            @Field("Assigned_To") String company_ID,
            @Field("Complaint_Status_ID") String Complaint_Status_ID,
            @Field("Comment") String Comment,
            @Field("Priority_ID") String Priority_ID,
            @Field("User_ID") String User_ID,
            @Field("Date") String Date);


    @GET("Values/GetComplaintList?")
    Observable<ComplaintList> getComplaintList(
            @Query("Company_ID") String Company_ID,
            @Query("Employee_ID") String Employee_ID,
            @Query("Customer_ID") String Customer_ID,
            @Query("Complaint_Status_ID") String Complaint_Status_ID,
            @Query("From_date") String From_date,
            @Query("To_Date") String To_Date,
            @Query("Value") String Value);

    @FormUrlEncoded
    @POST("Values/UpdateComplaint?")
    Observable<InsertPayment> updateComplaint(
            @Field("Complaint_ID") String Complaint_ID,
            @Field("Company_ID") String Company_ID,
            @Field("Customer_ID") String paymentType_Id,
            @Field("Complaint_Code") String Complaint_Code,
            @Field("Complaint_Date") String Complaint_Date,
            @Field("Complaint_Type_ID") String Complaint_Type_ID,
            @Field("Description") String Description,
            @Field("Product_ID") String balance,
            @Field("Assigned_Date") String Assigned_Date,
            @Field("Assigned_To") String company_ID,
            @Field("Complaint_Status_ID") String Complaint_Status_ID,
            @Field("Comment") String Comment,
            @Field("Priority_ID") String Priority_ID,
            @Field("User_ID") String User_ID,
            @Field("Date") String Date);

    @FormUrlEncoded
    @POST("Values/OpencloseComplaint?")
    Observable<InsertPayment> opencloseComplaint(
            @Field("complaint_ID") String Company_ID,
            @Field("open_Date") String open_Date,
            @Field("open_By") String open_By,
            @Field("closed_Date") String closed_Date,
            @Field("complaint_Status_ID") String complaint_Status_ID,
            @Field("comment") String comment,
            @Field("closed_By") String closed_By,
            @Field("user_ID") String user_ID,
            @Field("date") String date);


    @GET("Values/GetAccessList?")
    Observable<RolewiseAccess> getAccessList(
            @Query("Role_ID") String Role_ID);

    @GET("Values/GetHeader?")
    Observable<HeaderModel> getHeader(
            @Query("Company_Id") String Company_Id);

    @GET("Values/GetFooter?")
    Observable<FooterModel> getFooter(
            @Query("Company_Id") String Company_Id);

    @GET("Values/GePayment?")
    Observable<PaymentStatusModel> getPaymentStatus(
            @Query("Status_ID") String Status_ID);

    @GET("Values/GetAlacarteList?")
    Observable<AlacarteModel> getAlacarteList(
            @Query("Company_ID") String Company_ID,
            @Query("Customer_ID") String Customer_ID);

    @GET("Values/GetBouquetList?")
    Observable<BouquetModel> getBouquetList(
            @Query("Company_ID") String Company_ID,
            @Query("Customer_ID") String Customer_ID);

    @GET("Values/GetPackageList?")
    Observable<InternetPackageModel> getInternetPackageList(
            @Query("Company_ID") String Company_ID,
            @Query("Customer_ID") String Customer_ID);

    @FormUrlEncoded
    @POST("Values/Insert_CablePackage?")
    Call<InsertPayment> insertCablePackage(
            @Field("user_Id") String user_Id,
            @Field("company_Id") String company_Id,
            @Field("cable_Box_ID") String cable_Box_ID,
            @Field("packageId") int packageId,
            @Field("packagetype") String packagetype,
            @Field("date") String date);

    @FormUrlEncoded
    @POST("Values/Delete_CablePackage?")
    Call<InsertPayment> deleteCablePackage(
            @Field("user_Id") String user_Id,
            @Field("company_Id") String company_Id,
            @Field("cable_Box_ID") String cable_Box_ID,
            @Field("packageId") int packageId,
            @Field("packagetype") String packagetype,
            @Field("date") String date);

    @FormUrlEncoded
    @POST("Values/Insert_InternetPackage?")
    Call<InsertPayment> insert_InternetPackage(
            @Field("user_Id") String user_Id,
            @Field("company_Id") String company_Id,
            @Field("Internet_Box_ID") String cable_Box_ID,
            @Field("packageId") String packageId,
            @Field("packagetype") String packagetype,
            @Field("date") String date);

    @FormUrlEncoded
    @POST("Values/Delete_InternetPackage?")
    Call<InsertPayment> delete_InternetPackage(
            @Field("user_Id") String user_Id,
            @Field("company_Id") String company_Id,
            @Field("Internet_Box_ID") String cable_Box_ID,
            @Field("packageId") String packageId,
            @Field("packagetype") String packagetype,
            @Field("date") String date);

    @GET("Values/GetBillTypeList?")
    Observable<BillTypeModel> getBillType(
            @Query("Bill_Type_ID") String Company_ID);

    @GET("Values/GetBox_TypeList?")
    Observable<BoxTypeModel> getBoxTypeList(
            @Query("Company_ID") String Company_ID, @Query("BoxType_ID") String BoxType_ID);

    @GET("Values/GETCableBoxwithSubscriptionList?")
    Observable<CableBoxWithSubscription> getCableBoxwithSubscriptionList(
            @Query("Customer_ID") String Customer_ID);

    @GET("Values/GETInternetBoxwithSubscriptionList?")
    Observable<InternetBoxWithSubscription> getInternetBoxwithSubscriptionList(
            @Query("Customer_ID") String Customer_ID);



    @POST("Values/Update_CableBox?")
    Observable<UpdateBox> updateCableBox(
            @Body CableBoxSubscription cableBoxSubscription);


 @POST("Values/Get_CableExpiry?")
 Observable<UpdateBox> updateCableBoxUpdateSubscription(@Body CableBoxSubscription cableBoxSubscription);



    @POST("Values/Update_InternetBox?")
    Observable<UpdateBox> getInternetExpiry(@Body InternetBoxUpdateSubscription internetBoxUpdateSubscription);

    @POST("Values/Get_Internetexpiry")
    Observable<UpdateBox> updateInternetBoxSubscription(@Body InternetBoxUpdateSubscription internetBoxUpdateSubscription);


    @FormUrlEncoded
    @POST("Values/Generate_Invoice?")
    Observable<InsertPayment> generate_Invoice(
            @Field("user_Id") String user_Id,
            @Field("customer_Id") String customer_Id,
            @Field("triple_play_ID") String internet_Box_ID,
            @Field("date") String box_ID,
            @Field("triple_play") String boxType_ID,
            @Field("paid_Amount") String ip,
            @Field("employee_ID") String mac,
            @Field("paymentType_ID") String bill_Type_ID,
            @Field("payment_Date") String connection_Status_ID,
            @Field("transaction_No") String expiry_Date);

    @POST("Values/Insert_Invoice?")
    Observable<InsertPayment> insertInvoice(@Body JsonObject jsonObject);

    @POST("Values/Insert_Invoice?")
    Observable<InsertPayment> insertInvoice2(@Body InsertInvoiceModel model);


    @GET("Values/GetInvoice?")
    Observable<GetInvoiceModel> getInvoice(
            @Query("Invoice_ID") String Invoice_ID);


    @POST("Values/UpdateUSERCredential")
    Observable<ChangePasswordModel> changePassword(
            @Query("UserId") String UserId,
            @Query("Username") String Username,
            @Query("Oldpassword") String Oldpassword,
            @Query("Password") String Password
    );

    @GET("Values/GetConnection_StatusList")
    Call<CustomerStatusResponse> getCustomerStatus(
    );






}
