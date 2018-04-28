package OSterminalLinux;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by fanghui on 2017/10/16.
 */
public class TermLinuxTest {
    public static void main(String[] args) {
        String osname = System.getProperty("os.name");
        String osversion = System.getProperty("os.version");
        String userdir = System.getProperty("user.dir");
        String username = System.getProperty("user.name");
        System.out.println("Current OS :" + osname + "\n"
                            + "OS Version:" + osversion + "\n"
                            + "Current user direct: " + userdir + "\n"
                            + "Current user name:  " + username);
//        System.out.println("Current Process OS ENV is:");
//        System.out.println("**********************************************");
//        Map<String,String> map = System.getenv();
//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            System.out.println("* Key = " + entry.getKey() + ", Value = " + entry.getValue());
//        }
//        System.out.println("**********************************************");
//        System.out.println("^^^^^^^^ Current Process OS ENV end! ^^^^^^^^^^");

        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        System.out.println("=========== CALL CMD1 (系统命令)=========== \n"
                + "\"" + s + "\"" + ":\n");
        String result1 = cmd1(s);
        System.out.println(result1);
        System.out.println("=========== CALL CMD1 END =========== \n");

        System.out.println("=========== CALL CMD2（系统命令） =========== \n"
                + "\"" + s + "\"" + ":\n");
        String result2 = cmd2(s);
        System.out.println(result2);
        System.out.println("=========== CALL CMD2 END =========== \n");

        System.out.println("=========== CALL CMD3（管道命令） =========== \n"
                + "\"" + s + "\"" + ":\n");
        String result3 = cmd3(s);
        System.out.println(result3);
        System.out.println("=========== CALL CMD3 END =========== \n");

    }

    /*
    创建的子进程没有自己的终端控制台，所有标注操作都会通过三个流(getOutputStream()、getInputStream() 和 getErrorStream())
     重定向到父进程（父进程可通过这些流判断子进程的执行情况）
2，因为有些本机平台仅针对标准输入和输出流提供有限的缓冲区大小，如果读写子进程的输出流或输入流迅速出现失败，
则可能导致子进程阻塞，甚至产生死锁
     */


    //Runtime Proccess
    public static String cmd1(String cmd) {
        try {
            System.out.println("#### cmd1  Runtime Proccess #### @ String cmd1(String cmd)");
            Process process = Runtime.getRuntime().exec(cmd);
            StringBuilder result = new StringBuilder();
            String s = null;


            /*子进程输出给父进程一个错误流 */
            InputStream ise = process.getErrorStream();
            BufferedReader bre = new BufferedReader(new InputStreamReader(ise));
            while ((s = bre.readLine()) != null) {
                result.append("[ErrorStream:]").append(s).append("\n");
            }

            /*子进程输出给父进程一个输入流*/
            InputStream isi = process.getInputStream();
            BufferedReader bri = new BufferedReader(new InputStreamReader(isi));
            while ((s = bri.readLine()) != null) {
                result.append("[InputStream:]" + s).append("\n");
            }

            int pW = process.waitFor();//导致当前线程等待，如有必要，一直要等到由该 Process 对象表示的进程已经终止。
            System.out.println("---waitFor() VALUE:" + pW + "--- Process END!");
            int exit = process.exitValue();//返回子进程的出口值。根据惯例，值0表示正常终止
            System.out.println("---exitValue EXIT VALUE:" + exit + "--- 0 is OK!");

            process.destroy();
            System.out.println("---KILL process!---");

            bre.close();
            ise.close();
            bri.close();
            isi.close();
            return result.toString();

        } catch (Exception e) {
            System.out.println("No this command or deny access!");
        }
        return null;
    }

    //ProcessBuilder类
    /* 每个 ProcessBuilder 实例管理一个进程属性集。
    它的start() 方法利用这些属性创建一个新的 Process 实例。
    start() 方法可以从同一实例重复调用，以利用相同的或相关的属性创建新的子进程。
    redirectErrorStream 属性。最初，此属性为 false，意思是子进程的标准输出和错误输出被发送给两个独立的流，
    这些流可以通过 Process.getInputStream() 和 Process.getErrorStream() 方法来访问。
    如果将值设置为 true，标准错误将与标准输出合并。这使得关联错误消息和相应的输出变得更容易。在此情况下，
    合并的数据可从 Process.getInputStream() 返回的流读取，而从 Process.getErrorStream() 返回的流读取将直接到达文件尾。
    ProcessBuilder是一个final类，有两个带参数的构造方法，你可以通过构造方法来直接创建ProcessBuilder的对象。
    而Process是一个抽象类，一般都通过Runtime.exec()和ProcessBuilder.start()来间接创建其实例。

    */
    public static String cmd2(String cmd){
        StringBuilder result = new StringBuilder();
        try {
            System.out.println("#### cmd2  ProcessBuilder Proccess #### @  String cmd2(String cmd)");

            ProcessBuilder pb = new ProcessBuilder(cmd.split("\\s"));//创建进程生成器
//            pb.command(cmd);//设置此进程生成器的操作系统程序和参数
            //返回此进程生成器的工作目录。
            //由此对象的 start() 方法启动的后续子进程将使用此目录作为它们的工作目录。
            // 返回值可以为 null，这意味着要使用当前 Java 进程的工作目录，通常是由系统属性 user.dir 指定的目录作为子进程的工作目录。

//            if (pb.directory() != null){
//                File workdirect = pb.directory();
//                String path = workdirect.getAbsolutePath();
//                System.out.println("[info]ProcessBuilder is working at Absolute Path :" + path);
//            }else{
//                System.out.println("[info]ProcessBuilder work direct is NULL(Java 进程的工作目录)");
//            }

//            pb.directory(new File("myDir"));//设置此进程生成器的工作目录

            //返回此进程生成器环境的字符串映射视图 并修改env
//            Map<String,String> env = pb.environment();
//              env.put("VAR1", "myValue");
//              env.remove("OTHERVAR");
//              env.put("VAR2", env.get("VAR1") + "suffix");

            //设置此进程生成器的 redirectErrorStream 属性。
            // 如果此属性为 true，则任何由通过此对象的 start() 方法启动的后续子进程生成的错误输出都将与标准输出合并，
            // 因此两者均可使用 Process.getInputStream() 方法读取。这使得关联错误消息和相应的输出变得更容易。初始值为 false。

            pb.redirectErrorStream(true);

            //使用此进程生成器的属性启动一个新进程。一个新的 Process 对象，用于管理子进程
            // 在 directory() 指定的工作目录中，利用 environment() 指定的进程环境，新进程将调用由 command() 给出的命令和参数。
            //针对此进程生成器的后续修改将不会影响返回的 Process。
            Process process = pb.start();

            String s = null;
            /*连接到子进程正常输出的输入流*/
            InputStream isi = process.getInputStream();
            BufferedReader bri = new BufferedReader(new InputStreamReader(isi));
            while ((s = bri.readLine()) != null) {
                result.append("[InputStream:]" + s).append("\n");
            }

            int pW = process.waitFor();//导致当前线程等待，如有必要，一直要等到由该 Process 对象表示的进程已经终止。
            System.out.println("---waitFor() VALUE:" + pW + "--- Process END!");
            int exit = process.exitValue();//返回子进程的出口值。根据惯例，值0表示正常终止
            System.out.println("---exitValue EXIT VALUE:" + exit + "--- 0 is OK!");

            process.destroy();
            System.out.println("---KILL process!---");
            bri.close();
            isi.close();
        } catch (Exception e) {
            System.out.println("No this command or deny access!");
        }
        return result.toString();
    }

    public static String cmd3(String cmd){
        System.out.println("#### cmd3  ProcessBuilder Proccess 解决管道命令 如重定向 #### @ String cmd3(String cmd)");
        StringBuilder result = new StringBuilder();
        ProcessBuilder builder;

        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.indexOf("windows 9") > -1) {
            builder = new ProcessBuilder("command.com ", "/c", cmd);
        } else if (osName.startsWith("windows")) {
            builder = new ProcessBuilder("cmd.exe ", "/c", cmd);
        } else {
            // Linux,Unix
            builder = new ProcessBuilder("/bin/sh", "-c", cmd);
        }

        builder.redirectErrorStream(true);
        try {
            Process process = builder.start();
            Scanner scanner = new Scanner(process.getInputStream());
            while (scanner.hasNextLine()) {
                result.append("[InputStream]").append(scanner.nextLine()).append("\n");
            }
            int pW = process.waitFor();//导致当前线程等待，如有必要，一直要等到由该 Process 对象表示的进程已经终止。
            System.out.println("---waitFor() VALUE:" + pW + "--- Process END!");
            int exit = process.exitValue();//返回子进程的出口值。根据惯例，值0表示正常终止
            System.out.println("---exitValue EXIT VALUE:" + exit + "--- 0 is OK!");

            process.destroy();
            System.out.println("---KILL process!---");
            if (scanner != null) {
                scanner.close();
            }

        } catch (Exception e ) {
            System.out.println("No this command or deny access!");
        }
        return result.toString();
    }

}
