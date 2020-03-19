package daos;


import static org.junit.Assert.*;

import org.junit.Test;

import java.util.List;

public class CarTest {

    CarDAO car = new CarDAO();

    CarDTO carDTO = new CarDTO(
            6,
            "Honda",
            "Civic",
            2016,
            "blue"
    );

    @Test
      public void getCarFromResultSetTest(){
        CarDTO newCar = car.findById(1);
        assertEquals(newCar.getId(), newCar.getCarById());
    }

    @Test
    public void findAllTest(){
        List<CarDTO> list = car.findAll();
        int expectedSize = 5;
        int actualSize = list.size();
        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void findCarByIdTest(){
        CarDTO newCar = car.findById(1);
        assertEquals("Honda", newCar.getMake());
        assertEquals("Civic", newCar.getModel());
        assertEquals(2019, newCar.getYear());
        assertEquals("red", newCar.getColor());
    }

    @Test
    public void createCarTest(){
        assertTrue(car.create(carDTO));
        car.delete(6);
    }

    @Test
    public void deleteCarTest(){
        car.create(carDTO);
        assertEquals(6, carDTO.getId());
        car.delete(6);
        assertNull(car.findById(6));
    }

    @Test
    public void UpdateCarTest(){
        car.create(carDTO);
        carDTO.setColor("pink");
        car.update(carDTO);
        assertEquals("pink", carDTO.getColor());
    }

}
