package sercurity;

import GUI.LoginFormDialog;
import GUI.MainJPanel;

import javax.security.auth.callback.*;
import java.io.IOException;

public class CallbackHandlerImpl implements CallbackHandler {
    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        NameCallback nameCallback;
        PasswordCallback passwordCallback;
        LoginFormDialog loginFormDialog = MainJPanel.getInstance().getLoginFormDialog();
        loginFormDialog.show(true);
        for (Callback callback : callbacks) {
            if (callback instanceof NameCallback) {
                nameCallback = (NameCallback) callback;
                nameCallback.setName(loginFormDialog.getUsernameTextField().getText());
            } else if (callback instanceof PasswordCallback) {
                passwordCallback = (PasswordCallback) callback;
                passwordCallback.setPassword(loginFormDialog.getPasswordTextField().getText().toCharArray());
            }
        }
    }
}
