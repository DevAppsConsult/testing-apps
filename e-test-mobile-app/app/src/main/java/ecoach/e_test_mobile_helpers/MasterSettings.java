package ecoach.e_test_mobile_helpers;

/**
 * Created by banktech on 12/1/2014.
 */
public class MasterSettings {
    private static EcEnvironmentTarget TargetEnvironment = EcEnvironmentTarget.STAGING;

    public static Boolean IsDevMode() {
        return TargetEnvironment == EcEnvironmentTarget.DEV;
    }

    public static Boolean IsStagingMode() {
        return TargetEnvironment == EcEnvironmentTarget.STAGING;
    }

    public static Boolean IsProductionMode() {
        return TargetEnvironment == EcEnvironmentTarget.PROD;
    }
}
