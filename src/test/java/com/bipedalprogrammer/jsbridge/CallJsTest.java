package com.bipedalprogrammer.jsbridge;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CallJsTest {
    private CallJs subject;

    @Before
    public void setup() {
        subject = new CallJs();
    }

    @Test
    public void shouldReturnString() {
        Object result = subject.execute("'hello'");
        assertTrue(result instanceof java.lang.String);
        assertEquals("hello", (String)result);
    }
}
