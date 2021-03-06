/*
 * Created on Apr 5, 2008
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

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.swing.exception.LocationUnavailableException;
import org.junit.Test;

/**
 * Tests for {@link JTabbedPaneLocation#indexOf(javax.swing.JTabbedPane, String)}.
 * 
 * @author Yvonne Wang
 */
public class JTabbedPaneLocation_indexOf_Test extends JTabbedPaneLocation_TestCase {
  @Test
  public void should_return_index_of_tab_with_matching_title() {
    int index = location.indexOf(tabbedPane, "two");
    assertThat(index).isEqualTo(1);
  }

  @Test
  public void should_throw_error_if_a_matching_tab_cannot_be_found() {
    thrown.expect(LocationUnavailableException.class, "Unable to find a tab with title matching value 'three'");
    location.indexOf(tabbedPane, "three");
  }
}
