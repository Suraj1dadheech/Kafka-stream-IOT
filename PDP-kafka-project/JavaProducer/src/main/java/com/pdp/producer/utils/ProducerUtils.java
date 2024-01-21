package com.pdp.producer.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class ProducerUtils {
    Random random = new Random();

    public String fetchRandomUUIDForHealthDevice() {
        String[] uuids = {"417830e4-44f0-4c76-b19c-e3d470c8db9e",
                "34053e20-1e9b-4163-a476-b5c26a3bc98c",
                "3720970a-84df-41d4-9a22-215af0370a46",
                "6a6c2daa-24f9-4251-aede-ea6e5b96ce1f",
                "67e4bb85-2c62-4869-b7e8-40a50c26f715",
                ""};
        return uuids[random.nextInt(uuids.length)];
    }

    public String fetchRandomUUIDForWeatherDevice() {

        String[] uuids = {"be520bc3-15bd-404c-9693-bfb5329b05df",
                "f41e8631-cac4-4ab2-817e-1ae5f40cc9bc",
                "da4fe9ff-0fba-44c4-887e-152a5242aa65",
                "1ef81f9a-0ee8-49c1-927e-18ac445d45f8",
                "f0048997-5110-4f82-b979-ba95c608259c",
                ""};
        return uuids[random.nextInt(uuids.length)];

    }
}
