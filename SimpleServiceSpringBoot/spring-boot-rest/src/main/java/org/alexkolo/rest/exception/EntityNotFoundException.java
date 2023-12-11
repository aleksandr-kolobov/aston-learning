package org.alexkolo.rest.exception;

/*будет отправлять HttpStatus.NOT_FOUND когда исключение
@ResponseStatus(HttpStatus.NOT_FOUND)*/
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String massage) {
        super(massage);
    }

}
