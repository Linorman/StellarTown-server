package com.hllwz.stellartownserver.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Python工具类
 * @auther Linorman
 * @version 1.0.0
 */
public class PythonUtil {

    /**
     * 执行Python脚本
     * @param scriptPath
     * @return result
     */
    public static String execPython(String scriptPath, String[] params) {
        String result = "";
        try {
            Runtime runtime = Runtime.getRuntime();
            Process process = null;
            if (params.length == 0) {
                process = runtime.exec("python3 " + scriptPath);
            }
            else {
                String param = "";
                for (String s : params) {
                    param += s;
                    param += " ";
                }
                process = runtime.exec("python3 " + scriptPath + " " + param);
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                result += line;
                result += "\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

//        public static void main(String[] args) {
//            String result = execPython("src/main/resources/python/fetch.py", new String[]{"87.174", "43.468"});
//            System.out.println(result);
//    }
}
