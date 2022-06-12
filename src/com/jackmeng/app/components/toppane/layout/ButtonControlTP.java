package com.jackmeng.app.components.toppane.layout;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.jackmeng.app.components.inheritable.LikeButton;
import com.jackmeng.app.components.toppane.layout.InfoViewTP.InfoViewUpdateListener;
import com.jackmeng.app.constant.ColorManager;
import com.jackmeng.app.constant.Global;
import com.jackmeng.app.constant.Manager;
import com.jackmeng.app.events.AlignSliderWithBar;
import com.jackmeng.app.utils.DeImage;
import com.jackmeng.audio.AudioInfo;
import com.jackmeng.debug.Debugger;

import simple.audio.AudioException;

import java.awt.*;

import java.awt.event.*;

public class ButtonControlTP extends JPanel implements InfoViewUpdateListener, ActionListener, ChangeListener {
  private JButton playButton, nextButton, previousButton, loopButton, shuffleButton;
  private LikeButton likeButton;
  private JSlider progressSlider, volumeSlider;
  private JProgressBar progressBar;
  private JPanel sliders, buttons;
  private transient AudioInfo aif;
  private boolean hasPlayed = false;
  private transient Thread progressThread;

  public ButtonControlTP() {
    super();
    aif = null;
    setPreferredSize(new Dimension(Manager.BUTTONCONTROL_MIN_WIDTH, Manager.BUTTONCONTROL_MIN_HEIGHT));
    setMaximumSize(new Dimension(Manager.BUTTONCONTROL_MAX_WIDTH, Manager.BUTTONCONTROL_MAX_HEIGHT));
    setMinimumSize(new Dimension(Manager.BUTTONCONTROL_MIN_WIDTH, Manager.BUTTONCONTROL_MIN_HEIGHT));
    setOpaque(false);
    setLayout(new GridLayout(2, 1));

    buttons = new JPanel();
    buttons.setPreferredSize(
        new Dimension(Manager.BUTTONCONTROL_MIN_WIDTH, Manager.BUTTONCONTROL_MIN_HEIGHT / 2));
    buttons.setMinimumSize(
        new Dimension(Manager.BUTTONCONTROL_MIN_WIDTH, Manager.BUTTONCONTROL_MIN_HEIGHT / 2));
    buttons.setMaximumSize(
        new Dimension(Manager.BUTTONCONTROL_MAX_WIDTH, Manager.BUTTONCONTROL_MAX_HEIGHT / 2));
    buttons.setLayout(new FlowLayout(FlowLayout.LEFT, 15, getPreferredSize().height / 6));

    playButton = new JButton(
        DeImage.resizeImage(Global.rd.getFromAsImageIcon(Manager.BUTTONCTRL_PLAY_PAUSE_ICON),
            40, 40));
    playButton.setBackground(null);
    playButton.setBorder(null);
    playButton.addActionListener(this);

    nextButton = new JButton(
        DeImage.resizeImage(Global.rd.getFromAsImageIcon(Manager.BUTTONCTRL_FWD_ICON), 24, 24));
    nextButton.setBackground(null);
    nextButton.setBorder(null);

    previousButton = new JButton(
        DeImage.resizeImage(Global.rd.getFromAsImageIcon(Manager.BUTTONCTRL_BWD_ICON), 24, 24));
    previousButton.setBackground(null);
    previousButton.setBorder(null);

    loopButton = new JButton(
        DeImage.resizeImage(Global.rd.getFromAsImageIcon(Manager.BUTTONCTRL_LOOP_ICON), 24,
            24));
    loopButton.setBackground(null);
    loopButton.setBorder(null);

    shuffleButton = new JButton(
        DeImage.resizeImage(Global.rd.getFromAsImageIcon(Manager.BUTTONCTRL_SHUFFLE_ICON), 24,
            24));
    shuffleButton.setBackground(null);
    shuffleButton.setBorder(null);

    volumeSlider = new JSlider(0, 100);
    volumeSlider.setForeground(ColorManager.MAIN_FG_THEME);
    volumeSlider.setBorder(null);
    volumeSlider.setPreferredSize(new Dimension(Manager.BUTTONCONTROL_MIN_WIDTH / 4, 20));
    volumeSlider.setMinimumSize(volumeSlider.getPreferredSize());
    volumeSlider.setMaximumSize(new Dimension(Manager.BUTTONCONTROL_MIN_WIDTH / 2, 20));
    volumeSlider.addChangeListener(this);

    likeButton = new LikeButton(
        DeImage.resizeImage(Global.rd.getFromAsImageIcon(Manager.BUTTONCTRL_NOLIKE_ICON), 24,
            24),
        DeImage.resizeImage(Global.rd.getFromAsImageIcon(Manager.BUTTONCTRL_LIKE_ICON), 24,
            24));
    likeButton.setBackground(null);
    likeButton.setBorder(null);

    buttons.add(volumeSlider);
    buttons.add(shuffleButton);
    buttons.add(previousButton);
    buttons.add(playButton);
    buttons.add(nextButton);
    buttons.add(loopButton);
    buttons.add(likeButton);

    sliders = new JPanel();
    sliders.setLayout(new BoxLayout(sliders, BoxLayout.Y_AXIS));
    sliders.setPreferredSize(
        new Dimension(Manager.BUTTONCONTROL_MIN_WIDTH, Manager.BUTTONCONTROL_MIN_HEIGHT / 2));
    sliders.setMinimumSize(
        new Dimension(Manager.BUTTONCONTROL_MIN_WIDTH, Manager.BUTTONCONTROL_MIN_HEIGHT / 2));
    sliders.setMaximumSize(
        new Dimension(Manager.BUTTONCONTROL_MAX_WIDTH, Manager.BUTTONCONTROL_MAX_HEIGHT / 2));

    progressBar = new JProgressBar(0, 100);
    progressBar.setStringPainted(true);
    progressBar.setPreferredSize(new Dimension(getPreferredSize().width, getPreferredSize().height / 4));
    progressBar.setIndeterminate(true);
    progressBar.setForeground(ColorManager.MAIN_FG_THEME);
    progressBar.setBorder(null);
    progressBar.setAlignmentX(Component.CENTER_ALIGNMENT);

    progressSlider = new JSlider(0, 100);
    progressSlider.setValue(0);
    progressSlider.setForeground(ColorManager.MAIN_FG_THEME);
    progressSlider.setBorder(null);
    progressSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
    progressSlider.addChangeListener(new AlignSliderWithBar(progressSlider, progressBar));
    progressThread = new Thread(() -> {
      while (true) {
        if (Global.player.getStream().isPlaying()) {
          progressSlider
              .setValue((int) (Global.player.getStream().getPosition() * 100 / Global.player.getStream().getLength()));
        }
        try {
          Thread.sleep(50);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
    progressThread.start();

    sliders.add(progressSlider);
    sliders.add(Box.createVerticalStrut(Manager.BUTTONCONTROL_MIN_HEIGHT / 10));
    sliders.add(progressBar);

    add(buttons);
    add(sliders);
  }

  private void assertVolume() {
    Global.player.getStream().setVolume(Global.player.convertVolume(volumeSlider.getValue()));
  }

  @Override
  public void infoView(AudioInfo info) {
    progressBar.setIndeterminate(false);
    if (aif != null) {
      if (!aif.getTag(AudioInfo.KEY_ABSOLUTE_FILE_PATH).equals(info.getTag(AudioInfo.KEY_ABSOLUTE_FILE_PATH))) {
        new Thread(() -> Global.player.setFile(aif.getTag(AudioInfo.KEY_ABSOLUTE_FILE_PATH))).start();
      }
    }
    aif = info;
    progressSlider.setValue(0);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    new Thread(() -> {
      if (e.getSource().equals(playButton)) {
        if (aif != null) {
          if (!Global.player.getStream().isPlaying()) {
            if (!hasPlayed) {
              Global.player.setFile(aif.getTag(AudioInfo.KEY_ABSOLUTE_FILE_PATH));
              try {
                Global.player.getStream().open();
              } catch (AudioException e1) {
                Debugger.log(e1);
              }
              Global.player.play();
            } else {
              Global.player.getStream().resume();
            }
            assertVolume();
          } else {
            Global.player.getStream().pause();
          }
        }
      }
    }).start();

  }

  @Override
  public synchronized void stateChanged(ChangeEvent e) {
    if (e.getSource().equals(volumeSlider)) {
      new Thread(() -> Global.player.getStream().setVolume(Global.player.convertVolume(volumeSlider.getValue())))
          .start();
    }
  }
}
