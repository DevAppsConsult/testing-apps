package ecoach.e_test_mobile_services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import ecoach.e_test_mobile_helpers.AccountAuthenticator;

/**
 * Created by banktech on 12/1/2014.
 */
public class AuthenticatorService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        AccountAuthenticator authenticator = new AccountAuthenticator(this);
        return authenticator.getIBinder();
    }
}
