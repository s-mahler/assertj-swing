/*
 * Created on Jul 20, 2009
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
import static org.assertj.swing.test.core.CommonAssertions.assertThatErrorCauseIsDisabledComponent;
import static org.assertj.swing.test.core.CommonAssertions.assertThatErrorCauseIsNotShowingComponent;
import static org.assertj.swing.test.core.CommonAssertions.failWhenExpectingException;

import java.awt.Point;

import javax.swing.JPopupMenu;

import org.assertj.swing.test.recorder.ClickRecorderManager;
import org.assertj.swing.test.recorder.ToolkitClickRecorder;
import org.junit.Rule;
import org.junit.Test;

/**
 * Test for {@link ComponentDriver#invokePopupMenu(java.awt.Component, java.awt.Point)}.
 * 
 * @author Alex Ruiz
 */
public class ComponentDriver_invokePopupAtPoint_Test extends ComponentDriver_invokePopup_TestCase {
  @Rule
  public ClickRecorderManager clickRecorder = new ClickRecorderManager();

  @Test
  public void should_show_JPopupMenu() {
    showWindow();
    Point p = new Point(8, 6);
    ToolkitClickRecorder recorder = clickRecorder.attachToToolkitFor(window.textField);
    JPopupMenu found = driver.invokePopupMenu(window.textField, p);
    assertThat(found).isSameAs(popupMenu);
    recorder.wasRightClicked().timesClicked(1).clickedAt(p);
  }

  @Test(expected = NullPointerException.class)
  public void should_throw_error_if_point_is_null() {
    driver.invokePopupMenu(window.textField, null);
  }

  @Test
  public void should_throw_error_if_Component_is_disabled() {
    disableTextField();
    try {
      driver.invokePopupMenu(window.textField, new Point(8, 6));
      failWhenExpectingException();
    } catch (IllegalStateException e) {
      assertThatErrorCauseIsDisabledComponent(e);
    }
    assertThatTextFieldIsEmpty();
  }

  @Test
  public void should_throw_error_if_Component_is_not_showing_on_the_screen() {
    try {
      driver.invokePopupMenu(window.textField, new Point(8, 6));
      failWhenExpectingException();
    } catch (IllegalStateException e) {
      assertThatErrorCauseIsNotShowingComponent(e);
    }
    assertThatTextFieldIsEmpty();
  }
}