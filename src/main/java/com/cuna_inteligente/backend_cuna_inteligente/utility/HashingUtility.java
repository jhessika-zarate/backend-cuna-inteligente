package com.cuna_inteligente.backend_cuna_inteligente.utility;

import org.mindrot.jbcrypt.BCrypt;

public class HashingUtility {
    public HashingUtility() {
    }

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
