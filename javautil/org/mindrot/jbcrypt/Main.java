package org.mindrot.jbcrypt;

public class Main {

    public static void main(String[] args) {
// Hash a password for the first time
        String password = "testpassword";
        String password4 = "testpassword2";

        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println("hashed: " + hashed);
        String hashed4 = BCrypt.hashpw(password4, BCrypt.gensalt());
        System.out.println("hashed4: " + hashed4);
// gensalt's log_rounds parameter determines the complexity
// the work factor is 2**log_rounds, and the default is 10
        String hashed2 = BCrypt.hashpw(password, BCrypt.gensalt(15));
//        System.out.println(hashed2);
// Check that an unencrypted password matches one that has
// previously been hashed
        String candidate = "testpassword";
        String hashed3 = BCrypt.hashpw(candidate, hashed);
        System.out.println("hashed3: "  + hashed3);
//String candidate = "wrongtestpassword";
        if (BCrypt.checkpw(candidate, hashed))
            System.out.println("It matches");
        else
            System.out.println("It does not match");
    }

}
