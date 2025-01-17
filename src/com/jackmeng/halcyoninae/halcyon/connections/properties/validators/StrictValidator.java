/*
 *  Copyright: (C) 2022 MP4J Jack Meng
 * Halcyon MP4J is a music player designed openly.
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

package com.jackmeng.halcyoninae.halcyon.connections.properties.validators;

import com.jackmeng.halcyoninae.halcyon.connections.properties.PropertyValidator;

/**
 * @author Jack Meng
 * @since 3.2
 */
public class StrictValidator implements PropertyValidator {
    public String[] tempaltes;

    public StrictValidator(String... allowedArgs) {
        this.tempaltes = allowedArgs;
    }


    /**
     * @param propertyValue
     * @return boolean
     */
    @Override
    public boolean isValid(String propertyValue) {
        for (String template : tempaltes) {
            if (propertyValue.equalsIgnoreCase(template)) {
                return true;
            }
        }
        return false;
    }
}
