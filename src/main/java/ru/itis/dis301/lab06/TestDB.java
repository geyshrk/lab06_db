package ru.itis.dis301.lab06;

import java.sql.*;

public class TestDB {
    public static void main(String[] args) {

        try {
            DBConnection.getInstance();
            Connection connection =
                    DBConnection.getConnection();

            Statement statement = connection.createStatement();
            //Boolean result = statement.execute("create table users(id bigint primary key, name varchar(50))");

            int count = statement.executeUpdate(
                    "insert into users (id,name) values (3,'Misha')  ");


            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM users where id= ? ");
            preparedStatement.setLong(1, 1);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println(resultSet.getLong("id"));
                System.out.println(resultSet.getString("name"));
            }

            resultSet.close();
            System.out.println(count);
            statement.close();
            DBConnection.releaseConnection(connection);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
