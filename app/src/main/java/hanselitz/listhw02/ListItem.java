package hanselitz.listhw02;

import android.graphics.drawable.Drawable;
import android.os.Bundle;


public class ListItem {

    //Variable
    private String header = null;
    private String subHeader = null;
    private Drawable imageUser = null;

    public String getHeader() {


        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }


    public String getSubHeader() {
        return subHeader;
    }

    public void setSubHeader(String subHeader) {
        this.subHeader = subHeader;
    }

    public Drawable getImageUser() {
        return imageUser;
    }

    public void setImageUser(Drawable imageUser) {
        this.imageUser = imageUser;
    }



}