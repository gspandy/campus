package org.campus.media.image;


public interface ImageTransform {

	Image resize(Image image, int width, int height) throws ImageTransformException;
}
 