package com.jackmeng.app.components.inheritable;

import javax.swing.*;

import com.jackmeng.constant.Manager;

import java.awt.*;

import java.awt.event.*;

public class TabButton extends JPanel {

  private final JTabbedPane parentPane;

  public class CloseTabButton extends JButton implements ActionListener {
    public CloseTabButton() {
      setPreferredSize(new Dimension(Manager.BUTTON_STD_ICON_WIDTH_N_HEIGHT, Manager.BUTTON_STD_ICON_WIDTH_N_HEIGHT));
      setToolTipText("Close Tab");
      setUI(getUI());

      setContentAreaFilled(false);
      setFocusable(false);
      setBorder(null);
      setBorder(null);
      setBorderPainted(false);
      setRolloverEnabled(false);
      addActionListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g2.setStroke(new BasicStroke(1));
      g2.setColor(Color.WHITE);
      g2.drawLine(2, 2, getWidth() - 2, getHeight() - 2);
      g2.drawLine(getWidth() - 2, 2, 2, getHeight() - 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      int i = parentPane.indexOfTabComponent(TabButton.this);
      if (i != -1) {
        parentPane.remove(i);
      }
    }
  }

  public TabButton(JTabbedPane parent) {
    this.parentPane = parent;
    setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
    setOpaque(false);

    JLabel label = new JLabel() {
      @Override
      public String getText() {
        int index = parentPane.indexOfTabComponent(TabButton.this);
        if (index != -1) {
          return parentPane.getTitleAt(index);
        }
        return null;
      }
    };

    add(label);
    label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
    add(new CloseTabButton());
    setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
  }
}
