package utils;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class Logger {

    public static void logInfo(String message){
        Logger.log.info(message);
    }
}
