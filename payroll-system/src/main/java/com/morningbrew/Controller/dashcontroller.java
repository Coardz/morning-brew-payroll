package com.morningbrew.Controller;

import java.io.IOException;

import com.morningbrew.MainApp;

public class dashcontroller {
  

     private void swithtoHome() {
        try {
            MainApp.setRoot("dashboard");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    /////////////////////////////////////////////////
     private void swithtoPayslip() {
        try {
            MainApp.setRoot("payslip");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /////////////////////////////////////////////////
     private void swithtoResume() {
        try {
            MainApp.setRoot("resume");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    /////////////////////////////////////////////////
     private void swithtoattendance() {
        try {
            MainApp.setRoot("attendance");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}   
