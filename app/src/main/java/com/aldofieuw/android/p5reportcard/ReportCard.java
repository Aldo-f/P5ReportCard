package com.aldofieuw.android.p5reportcard;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;



public class ReportCard extends AppCompatActivity {

// Mark.java
    public class Mark {

        /** name of the Class **/
        private String mClass;

        /** score of the class**/
        private int mScore;

        public Mark(String nameClass, int score){
            mClass = nameClass;
            mScore = score;
        }

        /**
         * get name class
         */
        public String getNameClass(){
            return mClass;
        }

        /**
         * get Score
         */
        public int  getScore()
        {
            return mScore;
        }
    }

    //ReportAdapter.java
    public static class ReportAdapter extends ArrayAdapter<Mark> {
        private static final String LOG_TAG = ReportAdapter.class.getSimpleName();

        public ReportAdapter(Activity context, ArrayList<Mark> marks){
            // here we initialize the ArrayAdapters's internal storage
            super(context, 0, marks);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            // Check if the existing view is being reused, otherwise inflate the view
            View listItemView = convertView;
            if (listItemView == null){
                listItemView = LayoutInflater.from(getContext()).inflate(
                        R.layout.list_item, parent, false);
            }

            // Get the {@link Mark} object located at this position in the list
            Mark currentMark = getItem(position);

            // Find the TextView in the list_item.xml layout
            TextView scoreTextView = (TextView) listItemView.findViewById(R.id.score_text_view);
            // Get the version name from the current AndroidFlavor object and
            // set this text on the name TextView
            String score ;  // Convert the int to a String

            if (currentMark.getScore() == -1){
                /* if student is absent you need to place a -1 score, this is converted to the string ABS
                * and changed to bold*/
               score = "ABS";
                scoreTextView.setTypeface(null,Typeface.BOLD);
            }else
            {
                score= String.valueOf(currentMark.getScore());
                if ( Integer.valueOf(score) < 10) {
                    /* if score is less than 10, then he/she did not pass the class
                    so the number will be in bold and the color will become red
                     */
                   scoreTextView.setTextColor(Color.RED);
                    scoreTextView.setTypeface(null,Typeface.BOLD);
                }
            }
            /* Set the score to the TextView */
            scoreTextView.setText(score);


            // Find the TextView in the list_item.xml layout with the class view
            TextView classTextView = (TextView) listItemView.findViewById(R.id.class_text_view);
            // Get the score for the current class
            classTextView.setText(currentMark.getNameClass());


            // Return the whole list item layout (containing 2 TextViews)
            return listItemView;

        }
    }

    // ReportCard.java
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView word_list.xml
        setContentView(R.layout.word_list);

        //Make ArrayList (Class) with the marks
        ArrayList<Mark> marks = new ArrayList<>();

        marks.add(new Mark("Mathematics A",14));
        marks.add(new Mark("Physics I",8));
        marks.add(new Mark("Mathematics A",10));
        marks.add(new Mark("Physics I",12));
        marks.add(new Mark("Signals and Systems",11));
        marks.add(new Mark("Digital Electronics",10));
        marks.add(new Mark("Operating Systems II",10));
        marks.add(new Mark("Computer networks III",10));
        marks.add(new Mark("Algorithms",-1));
        marks.add(new Mark("Programming in C ++",10));
        marks.add(new Mark("Databases 1 and 2",10));
        marks.add(new Mark("Cross-disciplinary final project",12));
        marks.add(new Mark("Hardware programming",10));

        /* Extra */
        marks.add(new Mark("Mathematics A",14));
        marks.add(new Mark("Physics I",8));
        marks.add(new Mark("Mathematics A",10));
        marks.add(new Mark("Physics I",12));
        marks.add(new Mark("Signals and Systems",11));
        marks.add(new Mark("Digital Electronics",10));
        marks.add(new Mark("Operating Systems II",10));
        marks.add(new Mark("Computer networks III",10));
        marks.add(new Mark("Algorithms",-1));
        marks.add(new Mark("Programming in C ++",10));
        marks.add(new Mark("Databases 1 and 2",10));
        marks.add(new Mark("Cross-disciplinary final project",12));
        marks.add(new Mark("Hardware programming",10));


        //Show the marks withe the ReportAdapter
        ReportAdapter adapter =
                new ReportAdapter(this, marks);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);

    }
}
