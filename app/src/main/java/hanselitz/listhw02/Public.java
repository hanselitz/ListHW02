package hanselitz.listhw02;

import java.util.ArrayList;

/**
 * Created by Hansel Itz on 20/11/2014.
 */
public class Public {

    //Variables
    private static Public instance;
    private ArrayList<ListItem> arrayListItem = new ArrayList<ListItem>();

    public Public() {}

    public ArrayList<ListItem> getArrayListItem() {
        return arrayListItem;
    }

    public void setArrayListItem(ArrayList<ListItem> arrayListItem) {
        this.arrayListItem = arrayListItem;
    }

    //Get instance
    public static synchronized Public getInstance() {
        if (instance == null){
            instance = new Public();
        }
        return instance;
    }
}
