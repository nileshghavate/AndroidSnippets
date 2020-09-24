package com.dbit.tryxml;

import android.app.Application;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class
XMLPullParserHandler extends Application {

    List<Employee> employees;

    private Employee employee;
    private String text;

    // We initialize the ArrayList
    public XMLPullParserHandler() {
        employees = new ArrayList<Employee>();
    }


    // Returns the list of employees
    public List<Employee> getEmployees() {
        return employees;
    }

    // Does the job of parsing

    public List<Employee> parse(InputStream is) {
        XmlPullParserFactory factory = null;
        XmlPullParser parser = null;
        try {
            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);

            parser = factory.newPullParser();
            parser.setInput(is, null);

            int eventType = parser.getEventType();
            Log.i("XPPH","EventType "+eventType);

            Log.i("XPPH","XmlPullParser.START_DOCUMENT "+XmlPullParser.START_DOCUMENT);
            Log.i("XPPH","XmlPullParser.END_DOCUMENT "+XmlPullParser.END_DOCUMENT);
            Log.i("XPPH","XmlPullParser.START_TAG"+XmlPullParser.START_TAG);
            Log.i("XPPH","XmlPullParser.TEXT "+XmlPullParser.TEXT);
            Log.i("XPPH","XmlPullParser.END_TAG"+XmlPullParser.END_TAG);

            while (eventType != XmlPullParser.END_DOCUMENT) {
                Log.i("XPPH","EventType "+eventType);

                String tagname = parser.getName();
                Log.i("XPPH","TagName "+tagname);

                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase("employee")) {
                            // create a new instance of employee
                            employee = new Employee();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        Log.i("XPPH","text : "+text);
                        break;

                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase("employee")) {
                            // add employee object to list
                            employees.add(employee);
                            Log.i("XPPH","employee added to employees array ");
                        } else if (tagname.equalsIgnoreCase("name")) {
                            employee.setName(text);
                            Log.i("XPPH","Name added to employee ");
                        } else if (tagname.equalsIgnoreCase("id")) {
                            employee.setId(Integer.parseInt(text));
                        } else if (tagname.equalsIgnoreCase("department")) {
                            employee.setDepartment(text);
                        } else if (tagname.equalsIgnoreCase("email")) {
                            employee.setEmail(text);
                        } else if (tagname.equalsIgnoreCase("type")) {
                            employee.setType(text);
                        }
                        break;

                    default:
                        break;
                }
                eventType = parser.next();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return employees;
    }
}