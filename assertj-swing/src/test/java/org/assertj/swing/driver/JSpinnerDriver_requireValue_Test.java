/*
 * Created on Feb 25, 2008
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * 
 * Copyright @2008-2013 the original author or authors.
 */
package org.assertj.swing.driver;

import org.junit.Test;

/**
 * Tests for {@link JSpinnerDriver#requireValue(javax.swing.JSpinner, Object)}.
 * 
 * @author Alex Ruiz
 */
public class JSpinnerDriver_requireValue_Test extends JSpinnerDriver_TestCase {
  @Test
  public void should_pass_if_value_is_equal_to_expected() {
    selectLastValue();
    driver.requireValue(spinner, "Gandalf");
  }

  @Test
  public void should_fail_if_value_is_not_equal_to_expected() {
    selectLastValue();
    thrown.expectAssertionError("value", "[Frodo]", "[Gandalf]");
    driver.requireValue(spinner, "Frodo");
  }
}
