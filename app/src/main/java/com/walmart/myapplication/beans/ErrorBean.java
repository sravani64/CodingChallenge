package com.walmart.myapplication.beans;

/**
 * Created by sravani on 5/12/17.
 */

public  class ErrorBean {

    private String errorMessage;
    private String errorStatus;

    public ErrorBean(){

    }
    public String getErrorStatus() {
        return errorStatus;
    }

    public void setErrorStatus(String errorStatus) {
        this.errorStatus = errorStatus;
    }


    public ErrorBean(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage(){
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
