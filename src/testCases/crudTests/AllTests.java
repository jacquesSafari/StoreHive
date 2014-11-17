package testCases.crudTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CategoryCRUDTest.class, ProductsCRUDTest.class,
		StoreCRUDTest.class, StoreOwnerCRUDTest.class })
public class AllTests {

}
