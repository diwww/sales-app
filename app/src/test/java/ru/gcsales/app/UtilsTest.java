package ru.gcsales.app;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import ru.gcsales.app.data.model.remote.ItemResponse;
import ru.gcsales.app.presentation.Utils;

import static org.junit.Assert.assertEquals;

public class UtilsTest {

    @Test
    public void testParseJsonList() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResource("items.json").openStream();
        List<ItemResponse> itemResponses = Utils.parseJsonList(inputStream, ItemResponse.class);

        System.out.println(itemResponses);
        System.out.println(itemResponses.get(0).getName());

        assertEquals(3, itemResponses.size());
        assertEquals(156579, itemResponses.get(0).getId());
    }

    @Test
    public void testParseJson() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResource("item.json").openStream();
        ItemResponse itemResponse = Utils.parseJson(inputStream, ItemResponse.class);

        System.out.println(itemResponse);

        assertEquals(156579, itemResponse.getId());
    }
}