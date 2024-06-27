package Calculate;

public interface Calculator<T, P> {
    void calculate(P sample);

    T getResult();

    String getStringResult();
}
