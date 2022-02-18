package com.elifeindia.crm.model;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.util.List;

public class InternetBoxWithSubscription extends JSONObject {


    private List<InternetBoxwithSubscriptionDTO> internetBoxwithSubscription;
    private String total_InternetBox_amount;

    public String getTotal_InternetBox_amount() {
        return total_InternetBox_amount;
    }

    public void setTotal_InternetBox_amount(String total_InternetBox_amount) {
        this.total_InternetBox_amount = total_InternetBox_amount;
    }

    public List<InternetBoxwithSubscriptionDTO> getInternetBoxwithSubscription() {
        return internetBoxwithSubscription;
    }

    public void setInternetBoxwithSubscription(List<InternetBoxwithSubscriptionDTO> internetBoxwithSubscription) {
        this.internetBoxwithSubscription = internetBoxwithSubscription;
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
            private Double box_Amount;
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
}
