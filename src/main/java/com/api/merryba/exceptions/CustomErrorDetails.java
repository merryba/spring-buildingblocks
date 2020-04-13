package com.api.merryba.exceptions;

import java.util.Date;

public class CustomErrorDetails {
	
	
	
		public CustomErrorDetails(Date date, String message, String errordetails) {
		super();
		this.date = date;
		this.message = message;
		this.errordetails = errordetails;
	}
		private Date date;
		private String message;
		private String errordetails;
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public String getErrordetails() {
			return errordetails;
		}
		public void setErrordetails(String errordetails) {
			this.errordetails = errordetails;
		}
		
		

}
