package com.safetynet.alerts.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception used when the provided object is not valid
 */
@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "The provided object is not valid (some properties of the payload may be null)")
public class InvalidObjectException extends RuntimeException {
}