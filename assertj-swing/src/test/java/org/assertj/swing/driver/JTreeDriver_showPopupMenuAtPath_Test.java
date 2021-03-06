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

import static org.assertj.core.api.Assertions.assertThat;

import javax.swing.JPopupMenu;

import org.junit.Test;

/**
 * Tests for {@link JTreeDriver#showPopupMenu(javax.swing.JTree, String)}.
 * 
 * @author Alex Ruiz
 */
public class JTreeDriver_showPopupMenuAtPath_Test extends JTreeDriver_showPopupMenuAtCell_TestCase {
  @Test
  public void should_show_popup_menu() {
    showWindow();
    JPopupMenu popupMenu = driver.showPopupMenu(tree, "root");
    assertThat(popupMenu).isSameAs(popupMenu());
  }

  @Test
  public void should_scroll_to_node_before_showing_popup_menu() {
    showWindow();
    driver.click(tree);
    JPopupMenu popupMenu = driver.showPopupMenu(tree, "root/branch5");
    assertThat(popupMenu).isSameAs(popupMenu());
  }

  @Test
  public void should_throw_error_if_JTree_is_disabled() {
    disableTree();
    thrown.expectIllegalStateIsDisabledComponent();
    driver.showPopupMenu(tree, "root");
  }

  @Test
  public void should_throw_error_if_JTree_is_not_showing_on_the_screen() {
    thrown.expectIllegalStateIsNotShowingComponent();
    driver.showPopupMenu(tree, "root");
  }
}
