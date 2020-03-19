package daos;

import java.util.List;

public interface DAO <T>{
     T findById(int id);
     List<T> findAll();
     Boolean create(CarDTO dto);
     Boolean update(CarDTO dto);
     Boolean delete(int id);
}

