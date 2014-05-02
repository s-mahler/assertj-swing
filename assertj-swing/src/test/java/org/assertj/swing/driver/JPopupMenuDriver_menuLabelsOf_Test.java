/*
 * Created on Sep 5, 2007
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
 * Copyright @2007-2013 the original author or authors.
 */
package org.assertj.swing.driver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Arrays.array;

import org.junit.Test;

/**
 * Tests for {@link JPopupMenuDriver#menuLabelsOf(javax.swing.JPopupMenu)}.
 * 
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class JPopupMenuDriver_menuLabelsOf_Test extends JPopupMenuDriver_TestCase {
  @Test
  public void should_returns_all_menu_labels() {
    String[] labels = driver.menuLabelsOf(popupMenu);
    assertThat(labels).isEqualTo(array("First", "Second"));
  }
}
