package com.bipedalprogrammer.jsbridge;

import jdk.nashorn.api.scripting.ScriptObjectMirror;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import static org.junit.Assert.*;

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
        final String arg = "ECHO";
        subject.compile("function echo(val) {return val;}");
        Object result = subject.call("echo", arg);
        assertEquals(arg, result);
    }

    @Test
    public void shouldNotBeValid() {
        subject.compile(read("address.js"));
        UsAddress address = new UsAddress();
        Boolean valid = (Boolean) subject.call("isValidShippingAddress", address);
        assertFalse(valid);
    }

    @Test
    public void shouldBeValid() {
        subject.compile(read("address.js"));
        UsAddress address = new UsAddress();
        address.setZipCode("45030");
        address.setCity("Harrison");
        address.setState("Ohio");
        address.setStreet1("100 Harrison Avenue");
        Boolean valid = (Boolean) subject.call("isValidShippingAddress", address);
        assertTrue(valid);
    }

    @Test
    public void shouldAllowMultipleArgs() {
        subject.compile(read("address.js"));
        Object obj = subject.call("makeAddress", "USA", "45030", "Ohio",
                "100 Harrision Avenue", "");
        assertNotNull(obj);
        ScriptObjectMirror mirror = (ScriptObjectMirror) obj;
        assertTrue(mirror.containsKey("country"));
    }

    private Reader read(String path) {
        InputStream is = getClass().getClassLoader().getResourceAsStream(path);
        return new InputStreamReader(is);
    }
}
