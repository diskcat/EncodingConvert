package cn.cslg.service;

import java.io.*;

public class SecondService {

    private InputStreamReader isr = null;
    private OutputStreamWriter osw = null;
    private FileInputStream fis = null;
    private FileOutputStream fos = null;

    public SecondService() {
    }

    public String transcoding(String path, String before, String after){
        String s = null;
        try {
            isr = new InputStreamReader(new FileInputStream(path),before);
            String fileName = path.substring(path.lastIndexOf("\\")+1);
            s = path.replace(fileName, "new_" + fileName);
            File file = new File(s);
            if(file.exists()){
                file.delete();
            }
            osw = new OutputStreamWriter(new FileOutputStream(s), after);
            char[] c = new char[1024];
            int len = 0;
            while ((len = isr.read(c)) != -1) {
                osw.write(c, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                isr.close();
                osw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return s;
    }
}
