package com.kanted.androidcalendarapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sikanted on 11/18/2015.
 */
public class ThreeHorizontalTextViewsAdapter extends ArrayAdapter<ThreeStrings> {

    private int layoutResource;

    public ThreeHorizontalTextViewsAdapter(Context context, int layoutResource, List<ThreeStrings> threeStringsList) {
        super(context, layoutResource, threeStringsList);
        this.layoutResource = layoutResource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(layoutResource, null);
        }

        ThreeStrings threeStrings = getItem(position);

        if (threeStrings != null) {
            TextView leftTextView = (TextView) view.findViewById(R.id.firstTextView);
            TextView rightTextView = (TextView) view.findViewById(R.id.thirdTextView);
            TextView centreTextView = (TextView) view.findViewById(R.id.secondTextView);

            if (leftTextView != null) {
                leftTextView.setText(threeStrings.getFirst());
            }

            if (rightTextView != null) {
                rightTextView.setText(threeStrings.getSecond());
            }

            if (centreTextView != null) {
                centreTextView.setText(threeStrings.getThird());
            }
        }

        return view;
    }
}
