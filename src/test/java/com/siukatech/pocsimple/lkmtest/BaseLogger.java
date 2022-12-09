package com.siukatech.pocsimple.lkmtest;

//
//class ClassA {
//    protected String getStr() { return ""; }
//}
//
//class ClassB extends ClassA {
////    @Override
////    protected String getStr() { return ""; }
//@Override
//public String getStr() { return ""; }
//}

public class BaseLogger {
    private static BaseLogger log = new BaseLogger();
    private BaseLogger() {}
    public synchronized static BaseLogger getInstance() {
        return log;
    }
    private StringBuilder logMessage = new StringBuilder();
    public void addLog(String logMessage) {
        this.logMessage.append(logMessage + "|");
    }
}