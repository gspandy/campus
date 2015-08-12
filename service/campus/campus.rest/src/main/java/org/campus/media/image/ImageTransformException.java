package org.campus.media.image;


import org.campus.core.exception.handler.ExceptionInfo;
import org.campus.core.exception.handler.ExceptionInfoGetter;
import org.springframework.core.NestedRuntimeException;

public class ImageTransformException extends NestedRuntimeException implements ExceptionInfoGetter  {

	private static final long serialVersionUID = 1L;

	public ImageTransformException(String message) {
        super(message);
    }

    public ImageTransformException(String message, Throwable cause) {
        super(message, cause);
    }
    
	@Override
	public ExceptionInfo getInfo() {
		return null;
	}
}
