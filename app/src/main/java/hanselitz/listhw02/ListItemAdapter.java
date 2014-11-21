package hanselitz.listhw02;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by Hansel Itz on 20/11/2014.
 */

public class ListItemAdapter extends BaseAdapter{

    //Variables
    private Context mContext = null;
    private ArrayList <ListItem> arrayListItem = null;
    private LayoutInflater itemLayoutInflater = null;

    public ListItemAdapter (Context context, ArrayList <ListItem> arrayList) {
        mContext = context;
        itemLayoutInflater = LayoutInflater.from(context);
        arrayListItem = arrayList;
    }

    @Override
    public Object getItem(int position) {
        return arrayListItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public int getCount (){
        return arrayListItem.size();
    }

    static class Holder {
        ImageView Image;
        TextView Enterprise;
        TextView Giro;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        View view = convertView;
        //Check if view is null
        if (view == null){
            holder = new Holder ();
            view = itemLayoutInflater.inflate(R.layout.activity_list_item,null);
            holder.Image = (ImageView) view.findViewById(R.id.image);
            holder.Enterprise = (TextView) view.findViewById(R.id.txtEnterprise);
            holder.Giro = (TextView) view.findViewById(R.id.txtGiro);
            view.setTag(holder);
        }
        else{
            holder=(Holder)view.getTag();

        }
        holder.Image.setImageDrawable(arrayListItem.get(position).getImageUser());
        holder.Enterprise.setText(arrayListItem.get(position).getHeader());
        holder.Giro.setText(arrayListItem.get(position).getSubHeader());
        return view;
    }
}