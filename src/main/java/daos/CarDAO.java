package daos;

import models.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDAO extends DAO<Car>{

    // Queries
    private static final String INSERT = "INSERT INTO cars" +
            "(make, model, year, color)" +
            "values(?,?,?,?)";
    private static final String FIND_ONE = "SELECT * FROM cars WHERE id = ?";
    private static final String FIND_ALL = "SELECT * FROM cars";
    private static final String UPDATE = "UPDATE cars SET id = ?, make = ?, model = ?, year = ?, color = ?  WHERE id = ?";
    private static final String DELETE = "DELETE FROM cars WHERE id = ?";

    public CarDAO(Connection connection) {
        super(connection);
    }


    public Car findById(int id) {
        Car car = null;
        try(PreparedStatement pstmt = connection.prepareStatement(FIND_ONE)){
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                car = getCarFromResultSet(rs);

            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return car;
    }

    public List<Car> findAll() {
        List<Car> cars = new ArrayList<>();
        Car car = null;

        try(PreparedStatement pstmt = connection.prepareStatement(FIND_ALL);){
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                car = getCarFromResultSet(rs);
                cars.add(car);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return cars;
    }

    public Car create(Car dto) {
        int key = -1;
        try(PreparedStatement pstmt = this.connection.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)){
            pstmt.setString(1, dto.getMake());
            pstmt.setString(2, dto.getModel());
            pstmt.setInt(3, dto.getYear());
            pstmt.setString(4, dto.getColor());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();

            if (rs != null && rs.next()) {
                key = rs.getInt(1);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return this.findById(key);
    }

    public Car update(Car dto) {
        Car car = null;
        try(PreparedStatement pstmt = this.connection.prepareStatement(UPDATE)) {
            pstmt.setInt(1, dto.getId());
            pstmt.setString(2, dto.getMake());
            pstmt.setString(3, dto.getModel());
            pstmt.setInt(4, dto.getYear());
            pstmt.setString(5, dto.getColor());
            pstmt.executeUpdate();
            car = this.findById(dto.getId());
        } catch(SQLException e){
            e.printStackTrace();
        }
        return car;
    }

    public void delete(int id) {
        try(PreparedStatement pstmt = this.connection.prepareStatement(DELETE)){
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public Car getCarFromResultSet(ResultSet rs) throws SQLException {

        Car car = new Car();
        car.setId(rs.getInt("id"));
        car.setMake(rs.getString("make"));
        car.setModel(rs.getString("model"));
        car.setYear(rs.getInt("year"));
        car.setColor(rs.getString("color"));
        return car;

    }

}

