package org.eclipse.recommenders.snipeditor.tests import org.eclipse.xtext.junit4.XtextRunner

import org.eclipse.xtext.junit4.InjectWith
import org.junit.runner.RunWith
import org.eclipse.recommenders.snipeditor.SnipDSLInjectorProvider
import org.eclipse.xtext.junit4.util.ParseHelper
import static org.junit.Assert.*
import com.google.inject.Inject
import org.junit.Test
import org.eclipse.recommenders.snipeditor.snipDSL.domainmodel

@InjectWith(SnipDSLInjectorProvider)
@RunWith(XtextRunner)
class SnipTests {
	@Inject
ParseHelper<domainmodel> parser
 
@Test 
def void parseDomainmodel() {
  val model = parser.parse(
    "entity MyEntity {
      parent: MyEntity
    }")
	
}
	
}