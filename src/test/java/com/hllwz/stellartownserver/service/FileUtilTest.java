package com.hllwz.stellartownserver.service;

import com.hllwz.stellartownserver.utils.FileUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FileUtilTest {

    @Autowired
    private FileUtil fileClient;

    @Test
    public void test() {
        System.out.println(fileClient.bucketExists("test"));
    }
}