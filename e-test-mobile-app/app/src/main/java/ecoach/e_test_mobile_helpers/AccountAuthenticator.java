package ecoach.e_test_mobile_helpers;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import ecoach.e_test_mobile_application.SignIn;

/**
 * Created by banktech on 12/1/2014.
 */
public class AccountAuthenticator extends AbstractAccountAuthenticator {

    private Context _context;
    private AccountManager mAccountManager;

    public AccountAuthenticator(Context context) {
        super(context);
        _context = context;
        mAccountManager = AccountManager.get(_context);
    }

    public AccountAuthenticator() {
        super(null);
        /**do nothing*/

    }

    @Override
    public Bundle editProperties(AccountAuthenticatorResponse accountAuthenticatorResponse, String s) {
        return null;
    }

    @Override
    public Bundle addAccount(AccountAuthenticatorResponse response, String accountType, String authTokenType, String[] requiredFeatures, Bundle options) throws NetworkErrorException {
        final Intent intent = new Intent(_context, SignIn.class);
        intent.putExtra(Constants.Etest_ACCNT_TYPE, accountType);
        intent.putExtra(Constants.Etest_AUTH_TOKEN_TYPE, authTokenType);
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
        final Bundle bundle = new Bundle();
        bundle.putParcelable(AccountManager.KEY_INTENT, intent);
        return bundle;
    }

    @Override
    public Bundle confirmCredentials(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, Bundle bundle) throws NetworkErrorException {
        return null;
    }

    @Override
    public Bundle getAuthToken(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
        // Extract the username and password from the Account Manager, and ask
        // the server for an appropriate AuthToken.


        String authToken = mAccountManager.peekAuthToken(account, Constants.Etest_AUTH_TOKEN_TYPE);

        // No token here. Ask the server to revalidate with the credentials we have stored
        if (TextUtils.isEmpty(authToken)) {
            final String password = mAccountManager.getPassword(account);
            if (password != null) {
                UserValidator.UserValidationResult vr = UserValidator.authenticateUser(account.name, password);
                authToken = vr.EtestAuth; //we will get an empty authToken if the provided credentials are invalid
            }
        }

        // If we get an authToken - we return it
        if (!TextUtils.isEmpty(authToken)) {
            final Bundle result = new Bundle();
            result.putString(AccountManager.KEY_ACCOUNT_NAME, account.name);
            result.putString(AccountManager.KEY_ACCOUNT_TYPE, account.type);
            result.putString(AccountManager.KEY_AUTHTOKEN, authToken);
            return result;
        }

        //If token was empty, we need to fire our activity to get new credentials
        final Intent intent = new Intent(_context, SignIn.class);
        intent.putExtra(Constants.Etest_ACCNT_TYPE, account.name);
        intent.putExtra(Constants.Etest_AUTH_TOKEN_TYPE, account.type);
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, accountAuthenticatorResponse);
        options = new Bundle();
        options.putParcelable(AccountManager.KEY_INTENT, intent);
        return options;
    }

    @Override
    public String getAuthTokenLabel(String s) {
        return null;
    }

    @Override
    public Bundle updateCredentials(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, String s, Bundle bundle) throws NetworkErrorException {
        return null;
    }

    @Override
    public Bundle hasFeatures(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, String[] strings) throws NetworkErrorException {
        return null;
    }



    /*Hack because there's no member of the parent class which allows you to remove accounts..*/

}

