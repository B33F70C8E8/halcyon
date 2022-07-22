/*
 *  Copyright: (C) 2022 name of Jack Meng
 * Halcyon MP4J is music-playing software.
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program; If not, see <http://www.gnu.org/licenses/>.
 */

package com.halcyoninae.cosmos.components.minimizeplayer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import com.halcyoninae.halcyon.Halcyon;

public class MiniPlayerClickMenu extends MouseAdapter {
  private MiniPlayer e;

  public MiniPlayerClickMenu(MiniPlayer e) {
    this.e = e;
  }

  private void attempt(MouseEvent ex) {
    int x = ex.getX();
    int y = ex.getY();
    JPopupMenu menu = new JPopupMenu();
    menu.setBorder(BorderFactory.createEmptyBorder());
    JMenuItem close = new JMenuItem("Close");
    close.addActionListener(event_1 -> e.setVisible(false));
    menu.add(close);
    JMenuItem hideBC = new JMenuItem("Hide Big Player");
    hideBC.addActionListener(event_1 -> Halcyon.bgt.getFrame().setVisible(false));
    JMenuItem showBC = new JMenuItem("Show Big Player");
    showBC.addActionListener(event_1 -> Halcyon.bgt.getFrame().setVisible(true));
    menu.add(showBC);
    menu.add(hideBC);
    e.pounceListener();
    menu.show(e, x, y);
  }

  /**
   * @param e
   */
  @Override
  public void mousePressed(MouseEvent e) {
    if (e.getButton() == MouseEvent.BUTTON3) {
      attempt(e);
    }
  }

  /**
   * @param e
   */
  @Override
  public void mouseReleased(MouseEvent e) {
    if (e.getButton() == MouseEvent.BUTTON3) {
      attempt(e);
    }
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    if (e.getButton() == MouseEvent.BUTTON1) {
      attempt(e);
    }
  }

}
