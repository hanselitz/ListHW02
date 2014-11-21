package hanselitz.listhw02;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;



public class AddActivity extends Activity {

    private Public p = Public.getInstance();
    private ArrayList<ListItem> mArraylistAdd = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        Button btnAddPhoto = (Button) findViewById(R.id.btnAddPhoto);
        mArraylistAdd = new ArrayList<ListItem>();
        mArraylistAdd = p.getArrayListItem();
        final TextView txtCount = (TextView) findViewById(R.id.textCont);
        final TextView txtEnterprise = (TextView) findViewById(R.id.et_empresa);
        final TextView txtGiro = (TextView) findViewById(R.id.et_giro);
        final ImageView imagePrise = (ImageView) findViewById(R.id.imagePrise);

        txtCount.setText(""+mArraylistAdd.size()+"");

        btnAddPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ListItem listItem = new ListItem();
                listItem.setImageUser(imagePrise.getDrawable().getCurrent());
                listItem.setHeader(txtEnterprise.getText().toString());
                listItem.setSubHeader(txtGiro.getText().toString());

                mArraylistAdd.add(listItem);

                final ListItemAdapter listItemAdapter = new ListItemAdapter(getBaseContext(), mArraylistAdd);
                p.setArrayListItem(mArraylistAdd);
                txtCount.setText("" + mArraylistAdd.size() + "");
                txtEnterprise.setText("");
                txtGiro.setText("");
                imagePrise.setImageResource(R.drawable.noimageavailable);
            }
        });
    }

    private void selectImage() {

        final CharSequence[] options = { "Elegir de la galeria","Cancelar" };

        AlertDialog.Builder builder = new AlertDialog.Builder(AddActivity.this);
        builder.setTitle("Selecciona elemento");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Elegir de la galeria"))
                {
                    Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 1);

                }
                else if (options[item].equals("Cancelar")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        ImageView imgCoSet = (ImageView) findViewById(R.id.imagePrise);
         if (requestCode == 1) {

                Uri selectedImage = data.getData();
                String[] filePath = { MediaStore.Images.Media.DATA };
                Cursor c = getContentResolver().query(selectedImage,filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                Log.w("path of image from gallery......******************.........", picturePath + "");
                imgCoSet.setImageBitmap(thumbnail);
            }
        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add, menu);
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
