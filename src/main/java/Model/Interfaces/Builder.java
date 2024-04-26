package Model.Interfaces;

public interface Builder<T> {
    T build();

    T build(boolean editable);
}
