package com.bogdan.cormen.graphs;

import java.io.File;
import java.net.URL;

import static junit.framework.Assert.assertNotNull;

public class BaseFileTest {

    protected File loadResourceFile(String filePath) {
        URL url = this.getClass().getResource(filePath);
        assertNotNull("Can't open input file!", url);
        return new File(url.getFile());
    }
}
