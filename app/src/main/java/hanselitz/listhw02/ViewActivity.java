package hanselitz.listhw02;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class ViewActivity extends AddActivity {
    private Public p = Public.getInstance();
    private ArrayList <ListItem> mArraylist = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);


        ListView listView = (ListView) findViewById(R.id.list_elements);
        mArraylist = new ArrayList<ListItem>();

        //ListItem listItemViewCo = (ListItem)getApplication();
        mArraylist = p.getArrayListItem();
        final ListItemAdapter listItemAdapter = new ListItemAdapter(getBaseContext(), mArraylist);
        listView.setAdapter(listItemAdapter);
        //notify the adapter the list has changed with new data and any view reflecting the data set should refresh itself..
        listItemAdapter.notifyDataSetChanged();

        //set on click listeners
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //show header of the item clicked on a toast
                Toast.makeText(getBaseContext(), mArraylist.get(i).getHeader() + " pressed", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

