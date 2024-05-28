package com.example.springboottodo.utils;

import com.example.springboottodo.Entity.User;

import java.util.Random;

public class GenerateRandomUser {
    public static User generateRandomUser() {
        String[] firstNames = {"John", "Jane", "Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace", "Hank", "Irene", "Jack", "Kelly", "Liam", "Mia", "Noah", "Olivia", "Paul", "Quinn", "Rachel", "Sam", "Tina", "Uma", "Victor", "Wendy", "Xavier", "Yara", "Zane"};
        String[] lastNames = {"Smith", "Doe", "Johnson", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor", "Anderson", "Williams", "Jones", "Garcia", "Martin", "Thompson", "White", "Lopez", "Lee", "Gonzalez", "Harris", "Clark", "Lewis", "Robinson", "Walker", "Perez", "Hall", "Young", "Allen", "Sanchez", "Wright", "King", "Scott", "Green", "Baker", "Adams", "Nelson", "Hill", "Ramirez", "Campbell", "Mitchell", "Roberts", "Carter", "Phillips", "Evans", "Turner", "Torres", "Parker", "Collins", "Edwards", "Stewart", "Flores", "Morris", "Nguyen", "Murphy", "Rivera", "Cook", "Rogers", "Morgan", "Peterson", "Cooper", "Reed", "Bailey", "Bell", "Gomez", "Kelly", "Howard", "Ward", "Cox", "Diaz", "Richardson", "Wood", "Watson", "Brooks", "Bennett", "Gray", "James", "Reyes", "Cruz", "Hughes", "Price", "Myers", "Long", "Foster", "Sanders", "Ross", "Morales", "Powell", "Sullivan", "Russell", "Ortiz", "Jenkins", "Gutierrez", "Perry", "Butler", "Barnes", "Fisher"};
        String[] emails = {"gmail.com", "yahoo.com", "hotmail.com", "outlook.com", "protonmail.com", "aol.com", "icloud.com", "zoho.com", "yandex.com", "mail.com"};

        Random random = new Random();
        String firstName = firstNames[random.nextInt(firstNames.length)];
        String lastName = lastNames[random.nextInt(lastNames.length)];
        String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@" + emails[random.nextInt(emails.length)];
        String password = firstName.substring(0, random.nextInt(firstName.length())) +
                lastName.substring(0, random.nextInt(lastName.length())) +
                random.nextInt(10000);

        if(password.length() < 9) {
            password += generateRandomString(9 - password.length());
        }

        return new User(firstName + " " + lastName, email, password);
    }

    public static String generateRandomString(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            char randomChar = (char) ('a' + random.nextInt(26)); // Generate a random lowercase letter
            sb.append(randomChar);
        }

        return sb.toString();
    }
}
