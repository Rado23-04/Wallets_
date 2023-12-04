package Main.generic;

import java.util.List;

public interface CrudOperations<T> {
    List<T> getAll();

    T insert(T insert);
    T getById(T getByid);

    T delete(T toDelete);
    T put (T put);

}
