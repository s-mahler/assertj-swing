/*
 * Created on Feb 24, 2008
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

import org.assertj.swing.exception.LocationUnavailableException;
import org.junit.Test;

/**
 * Tests for {@link JComboBoxDriver#selectItem(javax.swing.JComboBox, String)}.
 * 
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class JComboBoxDriver_selectItemByText_Test extends JComboBoxDriver_TestCase {
  @Test
  public void should_select_item() {
    clearSelection();
    showWindow();
    driver.selectItem(comboBox, "third");
    assertThatSelectedItemIs("third");
  }

  @Test
  public void should_select_matching_item() {
    clearSelection();
    showWindow();
    driver.selectItem(comboBox, "thi.*");
    assertThatSelectedItemIs("third");
  }

  @Test
  public void should_not_do_anything_if_item_already_selected() {
    showWindow();
    select(2);
    driver.selectItem(comboBox, "third");
    assertThatSelectedItemIs("third");
  }

  @Test
  public void should_throw_error_if_JComboBox_is_disabled() {
    disableComboBox();
    thrown.expectIllegalStateIsDisabledComponent();
    driver.selectItem(comboBox, "first");
  }

  @Test
  public void should_throw_error_if_JComboBox_is_not_showing_on_the_screen() {
    thrown.expectIllegalStateIsNotShowingComponent();
    driver.selectItem(comboBox, "first");
  }

  @Test(expected = LocationUnavailableException.class)
  public void should_throw_error_if_item_does_not_exist() {
    showWindow();
    driver.selectItem(comboBox, "hundred");
  }
}
