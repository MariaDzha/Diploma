import org.testng.Assert;
import java.sql.*;

public class DBTester {

    public void checkLastOrder(String expectedStatus, Integer expectedAmount) {
        var orderSQL = "SELECT id, created, credit_id, payment_id FROM order_entity ORDER BY created DESC LIMIT 1";
        var paymentSQL = "SELECT id, amount, created, status, transaction_id FROM payment_entity WHERE transaction_id = ?";
        try(
                Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/app", "postgres", "postgres");
        )
        {
            PreparedStatement dataOrder = conn.prepareStatement(orderSQL);
            ResultSet resultSet = dataOrder.executeQuery();
            resultSet.next();
            System.out.println(resultSet);

            String payment_id = resultSet.getString("payment_id");

            PreparedStatement dataPayment = conn.prepareStatement(paymentSQL);
            dataPayment.setString(1, payment_id);
            resultSet = dataPayment.executeQuery();
            resultSet.next();
            System.out.println(resultSet);

            String status = resultSet.getString("status");
            Integer amount = resultSet.getInt("amount");

            if (expectedStatus == "DECLINED") {
            Assert.assertEquals(expectedStatus, status, "wrong status");
            }
            else {

                Assert.assertEquals(expectedAmount, amount, "wrong amount");
                Assert.assertEquals(expectedStatus, status, "wrong status");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void checkLastOrderCredit (String expectedStatus) {
        var creditRequestSQL = "SELECT id, bank_id, created, status FROM credit_request_entity ORDER BY created DESC LIMIT 1";

        try(
                Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/app", "postgres", "postgres");
        )
        {
            PreparedStatement dataOrder = conn.prepareStatement(creditRequestSQL);
            ResultSet resultSet = dataOrder.executeQuery();
            resultSet.next();
            System.out.println(resultSet);

            String id = resultSet.getString("id");

            PreparedStatement dataPayment = conn.prepareStatement(creditRequestSQL);
            dataPayment.setString(1, id);
            resultSet = dataPayment.executeQuery();
            resultSet.next();
            System.out.println(resultSet);

            String status = resultSet.getString("status");
            Assert.assertEquals(expectedStatus, status, "wrong status");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
