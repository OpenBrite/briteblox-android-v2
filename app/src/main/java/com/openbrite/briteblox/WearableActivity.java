package com.openbrite.briteblox;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;

public class WearableActivity extends Activity {
    protected Context ctx;
    protected Integer productColumns, productRows;
    protected DragButton[] buttons;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wearable);

        ctx = this;
        productColumns = MyApp.productColumns.get("Wearable");
        productRows = MyApp.productRows.get("Wearable");

        GridLayout layout = (GridLayout) findViewById(R.id.ledGrid);

        buttons = new DragButton[productColumns * productRows];
        int index;
        for (int row = 0; row < productRows; row++) {
            for (int col = 0; col < productColumns; col++) {
                index = (col * productRows) + row;
                DragButton b = new DragButton (ctx, index);
                b.setMinimumHeight(1);
                b.setMinimumWidth(1);
                layout.addView(b);
                buttons[index] = b;
            }
        }

        Button sendToWearable = (Button) findViewById(R.id.sendToWear);
        sendToWearable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder graphicEncoding = new StringBuilder();
                char nextByte = 0;
                int bitIndex;
                for (int f = 0; f < productColumns * productRows; f++) {
                    bitIndex = f % 8;
                    if (buttons[f].pixelIsActivated()) {
                        nextByte += 1 << bitIndex;
                    }
                    if (bitIndex == 7) {
                        graphicEncoding.append(nextByte);
                        Log.d("BblxWearable", "A char has been written (" + ((int) nextByte) + ")");
                        nextByte = 0;
                    }
                }
                DirectDrive.write(graphicEncoding.toString());
            }
        });
    }

}
