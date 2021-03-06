/*
 * Created on Nov 3, 2008
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
import static org.assertj.core.util.Lists.newArrayList;
import static org.assertj.swing.edt.GuiActionRunner.execute;
import static org.assertj.core.util.Arrays.array;

import java.awt.Dimension;
import java.util.Collection;

import javax.swing.JList;
import javax.swing.JScrollPane;

import org.assertj.swing.annotation.RunsInEDT;
import org.assertj.swing.cell.JListCellReader;
import org.assertj.swing.edt.GuiQuery;
import org.assertj.swing.test.core.RobotBasedTestCase;
import org.assertj.swing.test.swing.TestWindow;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Tests for {@link JListItemValueQuery#itemValue(JList, int, JListCellReader)}.
 * 
 * @author Alex Ruiz
 */
@RunWith(Parameterized.class)
public class JListItemValueQuery_itemValue_Test extends RobotBasedTestCase {
  private JList list;
  private JListCellReader cellReader;

  private final int index;
  private final String expectedValue;

  @Parameters
  public static Collection<Object[]> items() {
    return newArrayList(new Object[][] { { 0, "Yoda" }, { 1, "Luke" } });
  }

  public JListItemValueQuery_itemValue_Test(int index, String expectedValue) {
    this.index = index;
    this.expectedValue = expectedValue;
  }

  @Override
  protected void onSetUp() {
    MyWindow window = MyWindow.createNew();
    list = window.list;
    cellReader = new BasicJListCellReader();
  }

  @Test()
  public void should_return_item_value_as_text() {
    String actualValue = itemValue(list, index, cellReader);
    assertThat(actualValue).isEqualTo(expectedValue);
  }

  @RunsInEDT
  private static String itemValue(final JList list, final int index, final JListCellReader cellReader) {
    return execute(new GuiQuery<String>() {
      @Override
      protected String executeInEDT() {
        return JListItemValueQuery.itemValue(list, index, cellReader);
      }
    });
  }

  private static class MyWindow extends TestWindow {
    private static final Dimension LIST_SIZE = new Dimension(80, 40);

    final JList list = new JList(array(new Jedi("Yoda"), new Jedi("Luke")));

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
      super(JListItemValueQuery.class);
      addComponents(decorate(list));
    }

    private static JScrollPane decorate(JList list) {
      JScrollPane scrollPane = new JScrollPane(list);
      scrollPane.setPreferredSize(LIST_SIZE);
      return scrollPane;
    }
  }
}
