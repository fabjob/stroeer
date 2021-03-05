package de.fhe.casestudy.stroeer;

import org.apache.logging.log4j.LogManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppMain {
   public static void main(String[] args) {
      SpringApplication.run(AppMain.class, args);


      if (args != null && args.length > 0 && "openbrowser".equalsIgnoreCase(args[0]))
         try {
            Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start chrome http://localhost:8080/login/"});
         } catch (Exception e) {
            LogManager.getLogger().error("Unable to open browser", e);
         }
   }
}
