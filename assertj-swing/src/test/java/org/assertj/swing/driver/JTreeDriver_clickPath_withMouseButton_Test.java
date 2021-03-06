/*
 * Created on Dec 27, 2009
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
import static org.assertj.swing.core.MouseButton.RIGHT_BUTTON;

import org.assertj.swing.core.MouseButton;
import org.assertj.swing.exception.LocationUnavailableException;
import org.assertj.swing.test.recorder.ClickRecorder;
import org.assertj.swing.test.recorder.ClickRecorderManager;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link JTreeDriver#clickPath(javax.swing.JTree, String, org.assertj.swing.core.MouseButton)}.
 * 
 * @author Alex Ruiz
 */
public class JTreeDriver_clickPath_withMouseButton_Test extends JTreeDriver_clickCell_TestCase {
  @Rule
  public ClickRecorderManager clickRecorder = new ClickRecorderManager();

  @Test(expected = NullPointerException.class)
  public void should_throw_error_if_MouseButton_is_null() {
    MouseButton button = null;
    driver.clickPath(tree, "root", button);
  }

  @Test
  public void should_click_path() {
    showWindow();
    ClickRecorder recorder = clickRecorder.attachDirectlyTo(tree);
    driver.clickPath(tree, "root/branch1/branch1.1/branch1.1.1", RIGHT_BUTTON);
    recorder.clicked(RIGHT_BUTTON).timesClicked(1);
    String clickedPath = pathAtPoint(tree, recorder.pointClicked(), driver.separator());
    assertThat(clickedPath).isEqualTo("root/branch1/branch1.1/branch1.1.1");
  }

  @Test
  public void should_throw_error_if_path_not_found() {
    showWindow();
    thrown.expect(LocationUnavailableException.class, "Unable to find path 'another'");
    driver.clickPath(tree, "another", RIGHT_BUTTON);
  }

  @Test
  public void should_throw_error_if_JTree_is_disabled() {
    disableTree();
    thrown.expectIllegalStateIsDisabledComponent();
    driver.clickPath(tree, "root/branch1", RIGHT_BUTTON);
  }

  @Test
  public void should_throw_error_if_JTree_is_not_showing_on_the_screen() {
    thrown.expectIllegalStateIsNotShowingComponent();
    driver.clickPath(tree, "root/branch1", RIGHT_BUTTON);
  }
}
