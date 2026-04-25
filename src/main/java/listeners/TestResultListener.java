package listeners;

import db.TestResultRepository;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestResultListener implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult result) {
        TestResultRepository.save(result.getMethod().getMethodName(), "PASSED");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        TestResultRepository.save(result.getMethod().getMethodName(), "FAILED");
    }
}