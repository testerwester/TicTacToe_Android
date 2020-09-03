package com.example.severalactivities;

public class Box {

    private boolean isFilled;
    private char sign;

    public Box(){
        resetBox();
    }

    public void resetBox(){
        isFilled = false;
        sign = 'a';
    }

    public void setFilled() {
        isFilled = true;
    }

    public boolean isFilled() {
        return isFilled;
    }

    public char getSign() {
        return sign;
    }

    public void setSign(char sign) {
        this.sign = sign;
    }
}
