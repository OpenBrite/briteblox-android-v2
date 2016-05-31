package com.openbrite.briteblox;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;

public class WearableActivity extends Activity {
    protected Context ctx;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wearable);

        ctx = this;

        GridLayout layout = (GridLayout) findViewById(R.id.ledGrid);

        for (int f = 0; f < 13*8; f++) {
            DragButton b = new DragButton (ctx, f);
            b.setMinimumHeight(1);
            b.setMinimumWidth(1);
            layout.addView(b);
        }
    }

}
