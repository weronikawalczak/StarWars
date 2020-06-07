package com.weronika.nask;

import com.weronika.nask.client.StarwarsClient;
import com.weronika.nask.service.StarwarsServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

public class StarwarsServiceTest {
    @Mock
    private StarwarsClient starwarsClient;

    @Mock
    private ModelMapper characterMapper;

    StarwarsServiceImpl subject = new StarwarsServiceImpl(starwarsClient, characterMapper);

    @Test
    public void testSth(){

    }
}
