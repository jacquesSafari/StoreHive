package com.store.hive.utils;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.telephony.TelephonyManager;

import java.util.Vector;

public class AccountsManager {
    private static AccountsManager instance;

    public AccountsManager(){
        instance = this;
    }

    public static AccountsManager getInstance(){
        if(instance==null) instance = new AccountsManager();
        return instance;
    }

    public String[] getAccounts(Context context){
        AccountManager accountMgr = AccountManager.get(context);
        Account[] accounts = accountMgr.getAccounts();
        Vector bundle = new Vector();

        for (int k=0;k<accounts.length;k++) {
           if(accounts[k].name.indexOf("@")!=-1 && accounts[k].name.indexOf(".",accounts[k].name.indexOf("@")+1)!=-1 && !bundle.contains(accounts[k].name)) bundle.add(accounts[k].name);
        }


        String[] results = new String[bundle.size()];
        for (int k=0;k<bundle.size();k++) {
            results[k] = (String)bundle.get(k);
        }

        int n = results.length;
        String swap="";
        for (int c = 0; c < ( n - 1 ); c++) {
            for (int d = 0; d < n - c - 1; d++) {
                if (results[d].compareTo(results[d+1])<0)
                {
                    swap       = results[d];
                    results[d]   = results[d+1];
                    results[d+1] = swap;
                }
            }
        }

        return results;
    }

        public String fetchName(String emailAddress){
            String cleanedData="";
            String dataStr = emailAddress.split("@")[0];
            int curChar;
            for(int k=0;k<dataStr.length();k++){
                curChar = dataStr.substring(k,k+1).toCharArray()[0];
                if((curChar>='A' && curChar<='Z') || (curChar>='a' && curChar<='z')){
                    cleanedData+=dataStr.substring(k,k+1);
                }else{
                    cleanedData+=" ";
                }
            }
            return cleanedData;
        }

    public String getMobileNumber(Context context){
        TelephonyManager tm = (TelephonyManager)context.getSystemService(context.TELEPHONY_SERVICE);
        String theNumber = tm.getLine1Number();

            if(theNumber==null || theNumber.length()==0){
                String strUriInbox = "content://sms/inbox";
                Uri uriSmsConversations = Uri.parse(strUriInbox);
                String strOrder = "date";
                Cursor cConversation = context.getContentResolver().query(uriSmsConversations, null, null, null, strOrder);

                while (cConversation.moveToNext())
                {
                    try
                    {
                        theNumber=cConversation.getString(cConversation.getColumnIndex("address"));
                        if(theNumber!=null && theNumber.length()>0) break;
                    }
                    catch (Exception e)
                    {
                    }
                }
            }

        return theNumber;
    }
}