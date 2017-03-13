package log.annotation;

import java.io.Serializable;

public enum OperationType implements Serializable{
    CREATE, UPDATE, DELETE, ARCHIVE, MOVE;

    private static final long serialVersionUID = 1L;
}
