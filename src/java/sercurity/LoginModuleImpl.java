package sercurity;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.util.Map;

public class LoginModuleImpl implements LoginModule {
    private static final String TEST_USERNAME = "abc";
    private static final String TEST_PASSWORD = "1";
    private CallbackHandler callbackHandler = null;
    private boolean authenticationFlag;

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.callbackHandler = callbackHandler;
    }

    @Override
    public boolean login() throws LoginException {
        Callback[] callbacks = new Callback[2];
        callbacks[0] = new NameCallback("User name: ");
        callbacks[1] = new PasswordCallback("Password: ", false);
        try {
            callbackHandler.handle(callbacks);
        } catch (IOException | UnsupportedCallbackException e) {
            e.printStackTrace();
        }
        String name = ((NameCallback) callbacks[0]).getName();
        String password = String.valueOf(((PasswordCallback) callbacks[1]).getPassword());
        if(name.equals(TEST_USERNAME) && password.equals(TEST_PASSWORD)) {
            System.out.println("Authentication success");
            authenticationFlag = true;
            return true;
        }
        System.out.println("Authentication fail");
        authenticationFlag = false;
        return false;
    }

    @Override
    public boolean commit() throws LoginException {
        return false;
    }

    @Override
    public boolean abort() throws LoginException {
        return false;
    }

    @Override
    public boolean logout() throws LoginException {
        return false;
    }
}
