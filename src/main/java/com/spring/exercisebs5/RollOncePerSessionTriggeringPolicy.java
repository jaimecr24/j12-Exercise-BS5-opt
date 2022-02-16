package com.spring.exercisebs5;

import ch.qos.logback.core.rolling.TriggeringPolicyBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;

@Component
public class RollOncePerSessionTriggeringPolicy<E> extends TriggeringPolicyBase<E>  {
    private static boolean doRolling = true;
    private static long maxFileSize;

    @Override
    public boolean isTriggeringEvent(File activeFile, E event) {
    // roll the first time when the event gets called
        if (doRolling) {
            doRolling = false;
            return true;
        }
        if (activeFile.length()>maxFileSize) return true;
        return false;
    }

    @Autowired MyProperties myProperties;

    @PostConstruct
    void init() {
        System.out.println("En init");
        if (myProperties == null)
            System.out.println("myProperties es nulo");
        else {
          maxFileSize = Integer.parseInt(myProperties.getMaxFileSize());
          System.out.println("Saliendo de init con maxFileSize: " + maxFileSize);
        }
    }
}