package com.weronika.nask;

import com.weronika.nask.util.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UtilTest {

    @Test
    public void testDivision(){
        int elementsOnFirstPage=10;
        int allElementsCount=82;

        int pages = Util.getCeilOfDivision(allElementsCount, elementsOnFirstPage);

        Assertions.assertEquals(9, pages);
    }

    @Test
    public void testDivisionOnePage(){
        int elementsOnFirstPage=10;
        int allElementsCount=8;

        int pages = Util.getCeilOfDivision(allElementsCount, elementsOnFirstPage);

        Assertions.assertEquals(1, pages);
    }
}
