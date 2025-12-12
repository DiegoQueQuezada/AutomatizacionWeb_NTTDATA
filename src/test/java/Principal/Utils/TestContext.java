package Principal.Utils;

public class TestContext {
    private static String currentUserType;
    private static String currentScenarioName;

    public static void setCurrentUserType(String userType) {
        currentUserType = userType;
    }

    public static String getCurrentUserType() {
        return currentUserType;
    }

    public static void setCurrentScenarioName(String scenarioName) {
        currentScenarioName = scenarioName;
    }

    public static String getCurrentScenarioName() {
        return currentScenarioName;
    }

    public static void reset() {
        currentUserType = null;
        currentScenarioName = null;
    }
}