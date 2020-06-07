package com.weronika.nask;

import com.weronika.nask.client.StarwarsClient;
import com.weronika.nask.service.StarwarsService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

public class StarwarsServiceTest {
    @Mock
    private StarwarsClient starwarsClient;

    @Mock
    private ModelMapper characterMapper;

    StarwarsService subject = new StarwarsService(starwarsClient, characterMapper);

    @Test
    public void testSth(){

    }
}
