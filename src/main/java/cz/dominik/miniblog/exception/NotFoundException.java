package cz.dominik.miniblog.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String resource, Object id) {
        super(resource + " with id " + id + " not found");
    }
}
