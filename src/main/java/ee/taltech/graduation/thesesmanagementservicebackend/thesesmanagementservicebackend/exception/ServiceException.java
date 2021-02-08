package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.exception;

public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = -5781569050612288685L;

    public ServiceException(String message) {
        super(message);
    }


}


