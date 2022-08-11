package com.halcyoninae.cosmos.theme.bundles;

import com.formdev.flatlaf.intellijthemes.FlatLightFlatIJTheme;
import com.halcyoninae.cosmos.theme.Theme;
import com.halcyoninae.cosmos.theme.ThemeType;
import com.halcyoninae.halcyon.utils.ColorTool;

import javax.swing.plaf.basic.BasicLookAndFeel;
import java.awt.*;

/**
 * This is the secondary light theme of the program.
 * This theme features a contrast between OneLight and
 * the native teal look from the DarkGreen color scheme.
 *
 * @author Jack Meng
 * @since 3.3
 */
public class LightGreen implements Theme {

    @Override
    public Class<? extends BasicLookAndFeel> getLAF() {
        return FlatLightFlatIJTheme.class;
    }

    @Override
    public Color getBackgroundColor() {
        return ColorTool.hexToRGBA("#cef5e6");
    }

    @Override
    public Color getForegroundColor() {
        return ColorTool.hexToRGBA("#2ed35a");
    }

    @Override
    public String getThemeName() {
        return "Light Green";
    }

    @Override
    public String getCanonicalName() {
        return "light_green";
    }

    @Override
    public ThemeType getThemeType() {
        return ThemeType.LIGHT;
    }

}