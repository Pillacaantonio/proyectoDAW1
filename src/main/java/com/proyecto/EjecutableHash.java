package com.proyecto;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EjecutableHash {

    public static void main(String[] args) {
        String rawPassword = "1234";  // La contraseña en texto plano
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(rawPassword);

        System.out.println("Contraseña en texto plano: " + rawPassword);
        System.out.println("Contraseña hasheada: " + hashedPassword);


    }
}