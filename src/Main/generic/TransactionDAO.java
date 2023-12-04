package Main.generic;

import Main.entity.Account;
import Main.entity.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO implements CrudOperations<Transaction> {
    public static Connection connection;

    public TransactionDAO(Connection connection){
        this.connection= connection;
    }
    @Override
    public List<Transaction> getAll() {
        List<Account> accounts = new ArrayList<>();
        String sql = "Select * from Transaction";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String id_account = resultSet.getString("id_account");
                String rising = resultSet.getString("rising");
                int transaction_type = resultSet.getInt("transaction_type");
                String  currency = resultSet.getString("currency");

                Account account = new Account(id, id_account, rising, transaction_type, currency);
                accounts.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Transaction insert(Transaction insert) {
        String sql = "INSERT INTO transaction (id_account, rising, transaction_type, currency_id) VALUES (?, ?, ?, ?) RETURNING id";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, insert.getIdAccount());
            preparedStatement.setInt(2, insert.getRising());
            preparedStatement.setString(3, insert.getTransactionType());
            preparedStatement.setInt(4, insert.getCurrencyId());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int generatedId = resultSet.getInt(1);
                insert.setId(generatedId);
                return insert;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Transaction getById(Transaction getByid) {
        String sql = "SELECT * FROM transaction WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, getByid.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int idAccount = resultSet.getInt("id_account");
                int rising = resultSet.getInt("rising");
                String transactionType = resultSet.getString("transaction_type");
                int currencyId = resultSet.getInt("currency_id");

                getByid.setIdAccount(idAccount);
                getByid.setRising(rising);
                getByid.setTransactionType(transactionType);
                getByid.setCurrencyId(currencyId);

                return getByid;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Transaction delete(Transaction toDelete) {
        String sql = "DELETE FROM transaction WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, toDelete.getId());
            preparedStatement.executeUpdate();
            return toDelete;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Transaction put(Transaction put) {
        String query = "UPDATE transaction SET id_account = ?, rising = ?, transaction_type = ?, currency_id = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, put.getIdAccount());
            preparedStatement.setInt(2, put.getRising());
            preparedStatement.setString(3, put.getTransactionType());
            preparedStatement.setInt(4, put.getCurrencyId());
            preparedStatement.setInt(5, put.getId());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                return put;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
