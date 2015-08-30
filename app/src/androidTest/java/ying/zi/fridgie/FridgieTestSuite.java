package ying.zi.fridgie;

import android.test.suitebuilder.TestSuiteBuilder;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Created by ziw on 8/30/2015.
 */
public class FridgieTestSuite extends TestSuite {

    public static Test suite() {
        return new TestSuiteBuilder(FridgieTestSuite.class)
                .includeAllPackagesUnderHere().build();
    }

    public FridgieTestSuite() {
        super();
    }
}
