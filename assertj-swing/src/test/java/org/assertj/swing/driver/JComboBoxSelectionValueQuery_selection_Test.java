/*
 * Created on Nov 12, 2008
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
import static org.assertj.core.util.Arrays.array;
import static org.assertj.swing.driver.JComboBoxMakeEditableAndSelectItemTask.makeEditableAndSelectIndex;
import static org.assertj.swing.driver.JComboBoxMakeEditableAndSelectItemTask.makeEditableAndSelectItem;
import static org.assertj.swing.driver.JComboBoxSetSelectedIndexTask.setSelectedIndex;
import static org.assertj.swing.edt.GuiActionRunner.execute;

import javax.swing.JComboBox;

import org.assertj.swing.annotation.RunsInEDT;
import org.assertj.swing.cell.JComboBoxCellReader;
import org.assertj.swing.edt.GuiQuery;
import org.assertj.swing.test.core.RobotBasedTestCase;
import org.assertj.swing.test.swing.TestWindow;
import org.assertj.swing.util.Pair;
import org.junit.Test;

/**
 * Tests for {@link JComboBoxSelectionValueQuery#selection(JComboBox, JComboBoxCellReader)}.
 * 
 * @author Alex Ruiz
 */
public class JComboBoxSelectionValueQuery_selection_Test extends RobotBasedTestCase {
  private JComboBox comboBox;
  private JComboBoxCellReader cellReader;

  @Override
  protected void onSetUp() {
    MyWindow window = MyWindow.createNew();
    comboBox = window.comboBox;
    cellReader = new BasicJComboBoxCellReader();
  }

  @Test
  public void should_return_false_and_null_if_not_editable_JComboBox_does_not_have_selection() {
    Pair<Boolean, String> selection = JComboBoxSelectionValueQuery.selection(comboBox, cellReader);
    assertThat(selection.first).isFalse();
    assertThat(selection.second).isNull();
  }

  @Test
  public void should_return_true_and_text_of_selected_item_in_not_editable_JComboBox() {
    setSelectedIndex(comboBox, 0);
    robot.waitForIdle();
    Pair<Boolean, String> selection = JComboBoxSelectionValueQuery.selection(comboBox, cellReader);
    assertThatSelectionIsEqual(selection, "first");
  }

  @Test
  public void should_return_text_of_selected_item_in_editable_JComboBox() {
    makeEditableAndSelectIndex(comboBox, 0);
    robot.waitForIdle();
    Pair<Boolean, String> selection = JComboBoxSelectionValueQuery.selection(comboBox, cellReader);
    assertThatSelectionIsEqual(selection, "first");
  }

  @Test
  public void should_return_text_of_entered_item_in_editable_JComboBox() {
    makeEditableAndSelectItem(comboBox, "Hello");
    robot.waitForIdle();
    Pair<Boolean, String> selection = JComboBoxSelectionValueQuery.selection(comboBox, cellReader);
    assertThatSelectionIsEqual(selection, "Hello");
  }

  private void assertThatSelectionIsEqual(Pair<Boolean, String> selection, String expected) {
    assertThat(selection.first).isTrue();
    assertThat(selection.second).isEqualTo(expected);
  }

  private static class MyWindow extends TestWindow {
    final JComboBox comboBox = new JComboBox(array("first", "second", "third"));

    @RunsInEDT
    static MyWindow createNew() {
      return execute(new GuiQuery<MyWindow>() {
        @Override
        protected MyWindow executeInEDT() {
          return new MyWindow();
        }
      });
    }

    private MyWindow() {
      super(JComboBoxSelectionValueQuery_selection_Test.class);
      comboBox.setSelectedIndex(-1);
      addComponents(comboBox);
    }
  }
}
