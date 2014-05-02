/*
 * Created on Nov 30, 2008
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
package org.assertj.swing.finder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.swing.edt.GuiActionRunner.execute;
import static org.assertj.swing.finder.WindowFinder.findDialog;

import java.awt.Frame;

import javax.swing.JDialog;

import org.assertj.swing.annotation.RunsInEDT;
import org.assertj.swing.edt.GuiQuery;
import org.assertj.swing.fixture.DialogFixture;
import org.assertj.swing.test.core.RobotBasedTestCase;
import org.assertj.swing.test.swing.TestDialog;
import org.assertj.swing.test.swing.TestWindow;
import org.junit.Test;

/**
 * Test case for <a href="http://code.google.com/p/fest/issues/detail?id=228">Bug 228</a>.
 * 
 * @author Ken Geis
 * @author Alex Ruiz
 */
public class Bug228_ModalWindowBlocks_Test extends RobotBasedTestCase {
  @Test
  public void should_not_block() {
    TestWindow window = TestWindow.createNewWindow(getClass());
    robot.showWindow(window);
    MyDialog.createAndShowNew(window);
    DialogFixture found = findDialog(JDialog.class).using(robot);
    assertThat(found.target()).isInstanceOf(MyDialog.class);
  }

  private static class MyDialog extends TestDialog {
    @RunsInEDT
    static void createAndShowNew(final Frame owner) {
      MyDialog dialog = execute(new GuiQuery<MyDialog>() {
        @Override
        protected MyDialog executeInEDT() {
          return new MyDialog(owner);
        }
      });
      dialog.display();
    }

    MyDialog(Frame owner) {
      super(owner);
      setModal(true);
    }
  }
}
