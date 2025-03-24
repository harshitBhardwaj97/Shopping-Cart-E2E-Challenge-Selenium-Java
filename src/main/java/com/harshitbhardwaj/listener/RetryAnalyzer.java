package com.harshitbhardwaj.listener;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * This class implements the IRetryAnalyzer interface and provides a custom retry mechanism
 * for failed tests. The test will be retried up to a specified maximum number of times
 * before it is considered failed.
 *
 * @author Harshit Bhardwaj
 */
public class RetryAnalyzer implements IRetryAnalyzer {

    // Maximum number of retry attempts
    private static final int maxRetryCount = 3;

    // Current retry count
    private int retryCount = 0;

    /**
     * This method is called to determine whether a test should be retried.
     * It allows retrying a test for a maximum number of attempts (defined by maxRetryCount).
     *
     * @param result The ITestResult object that contains the result of the test execution.
     * @return true if the test should be retried, false if it should not.
     */
    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;  // Increment the retry count
            System.out.println("Retrying test: " + result.getName() + " for the " + retryCount + " time(s).");
            return true;  // Retry the test
        }
        return false;  // Don't retry, stop here
    }
}
