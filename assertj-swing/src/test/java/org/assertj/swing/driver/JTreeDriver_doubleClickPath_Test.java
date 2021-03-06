/*
 * Created on Jul 23, 2009
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

import javax.swing.JTree;

import org.assertj.swing.core.MouseButton;
import org.assertj.swing.test.recorder.ClickRecorder;
import org.assertj.swing.test.recorder.ClickRecorderManager;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link JTreeDriver#doubleClickPath(JTree, String)}.
 * 
 * @author Alex Ruiz
 */
public class JTreeDriver_doubleClickPath_Test extends JTreeDriver_clickCell_TestCase {
  @Rule
  public ClickRecorderManager clickRecorder = new ClickRecorderManager();

  @Test
  public void should_double_click_cell() {
    showWindow();
    ClickRecorder recorder = clickRecorder.attachDirectlyTo(tree);
    driver.doubleClickPath(tree, "root");
    recorder.clicked(MouseButton.LEFT_BUTTON).timesClicked(2);
    assertThat(rowAt(recorder.pointClicked())).isEqualTo(0);
  }

  @Test
  public void should_throw_error_if_JTree_is_disabled() {
    disableTree();
    thrown.expectIllegalStateIsDisabledComponent();
    driver.doubleClickPath(tree, "root");
  }

  @Test
  public void should_throw_error_if_JTree_is_not_showing_on_the_screen() {
    thrown.expectIllegalStateIsNotShowingComponent();
    driver.doubleClickPath(tree, "root");
  }
}
