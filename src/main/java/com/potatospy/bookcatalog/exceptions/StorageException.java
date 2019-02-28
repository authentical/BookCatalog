/* Written by Spring.io with licence
* https://creativecommons.org/licenses/by-nd/3.0/
* No changes were made from original
* */

package com.potatospy.bookcatalog.exceptions;

public class StorageException extends RuntimeException {

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
