package org.kevin.magnial;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import org.kevin.magnial.view.AppConfig;
import org.kevin.magnial.view.SpolierTextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String text = "[剧透:这是一段剧透文字] 动态表情:teasing::teasing::teasing:  emoji   :anguished::apple::art:  #我是标签可以点击的# @王昭君  https://www.baidu.com";
        SpolierTextView spoiler_Text =  findViewById(R.id.tv_Spoiler);
        spoiler_Text.setData(AppConfig.getImageHtml(text));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
