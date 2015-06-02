package com.project.appium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessKiller {

    public static void closeSimulatorAndInstruments() {
        killProcess("iPhoneSimulator");
        killProcess("launchd_sim");
        killProcess("instruments");
    }

    private static void killProcess(String processName) {
        try {
            String line;
            Process p = Runtime.getRuntime().exec("/bin/sh -c ps -A | grep " + processName);
            p.waitFor();
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                if (!line.isEmpty()) {
                    String[] processInfo = line.split("\\s+");
                    if (line.contains(processName)) {
                        Runtime.getRuntime().exec("kill -9 " + processInfo[1]);
                    }
                }
            }
            input.close();
        } catch (IOException e) {
        } catch (InterruptedException e) {
        }
    }
}
