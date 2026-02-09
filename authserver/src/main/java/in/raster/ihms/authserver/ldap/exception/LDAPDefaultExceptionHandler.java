package in.raster.ihms.authserver.ldap.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class LDAPDefaultExceptionHandler {

    @ExceptionHandler(value = {LdapCommunicationException.class})
    public ResponseEntity ldapCoomunicationFailure(LdapCommunicationException comexception, WebRequest req) {
        return new ResponseEntity(comexception.getMessage(), HttpStatus.CONFLICT);
    }
}
