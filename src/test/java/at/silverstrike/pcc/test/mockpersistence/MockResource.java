/**
 * This file is part of Project Control Center (PCC).
 * 
 * Project Control Center (PCC) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Project Control Center (PCC) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Project Control Center (PCC).  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright 2010 Dmitri Anatol'evich Pisarenko
 **/

package at.silverstrike.pcc.test.mockpersistence;

import at.silverstrike.pcc.api.model.Resource;

class MockResource implements Resource {
    private String abbreviation;
    private double dailyLimitInHours;
    private Long id;

    public String getAbbreviation() {
        return abbreviation;
    }

    public double getDailyLimitInHours() {
        return dailyLimitInHours;
    }

    public Long getId() {
        return id;
    }

    public void setAbbreviation(final String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public void setDailyLimitInHours(final double dailyLimitInHours) {
        this.dailyLimitInHours = dailyLimitInHours;
    }

    public void setId(final Long id) {
        this.id = id;
    }

}
