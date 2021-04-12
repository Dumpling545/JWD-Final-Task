package by.tc.task05.service.validator.impl;

import by.tc.task05.service.exception.InvalidImageTypeException;
import by.tc.task05.service.exception.NoImageException;
import by.tc.task05.service.exception.ServiceException;
import by.tc.task05.service.validator.ImageValidator;
import jakarta.servlet.http.Part;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class ImageValidatorImpl implements ImageValidator {
    private final static String MISC_BUNDLE = "by.tc.task05.bundle.misc";
    private final static String IMAGE_TYPES_KEY = "imageTypes";
    private final static List<String> IMAGE_TYPES;

    static {
        ResourceBundle rb = ResourceBundle.getBundle(MISC_BUNDLE);
        IMAGE_TYPES = Arrays.asList(rb.getString(IMAGE_TYPES_KEY).split(","));
    }

    @Override
    public void validateImage(Part imageFile)
            throws InvalidImageTypeException, NoImageException {
        if (imageFile != null) {
            String fileName = imageFile.getSubmittedFileName();
            if (!IMAGE_TYPES
                    .contains(fileName.substring(fileName.lastIndexOf('.')))) {
                throw new InvalidImageTypeException();
            }
        } else {
            throw new NoImageException();
        }
    }
}
