package project.database;

import project.classes.*;
import project.csv.Audit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    public Audit audit = Audit.getInstance();

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/restaurant";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "12veronica34";

    public static Connection getDBConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
    }

    /* CHEFS */

    private static final String GET_ALL_CHEFS = "SELECT * FROM chefs";
    private static final String GET_ORDERED_CHEFS_BY_HIREDATE= "SELECT * FROM chefs order by salary";
    private static final String ADD_NEW_CHEF = "INSERT INTO chefs values(NULL, ?, ?, ?, ?)";
    private static final String DELETE_CHEF = "DELETE FROM chefs WHERE name = ?";

    public List<Chef> getChefs() throws SQLException {
        List<Chef> returnedList = new ArrayList<>();
        PreparedStatement preparedStatement = getDBConnection().prepareStatement(GET_ALL_CHEFS);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Chef chef = new Chef(resultSet.getInt(1),
                                 resultSet.getString(2),
                                 resultSet.getInt(3),
                                 resultSet.getString(4),
                                 resultSet.getInt(5));
            returnedList.add(chef);
        }
        return returnedList;
    }

    public List<Chef> getOrderedChefsBySalary() throws SQLException {
        List<Chef> returnedList = new ArrayList<>();
        PreparedStatement preparedStatement = getDBConnection().prepareStatement(GET_ORDERED_CHEFS_BY_HIREDATE);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Chef chef = new Chef(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getString(4),
                    resultSet.getInt(5));
            returnedList.add(chef);
        }
        return returnedList;
    }

    public boolean addChef(Chef chef) throws SQLException {
        PreparedStatement preparedStatement = getDBConnection().prepareStatement(ADD_NEW_CHEF);
        preparedStatement.setString(1, chef.getName());
        preparedStatement.setInt(2, chef.getAge());
        preparedStatement.setString(3, chef.getHireDate());
        preparedStatement.setInt(4, chef.getSalary());
        return preparedStatement.executeUpdate() > 0;
    }

        public boolean deleteChef(String name) throws SQLException {
        PreparedStatement preparedStatement = getDBConnection().prepareStatement(DELETE_CHEF);
        preparedStatement.setString(1, name);
        return preparedStatement.executeUpdate() > 0;
    }

    public boolean updateChef(String column, int value, int chefId) throws SQLException {
        String UPDATE_CHEF = "UPDATE chefs SET " + column + " = ? WHERE chefId = ? ";
        PreparedStatement preparedStatement = getDBConnection().prepareStatement(UPDATE_CHEF);
        preparedStatement.setInt(1, value);
        preparedStatement.setInt(2, chefId);
        return preparedStatement.executeUpdate() > 0;
    }

    /* WAITERS */

    private static final String GET_ALL_WAITERS = "SELECT * FROM waiters";
    private static final String ADD_NEW_WAITER = "INSERT INTO waiters values(NULL, ?, ?, ?, ?)";
    private static final String DELETE_WAITER = "DELETE FROM waiters WHERE name = ?";

    public List<Waiter> getWaiters() throws SQLException {
        List<Waiter> returnedList = new ArrayList<>();
        PreparedStatement preparedStatement = getDBConnection().prepareStatement(GET_ALL_WAITERS);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Waiter waiter = new Waiter(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getString(4),
                    resultSet.getInt(5));
            returnedList.add(waiter);
        }
        return returnedList;
    }

    public boolean addWaiter(Waiter waiter) throws SQLException {
        PreparedStatement preparedStatement = getDBConnection().prepareStatement(ADD_NEW_WAITER);
        preparedStatement.setString(1, waiter.getName());
        preparedStatement.setInt(2, waiter.getAge());
        preparedStatement.setString(3, waiter.getHireDate());
        preparedStatement.setInt(4, waiter.getSalary());
        return preparedStatement.executeUpdate() > 0;
    }

    public boolean deleteWaiter(String waiterName) throws SQLException {
        PreparedStatement preparedStatement = getDBConnection().prepareStatement(DELETE_WAITER);
        preparedStatement.setString(1, waiterName);
        return preparedStatement.executeUpdate() > 0;
    }

    public boolean updateWaiter(String column, int value, int waiterId) throws SQLException {
        String UPDATE_WAITER = "UPDATE waiters SET " + column + " = ? WHERE waiterId = ? ";
        PreparedStatement preparedStatement = getDBConnection().prepareStatement(UPDATE_WAITER);
        preparedStatement.setInt(1, value);
        preparedStatement.setInt(2, waiterId);
        return preparedStatement.executeUpdate() > 0;
    }

    /* ORDERS */
    private static final String GET_ALL_ORDERS = "SELECT * FROM orders";
    private static final String ADD_NEW_ORDER = "INSERT INTO orders (orderMenu, orderPrice, chefId, waiterId) values(?, ?, ?, ?)";
    private static final String DELETE_ORDER = "DELETE FROM orders WHERE orderId = ?";

    public List<Order> getOrders() throws SQLException {
        List<Order> returnedList = new ArrayList<>();
        PreparedStatement preparedStatement = getDBConnection().prepareStatement(GET_ALL_ORDERS);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Order order = new Order(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getInt(4),
                    resultSet.getInt(5));
            returnedList.add(order);
        }
        return returnedList;
    }

    public boolean addOrder(Order order) throws SQLException {
        PreparedStatement preparedStatement = getDBConnection().prepareStatement(ADD_NEW_ORDER);
        preparedStatement.setString(1, order.getNewOrderMenu());
        preparedStatement.setDouble(2, order.getOrderPrice());
        preparedStatement.setInt(3, order.getChefId());
        preparedStatement.setInt(4, order.getWaiterId());
        return preparedStatement.executeUpdate() > 0;
    }

    public boolean deleteOrder(int orderId) throws SQLException {
        PreparedStatement preparedStatement = getDBConnection().prepareStatement(DELETE_ORDER);
        preparedStatement.setInt(1, orderId);
        return preparedStatement.executeUpdate() > 0;
    }

    public boolean updateOrderMenu(String orderMenu, int orderId) throws SQLException {
        String UPDATE_WAITER = "UPDATE orders SET orderMenu = ? WHERE orderId = ? ";
        PreparedStatement preparedStatement = getDBConnection().prepareStatement(UPDATE_WAITER);
        preparedStatement.setString(1, orderMenu);
        preparedStatement.setInt(2, orderId);
        return preparedStatement.executeUpdate() > 0;
    }

    public boolean updateOrderPrice(double orderPrice, int orderId) throws SQLException {
        String UPDATE_WAITER = "UPDATE orders SET orderPrice = ? WHERE orderId = ? ";
        PreparedStatement preparedStatement = getDBConnection().prepareStatement(UPDATE_WAITER);
        preparedStatement.setDouble(1, orderPrice);
        preparedStatement.setInt(2, orderId);
        return preparedStatement.executeUpdate() > 0;
    }

    /* FOOD */
    private static final String GET_ALL_FOOD = "SELECT * FROM foods";
    private static final String ADD_NEW_FOOD = "INSERT INTO foods (foodName, category, ingredients, amount) values(?, ?, ?, ?)";
    private static final String SEARCH_FOOD = "SELECT * FROM foods WHERE foodName = ?";

    public List<Food> getFoods() throws SQLException {
        List<Food> returnedList = new ArrayList<>();
        PreparedStatement preparedStatement = getDBConnection().prepareStatement(GET_ALL_FOOD);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Food food = new Food(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5));
            returnedList.add(food);
        }
        return returnedList;
    }

    public boolean addFood(Food food) throws SQLException {
        PreparedStatement preparedStatement = getDBConnection().prepareStatement(ADD_NEW_FOOD);
        preparedStatement.setString(1, food.getProductName());
        preparedStatement.setString(2, food.getCategory());
        preparedStatement.setString(3, food.getIngredients());
        preparedStatement.setInt(4, food.getAmount());
        return preparedStatement.executeUpdate() > 0;
    }

    public List<Food> searchFood(String foodName) throws SQLException {
        List<Food> returnedList = new ArrayList<>();
        PreparedStatement preparedStatement = getDBConnection().prepareStatement(SEARCH_FOOD);
        preparedStatement.setString(1, foodName);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Food food = new Food(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5));
            returnedList.add(food);
        }
        return returnedList;
    }

    /* DRINKS */

    private static final String GET_ALL_DRINKS = "SELECT * FROM drinks";
    private static final String ADD_NEW_DRINK = "INSERT INTO drinks (drinkName, category, sugary, spirituous) values(?, ?, ?, ?)";

    public List<Drink> getDrinks() throws SQLException {
        List<Drink> returnedList = new ArrayList<>();
        PreparedStatement preparedStatement = getDBConnection().prepareStatement(GET_ALL_DRINKS);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Drink drink = new Drink(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getBoolean(4),
                    resultSet.getBoolean(5));
            returnedList.add(drink);
        }
        return returnedList;
    }

    public boolean addDrink(Drink drink) throws SQLException {
        PreparedStatement preparedStatement = getDBConnection().prepareStatement(ADD_NEW_DRINK);
        preparedStatement.setString(1, drink.getProductName());
        preparedStatement.setString(2, drink.getCategory());
        preparedStatement.setBoolean(3, drink.isSugary());
        preparedStatement.setBoolean(4, drink.isSpirituous());
        return preparedStatement.executeUpdate() > 0;
    }
}