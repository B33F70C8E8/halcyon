package com.halcyoninae.cosmos.theme.bundles;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatAtomOneDarkIJTheme;
import com.halcyoninae.cosmos.theme.Theme;
import com.halcyoninae.cosmos.theme.ThemeType;
import com.halcyoninae.halcyon.utils.ColorTool;

import javax.swing.plaf.basic.BasicLookAndFeel;
import java.awt.*;

/**
 * This is the default dark theme of the program.
 * <p>
 * It contrasts the OneDark background color, with the
 * tealish foreground colors.
 *
 * @author Jack Meng
 * @since 3.3
 */
public class DarkGreen implements Theme {

    @Override
    public Class<? extends BasicLookAndFeel> getLAF() {
        return FlatAtomOneDarkIJTheme.class;
    }

    @Override
    public Color getBackgroundColor() {
        return ColorTool.hexToRGBA("#cef5e6");
    }

    @Override
    public Color getForegroundColor() {
        return ColorTool.hexToRGBA("#89EDA4");
    }

    @Override
    public String getThemeName() {
        return "Dark Green";
    }

    @Override
    public String getCanonicalName() {
        return "dark_green";
    }

    @Override
    public ThemeType getThemeType() {
        return ThemeType.DARK;
    }

}
