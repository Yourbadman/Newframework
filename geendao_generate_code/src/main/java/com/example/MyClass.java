package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

public class MyClass {

    //数据库的版本
    private static final int VERSION = 1;
    //要生成数据库存储对象的路径，此路径比较重要，如果写错在执行的时候容易出现找不到路径的异常
    private static final String PATH = "../Newframework/app/src/main/java-gen";

    public static void main(String[] args) throws Exception {

        Schema schema = new Schema(VERSION, "com.json.greendao");

        //添加频道
        addChannelItem(schema, "ChannelItem");

        new DaoGenerator().generateAll(schema, PATH);
    }

    private static void addChannelItem(Schema schema, String name) {
        Entity note = schema.addEntity(name);
        note.implementsSerializable();
       /* public Integer id;

        public String name;

        public Integer orderId;

        public Integer selected;*/

        note.addIntProperty("id");
        note.addStringProperty("name").notNull();
        note.addIntProperty("orderId");
        note.addIntProperty("selected");
        note.addStringProperty("channelId");
    }

    private static void addNote(Schema schema) {
        Entity note = schema.addEntity("Note");
        note.addIdProperty();
        note.addStringProperty("text").notNull();
        note.addStringProperty("comment");
        note.addDateProperty("date");
    }

    private static void addCustomerOrder(Schema schema) {
        Entity customer = schema.addEntity("Customer");
        customer.addIdProperty();
        customer.addStringProperty("name").notNull();

        Entity order = schema.addEntity("Order");
        order.setTableName("ORDERS"); // "ORDER" is a reserved keyword
        order.addIdProperty();
        Property orderDate = order.addDateProperty("date").getProperty();
        Property customerId = order.addLongProperty("customerId").notNull().getProperty();
        order.addToOne(customer, customerId);

        ToMany customerToOrders = customer.addToMany(order, customerId);
        customerToOrders.setName("orders");
        customerToOrders.orderAsc(orderDate);
    }
}