package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepositoryDb implements Repository {
    @Override
    public void create(Person person) {
        String sql = "insert into person (name, age) values (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            statement.setString(1, person.getName());
            statement.setInt(2, person.getAge());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Создание Person не удалось, ни одна строка не была затронута");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    long id = generatedKeys.getLong(1);
                    System.out.println("Person saved with id = " + id);
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при сохранении Person: " + e.getMessage());
            throw new RuntimeException("Ошибка сохранения Person", e);
        }
    }
//todo подумать id будет передаваться из вне или в person , пока в person
    @Override
    public void updateById(Person person) {
        String sql = "update person set name=?, age=? where id=?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, person.getName());
            statement.setInt(2, person.getAge());
            statement.setLong(3, person.getId());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                System.out.println("Person with id = " + person.getId() + " not found");

            } else {
                System.out.println("Person with id = " + person.getId() + " updated");
            }

        } catch (SQLException e) {
            System.err.println("Ошибка при обновлении Person: " + e.getMessage());
            throw new RuntimeException("Ошибка обновления Person", e);
        }
    }


    @Override
    public List<Person> getAll() {
        String sql = "select * from person";
        List<Person> persons = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery();
        ) {
            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getLong("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                persons.add(person);
            }
            System.out.println("✓ Загружено " + persons.size() + " записей");
            return persons;

        } catch (SQLException e) {
            System.err.println("Ошибка при получении всех Person: " + e.getMessage());
            throw new RuntimeException("Ошибка получения всех Person", e);
        }
    }

    @Override
    public Person getById(Long id) {
        String sql = "select * from person where id = ?" ;
        Person person = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    person = new Person();
                    person.setName(resultSet.getString("name"));
                    person.setAge(resultSet.getInt("age"));

                } else {
                    System.out.println("⚠ Person с id = " + id + " не найден");
                }
            }return person;

        } catch (SQLException e) {
            System.err.println("Ошибка при получении  Person: " + e.getMessage());
            throw new RuntimeException("Ошибка получения  Person", e);
        }
    }

    @Override
    public void deleteById(Long id) {
        String sql = "delete from person where id = ?";
        try(Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);){
            statement.setLong(1, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("Person with id = " + id+ " not found");
            }else {
                System.out.println("Person with id = " + id + " deleted");
            }

        } catch (SQLException e) {
            System.err.println("Ошибка при удалении  Person: " + e.getMessage());
            throw new RuntimeException("Ошибка удаления  Person", e);
        }
    }
}
