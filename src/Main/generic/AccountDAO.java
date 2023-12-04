package Main.generic;

import Main.configuration.DatabaseConfig;
import Main.entity.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO implements CrudOperations<Account>{
    public static Connection connection;

    public AccountDAO(Connection connection){
        this.connection= connection;
    }
    @Override
    public List<Account> getAll() {
        List<Account> accounts = new ArrayList<>();
        String sql = "Select * from Account";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String pay = resultSet.getString("pay");
                String accountType = resultSet.getString("account_type");
                int currencyId = resultSet.getInt("currency_id");
                String  createDate = resultSet.getString("create_date");

                Account account = new Account(id, pay, accountType, currencyId, createDate);
                accounts.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Account insert(Account insert) {
        String sql = "Insert into account (pay,account_type,currency,create_date) values(?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, insert.getPay());
            preparedStatement.setString(2, insert.getAccountType());
            preparedStatement.setInt(3, insert.getCurrencyId());
            preparedStatement.setString(4, insert.getCreateDate());

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
    public Account getById(Account getByid) {
        String sql = "Select * from account where id =?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, getByid.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String pay = resultSet.getString("pay");
                String accountType = resultSet.getString("account_type");
                int currencyId = resultSet.getInt("currency_id");
                String createDate = resultSet.getString("create_date");

                getByid.setPay(pay);
                getByid.setAccountType(accountType);
                getByid.setCurrencyId(currencyId);
                getByid.setCreateDate(createDate);

                return getByid;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Account delete(Account toDelete) {
        String sql = "Delete * from Account where id = ?";
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
    public Account put(Account put) {
        String query = "UPDATE account SET pay = ?, account_type = ?, currency_id = ?, create_date = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, put.getPay());
            preparedStatement.setString(2, put.getAccountType());
            preparedStatement.setInt(3, put.getCurrencyId());
            preparedStatement.setString(4, put.getCreateDate());
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
