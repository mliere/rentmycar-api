package local.rentmycar.api.service.Exceptions;

public class MissingResourceException extends RuntimeException {

    public MissingResourceException(String property, String value) {
        super(String.format("Resource of type %s with id %s does not exist.", property, value));
    }
}
