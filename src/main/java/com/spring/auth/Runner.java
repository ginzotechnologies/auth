package com.spring.auth;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author diegotobalina created on 24/06/2020 Class that runs at the start of the application
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class Runner implements ApplicationRunner {

    @Override
    @SneakyThrows
    public void run(ApplicationArguments args) {
    }
}
