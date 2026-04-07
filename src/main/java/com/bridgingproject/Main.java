package com.bridgingproject;

import java.sql.*;

public class Main {
    //For connecting the Database
    public Connection getConnection(){
        Connection con = null;
        try {
            con = DriverManager.getConnection(
                    "JDBC:mysql://localhost:33060/test_db?useSSL=false&allowPublicKeyRetrieval=true",
                    "testuser", "testtest");
            //check the port number
            IO.println("Connected Successful."); //database connection
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return con;
    }

    public void office_data(Connection con)
    {
//        Select data from the database
        try{
            PreparedStatement statement = con.prepareStatement("SELECT * FROM offices");
            ResultSet result = statement.executeQuery();
            while (result.next()){
                System.out.println(result.getString(1) + "," + result.getString(2)
                        + ","+result.getString(3));
            }
            result.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void employee_data(Connection con)
    {
//        Select data from the database
        try{
            PreparedStatement statement = con.prepareStatement("SELECT e.*,o.city " +
                    "FROM offices o,employees e " +
                    "WHERE o.officeCode = e.officeCode " +
                    "AND city = ?");
            statement.setString(1,"Paris");

            ResultSet result = statement.executeQuery();
            while (result.next()){
                System.out.println(result.getString(1) + "," + result.getString(2)
                        + ","+result.getString(3));
            }
            result.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void insert_offices(Connection con, String officeCode, String city,
                               String phone, String addressLine1, String country,
                               String postalCode, String territory){
        try{
            PreparedStatement statement = con.prepareStatement("INSERT INTO offices(officeCode, city, phone, addressLine1, country, postalCode, territory) " +
                            "VALUES(?,?,?,?,?,?,?)");
            statement.setString(1,officeCode);
            statement.setString(2,city);
            statement.setString(3,phone);
            statement.setString(4,addressLine1);
            statement.setString(5,country);
            statement.setString(6,postalCode);
            statement.setString(7,territory);
            statement.execute();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update_city(Connection con, String city, String phone){
        try{
            PreparedStatement statement = con.prepareStatement("UPDATE offices SET city=?, phone=? WHERE officeCode=?");
            statement.setString(1,city);
            statement.setString(2,phone);
            statement.setString(3,"8");
            statement.execute();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void delete(Connection con){
        try{
            PreparedStatement statement = con.prepareStatement("DELETE FROM offices WHERE officeCode=?");
            statement.setString(1,"8");
            statement.execute();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void main() {
        Main m = new Main();
        Connection con = m.getConnection();
//        System.out.println("Insert Office Data");
//        m.insert_offices(con, "8","Yangon","+95 9785868493","Sanchaung", "Myanmar","11111","Asia");

        System.out.println("-------------Employee Data in Paris");
        m.employee_data(con);

//        System.out.println("Update Office Data");
//        m.update_city(con, "Mandalay","+95 9785868493");


        m.delete(con);
        System.out.println("Deleted Office Data");

        System.out.println("Select Data From Office");
        m.office_data(con); //Select data

        try {
            if(con != null){
                con.close();
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    //connection class
    //Statement class / PreparedStatement class (including sql injection, used when including the user input)
    //ResultSet class (For storing the output from the database) Start from Index 1

}
