package springE2E;

import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.springframework.boot.test.context.SpringBootTest;

@Suite
@SuiteDisplayName("Spring E2E testing")
@SelectPackages("springE2E.test")
@IncludeClassNamePatterns(".*Test")
@SpringBootTest
public class TestRunner {
}
