package com.jsonwong.newframework.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Authors：Administrator on 2016/4/7 23:32
 */
public class Person {


    /**
     * firstName : Bill
     * lastName : Gates
     */

    private List<EmployeesBean> employees;

    public static Person objectFromData(String str) {

        return new Gson().fromJson(str, Person.class);
    }

    public static Person objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), Person.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Person> arrayPersonFromData(String str) {

        Type listType = new TypeToken<ArrayList<Person>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<Person> arrayPersonFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<Person>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public List<EmployeesBean> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeesBean> employees) {
        this.employees = employees;
    }

    public static class EmployeesBean {
        private String firstName;
        private String lastName;

        public static EmployeesBean objectFromData(String str) {

            return new Gson().fromJson(str, EmployeesBean.class);
        }

        public static EmployeesBean objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), EmployeesBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static List<EmployeesBean> arrayEmployeesBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<EmployeesBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public static List<EmployeesBean> arrayEmployeesBeanFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);
                Type listType = new TypeToken<ArrayList<EmployeesBean>>() {
                }.getType();

                return new Gson().fromJson(jsonObject.getString(str), listType);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return new ArrayList();


        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }
}
