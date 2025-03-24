package com.harshitbhardwaj.listener;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * This class implements the IAnnotationTransformer interface and is used to modify
 * TestNG annotations at runtime. In this case, it adds a retry mechanism to the tests.
 * <p>
 * The transform method allows modifying the annotation for a test method before the test
 * is executed.
 *
 * @author Harshit Bhardwaj
 */
public class AnnotationTransformer implements IAnnotationTransformer {

    /**
     * This method is called at runtime to modify the annotations of test methods.
     * In this implementation, it sets a retry analyzer to retry failed tests.
     *
     * @param annotation      The test annotation to transform.
     * @param testClass       The test class containing the test method.
     * @param testConstructor The constructor of the test class.
     * @param testMethod      The method that represents the test.
     */
    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        // Set a retry analyzer for failed tests
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }
}
