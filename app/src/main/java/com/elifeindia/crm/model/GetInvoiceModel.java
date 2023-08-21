package com.elifeindia.crm.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetInvoiceModel {


    /**
     * cableBoxwithSubscription : [{"cableBox":{"cable_Box_ID":null,"invoice_Box_ID":1131,"customer_ID":null,"box_ID":null,"boxType_ID":2,"vcno":"2V425","stbno":"2S425","cafno":"2C425","iPtv":"2I425","bill_Type_ID":3,"noofMonth":1,"noofDays":null,"activation_Date":"2021-05-09T00:00:00Z","expiry_Date":"2021-06-09T00:00:00Z","alacarte_Amount":null,"bouquet_Amount":null,"box_Amount":300,"connection_Status_ID":null,"bill_Type":"DateWise","status_Name":null,"boxTypeName":"APSFL","payment_Date":null,"paid_Amount":null,"status":null},"boxAlacareteList":{"boxAlacarete":[{"box_Alacarte_ID":null,"cable_Box_ID":null,"alacarte_ID":1,"price":22.42,"tax_per":0,"tax":3.42,"total_Price":null,"alacarte_Name":"Star Plus"},{"box_Alacarte_ID":null,"cable_Box_ID":null,"alacarte_ID":3,"price":22.42,"tax_per":0,"tax":3.42,"total_Price":null,"alacarte_Name":"Sony TV"},{"box_Alacarte_ID":null,"cable_Box_ID":null,"alacarte_ID":4,"price":22.42,"tax_per":0,"tax":3.42,"total_Price":null,"alacarte_Name":"Colors"}]},"boxBouquetList":{"boxBouquet":[{"box_Bouquet_ID":null,"cable_Box_ID":null,"bouquet_ID":2,"price":250,"tax_per":0,"tax":38.14,"total_Price":null,"bouquet_Name":"250 Package"}]}}]
     * internetBoxwithSubscription : []
     * paymentMaster : [{"payment_Id":350,"paymentType_ID":null,"paymentType":"GPAY","payment_Date":"2021-04-13T00:00:00Z","employee_Name":"eLife Admin","employee_ID":null,"paid_Amount":200,"transaction_No":null}]
     * company_ID : null
     * customer_ID : null
     * account_No : 425
     * subscriber_ID : 2425
     * title : Mr
     * name : Customer 2425
     * soDoWo : null
     * address : 2425
     * contact_No : 5555552425
     * invoice_Date : 2021-04-13T00:00:00Z
     * invoice_Number : 66
     * tax_Amount : null
     * amount : 300.0
     * previous_Balance : 292.26
     * discount : null
     * inv_Amount : 300.0
     * balance : 392.26
     * status : Partial Paid
     * user_Id : null
     * customer_Id : 326
     * triple_play_ID : null
     * date : null
     * triple_play : Cable
     * paid_Amount : null
     * employee_ID : null
     * paymentType_ID : null
     * payment_Date : null
     * transaction_No : null
     */

    private Object company_ID;
    private Object customer_ID;
    private int account_No;
    private String subscriber_ID;
    private String title;
    private String name;
    private Object soDoWo;
    private String address;
    private String contact_No;
    private String invoice_Date;
    private String invoice_Number;
    private Object tax_Amount;
    private double amount;
    private double previous_Balance;
    private Object discount;
    private double inv_Amount;
    private double balance;
    private String status;
    private Object user_Id;
    private int customer_Id;
    private Object triple_play_ID;
    private Object date;
    private String triple_play;
    private Object paid_Amount;
    private Object employee_ID;
    private Object paymentType_ID;
    private Object payment_Date;
    private Object transaction_No;
    private List<CableBoxwithSubscriptionDTO> cableBoxwithSubscription;

    //private List<?> internetBoxwithSubscription;

    private List<InternetBoxwithSubscriptionDTO> internetBoxwithSubscription;


    private List<PaymentMasterDTO> paymentMaster;

    public Object getCompany_ID() {
        return company_ID;
    }

    public void setCompany_ID(Object company_ID) {
        this.company_ID = company_ID;
    }

    public Object getCustomer_ID() {
        return customer_ID;
    }

    public void setCustomer_ID(Object customer_ID) {
        this.customer_ID = customer_ID;
    }

    public int getAccount_No() {
        return account_No;
    }

    public void setAccount_No(int account_No) {
        this.account_No = account_No;
    }

    public String getSubscriber_ID() {
        return subscriber_ID;
    }

    public void setSubscriber_ID(String subscriber_ID) {
        this.subscriber_ID = subscriber_ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getSoDoWo() {
        return soDoWo;
    }

    public void setSoDoWo(Object soDoWo) {
        this.soDoWo = soDoWo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact_No() {
        return contact_No;
    }

    public void setContact_No(String contact_No) {
        this.contact_No = contact_No;
    }

    public String getInvoice_Date() {
        return invoice_Date;
    }

    public void setInvoice_Date(String invoice_Date) {
        this.invoice_Date = invoice_Date;
    }

    public String getInvoice_Number() {
        return invoice_Number;
    }

    public void setInvoice_Number(String invoice_Number) {
        this.invoice_Number = invoice_Number;
    }

    public Object getTax_Amount() {
        return tax_Amount;
    }

    public void setTax_Amount(Object tax_Amount) {
        this.tax_Amount = tax_Amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getPrevious_Balance() {
        return previous_Balance;
    }

    public void setPrevious_Balance(double previous_Balance) {
        this.previous_Balance = previous_Balance;
    }

    public Object getDiscount() {
        return discount;
    }

    public void setDiscount(Object discount) {
        this.discount = discount;
    }

    public double getInv_Amount() {
        return inv_Amount;
    }

    public void setInv_Amount(double inv_Amount) {
        this.inv_Amount = inv_Amount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(Object user_Id) {
        this.user_Id = user_Id;
    }

    public int getCustomer_Id() {
        return customer_Id;
    }

    public void setCustomer_Id(int customer_Id) {
        this.customer_Id = customer_Id;
    }

    public Object getTriple_play_ID() {
        return triple_play_ID;
    }

    public void setTriple_play_ID(Object triple_play_ID) {
        this.triple_play_ID = triple_play_ID;
    }

    public Object getDate() {
        return date;
    }

    public void setDate(Object date) {
        this.date = date;
    }

    public String getTriple_play() {
        return triple_play;
    }

    public void setTriple_play(String triple_play) {
        this.triple_play = triple_play;
    }

    public Object getPaid_Amount() {
        return paid_Amount;
    }

    public void setPaid_Amount(Object paid_Amount) {
        this.paid_Amount = paid_Amount;
    }

    public Object getEmployee_ID() {
        return employee_ID;
    }

    public void setEmployee_ID(Object employee_ID) {
        this.employee_ID = employee_ID;
    }

    public Object getPaymentType_ID() {
        return paymentType_ID;
    }

    public void setPaymentType_ID(Object paymentType_ID) {
        this.paymentType_ID = paymentType_ID;
    }

    public Object getPayment_Date() {
        return payment_Date;
    }

    public void setPayment_Date(Object payment_Date) {
        this.payment_Date = payment_Date;
    }

    public Object getTransaction_No() {
        return transaction_No;
    }

    public void setTransaction_No(Object transaction_No) {
        this.transaction_No = transaction_No;
    }

    public List<CableBoxwithSubscriptionDTO> getCableBoxwithSubscription() {
        return cableBoxwithSubscription;
    }

    public void setCableBoxwithSubscription(List<CableBoxwithSubscriptionDTO> cableBoxwithSubscription) {
        this.cableBoxwithSubscription = cableBoxwithSubscription;
    }

//    public List<?> getInternetBoxwithSubscription() {
//        return internetBoxwithSubscription;
//    }
//
//    public void setInternetBoxwithSubscription(List<?> internetBoxwithSubscription) {
//        this.internetBoxwithSubscription = internetBoxwithSubscription;
//    }



    public List<InternetBoxwithSubscriptionDTO> getInternetBoxwithSubscription() {
        return internetBoxwithSubscription;
    }

    public void setInternetBoxwithSubscription(List<InternetBoxwithSubscriptionDTO> internetBoxwithSubscription) {
        this.internetBoxwithSubscription = internetBoxwithSubscription;
    }




    public List<PaymentMasterDTO> getPaymentMaster() {
        return paymentMaster;
    }

    public void setPaymentMaster(List<PaymentMasterDTO> paymentMaster) {
        this.paymentMaster = paymentMaster;
    }







    public static class InternetBoxwithSubscriptionDTO {
        /**
         * internetBox : {"internet_Box_ID":1,"customer_ID":1,"box_ID":1,"boxType_ID":1,"ip":"sample string 1","mac":"sample string 2","bill_Type_ID":1,"noofMonth":1,"activation_Date":"2021-01-03T18:54:02.2567859Z","expiry_Date":"2021-01-03T18:54:02.2567859Z","package_Amount":1,"box_Amount":1,"connection_Status_ID":1,"bill_Type":"sample string 3","status_Name":"sample string 4","boxTypeName":"sample string 5","payment_Date":"2021-01-03T18:54:02.2567859Z","paid_Amount":1,"status":"sample string 6"}
         * boxpackageList : {"boxpackage":[{"box_Package_ID":1,"internet_Box_ID":1,"package_ID":1,"price":1,"tax_Percent":1,"tax_Amount":1,"pricewithouttax":1,"package":"sample string 1"},{"box_Package_ID":1,"internet_Box_ID":1,"package_ID":1,"price":1,"tax_Percent":1,"tax_Amount":1,"pricewithouttax":1,"package":"sample string 1"}],"status":"sample string 1"}
         */

        private InternetBoxDTO internetBox;
        private BoxpackageListDTO boxpackageList;

        public InternetBoxDTO getInternetBox() {
            return internetBox;
        }

        public void setInternetBox(InternetBoxDTO internetBox) {
            this.internetBox = internetBox;
        }

        public BoxpackageListDTO getBoxpackageList() {
            return boxpackageList;
        }

        public void setBoxpackageList(BoxpackageListDTO boxpackageList) {
            this.boxpackageList = boxpackageList;
        }

        public static class InternetBoxDTO {
            /**
             * internet_Box_ID : 1
             * customer_ID : 1
             * box_ID : 1
             * boxType_ID : 1
             * ip : sample string 1
             * mac : sample string 2
             * bill_Type_ID : 1
             * noofMonth : 1
             * activation_Date : 2021-01-03T18:54:02.2567859Z
             * expiry_Date : 2021-01-03T18:54:02.2567859Z
             * package_Amount : 1.0
             * box_Amount : 1.0
             * connection_Status_ID : 1
             * bill_Type : sample string 3
             * status_Name : sample string 4
             * boxTypeName : sample string 5
             * payment_Date : 2021-01-03T18:54:02.2567859Z
             * paid_Amount : 1.0
             * status : sample string 6
             */

            private Integer internet_Box_ID;
            private Integer customer_ID;
            private Integer box_ID;
            private Integer boxType_ID;
            private String ip;
            private String mac;
            private Integer bill_Type_ID;
            private Integer noofMonth;
            private String activation_Date;
            private String expiry_Date;
            private Double package_Amount;
            private Double box_Amount=0.0;
            private Integer connection_Status_ID;
            private String bill_Type;
            private String status_Name;
            private String boxTypeName;
            private String payment_Date;
            private Double paid_Amount;
            private String status;

            public Integer getInternet_Box_ID() {
                return internet_Box_ID;
            }

            public void setInternet_Box_ID(Integer internet_Box_ID) {
                this.internet_Box_ID = internet_Box_ID;
            }

            public Integer getCustomer_ID() {
                return customer_ID;
            }

            public void setCustomer_ID(Integer customer_ID) {
                this.customer_ID = customer_ID;
            }

            public Integer getBox_ID() {
                return box_ID;
            }

            public void setBox_ID(Integer box_ID) {
                this.box_ID = box_ID;
            }

            public Integer getBoxType_ID() {
                return boxType_ID;
            }

            public void setBoxType_ID(Integer boxType_ID) {
                this.boxType_ID = boxType_ID;
            }

            public String getIp() {
                return ip;
            }

            public void setIp(String ip) {
                this.ip = ip;
            }

            public String getMac() {
                return mac;
            }

            public void setMac(String mac) {
                this.mac = mac;
            }

            public Integer getBill_Type_ID() {
                return bill_Type_ID;
            }

            public void setBill_Type_ID(Integer bill_Type_ID) {
                this.bill_Type_ID = bill_Type_ID;
            }

            public Integer getNoofMonth() {
                return noofMonth;
            }

            public void setNoofMonth(Integer noofMonth) {
                this.noofMonth = noofMonth;
            }

            public String getActivation_Date() {
                return activation_Date;
            }

            public void setActivation_Date(String activation_Date) {
                this.activation_Date = activation_Date;
            }

            public String getExpiry_Date() {
                return expiry_Date;
            }

            public void setExpiry_Date(String expiry_Date) {
                this.expiry_Date = expiry_Date;
            }

            public Double getPackage_Amount() {
                return package_Amount;
            }

            public void setPackage_Amount(Double package_Amount) {
                this.package_Amount = package_Amount;
            }

            public Double getBox_Amount() {
                return box_Amount;
            }

            public void setBox_Amount(Double box_Amount) {
                this.box_Amount = box_Amount;
            }

            public Integer getConnection_Status_ID() {
                return connection_Status_ID;
            }

            public void setConnection_Status_ID(Integer connection_Status_ID) {
                this.connection_Status_ID = connection_Status_ID;
            }

            public String getBill_Type() {
                return bill_Type;
            }

            public void setBill_Type(String bill_Type) {
                this.bill_Type = bill_Type;
            }

            public String getStatus_Name() {
                return status_Name;
            }

            public void setStatus_Name(String status_Name) {
                this.status_Name = status_Name;
            }

            public String getBoxTypeName() {
                return boxTypeName;
            }

            public void setBoxTypeName(String boxTypeName) {
                this.boxTypeName = boxTypeName;
            }

            public String getPayment_Date() {
                return payment_Date;
            }

            public void setPayment_Date(String payment_Date) {
                this.payment_Date = payment_Date;
            }

            public Double getPaid_Amount() {
                return paid_Amount;
            }

            public void setPaid_Amount(Double paid_Amount) {
                this.paid_Amount = paid_Amount;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }

        public static class BoxpackageListDTO {
            /**
             * boxpackage : [{"box_Package_ID":1,"internet_Box_ID":1,"package_ID":1,"price":1,"tax_Percent":1,"tax_Amount":1,"pricewithouttax":1,"package":"sample string 1"},{"box_Package_ID":1,"internet_Box_ID":1,"package_ID":1,"price":1,"tax_Percent":1,"tax_Amount":1,"pricewithouttax":1,"package":"sample string 1"}]
             * status : sample string 1
             */

            private String status;
            private List<BoxpackageDTO> boxpackage;

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public List<BoxpackageDTO> getBoxpackage() {
                return boxpackage;
            }

            public void setBoxpackage(List<BoxpackageDTO> boxpackage) {
                this.boxpackage = boxpackage;
            }

            public static class BoxpackageDTO {
                /**
                 * box_Package_ID : 1
                 * internet_Box_ID : 1
                 * package_ID : 1
                 * price : 1.0
                 * tax_Percent : 1.0
                 * tax_Amount : 1.0
                 * pricewithouttax : 1.0
                 * package : sample string 1
                 */

                private Integer box_Package_ID;
                private Integer internet_Box_ID;
                private Integer package_ID;
                private Double price;
                private Double tax_Percent;
                private Double tax_Amount;
                private Double pricewithouttax;
                @SerializedName("package")
                private String packageX;

                public Integer getBox_Package_ID() {
                    return box_Package_ID;
                }

                public void setBox_Package_ID(Integer box_Package_ID) {
                    this.box_Package_ID = box_Package_ID;
                }

                public Integer getInternet_Box_ID() {
                    return internet_Box_ID;
                }

                public void setInternet_Box_ID(Integer internet_Box_ID) {
                    this.internet_Box_ID = internet_Box_ID;
                }

                public Integer getPackage_ID() {
                    return package_ID;
                }

                public void setPackage_ID(Integer package_ID) {
                    this.package_ID = package_ID;
                }

                public Double getPrice() {
                    return price;
                }

                public void setPrice(Double price) {
                    this.price = price;
                }

                public Double getTax_Percent() {
                    return tax_Percent;
                }

                public void setTax_Percent(Double tax_Percent) {
                    this.tax_Percent = tax_Percent;
                }

                public Double getTax_Amount() {
                    return tax_Amount;
                }

                public void setTax_Amount(Double tax_Amount) {
                    this.tax_Amount = tax_Amount;
                }

                public Double getPricewithouttax() {
                    return pricewithouttax;
                }

                public void setPricewithouttax(Double pricewithouttax) {
                    this.pricewithouttax = pricewithouttax;
                }

                public String getPackageX() {
                    return packageX;
                }

                public void setPackageX(String packageX) {
                    this.packageX = packageX;
                }
            }
        }
    }








    public static class CableBoxwithSubscriptionDTO {
        /**
         * cableBox : {"cable_Box_ID":null,"invoice_Box_ID":1131,"customer_ID":null,"box_ID":null,"boxType_ID":2,"vcno":"2V425","stbno":"2S425","cafno":"2C425","iPtv":"2I425","bill_Type_ID":3,"noofMonth":1,"noofDays":null,"activation_Date":"2021-05-09T00:00:00Z","expiry_Date":"2021-06-09T00:00:00Z","alacarte_Amount":null,"bouquet_Amount":null,"box_Amount":300,"connection_Status_ID":null,"bill_Type":"DateWise","status_Name":null,"boxTypeName":"APSFL","payment_Date":null,"paid_Amount":null,"status":null}
         * boxAlacareteList : {"boxAlacarete":[{"box_Alacarte_ID":null,"cable_Box_ID":null,"alacarte_ID":1,"price":22.42,"tax_per":0,"tax":3.42,"total_Price":null,"alacarte_Name":"Star Plus"},{"box_Alacarte_ID":null,"cable_Box_ID":null,"alacarte_ID":3,"price":22.42,"tax_per":0,"tax":3.42,"total_Price":null,"alacarte_Name":"Sony TV"},{"box_Alacarte_ID":null,"cable_Box_ID":null,"alacarte_ID":4,"price":22.42,"tax_per":0,"tax":3.42,"total_Price":null,"alacarte_Name":"Colors"}]}
         * boxBouquetList : {"boxBouquet":[{"box_Bouquet_ID":null,"cable_Box_ID":null,"bouquet_ID":2,"price":250,"tax_per":0,"tax":38.14,"total_Price":null,"bouquet_Name":"250 Package"}]}
         */

        private CableBoxDTO cableBox;
        private BoxAlacareteListDTO boxAlacareteList;
        private BoxBouquetListDTO boxBouquetList;

        public CableBoxDTO getCableBox() {
            return cableBox;
        }

        public void setCableBox(CableBoxDTO cableBox) {
            this.cableBox = cableBox;
        }

        public BoxAlacareteListDTO getBoxAlacareteList() {
            return boxAlacareteList;
        }

        public void setBoxAlacareteList(BoxAlacareteListDTO boxAlacareteList) {
            this.boxAlacareteList = boxAlacareteList;
        }

        public BoxBouquetListDTO getBoxBouquetList() {
            return boxBouquetList;
        }

        public void setBoxBouquetList(BoxBouquetListDTO boxBouquetList) {
            this.boxBouquetList = boxBouquetList;
        }

        public static class CableBoxDTO {
            /**
             * cable_Box_ID : null
             * invoice_Box_ID : 1131
             * customer_ID : null
             * box_ID : null
             * boxType_ID : 2
             * vcno : 2V425
             * stbno : 2S425
             * cafno : 2C425
             * iPtv : 2I425
             * bill_Type_ID : 3
             * noofMonth : 1
             * noofDays : null
             * activation_Date : 2021-05-09T00:00:00Z
             * expiry_Date : 2021-06-09T00:00:00Z
             * alacarte_Amount : null
             * bouquet_Amount : null
             * box_Amount : 300.0
             * connection_Status_ID : null
             * bill_Type : DateWise
             * status_Name : null
             * boxTypeName : APSFL
             * payment_Date : null
             * paid_Amount : null
             * status : null
             */

            private Object cable_Box_ID;
            private int invoice_Box_ID;
            private Object customer_ID;
            private Object box_ID;
            private int boxType_ID;
            private String vcno="";
            private String stbno="";
            private String cafno="";
            private String iPtv;
            private int bill_Type_ID;
            private int noofMonth;
            private Object noofDays;
            private String activation_Date;
            private String expiry_Date;
            private Object alacarte_Amount;
            private Object bouquet_Amount;
            private double box_Amount;
            private Object connection_Status_ID;
            private String bill_Type;
            private Object status_Name;
            private String boxTypeName;
            private Object payment_Date;
            private Object paid_Amount;
            private Object status;

            public Object getCable_Box_ID() {
                return cable_Box_ID;
            }

            public void setCable_Box_ID(Object cable_Box_ID) {
                this.cable_Box_ID = cable_Box_ID;
            }

            public int getInvoice_Box_ID() {
                return invoice_Box_ID;
            }

            public void setInvoice_Box_ID(int invoice_Box_ID) {
                this.invoice_Box_ID = invoice_Box_ID;
            }

            public Object getCustomer_ID() {
                return customer_ID;
            }

            public void setCustomer_ID(Object customer_ID) {
                this.customer_ID = customer_ID;
            }

            public Object getBox_ID() {
                return box_ID;
            }

            public void setBox_ID(Object box_ID) {
                this.box_ID = box_ID;
            }

            public int getBoxType_ID() {
                return boxType_ID;
            }

            public void setBoxType_ID(int boxType_ID) {
                this.boxType_ID = boxType_ID;
            }

            public String getVcno() {
                return vcno;
            }

            public void setVcno(String vcno) {
                this.vcno = vcno;
            }

            public String getStbno() {
                return stbno;
            }

            public void setStbno(String stbno) {
                this.stbno = stbno;
            }

            public String getCafno() {
                return cafno;
            }

            public void setCafno(String cafno) {
                this.cafno = cafno;
            }

            public String getIPtv() {
                return iPtv;
            }

            public void setIPtv(String iPtv) {
                this.iPtv = iPtv;
            }

            public int getBill_Type_ID() {
                return bill_Type_ID;
            }

            public void setBill_Type_ID(int bill_Type_ID) {
                this.bill_Type_ID = bill_Type_ID;
            }

            public int getNoofMonth() {
                return noofMonth;
            }

            public void setNoofMonth(int noofMonth) {
                this.noofMonth = noofMonth;
            }

            public Object getNoofDays() {
                return noofDays;
            }

            public void setNoofDays(Object noofDays) {
                this.noofDays = noofDays;
            }

            public String getActivation_Date() {
                return activation_Date;
            }

            public void setActivation_Date(String activation_Date) {
                this.activation_Date = activation_Date;
            }

            public String getExpiry_Date() {
                return expiry_Date;
            }

            public void setExpiry_Date(String expiry_Date) {
                this.expiry_Date = expiry_Date;
            }

            public Object getAlacarte_Amount() {
                return alacarte_Amount;
            }

            public void setAlacarte_Amount(Object alacarte_Amount) {
                this.alacarte_Amount = alacarte_Amount;
            }

            public Object getBouquet_Amount() {
                return bouquet_Amount;
            }

            public void setBouquet_Amount(Object bouquet_Amount) {
                this.bouquet_Amount = bouquet_Amount;
            }

            public double getBox_Amount() {
                return box_Amount;
            }

            public void setBox_Amount(double box_Amount) {
                this.box_Amount = box_Amount;
            }

            public Object getConnection_Status_ID() {
                return connection_Status_ID;
            }

            public void setConnection_Status_ID(Object connection_Status_ID) {
                this.connection_Status_ID = connection_Status_ID;
            }

            public String getBill_Type() {
                return bill_Type;
            }

            public void setBill_Type(String bill_Type) {
                this.bill_Type = bill_Type;
            }

            public Object getStatus_Name() {
                return status_Name;
            }

            public void setStatus_Name(Object status_Name) {
                this.status_Name = status_Name;
            }

            public String getBoxTypeName() {
                return boxTypeName;
            }

            public void setBoxTypeName(String boxTypeName) {
                this.boxTypeName = boxTypeName;
            }

            public Object getPayment_Date() {
                return payment_Date;
            }

            public void setPayment_Date(Object payment_Date) {
                this.payment_Date = payment_Date;
            }

            public Object getPaid_Amount() {
                return paid_Amount;
            }

            public void setPaid_Amount(Object paid_Amount) {
                this.paid_Amount = paid_Amount;
            }

            public Object getStatus() {
                return status;
            }

            public void setStatus(Object status) {
                this.status = status;
            }
        }

        public static class BoxAlacareteListDTO {
            private List<BoxAlacareteDTO> boxAlacarete;

            public List<BoxAlacareteDTO> getBoxAlacarete() {
                return boxAlacarete;
            }

            public void setBoxAlacarete(List<BoxAlacareteDTO> boxAlacarete) {
                this.boxAlacarete = boxAlacarete;
            }

            public static class BoxAlacareteDTO {
                /**
                 * box_Alacarte_ID : null
                 * cable_Box_ID : null
                 * alacarte_ID : 1
                 * price : 22.42
                 * tax_per : 0.0
                 * tax : 3.42
                 * total_Price : null
                 * alacarte_Name : Star Plus
                 */

                private Object box_Alacarte_ID;
                private Object cable_Box_ID;
                private int alacarte_ID;
                private double price;
                private double tax_per;
                private double tax;
                private Object total_Price;
                private String alacarte_Name;

                public Object getBox_Alacarte_ID() {
                    return box_Alacarte_ID;
                }

                public void setBox_Alacarte_ID(Object box_Alacarte_ID) {
                    this.box_Alacarte_ID = box_Alacarte_ID;
                }

                public Object getCable_Box_ID() {
                    return cable_Box_ID;
                }

                public void setCable_Box_ID(Object cable_Box_ID) {
                    this.cable_Box_ID = cable_Box_ID;
                }

                public int getAlacarte_ID() {
                    return alacarte_ID;
                }

                public void setAlacarte_ID(int alacarte_ID) {
                    this.alacarte_ID = alacarte_ID;
                }

                public double getPrice() {
                    return price;
                }

                public void setPrice(double price) {
                    this.price = price;
                }

                public double getTax_per() {
                    return tax_per;
                }

                public void setTax_per(double tax_per) {
                    this.tax_per = tax_per;
                }

                public double getTax() {
                    return tax;
                }

                public void setTax(double tax) {
                    this.tax = tax;
                }

                public Object getTotal_Price() {
                    return total_Price;
                }

                public void setTotal_Price(Object total_Price) {
                    this.total_Price = total_Price;
                }

                public String getAlacarte_Name() {
                    return alacarte_Name;
                }

                public void setAlacarte_Name(String alacarte_Name) {
                    this.alacarte_Name = alacarte_Name;
                }
            }
        }

        public static class BoxBouquetListDTO {
            private List<BoxBouquetDTO> boxBouquet;

            public List<BoxBouquetDTO> getBoxBouquet() {
                return boxBouquet;
            }

            public void setBoxBouquet(List<BoxBouquetDTO> boxBouquet) {
                this.boxBouquet = boxBouquet;
            }

            public static class BoxBouquetDTO {
                /**
                 * box_Bouquet_ID : null
                 * cable_Box_ID : null
                 * bouquet_ID : 2
                 * price : 250.0
                 * tax_per : 0.0
                 * tax : 38.14
                 * total_Price : null
                 * bouquet_Name : 250 Package
                 */

                private Object box_Bouquet_ID;
                private Object cable_Box_ID;
                private int bouquet_ID;
                private double price;
                private double tax_per;
                private double tax;
                private Object total_Price;
                private String bouquet_Name;

                public Object getBox_Bouquet_ID() {
                    return box_Bouquet_ID;
                }

                public void setBox_Bouquet_ID(Object box_Bouquet_ID) {
                    this.box_Bouquet_ID = box_Bouquet_ID;
                }

                public Object getCable_Box_ID() {
                    return cable_Box_ID;
                }

                public void setCable_Box_ID(Object cable_Box_ID) {
                    this.cable_Box_ID = cable_Box_ID;
                }

                public int getBouquet_ID() {
                    return bouquet_ID;
                }

                public void setBouquet_ID(int bouquet_ID) {
                    this.bouquet_ID = bouquet_ID;
                }

                public double getPrice() {
                    return price;
                }

                public void setPrice(double price) {
                    this.price = price;
                }

                public double getTax_per() {
                    return tax_per;
                }

                public void setTax_per(double tax_per) {
                    this.tax_per = tax_per;
                }

                public double getTax() {
                    return tax;
                }

                public void setTax(double tax) {
                    this.tax = tax;
                }

                public Object getTotal_Price() {
                    return total_Price;
                }

                public void setTotal_Price(Object total_Price) {
                    this.total_Price = total_Price;
                }

                public String getBouquet_Name() {
                    return bouquet_Name;
                }

                public void setBouquet_Name(String bouquet_Name) {
                    this.bouquet_Name = bouquet_Name;
                }
            }
        }
    }

    public static class PaymentMasterDTO {
        /**
         * payment_Id : 350
         * paymentType_ID : null
         * paymentType : GPAY
         * payment_Date : 2021-04-13T00:00:00Z
         * employee_Name : eLife Admin
         * employee_ID : null
         * paid_Amount : 200.0
         * transaction_No : null
         */

        private int payment_Id;
        private Object paymentType_ID;
        private String paymentType;
        private String payment_Date;
        private String employee_Name;
        private Object employee_ID;
        private String paid_Amount;
        private Object transaction_No;

        public String getPaid_Amount() {
            return paid_Amount;
        }

        public void setPaid_Amount(String paid_Amount) {
            this.paid_Amount = paid_Amount;
        }

        public int getPayment_Id() {
            return payment_Id;
        }

        public void setPayment_Id(int payment_Id) {
            this.payment_Id = payment_Id;
        }

        public Object getPaymentType_ID() {
            return paymentType_ID;
        }

        public void setPaymentType_ID(Object paymentType_ID) {
            this.paymentType_ID = paymentType_ID;
        }

        public String getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(String paymentType) {
            this.paymentType = paymentType;
        }

        public String getPayment_Date() {
            return payment_Date;
        }

        public void setPayment_Date(String payment_Date) {
            this.payment_Date = payment_Date;
        }

        public String getEmployee_Name() {
            return employee_Name;
        }

        public void setEmployee_Name(String employee_Name) {
            this.employee_Name = employee_Name;
        }

        public Object getEmployee_ID() {
            return employee_ID;
        }

        public void setEmployee_ID(Object employee_ID) {
            this.employee_ID = employee_ID;
        }


        public Object getTransaction_No() {
            return transaction_No;
        }

        public void setTransaction_No(Object transaction_No) {
            this.transaction_No = transaction_No;
        }
    }
}
