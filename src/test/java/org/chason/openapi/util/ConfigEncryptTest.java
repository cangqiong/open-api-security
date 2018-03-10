package org.chason.openapi.util;

import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ConfigEncryptTest {

    private String pass = "pass";

    @Test
    public void test() {

        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        // 获取加密参数
        encryptor.setPassword(pass);

        System.out.println(encryptor.encrypt("root"));
        System.out.println(encryptor.encrypt("admin"));
        System.out.println(encryptor.decrypt("GlkQze6XPF2fJZXcqp5cLw=="));
    }
}