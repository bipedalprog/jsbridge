package com.bipedalprogrammer.jsbridge;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.Reader;

public class CallJs {
    private ScriptEngine engine;

    public CallJs() {
        ScriptEngineManager manager = new ScriptEngineManager();
        engine = manager.getEngineByName("nashorn");

    }

    public ScriptEngine getEngine() {
        return this.engine;
    }

    /**
     * Execute the javascript and return the result.
     * @param js String containing script.
     * @return Object representing the return value.
     */
    public Object execute(String js) {
        Object result = null;
        try {
            result = engine.eval(js);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Update the state of javascript instance with the contents of the given script.
     * @param js String containing the script.
     */
    public void compile(String js) {
        try {
            engine.eval(js);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update the statue of the javascript instance with contents of the given stream.
     * @param reader Reader interface to javascript file.
     */
    public void compile(Reader reader) {
        try {
            engine.eval(reader);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    /**
     * Invoke a javascript function by name with a single argument.
     * @param methodName name of function to call.
     * @param arg Argument.
     * @return result.
     */
    public Object call(String methodName, Object arg) {
        Object result = null;
        try {
            Invocable invoce = (Invocable) engine;
            result = invoce.invokeFunction(methodName, arg);
        } catch (NoSuchMethodException | ScriptException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Invoke a javascript function with more than one argument.
     * @param methodName function to call.
     * @param args arguments.
     * @return result.
     */
    public Object call(String methodName, Object... args) {
        Object result = null;
        try {
            Invocable invoce = (Invocable) engine;
            result = invoce.invokeFunction(methodName, args);
        } catch (NoSuchMethodException | ScriptException e) {
            e.printStackTrace();
        }
        return result;
    }
}
