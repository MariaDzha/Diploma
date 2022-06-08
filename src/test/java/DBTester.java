
import org.junit.jupiter.api.Assertions;

import java.sql.*;

public class DBTester {

    public String getLastStatusCredit() {
        String status = null;
        var creditRequestSQL = "SELECT id, bank_id, created, status FROM credit_request_entity ORDER BY created DESC LIMIT 1";

        try (
                Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/app", "postgres", "postgres");
        ) {
            PreparedStatement dataOrder = conn.prepareStatement(creditRequestSQL);
            ResultSet resultSet = dataOrder.executeQuery();
            resultSet.next();

            String id = resultSet.getString("id");

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

    public String getLastStatus() {
        String status = null;

        var orderSQL = "SELECT id, created, credit_id, payment_id FROM order_entity ORDER BY created DESC LIMIT 1";
        var paymentSQL = "SELECT id, amount, created, status, transaction_id FROM payment_entity WHERE transaction_id = ?";
        try (
                Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/app", "postgres", "postgres");
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

    public int getLastAmount () {
        Integer amount = null;

        var orderSQL = "SELECT id, created, credit_id, payment_id FROM order_entity ORDER BY created DESC LIMIT 1";
        var paymentSQL = "SELECT id, amount, created, status, transaction_id FROM payment_entity WHERE transaction_id = ?";
        try (
                Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/app", "postgres", "postgres");
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

