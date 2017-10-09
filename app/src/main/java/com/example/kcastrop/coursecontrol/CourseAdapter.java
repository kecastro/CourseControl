package com.example.kcastrop.coursecontrol;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import entities.Course;

/**
 * Created by AnDresLoPz on 10/8/2017.
 */

public class CourseAdapter extends BaseAdapter{

    private Activity mActivity;
    private ArrayList<Course> courses ;

    public CourseAdapter(){

    }

    public CourseAdapter(Activity context, ArrayList<Course> navItems) {
        this.setmActivity(context);
        this.setCourses(navItems);
    }

    @Override
    public int getCount() {
        return getCourses().size();
    }

    @Override
    public Object getItem(int i) {
        return getCourses().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i+1;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View nView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) getmActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            nView = inflater.inflate(R.layout.course_item, null);
        }
        else {
            nView = view;
        }

        Course course = getCourses().get(i);
        TextView name = (TextView) nView.findViewById(R.id.name_course);
        TextView info = (TextView) nView.findViewById(R.id.info_course);
        TextView id = (TextView) nView.findViewById(R.id.id_course);

        name.setText( course.getName());
        info.setText( course.getCreator().getEmail());
        id.setText(course.getCourseId());
        return nView;
    }

    public Activity getmActivity() {
        return mActivity;
    }

    public void setmActivity(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

}
