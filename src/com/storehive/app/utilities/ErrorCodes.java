package com.storehive.app.utilities;

public class ErrorCodes {

	//login errors
	public static final String LOG_ERR_1 = "LOG_ERR_1"; //username does not exist
	public static final String LOG_ERR_2 = "LOG_ERR_2"; //password incorrect
	public static final String LOG_SUC_1 = "LOG_SUC_1"; //login success
	
	//register errors
	public static final String REG_SUC_1 = "REG_SUC_1"; //register success
	public static final String REG_ERR_1 = "REG_ERR_1"; //register error null pointer
	public static final String REG_ERR_2 = "REG_ERR_2"; //register error username exists
	
	//categories errors
	public static final String CAT_SUC_1 = "CAT_SUC_1"; //creation success
	public static final String CAT_ERR_1 = "CAT_ERR_1"; //register error null pointer
	public static final String CAT_ERR_2 = "CAT_ERR_2"; //category error category name exists
	public static final String CAT_ERR_3 = "CAT_ERR_3"; //category error category update fail
	
	//store errors
	public static final String STORE_SUC_1 = "STORE_SUC_1"; //creation success
	public static final String STORE_ERR_1 = "STORE_ERR_1"; //register error null pointer
	public static final String STORE_ERR_2 = "STORE_ERR_2"; //
	public static final String STORE_ERR_3 = "STORE_ERR_3"; //store error update fail
	
	//product errors
	public static final String PROD_SUC_1 = "PROD_SUC_1"; //creation success
	public static final String PROD_ERR_1 = "PROD_ERR_1"; //register error null pointer
	public static final String PROD_ERR_2 = "PROD_ERR_2"; //
	public static final String PROD_ERR_3 = "PROD_ERR_3"; //product error update fail
}
