// Copyright 2016 Google Inc.
// 
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
// 
//      http://www.apache.org/licenses/LICENSE-2.0
// 
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.googleio;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.widget.TextView;

import java.security.SecureRandom;
import java.util.Random;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main_done);
        //setContentView(R.layout.activity_main_start);

        setContentView(R.layout.activity_add_view_programatically);
        uiInitialization();
    }

    private void uiInitialization () {
        int color = (Build.VERSION.SDK_INT < 23)
                ? this.getResources().getColor(android.R.color.holo_green_light)
                : this.getResources().getColor(android.R.color.holo_green_light, null);

        int margin = (int)(10f * getResources().getDisplayMetrics().density);

        String text = "Hello World";
        Random rand = new SecureRandom();
        // 0 to 100 inclusive.
        int number = rand.nextInt(100000);

        TextView textView = new TextView(this);
        textView.setId(number);
        textView.setBackgroundColor(color);
        textView.setText(text);

        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(
                // wrap content case
                //ConstraintLayout.LayoutParams.WRAP_CONTENT,
                //ConstraintLayout.LayoutParams.WRAP_CONTENT);

                // match parent case:
                0,0);
        ConstraintLayout container = findViewById(R.id.mainContainer);
        container.addView(textView, params);

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(container);
        constraintSet.connect(textView.getId(), ConstraintSet.START, container.getId(), ConstraintSet.START,margin);
        constraintSet.connect(textView.getId(), ConstraintSet.END, container.getId(), ConstraintSet.END,margin);
        constraintSet.connect(textView.getId(), ConstraintSet.TOP, container.getId(), ConstraintSet.TOP,margin);
        constraintSet.connect(textView.getId(), ConstraintSet.BOTTOM, container.getId(), ConstraintSet.BOTTOM,margin);
        constraintSet.applyTo(container);
    }
}
