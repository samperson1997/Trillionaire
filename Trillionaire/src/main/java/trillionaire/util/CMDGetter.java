package trillionaire.util;

/**
 * Created by USER on 2017/6/5.
 */
public class CMDGetter {

    public static int getOSPathStarter(){

        String osName = System.getProperty("os.name");

        if(osName.equals("Windows 10")){

            return 1;
        }
        else{

            return 0;
        }

    }

    public static String getCmdCharSet(){

        String osName = System.getProperty("os.name");

        if(osName.equals("Windows 10")){

            return "GB2312";
        }
        else{

            return "UTF-8";
        }

    }

    public static String[] getCommand(String command){

        String osName = System.getProperty("os.name");

        if(osName.equals("Windows 10")){

            return new String[] { "cmd.exe", "/C", "activate python36 && " + command };
        }
        else{

            System.out.println(command);
            return new String[] { "/bin/sh", "-c", command };
        }

    }



}
