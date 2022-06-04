package com.jackmeng.music.components.toppane;

import javax.swing.*;

import com.jackmeng.music.Manager;
import com.jackmeng.music.components.toppane.layout.ButtonControlTP;
import com.jackmeng.music.components.toppane.layout.InfoViewTP;

import java.awt.*;

public class TopPane extends JPanel {
  public TopPane(InfoViewTP ifp, ButtonControlTP bctp) {
    setPreferredSize(new Dimension(Manager.TOPPANE_MIN_WIDTH, Manager.TOPPANE_MIN_HEIGHT));
    setMaximumSize(new Dimension(Manager.TOPPANE_MAX_WIDTH, Manager.TOPPANE_MAX_HEIGHT));
    setMinimumSize(new Dimension(Manager.TOPPANE_MIN_WIDTH, Manager.TOPPANE_MIN_HEIGHT));
    setLayout(new BorderLayout());
    
    add(ifp, BorderLayout.PAGE_START);
    add(bctp, BorderLayout.PAGE_END);
  }
}