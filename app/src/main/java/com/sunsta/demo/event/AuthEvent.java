package com.sunsta.demo.event;

/**
 * Created by sunsta on
 * <p>
 * SignUpEvent继承自AuthEvent是为了统一逻辑。
 */
public class AuthEvent {
    public final static class SignUpEvent extends AuthEvent {
        private final String username;
        private final String password;

        public SignUpEvent(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }

}
