package Model.Interfaces;

public interface Builder<T> {
    T build();

    T build(String type);
}
