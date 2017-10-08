package com.example.kcastrop.coursecontrol;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import entities.Course;

/**
 * Created by AnDresLoPz on 10/8/2017.
 */

public class courseAdapter extends BaseAdapter {

    Activity mActivity;
    ArrayList<Course> courses ;

    public courseAdapter(Activity context, ArrayList<Course> navItems) {
        this.mActivity = context;
        this.courses = navItems;
    }

    @Override
    public int getCount() {
        return courses.size();
    }

    @Override
    public Object getItem(int i) {
        return courses.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i+1;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View nView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            nView = inflater.inflate(R.layout.course_item, null);
        }
        else {
            nView = view;
        }

        Course course = courses.get(i);
        TextView name = (TextView) nView.findViewById(R.id.name_course);
        TextView info = (TextView) nView.findViewById(R.id.info_course);

        name.setText( course.getName());
        info.setText( course.getCreator().getUsername());

        return nView;
    }
}
