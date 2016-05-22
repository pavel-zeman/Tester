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
import test.common.element.PivotTable;

public class Gcc002ITest extends TestBase {

    private static final String PAGE = "GCC002I";

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

    @Test
    public void testTableLoad() {
        Page page = Page.get(PAGE);
        DayIntervalFilter dayIntervalFilter = page.getDayIntervalFilter();
        dayIntervalFilter.getDayFrom().setValue(5, 5, 2015);
        dayIntervalFilter.getDayTo().setValue(4, 6, 2015);
        ComboSingleSelect party = page.getComboSingleSelectFilter(Dimension.PARTY);

        party.setValue("MAVIR");
        page.submit();
        PivotTable table = page.getPivotTable();
        table.waitForData();
        Assert.assertEquals("10,000", table.getValue(0, 1));

        party.setValue(DefaultValue.PARTY);
        page.submit();
        table.waitForData();
        Assert.assertEquals("1,060", table.getValue(0, 1));

    }



}
