package com.neo.ticketingapp.validation;

import org.junit.Test;

public class GeneralUtilsTest {

    @Test
    public void isName() {
        GeneralUtils g1 = new GeneralUtils();
        System.out.println(g1.isName("Sathira Gura", "First Name"));
    }

    @Test
    public void isValidEmailAddress() {
        GeneralUtils g1 = new GeneralUtils();
        System.out.println(g1.isEmail("enzolu@gmail.com"));
    }

    @Test
    public void isPhone() {
        GeneralUtils g1 = new GeneralUtils();
        System.out.println(g1.isPhone("0770036042"));
    }

    @Test
    public void isPassword() {
        GeneralUtils g1 = new GeneralUtils();
        System.out.println(g1.isPassword("Satgl3"));
    }

    @Test
    public void encryptPassword() {
        GeneralUtils g1 = new GeneralUtils();
        System.out.println(g1.encryptPassword("Test123#"));
    }
}