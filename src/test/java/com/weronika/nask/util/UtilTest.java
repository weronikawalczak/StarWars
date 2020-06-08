package com.weronika.nask.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UtilTest {

    @Test
    public void testCeilingDivisionOnMultiplePages(){
        int elementsOnFirstPage=10;
        int allElementsCount=82;

        int pages = Util.getCeilOfDivision(allElementsCount, elementsOnFirstPage);

        Assertions.assertEquals(9, pages);
    }

    @Test
    public void testCeilingDivisionOnOnePage(){
        int elementsOnFirstPage=10;
        int allElementsCount=8;

        int pages = Util.getCeilOfDivision(allElementsCount, elementsOnFirstPage);

        Assertions.assertEquals(1, pages);
    }

    @Test
    public void testGettingLastDigitFromURL(){
        String characterId = "15";
        String url = String.format("https://swapi.dev/api/people/%s/", characterId);

        int lastDigitFromURL = Util.getLastDigitFromURL(url);

        Assertions.assertEquals(Integer.valueOf(characterId), lastDigitFromURL);
    }

    @Test
    public void testCalculateCharacterIdByPage(){
        int pageSize = 10;
        int pageNumber = 2;
        int indexOnPage = 3;

        Assertions.assertEquals(13, Util.calculateCharacterIdByPage(pageSize, pageNumber, indexOnPage));
    }
}
