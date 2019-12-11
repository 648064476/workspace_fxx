package com.fxx;


import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

/**
 * @author gantingting
 */
@SpringBootApplication
public class Test {
    public static void main (String[] args) {
//        SpringApplication.run(Test.class, args);
        User user = new User("anna@gmail.com", "1234");
        String email = Optional.ofNullable(user)
                .map(u -> u.getEmail()).orElse("default@gmail.com");

        System.out.println(email);

//        assertEquals(email, user.getEmail());
    }

    private static class User {
        private String email;
        private String name;
        public User (String s, String s1) {
            email = s;
            name = s1;
        }

        public String getEmail () {
            return email;
        }

        public void setEmail (String email) {
            this.email = email;
        }

        public String getName () {
            return name;
        }

        public void setName (String name) {
            this.name = name;
        }
    }
}
