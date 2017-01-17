package com.bipedalprogrammer.jsbridge;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Created by bipedal on 1/13/17.
 */
public class CallJs {
    private ScriptEngineManager manager;
    private ScriptEngine engine;

    public CallJs() {
        manager = new ScriptEngineManager();
        engine = manager.getEngineByName("nashorn");

    }

    public ScriptEngine getEngine() {
        return this.engine;
    }

    public Object execute(String js) {
        Object result = null;
        try {
            result = engine.eval(js);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void compile(String js) {
        try {
            engine.eval(js);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    public Object call(String methodName, Object arg) {
        Object result = null;
        try {
            Invocable invoce = (Invocable) engine;
            result = invoce.invokeFunction(methodName, arg);
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return result;
    }
}
