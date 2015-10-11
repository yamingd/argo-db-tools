package com.argo.db.tools;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by yamingd on 9/19/15.
 */
public class AppTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testMain() throws Exception {
        String[] args = new String[]{"-a=xls"};
        App.main(args);
    }
}
