package daos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAO implements DAO{

    // Queries
    private static final String INSERT = "INSERT INTO cars" +
            "(id, make, model, year, color)" +
            "values(?,?,?,?,?)";
    private static final String FIND_ALL = "SELECT * FROM cars";

    Connection connection = AppRunner.getConnection();

    public CarDTO findById(int id) {

        try{
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM cars WHERE id = " + id);
            if(rs.next()){
                return getCarFromResultSet(rs);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;

    }

    public List<CarDTO> findAll() {

        List<CarDTO> carDTOS = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(FIND_ALL);
            while(rs.next()){
                carDTOS.add(getCarFromResultSet(rs));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return carDTOS;
    }

    public Boolean create(CarDTO dto) {

        try{
            PreparedStatement pstmt = connection.prepareStatement(INSERT);
            pstmt.setInt(1, dto.getId());
            pstmt.setString(2, dto.getMake());
            pstmt.setString(3, dto.getModel());
            pstmt.setInt(4, dto.getYear());
            pstmt.setString(5, dto.getColor());
            int i = pstmt.executeUpdate();
            if(i == 1){
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;

    }

    public Boolean update(CarDTO dto) {

        try{
            PreparedStatement pstmt = connection.prepareStatement("UPDATE cars SET id=?, make=?, model=?, year=?, color = ? WHERE id=" + dto.getId());
            pstmt.setInt(1, dto.getId());
            pstmt.setString(2, dto.getMake());
            pstmt.setString(3, dto.getModel());
            pstmt.setInt(4, dto.getYear());
            pstmt.setString(5, dto.getColor());
            int i = pstmt.executeUpdate();
            if(i == 1){
                return true;
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return false;

    }

    public Boolean delete(int id) {

        try{
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM cars WHERE id = " + id);
            int i = pstmt.executeUpdate();
            if(i == 1){
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;

    }

    public CarDTO getCarFromResultSet(ResultSet rs) throws SQLException {

        CarDTO carDTO = new CarDTO();
        carDTO.setId(rs.getInt("id"));
        carDTO.setMake(rs.getString("make"));
        carDTO.setModel(rs.getString("model"));
        carDTO.setYear(rs.getInt("year"));
        carDTO.setColor(rs.getString("color"));
        return carDTO;

    }

}

