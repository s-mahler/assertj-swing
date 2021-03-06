/*
 * Created on Jul 16, 2009
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

import javax.swing.JTree;

import org.assertj.swing.exception.LocationUnavailableException;
import org.junit.Test;

/**
 * Tests for {@link JTreeDriver#collapsePath(JTree, String)}.
 * 
 * @author Alex Ruiz
 */
public class JTreeDriver_collapsePath_Test extends JTreeDriver_toggleCell_TestCase {
  @Test
  public void should_collapse_cell() {
    showWindow();
    requireRowExpanded(0);
    driver.collapsePath(tree, "root");
    requireRowCollapsed(0);
  }

  @Test
  public void should_not_do_anything_if_cell_already_collapsed() {
    showWindow();
    requireRowCollapsed(2);
    driver.collapsePath(tree, "root");
    requireRowCollapsed(2);
  }

  @Test
  public void should_throw_error_if_given_path_does_not_exist() {
    showWindow();
    thrown.expect(LocationUnavailableException.class, "Unable to find path 'somePath'");
    driver.collapsePath(tree, "somePath");
  }

  @Test
  public void should_throw_error_if_JTree_is_disabled() {
    disableTree();
    thrown.expectIllegalStateIsDisabledComponent();
    driver.collapsePath(tree, "root");
  }

  @Test
  public void should_throw_error_if_JTree_is_not_showing_on_the_screen() {
    thrown.expectIllegalStateIsNotShowingComponent();
    driver.collapsePath(tree, "root");
  }
}
