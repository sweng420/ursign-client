package ursign.ursign_client;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  TestFeatureXML.class,
  TestFeatureMultimedia.class,
  TestFeaturePage.class,
  TestFeatureUser.class,
  
})

public class TestSuite {
  // the class remains empty,
  // used only as a holder for the above annotations
}