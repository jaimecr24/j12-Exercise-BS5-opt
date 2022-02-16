package com.spring.exercisebs5;

import ch.qos.logback.core.rolling.TriggeringPolicyBase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.File;

//@Configuration
//@PropertySource("application.configuration")
public class RollOncePerSessionTriggeringPolicy<E> extends TriggeringPolicyBase<E> {
    private static boolean doRolling = true;

    //Duda: no lee el valor del fichero.
    //@Value("${maxFileSize}")
    //private String maxFileSize;

    @Override
    public boolean isTriggeringEvent(File activeFile, E event) {
    // roll the first time when the event gets called
        //System.out.println("maxFileSize: "+maxFileSize);
        if (doRolling) {
            doRolling = false;
            return true;
        }
        if (activeFile.length()>5120) return true;
        return false;
    }
}