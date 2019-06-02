package com.entry.activitystudy.contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.entry.activitystudy.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContentProviderActivity extends AppCompatActivity {


    private ListView listView;
    private ContentResolver cr;
    private List<Map<String, Object>> data;
    private SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);
        listView = (ListView) findViewById(R.id.lv_main_list);
        //获取内容访问者
        cr = getContentResolver();

        data =new ArrayList<Map<String,Object>>();
        sa = new SimpleAdapter(this, data,android.R.layout.simple_list_item_2,new String[]{"names","phones"},new int[]{android.R.id
                .text1,android.R.id.text2});
        listView.setAdapter(sa);

    }
    public void getContent(View view){
        Cursor cursor= cr.query(Uri.parse("content://com.android.contacts/raw_contacts"),null,null,null,null);
        while(cursor.moveToNext()){
            Map<String, Object> map=new HashMap<String, Object>();
            int id=  cursor.getColumnIndex("_id");
            String displayName=cursor.getString(cursor.getColumnIndex("display_name"));
            Log.i("test",id+displayName);
            map.put("names",displayName);

            //根据联系人获取联系人数据
            Cursor cursor2=cr.query(Uri.parse("content://com.android.contacts/raw_contacts/"+id+"/data"),null,null,null,null);
            while(cursor2.moveToNext()){
                //  int type=cursor2.getInt(cursor2.getColumnIndex("mimetype_id"));
                String type=cursor2.getString(cursor2.getColumnIndex("mimetype"));
                String data1=null;
                if ("vnd.android.cursor.item/phone_v2".equals(type)){
                    data1 = cursor2.getString(cursor2.getColumnIndex("data1"));
                    Log.i("test","   "+type+" "+data1);
                    map.put("phones",data1);
                }
            }

            data.add(map);
            //通知适配器发生改变
            sa.notifyDataSetChanged();
        }


    }

    public void getPhone(View view){
        Uri uri = Uri.parse("content://contacts/people");
        Intent intent = new Intent(Intent.ACTION_PICK, uri);
        startActivityForResult(intent, 0);

    }

    public static void actionStart(AppCompatActivity activity){
        Intent intent = new Intent(activity, ContentProviderActivity.class);
        activity.startActivity(intent);
    }
}

