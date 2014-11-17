package testCases;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import testCases.crudTests.CategoryCRUDTest;
import testCases.crudTests.ProductsCRUDTest;
import testCases.crudTests.StoreCRUDTest;
import testCases.crudTests.StoreOwnerCRUDTest;

@RunWith(Suite.class)
@SuiteClasses({ CategoryCRUDTest.class, ProductsCRUDTest.class,
		StoreCRUDTest.class, StoreOwnerCRUDTest.class })
public class AllTests {

}
