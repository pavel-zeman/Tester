package test.gcc;


import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import test.common.TestBase;
import test.common.constant.DefaultValue;
import test.common.constant.Dimension;
import test.common.element.ComboSingleSelect;
import test.common.element.DayIntervalFilter;
import test.common.element.Page;

public class Gcc003ITest extends TestBase {

    private static final String PAGE = "GCC003I";

    @Test
    public void testDefaultData() {
        Page page = Page.get(PAGE);
        DayIntervalFilter dayIntervalFilter = page.getDayIntervalFilter();
        ComboSingleSelect party = page.getComboSingleSelectFilter(Dimension.PARTY);
        party.waitForDefaultValueSet();

        Assert.assertEquals(LocalDate.now().minusDays(1), dayIntervalFilter.getDayFrom().getValue());
        Assert.assertEquals(LocalDate.now().minusDays(1), dayIntervalFilter.getDayTo().getValue());
        Assert.assertEquals(DefaultValue.PARTY, party.getValue());
    }



}
