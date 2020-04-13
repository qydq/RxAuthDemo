package com.sunsta.demo.action;

/**
 * Created by sunsta on 24/04/17.
 * <p>
 * SignUpAction里定义了我们注册所有需要的信息,代码和SignUpEvent差不多，
 * 分离的好处是可以对数据进行再处理或者合并打包等
 */

public class AuthAction {
    public final static class SignUpAction extends AuthAction {
        private final String username;
        private final String password;

        public SignUpAction(String username, String password) {
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
