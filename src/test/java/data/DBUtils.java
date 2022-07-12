package data;

import java.sql.*;

public class DBUtils {
    private DBUtils() {
    }

    public static String getLastStatusCredit() {
        String dbUrl = System.getProperty("db.url");
        String dbUser = System.getProperty("db.user");
        String dbPassword = System.getProperty("db.password");
        String status = null;
        var orderSQL = "SELECT id, created, credit_id, payment_id FROM order_entity ORDER BY created DESC LIMIT 1";
        var creditRequestSQL = "SELECT id, bank_id, created, status FROM credit_request_entity WHERE bank_id = ? ORDER BY created DESC LIMIT 1";

        try (
                Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        ) {
            PreparedStatement dataOrder = conn.prepareStatement(orderSQL);
            ResultSet resultSet = dataOrder.executeQuery();
            resultSet.next();

            String id = resultSet.getString("payment_id");

            PreparedStatement dataPayment = conn.prepareStatement(creditRequestSQL);
            dataPayment.setString(1, id);
            resultSet = dataPayment.executeQuery();
            resultSet.next();

            status = resultSet.getString("status");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public static String getLastStatus() {
        String dbUrl = System.getProperty("db.url");
        String dbUser = System.getProperty("db.user");
        String dbPassword = System.getProperty("db.password");
        String status = null;

        var orderSQL = "SELECT id, created, credit_id, payment_id FROM order_entity ORDER BY created DESC LIMIT 1";
        var paymentSQL = "SELECT id, amount, created, status, transaction_id FROM payment_entity WHERE transaction_id = ?";
        try (
                Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        ) {
            PreparedStatement dataOrder = conn.prepareStatement(orderSQL);
            ResultSet resultSet = dataOrder.executeQuery();
            resultSet.next();

            String payment_id = resultSet.getString("payment_id");

            PreparedStatement dataPayment = conn.prepareStatement(paymentSQL);
            dataPayment.setString(1, payment_id);
            resultSet = dataPayment.executeQuery();
            resultSet.next();

            status = resultSet.getString("status");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public static int getLastAmount () {
        String dbUrl = System.getProperty("db.url");
        String dbUser = System.getProperty("db.user");
        String dbPassword = System.getProperty("db.password");
        Integer amount = null;

        var orderSQL = "SELECT id, created, credit_id, payment_id FROM order_entity ORDER BY created DESC LIMIT 1";
        var paymentSQL = "SELECT id, amount, created, status, transaction_id FROM payment_entity WHERE transaction_id = ?";
        try (
                Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        ) {
            PreparedStatement dataOrder = conn.prepareStatement(orderSQL);
            ResultSet resultSet = dataOrder.executeQuery();
            resultSet.next();

            String payment_id = resultSet.getString("payment_id");

            PreparedStatement dataPayment = conn.prepareStatement(paymentSQL);
            dataPayment.setString(1, payment_id);
            resultSet = dataPayment.executeQuery();
            resultSet.next();

            amount = resultSet.getInt("amount");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return amount;
    }
}

