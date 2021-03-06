/*
 * Created on Aug 13, 2009
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
 * Copyright @2009-2013 the original author or authors.
 */
package org.assertj.swing.driver;

import org.junit.Test;

/**
 * Tests for {@link JTableHeaderDriver#clickColumn(javax.swing.table.JTableHeader, int)}.
 * 
 * @author Yvonne Wang
 */
public class JTableHeaderDriver_clickColumnByIndex_Test extends JTableHeaderDriver_TestCase {
  @Test(expected = IndexOutOfBoundsException.class)
  public void should_throw_error_if_index_is_negative() {
    driver.clickColumn(tableHeader, -1);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void should_throw_error_if_index_is_out_of_bounds() {
    driver.clickColumn(tableHeader, 2);
  }

  @Test
  public void should_throw_error_if_JTableHeader_is_disabled() {
    disableTableHeader();
    thrown.expectIllegalStateIsDisabledComponent();
    driver.clickColumn(tableHeader, 0);
  }

  @Test
  public void should_throw_error_if_JTableHeader_is_not_showing_on_the_screen() {
    thrown.expectIllegalStateIsNotShowingComponent();
    driver.clickColumn(tableHeader, 0);
  }
}
