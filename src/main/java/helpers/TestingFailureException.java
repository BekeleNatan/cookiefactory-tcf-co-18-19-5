package helpers;

import java.util.ArrayList;
import java.util.List;

public class TestingFailureException {
    private boolean expectException;
    private List<RuntimeException> exceptions = new ArrayList<>();

    public void expectException() {
        expectException = true;
    }

    public void add(RuntimeException e) {
        if (!expectException) {
            throw e;
        }
        exceptions.add(e);
    }

    public void addIllegalArgumentException(IllegalArgumentException e) {
        if (!expectException) {
            throw e;
        }
        exceptions.add(e);
    }

    public boolean aFailureIsDetected(){
        return exceptions.size() == 1;
    }
}


