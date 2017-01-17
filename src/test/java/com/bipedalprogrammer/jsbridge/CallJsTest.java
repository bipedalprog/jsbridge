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

    @Test
    public void shouldReturnObject() {
        Object result = subject.execute("JSON.parse('{\"key\": \"value\"}')");
        assertTrue(result instanceof java.util.Map);
        assertEquals("value", ((java.util.Map<String, String>)result).get("key"));
    }

    @Test
    public void shouldCallFunction() {
        subject.compile("function echo(val) {return val;}");
        Object result = subject.call("echo", "echo");
        assertEquals("echo", result);
    }
}
