package br.com.smashingthosepumpkins.app;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {
    public void start(String[] args) {
        System.out.println("Hello World!");
    }
}
