package Main.generic;

import Main.entity.Currency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CurrencyDAO implements CrudOperations<Currency>{
    private Connection connection;

    public CurrencyDAO(Connection connection) {
        this.connection = connection;
    }
    @Override
    public List<Currency> getAll() {
        List<Currency> currencies = new ArrayList<>();
        String sql = "SELECT * FROM currency";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String code = resultSet.getString("code");
                String name = resultSet.getString("name");
                int exchangeRate = resultSet.getInt("exchange_rate");

                Currency currency = new Currency(id, code, name, exchangeRate);
                currencies.add(currency);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Currency insert(Currency insert) {
        String sql = "INSERT INTO currency (code, name, exchange_rate) VALUES (?, ?, ?) RETURNING id";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, insert.getCode());
            preparedStatement.setString(2, insert.getName());
            preparedStatement.setInt(3, insert.getExchange_rate());

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
    public Currency getById(Currency getByid) {
        String sql = "SELECT * FROM currency WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, getByid.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String code = resultSet.getString("code");
                String name = resultSet.getString("name");
                int exchangeRate = resultSet.getInt("exchange_rate");

                getByid.setCode(code);
                getByid.setName(name);
                getByid.setExchange_rate(exchangeRate);

                return getByid;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Currency delete(Currency toDelete) {
        String query = "DELETE FROM currency WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, toDelete.getId());
            preparedStatement.executeUpdate();
            return toDelete;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Currency put(Currency put) {
        String sql = "UPDATE currency SET code = ?, name = ?, exchange_rate = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, put.getCode());
            preparedStatement.setString(2, put.getName());
            preparedStatement.setInt(3, put.getExchange_rate());
            preparedStatement.setInt(4, put.getId());

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
