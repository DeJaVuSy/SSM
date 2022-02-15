package com.zjp.generatemysql.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileDelUtil {

    public static void main(String[] args) {
        deleteClass("EduClass");
    }

    /**
     * 删除文件Class类
     * @param ClassName 类名
     * @return
     */
    public static void deleteClass(String ClassName) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        //获取当前时间
        String currentTime = df.format(new Date());
        //String ClassName = "Student";

        Class class1 = FileDelUtil.class;

        //获取本class的目录
        String classPath = new File("").getAbsolutePath()+"\\src\\main\\java\\"+class1.getPackage().getName().replace(".", "\\");
        File file = new File(classPath);
        //获取上级的父目录名称
        String parentFileName = file.getParentFile().getName();
        //获取controller目录
        String controllerPath = file.getParent()+"\\controller\\"+ClassName+"Controller.java";
        //获取entity目录
        String entityPath = file.getParent()+"\\entity\\"+ClassName+".java";
        //获取mapper目录
        String mapperPath = file.getParent()+"\\mapper\\"+ClassName+"Mapper.java";
        //获取service目录
        String servicePath = file.getParent()+"\\service\\I"+ClassName+"Service.java";
        //获取impl目录
        String serviceImplPath = file.getParent()+"\\service\\impl\\"+ClassName+"ServiceImpl.java";
        //获取resources中的mapper目录
        String resourcesMapperPath = new File("").getAbsolutePath()+"\\src\\main\\resources\\mapper\\"+parentFileName+"\\"+ClassName+"Mapper.xml";

        String[] arrayPath = {controllerPath,entityPath,mapperPath,servicePath,serviceImplPath,resourcesMapperPath};

        String[] arrayClassName = {ClassName+"Controller.java",ClassName+".java",ClassName+"Mapper.java",ClassName+"Service.java",ClassName+"ServiceImpl.java",ClassName+"Mapper.xml"};

//        System.out.println(controllerPath);
//        System.out.println(entityPath);
//        System.out.println(mapperPath);
//        System.out.println(servicePath);
//        System.out.println(serviceImplPath);
//        System.out.println(resourcesMapperPath);
        //StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0;i < arrayPath.length; i++){
            //System.out.println(String.join("-",s,s));;
            String Path = arrayPath[i];
            String Name = arrayClassName[i];
            if (deleteFile(Path)){
                System.out.println(currentTime+" "+Name+"文件删除成功，文件目录："+Path);
                //stringBuilder.append(currentTime+" "+Name+"文件删除成功，文件目录："+Path).append("\r\n");
            }else {
                System.out.println(currentTime+" "+Name+"文件不存在，文件目录："+Path);
                //stringBuilder.append(currentTime+" "+Name+"文件不存在，文件目录："+Path).append("\r\n");
            }
        }
        //System.out.println(stringBuilder);
        //return stringBuilder.toString();
    }




    /**
     * 入口方法
     * 根据路径删除指定的目录或文件，无论存在与否
     * @param path  要删除的目录或文件路径
     * @return 删除成功返回 true，否则返回 false。
     */
    public static boolean deleteFolder(String path) {
        //验证字符串是否为正确路径名的正则表达式
        String regex = "[A-Za-z]:\\\\[^:?\"><*]*";
        if (path == null || !path.matches(regex)) {
            System.out.println("path=" + path);
            return false;
        }
        File file = new File(path);
        //判断目录或文件是否存在
        if (!file.exists()) {
            return false;
        } else {
            //判断是否为文件
            if (file.isFile()) {
                return deleteFile(path);
            } else {
                return deleteDirectory(path);
            }
        }
    }

    /**
     * 删除单个文件
     * @param path 被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    private static boolean deleteFile(String path) {
        File file = new File(path);
        //路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            return true;
        }
        return false;
    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     * @param  path 被删除目录的文件路径
     * @return  目录删除成功返回true，否则返回false
     */
    private static boolean deleteDirectory(String path) {
        //如果path不以文件分隔符结尾，自动添加文件分隔符
        if (!path.endsWith(File.separator)) {
            path = path + File.separator;
        }
        File dirFile = new File(path);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {//删除子文件
                flag = deleteFile(files[i].getAbsolutePath());
            }else { //删除子目录
                flag = deleteDirectory(files[i].getAbsolutePath());
            }
            if (!flag) return flag;
        }
        //删除当前目录
        return dirFile.delete();
    }

}
