package senac.controlefinanceiro;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionButton;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionHelper;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionLayout;
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RFACLabelItem;
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RapidFloatingActionContentLabelList;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity implements RapidFloatingActionContentLabelList.OnRapidFloatingActionContentLabelListListener {

    private RapidFloatingActionLayout rfaLayout;
    private RapidFloatingActionButton rfaBtn;
    private RapidFloatingActionHelper rfabHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            rfaLayout = findViewById(R.id.activity_main_rfal);
            rfaBtn = findViewById(R.id.activity_main_rfab);

            RapidFloatingActionContentLabelList rfaContent = new RapidFloatingActionContentLabelList(getApplicationContext());
            rfaContent.setOnRapidFloatingActionContentLabelListListener(this);
            List<RFACLabelItem> items = new ArrayList<>();
            items.add(new RFACLabelItem<Integer>()
                    .setLabel("Github: wangjiegulu")
                    .setResId(R.mipmap.ic_launcher)
                    .setIconNormalColor(0xffd84315)
                    .setIconPressedColor(0xffbf360c)
                    .setWrapper(0)
            );
            items.add(new RFACLabelItem<Integer>()
                    .setLabel("tiantian.china.2@gmail.com")
                    .setResId(R.mipmap.ic_launcher)
                    .setIconNormalColor(0xff4e342e)
                    .setIconPressedColor(0xff3e2723)
                    .setLabelColor(Color.WHITE)
                    .setLabelSizeSp(14)
                    .setWrapper(1)
            );
            items.add(new RFACLabelItem<Integer>()
                    .setLabel("WangJie")
                    .setResId(R.mipmap.ic_launcher)
                    .setIconNormalColor(0xff056f00)
                    .setIconPressedColor(0xff0d5302)
                    .setLabelColor(0xff056f00)
                    .setWrapper(2)
            );
            items.add(new RFACLabelItem<Integer>()
                    .setLabel("Compose")
                    .setResId(R.mipmap.ic_launcher)
                    .setIconNormalColor(0xff283593)
                    .setIconPressedColor(0xff1a237e)
                    .setLabelColor(0xff283593)
                    .setWrapper(3)
            );
            rfaContent
                    .setItems(items)
                    .setIconShadowColor(0xff888888)
            ;

            rfabHelper = new RapidFloatingActionHelper(
                    getApplicationContext(),
                    rfaLayout,
                    rfaBtn,
                    rfaContent
            ).build();

        } catch (Exception e){
            Log.e("main", "onCreate: " + e.getMessage());
        }
    }

    @Override
    public void onRFACItemLabelClick(int position, RFACLabelItem item) {
        Toast.makeText(getApplicationContext(), "clicked label: " + position, Toast.LENGTH_SHORT).show();
        rfabHelper.toggleContent();
    }

    @Override
    public void onRFACItemIconClick(int position, RFACLabelItem item) {
        Toast.makeText(getApplicationContext(), "clicked icon: " + position, Toast.LENGTH_SHORT).show();
        rfabHelper.toggleContent();
    }
}
