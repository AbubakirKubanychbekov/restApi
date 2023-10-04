package peaksoft.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Getter
@Setter
@Builder
public class ExceptionResponse {

    private HttpStatus httpStatus;

    private String exceptionClassName;

    private String message;
}
