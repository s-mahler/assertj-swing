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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.swing.core.MouseButton.RIGHT_BUTTON;

import java.awt.Point;

import org.assertj.swing.exception.LocationUnavailableException;
import org.assertj.swing.test.recorder.ClickRecorder;
import org.assertj.swing.test.recorder.ClickRecorderManager;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link JListDriver#clickItem(javax.swing.JList, String, org.assertj.swing.core.MouseButton, int)}.
 * 
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class JListDriver_clickItemByText_Test extends JListDriver_TestCase {
  @Rule
  public ClickRecorderManager clickRecorder = new ClickRecorderManager();

  @Test
  public void should_click_item_with_text_equal_to_given_one() {
    clearSelection();
    showWindow();
    ClickRecorder recorder = clickRecorder.attachDirectlyTo(list);
    driver.clickItem(list, "two", RIGHT_BUTTON, 2);
    recorder.clicked(RIGHT_BUTTON).timesClicked(2);
    Point pointClicked = recorder.pointClicked();
    assertThat(locationToIndex(pointClicked)).isEqualTo(1);
  }

  @Test
  public void should_click_item_with_text_matching_given_pattern() {
    clearSelection();
    showWindow();
    ClickRecorder recorder = clickRecorder.attachDirectlyTo(list);
    driver.clickItem(list, "tw.*", RIGHT_BUTTON, 2);
    recorder.clicked(RIGHT_BUTTON).timesClicked(2);
    Point pointClicked = recorder.pointClicked();
    assertThat(locationToIndex(pointClicked)).isEqualTo(1);
  }

  @Test
  public void should_throw_error_if_JList_is_disabled() {
    disableList();
    thrown.expectIllegalStateIsDisabledComponent();
    driver.clickItem(list, "two", RIGHT_BUTTON, 2);
  }

  @Test
  public void should_throw_error_if_JList_is_not_showing_on_the_screen() {
    thrown.expectIllegalStateIsNotShowingComponent();
    driver.clickItem(list, "two", RIGHT_BUTTON, 2);
  }

  @Test(expected = LocationUnavailableException.class)
  public void should_throw_error_if_item_to_click_was_not_found() {
    showWindow();
    driver.clickItem(list, "hello", RIGHT_BUTTON, 2);
  }
}
