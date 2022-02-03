package br.com.smashingthosepumpkins.app;

import org.springframework.stereotype.Service;

/**
 * @author Pablo A. G. Silva Jr. on 26/01/2022
 * @project pokemon-repository-testing
 */
@Service
public class ShutdownService {
    public void shutdown() {
        System.exit(0);
    }

    public void shutdown(Exception e) {
        e.printStackTrace();
        System.exit(0);
    }
}
