package com.rajateck.wael.raja.connection;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;
import com.rajateck.wael.raja.delegates.CheckWarrantyDelegate;
import com.rajateck.wael.raja.delegates.ContactUsDelegate;
import com.rajateck.wael.raja.delegates.GetOffersListDelegate;
import com.rajateck.wael.raja.delegates.networkDelegates.GetAccessoriesListDelegate;
import com.rajateck.wael.raja.delegates.networkDelegates.GetAndroidAppsDelegate;
import com.rajateck.wael.raja.delegates.networkDelegates.GetHardWareDelegare;
import com.rajateck.wael.raja.delegates.networkDelegates.GetMobileListDelegate;
import com.rajateck.wael.raja.delegates.networkDelegates.GetNewsListDelegate;
import com.rajateck.wael.raja.models.AccessoryItem;
import com.rajateck.wael.raja.models.AndroidApplication;
import com.rajateck.wael.raja.models.ContactNode;
import com.rajateck.wael.raja.models.HardWare;
import com.rajateck.wael.raja.models.Mobile;
import com.rajateck.wael.raja.models.News;
import com.rajateck.wael.raja.models.OfferItem;
import com.rajateck.wael.raja.models.WarrentyCheckDetails;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by wael on 2/8/17.
 */

public class Connection {


    private static final String TAG = "Connection: ";

    public static void getMobilesList(final GetMobileListDelegate getMobileListDelegate,
                                      final Context context) {
        try {

            try {
                System.out.println("Connection : Mobile list  URL  = " + APIEndpoints.GetMobileList.getLink());
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            Ion.with(context)
                    .load("GET", APIEndpoints.GetMobileList.getLink())
                    .setTimeout(30000)
                    .addHeader("Content-Type", "application/json")
                    .asString()
                    .withResponse()
                    .setCallback(new FutureCallback<Response<String>>() {
                        @Override
                        public void onCompleted(Exception e, Response<String> result) {
                            try {
                                if (e == null) {
                                    String responseCode = "" + result.getHeaders().code();

                                    try {
                                        System.out.println("Connection.onCompleted : mobile list result code= " + responseCode);

                                        try {

                                        } catch (Exception ex) {
                                            ex.printStackTrace();
                                        }
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }

                                    if (responseCode.equalsIgnoreCase(ResponseCodes.Success.getCode())) {

                                        try {
                                            JSONArray jsonArray = new JSONArray(result.getResult().toString());
                                            System.out.println("Connection.onCompleted : mobile list result = " + jsonArray.toString());
                                            ArrayList<Mobile> mobiles = Mobile.getMoblieList(jsonArray);
                                            if (mobiles == null) {
                                                getMobileListDelegate.getMobileListConnectionErrorDelegate();
                                            } else {
                                                getMobileListDelegate.getMobileListSuccessDelegate(mobiles, true);
                                            }
                                        } catch (Exception ex) {
                                            ex.printStackTrace();
                                            getMobileListDelegate.getMobileListConnectionErrorDelegate();
                                        }

                                    } else {
                                        Log.e(TAG, "ResponseCode is strange: " + responseCode);
                                        getMobileListDelegate.getMobileListConnectionErrorDelegate();
                                    }
                                } else {
                                    Log.e(TAG, "There are error in connection");
                                    getMobileListDelegate.getMobileListConnectionErrorDelegate();
                                }

                            } catch (Exception exp) {
                                Log.d(TAG, " the Exception during connection");
                                getMobileListDelegate.getMobileListConnectionErrorDelegate();
                                exp.printStackTrace();
                            }
                        }
                    });

        } catch (Exception e) {
            Log.d(TAG, " the Exception 1 during connection");
            getMobileListDelegate.getMobileListConnectionErrorDelegate();
            e.printStackTrace();
        }
    }


    public static void submitContactUs(final ContactUsDelegate contactUsDelegate,
                                       final Context context,
                                       final String name,
                                       final String message,
                                       final String mobileNumber) {
        try {

            try {
                System.out.println("Connection : Mobile list  URL  = " + APIEndpoints.SubmitContactUs.getLink());
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            JsonObject parameters = new JsonObject();
            parameters.addProperty("title", name);
            parameters.addProperty("body[und][0][value]", message);
            parameters.addProperty("type", "contact");
            parameters.addProperty("field_mobile[und][0][value]", mobileNumber);

            System.out.println("Connection.submitContactUs: the body =  " + parameters.toString());


            Ion.with(context)
                    .load("POST", APIEndpoints.SubmitContactUs.getLink())
                    .setTimeout(30000)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .setHeader("Authorization", "Basic c3VwZXJoZXJvOmNoYW5nZU1lIVNEUlRIR0pHSEdGRFNGR2g=")
                    .setBodyParameter("title", name)
                    .setBodyParameter("body[und][0][value]",message)
                    .setBodyParameter("type","contact")
                    .setBodyParameter("field_mobile[und][0][value]",mobileNumber)
                    .asString()
                    .withResponse()
                    .setCallback(new FutureCallback<Response<String>>() {
                        @Override
                        public void onCompleted(Exception e, Response<String> result) {
                            try {
                                if (e == null) {
                                    String responseCode = "" + result.getHeaders().code();

                                    try {
                                        System.out.println("Connection.onCompleted : mobile list result code= " + responseCode);

                                        try {

                                        } catch (Exception ex) {
                                            ex.printStackTrace();
                                        }
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }

                                    if (responseCode.equalsIgnoreCase(ResponseCodes.Success.getCode())) {

                                        try {
                                            JSONObject jsonArray = new JSONObject(result.getResult().toString());
                                            System.out.println("Connection.onCompleted : contact node list result = " + jsonArray.toString());
                                            ContactNode contactNode = new ContactNode(jsonArray);
                                            if (contactNode == null) {
                                                contactUsDelegate.submitContactUsConnectionErrorDelegate();
                                            } else {
                                                contactUsDelegate.submitContactUsDelegateSuccess(contactNode);
                                            }
                                        } catch (Exception ex) {
                                            ex.printStackTrace();
                                            contactUsDelegate.submitContactUsConnectionErrorDelegate();
                                        }

                                    } else {
                                        Log.e(TAG, "ResponseCode is strange: " + responseCode);
                                        contactUsDelegate.submitContactUsConnectionErrorDelegate();
                                    }
                                } else {
                                    Log.e(TAG, "There are error in connection");
                                    contactUsDelegate.submitContactUsConnectionErrorDelegate();
                                }

                            } catch (Exception exp) {
                                Log.d(TAG, " the Exception during connection");
                                contactUsDelegate.submitContactUsConnectionErrorDelegate();
                                exp.printStackTrace();
                            }
                        }
                    });

        } catch (Exception e) {
            Log.d(TAG, " the Exception 1 during connection");
            contactUsDelegate.submitContactUsConnectionErrorDelegate();
            e.printStackTrace();
        }
    }

    public static void getNewsList(final GetNewsListDelegate getNewsListDelegate,
                                   final Context context) {
        try {

            try {
                System.out.println("Connection : Home list  URL  = " + APIEndpoints.GetHomeList.getLink());
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            Ion.with(context)
                    .load("GET", APIEndpoints.GetHomeList.getLink())
                    .setTimeout(30000)
                    .addHeader("Content-Type", "application/json")
                    .asString()
                    .withResponse()
                    .setCallback(new FutureCallback<Response<String>>() {
                        @Override
                        public void onCompleted(Exception e, Response<String> result) {
                            try {
                                if (e == null) {
                                    String responseCode = "" + result.getHeaders().code();

                                    try {
                                        System.out.println("Connection.onCompleted : mobile list result code= " + responseCode);

                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }

                                    if (responseCode != null && responseCode.equalsIgnoreCase(ResponseCodes.Success.getCode())) {

                                        try {
                                            JSONArray jsonArray = new JSONArray(result.getResult().toString());
                                            System.out.println("Connection.onCompleted : News list result = " + jsonArray.toString());
                                            ArrayList<News> news = News.getNewsList(jsonArray);
                                            if (news == null) {
                                                getNewsListDelegate.getNewsListConnectionErrorDelegate();
                                            } else {
                                                getNewsListDelegate.getNewsListSuccessDelegate(news, true);
                                            }
                                        } catch (Exception ex) {
                                            ex.printStackTrace();
                                            getNewsListDelegate.getNewsListConnectionErrorDelegate();
                                        }

                                    } else {
                                        Log.e(TAG, "ResponseCode is strange: " + responseCode);
                                        getNewsListDelegate.getNewsListConnectionErrorDelegate();
                                    }
                                } else {
                                    Log.e(TAG, "There are error in connection");
                                    getNewsListDelegate.getNewsListConnectionErrorDelegate();
                                }

                            } catch (Exception exp) {
                                Log.d(TAG, " the Exception during connection");
                                getNewsListDelegate.getNewsListConnectionErrorDelegate();
                                exp.printStackTrace();
                            }
                        }
                    });

        } catch (Exception e) {
            Log.d(TAG, " the Exception 1 during connection");
            getNewsListDelegate.getNewsListConnectionErrorDelegate();
            e.printStackTrace();
        }
    }


    public static void getAccessories(final GetAccessoriesListDelegate getAccessoriesListDelegate,
                                      final Context context) {
        try {

            try {
                System.out.println("Connection : Accessories list  URL  = " + APIEndpoints.GetAccessoriesLink.getLink());
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            Ion.with(context)
                    .load("GET", APIEndpoints.GetAccessoriesLink.getLink())
                    .setTimeout(30000)
                    .addHeader("Content-Type", "application/json")
                    .asString()
                    .withResponse()
                    .setCallback(new FutureCallback<Response<String>>() {
                        @Override
                        public void onCompleted(Exception e, Response<String> result) {
                            try {
                                if (e == null) {
                                    String responseCode = "" + result.getHeaders().code();

                                    try {
                                        System.out.println("Connection.onCompleted : mobile list result code= " + responseCode);

                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }

                                    if (responseCode.equalsIgnoreCase(ResponseCodes.Success.getCode())) {

                                        try {
                                            JSONArray jsonArray = new JSONArray(result.getResult().toString());
                                            System.out.println("Connection.onCompleted : Accessories list result = " + jsonArray.toString());
                                            ArrayList<AccessoryItem> accessoryItems = AccessoryItem.getAccessoriesList(jsonArray);
                                            if (accessoryItems == null) {
                                                getAccessoriesListDelegate.getAccessoryListConnectionErrorDelegate();
                                            } else {
                                                getAccessoriesListDelegate.getAccessoryListSuccessDelegate(accessoryItems, true);
                                            }
                                        } catch (Exception ex) {
                                            ex.printStackTrace();
                                            getAccessoriesListDelegate.getAccessoryListConnectionErrorDelegate();
                                        }

                                    } else {
                                        Log.e(TAG, "ResponseCode is strange: " + responseCode);
                                        getAccessoriesListDelegate.getAccessoryListConnectionErrorDelegate();
                                    }
                                } else {
                                    Log.e(TAG, "There are error in connection");
                                    getAccessoriesListDelegate.getAccessoryListConnectionErrorDelegate();
                                }

                            } catch (Exception exp) {
                                Log.d(TAG, " the Exception during connection");
                                getAccessoriesListDelegate.getAccessoryListConnectionErrorDelegate();
                                exp.printStackTrace();
                            }
                        }
                    });

        } catch (Exception e) {
            Log.d(TAG, " the Exception 1 during connection");
            getAccessoriesListDelegate.getAccessoryListConnectionErrorDelegate();
            e.printStackTrace();
        }
    }


    public static void getHardWare(final GetHardWareDelegare getHardWareDelegare,
                                   final Context context) {
        try {

            try {
                System.out.println("Connection : Accessories list  URL  = " + APIEndpoints.GetHardWare.getLink());
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            Ion.with(context)
                    .load("GET", APIEndpoints.GetHardWare.getLink())
                    .setTimeout(30000)
                    .addHeader("Content-Type", "application/json")
                    .asString()
                    .withResponse()
                    .setCallback(new FutureCallback<Response<String>>() {
                        @Override
                        public void onCompleted(Exception e, Response<String> result) {
                            try {
                                if (e == null) {
                                    String responseCode = "" + result.getHeaders().code();

                                    try {
                                        System.out.println("Connection.onCompleted : mobile list result code= " + responseCode);

                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }

                                    if (responseCode.equalsIgnoreCase(ResponseCodes.Success.getCode())) {

                                        try {
                                            JSONArray jsonArray = new JSONArray(result.getResult().toString());
                                            System.out.println("Connection.onCompleted : Accessories list result = " + jsonArray.toString());
                                            ArrayList<HardWare> hardWaresItems = HardWare.getHardWareList(jsonArray);
                                            if (hardWaresItems == null) {
                                                getHardWareDelegare.getHardWareListConnectionErrorDelegate();
                                            } else {
                                                getHardWareDelegare.getHardWareListSuccessDelegate(hardWaresItems, true);
                                            }
                                        } catch (Exception ex) {
                                            ex.printStackTrace();
                                            getHardWareDelegare.getHardWareListConnectionErrorDelegate();
                                        }

                                    } else {
                                        Log.e(TAG, "ResponseCode is strange: " + responseCode);
                                        getHardWareDelegare.getHardWareListConnectionErrorDelegate();
                                    }
                                } else {
                                    Log.e(TAG, "There are error in connection");
                                    getHardWareDelegare.getHardWareListConnectionErrorDelegate();
                                }

                            } catch (Exception exp) {
                                Log.d(TAG, " the Exception during connection");
                                getHardWareDelegare.getHardWareListConnectionErrorDelegate();
                                exp.printStackTrace();
                            }
                        }
                    });

        } catch (Exception e) {
            Log.d(TAG, " the Exception 1 during connection");
            getHardWareDelegare.getHardWareListConnectionErrorDelegate();
            e.printStackTrace();
        }
    }


    public static void getAndroidApplicationsList(final GetAndroidAppsDelegate getAndroidAppsDelegate,
                                                  final Context context) {
        try {

            try {
                System.out.println("Connection : Mobile list  URL  = " + APIEndpoints.GetAndroiApplicationsLink.getLink());
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            Ion.with(context)
                    .load("GET", APIEndpoints.GetAndroiApplicationsLink.getLink())
                    .setTimeout(30000)
                    .addHeader("Content-Type", "application/json")
                    .asString()
                    .withResponse()
                    .setCallback(new FutureCallback<Response<String>>() {
                        @Override
                        public void onCompleted(Exception e, Response<String> result) {
                            try {
                                if (e == null) {
                                    String responseCode = "" + result.getHeaders().code();

                                    try {
                                        System.out.println("Connection.onCompleted : mobile list result code= " + responseCode);

                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }

                                    if (responseCode.equalsIgnoreCase(ResponseCodes.Success.getCode())) {

                                        try {
                                            JSONArray jsonArray = new JSONArray(result.getResult().toString());
                                            System.out.println("Connection.onCompleted : mobile list result = " + jsonArray.toString());
                                            ArrayList<AndroidApplication> androidApplications = AndroidApplication.getAppsList(jsonArray);
                                            if (androidApplications == null) {
                                                getAndroidAppsDelegate.getAppsListConnectionErrorDelegate();
                                            } else {
                                                getAndroidAppsDelegate.getAppsListSuccessDelegate(androidApplications, true);
                                            }
                                        } catch (Exception ex) {
                                            ex.printStackTrace();
                                            getAndroidAppsDelegate.getAppsListConnectionErrorDelegate();
                                        }

                                    } else {
                                        Log.e(TAG, "ResponseCode is strange: " + responseCode);
                                        getAndroidAppsDelegate.getAppsListConnectionErrorDelegate();
                                    }
                                } else {
                                    Log.e(TAG, "There are error in connection");
                                    getAndroidAppsDelegate.getAppsListConnectionErrorDelegate();
                                }

                            } catch (Exception exp) {
                                Log.d(TAG, " the Exception during connection");
                                getAndroidAppsDelegate.getAppsListConnectionErrorDelegate();
                                exp.printStackTrace();
                            }
                        }
                    });

        } catch (Exception e) {
            Log.d(TAG, " the Exception 1 during connection");
            getAndroidAppsDelegate.getAppsListConnectionErrorDelegate();
            e.printStackTrace();
        }
    }


    public static void checkWarrnty(final CheckWarrantyDelegate checkWarrantyDelegate,
                                    final Context context,
                                    String IMEI_code) {
        try {
            String url = "http://beesolution.co/api/check-warranty?field_imei_2_value=" + IMEI_code + "&field_device_imei_number_value=" + IMEI_code;
            try {
                System.out.println("Connection : Mobile list  URL  = " + url);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            Ion.with(context)
                    .load("GET", url)
                    .setTimeout(30000)
                    .addHeader("Content-Type", "application/json")
                    .asString()
                    .withResponse()
                    .setCallback(new FutureCallback<Response<String>>() {
                        @Override
                        public void onCompleted(Exception e, Response<String> result) {
                            try {
                                if (e == null) {
                                    String responseCode = "" + result.getHeaders().code();

                                    try {
                                        System.out.println("Connection.onCompleted : mobile list result code= " + responseCode);

                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }

                                    if (responseCode.equalsIgnoreCase(ResponseCodes.Success.getCode())) {

                                        try {
                                            JSONArray jsonArray = new JSONArray(result.getResult().toString());
                                            System.out.println("Connection.onCompleted : mobile list result = " + jsonArray.toString());
                                            if (jsonArray.length() == 0) {
                                                checkWarrantyDelegate.getWarrantyDelegateFailure("Your mobile is not belong to our warranty system.");
                                            } else {
                                                WarrentyCheckDetails warrentyCheckDetails = new WarrentyCheckDetails(jsonArray.getJSONObject(0));

                                                if (warrentyCheckDetails != null &&
                                                        warrentyCheckDetails.getStartdate() != null) {
                                                    checkWarrantyDelegate.getWarrantyDelegateSuccess(warrentyCheckDetails);
                                                } else {
                                                    checkWarrantyDelegate.getWarrantyDelegateFailure("Error happened during validating IMEI Code");
                                                }
                                            }


                                        } catch (Exception ex) {
                                            ex.printStackTrace();
                                            checkWarrantyDelegate.getWarrantyDelegateFailure("Error happened during validating IMEI Code");
                                        }

                                    } else {
                                        Log.e(TAG, "ResponseCode is strange: " + responseCode);
                                        checkWarrantyDelegate.getWarrantyConnectionErrorDelegate();
                                    }
                                } else {
                                    Log.e(TAG, "There are error in connection");
                                    checkWarrantyDelegate.getWarrantyConnectionErrorDelegate();
                                }

                            } catch (Exception exp) {
                                Log.d(TAG, " the Exception during connection");
                                checkWarrantyDelegate.getWarrantyConnectionErrorDelegate();
                                exp.printStackTrace();
                            }
                        }
                    });

        } catch (Exception e) {
            Log.d(TAG, " the Exception 1 during connection");
            checkWarrantyDelegate.getWarrantyConnectionErrorDelegate();
            e.printStackTrace();
        }
    }


    public static void getOffersList(final GetOffersListDelegate getOffersListDelegate,
                                     final Context context) {
        try {

            try {
                System.out.println("Connection : Mobile list  URL  = " + APIEndpoints.GetOffersList.getLink());
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            Ion.with(context)
                    .load("GET", APIEndpoints.GetOffersList.getLink())
                    .setTimeout(30000)
                    .addHeader("Content-Type", "application/json")
                    .asString()
                    .withResponse()
                    .setCallback(new FutureCallback<Response<String>>() {
                        @Override
                        public void onCompleted(Exception e, Response<String> result) {
                            try {
                                if (e == null) {
                                    String responseCode = "" + result.getHeaders().code();

                                    try {
                                        System.out.println("Connection.onCompleted : mobile list result code= " + responseCode);

                                        try {

                                        } catch (Exception ex) {
                                            ex.printStackTrace();
                                        }
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }

                                    if (responseCode.equalsIgnoreCase(ResponseCodes.Success.getCode())) {

                                        try {
                                            JSONArray jsonArray = new JSONArray(result.getResult().toString());
                                            System.out.println("Connection.onCompleted : mobile list result = " + jsonArray.toString());
                                            ArrayList<OfferItem> offersItem = OfferItem.getOffersList(jsonArray);
                                            if (offersItem == null) {
                                                getOffersListDelegate.getOffersListConnectionErrorDelegate();
                                            } else {
                                                getOffersListDelegate.getOffersListSuccessDelegate(offersItem, true);
                                            }
                                        } catch (Exception ex) {
                                            ex.printStackTrace();
                                            getOffersListDelegate.getOffersListConnectionErrorDelegate();
                                        }

                                    } else {
                                        Log.e(TAG, "ResponseCode is strange: " + responseCode);
                                        getOffersListDelegate.getOffersListConnectionErrorDelegate();
                                    }
                                } else {
                                    Log.e(TAG, "There are error in connection");
                                    getOffersListDelegate.getOffersListConnectionErrorDelegate();
                                }

                            } catch (Exception exp) {
                                Log.d(TAG, " the Exception during connection");
                                getOffersListDelegate.getOffersListConnectionErrorDelegate();
                                exp.printStackTrace();
                            }
                        }
                    });

        } catch (Exception e) {
            Log.d(TAG, " the Exception 1 during connection");
            getOffersListDelegate.getOffersListConnectionErrorDelegate();
            e.printStackTrace();
        }
    }

}

