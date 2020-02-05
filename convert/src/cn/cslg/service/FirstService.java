package cn.cslg.service;

import java.io.*;
import java.nio.Buffer;
import java.nio.file.Files;

public class FirstService {
    private InputStreamReader isr = null;
    private OutputStreamWriter osw = null;
    private FileInputStream fis = null;
    private FileOutputStream fos = null;
    private String path1;
    public FirstService(){

    }
    public FirstService(String filepath) {
        String path2 = filepath.substring(filepath.lastIndexOf("\\",filepath.length()));
        this.path1 = path2.replace("\\","");
        String exist = filepath.replace(path1,"new_"+path1);
        File file = new File(exist);
        if (file.exists()){
            delFile(file);
        }
    }

    public void delFile(File file){
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for (File file1 : files) {
                delFile(file1);
            }
        }
        file.delete();
    }

    //对路径下所有后缀名文件转换
    public void getAllFiles(String path,String before,String after,String suffix) {
        File file = new File(path);
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                getAllFiles(f.getPath(),before,after,suffix);//递归
            } else {
                //对文件进行过滤
                if (f.getPath().endsWith(suffix)) {
                    //在这里面进行代码的转换
//                    System.out.println(f);
                    changeCodeEcoding(f.getPath(),before,after);
                }else{
                    //不进行转换直接复制
//                    System.out.println(f.getPath());
                    copyFile(f.getPath());
                }
            }
        }
    }
    //复制非后缀名的文件
    private void copyFile(String path) {
        try{
            fis = new  FileInputStream(path);
            String s = changePath(path);
            File file = new File(s);
            if(!file.exists()){
                file.getParentFile().mkdirs();
                fos = new FileOutputStream(s);
                byte[] bytes = new byte[1024];
                int len = 0;
                while ((len = fis.read(bytes)) != -1) {
                    fos.write(bytes, 0, len);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                fis.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //对文件进行编码转化
    public void changeCodeEcoding(String path,String before,String after) {
        try {
            isr = new InputStreamReader(new FileInputStream(path),before);
            String s = changePath(path);
            File file = new File(s);
            if(!file.exists()){
                file.getParentFile().mkdirs();
                osw = new OutputStreamWriter(new FileOutputStream(s), after);
                char[] c = new char[1024];
                int len = 0;
                while ((len = isr.read(c)) != -1) {
                    osw.write(c, 0, len);
                }
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
    }

    //将a项目copy转换编码到同路径的aa文件夹下
    public String changePath(String path) {
        String newPath = path.replace(path1, "new_"+path1);
        return newPath;
    }


}
