package at.silverstrike.pcc.impl.persistence;

import static at.silverstrike.pcc.api.conventions.MessageCodePrefixRegistry.Module.persistence;
import at.silverstrike.pcc.api.conventions.MessageCodePrefixRegistry;

final class ErrorCodes {
    private final static String PREFIX = MessageCodePrefixRegistry.getInstance().getPrefix(persistence);
    
    public final static String M_001_OPEN_SESSION = PREFIX + "001";

    public static final String M_002_OPEN_SESSION = PREFIX + "002";
    
    public static final String M_003_OPEN_SESSION = PREFIX + "003";
    
    public static final String M_004_OPEN_SESSION = PREFIX + "004";
    
    public static final String M_005_DAILY_PLAN_NOT_FOUND_SCHEDULE = PREFIX + "005";
    
    public static final String M_006_DAILY_PLAN_NOT_FOUND_TO_DO = PREFIX + "006";
    
    public static final String M_007_DAILY_PLAN_LIST1 = PREFIX + "007";
    
    public static final String M_008_DAILY_PLAN_LIST2 = PREFIX + "008";
    
    public static final String M_009_GET_DAILY_PLAN = PREFIX + "009";
    
    public static final String M_010_GENERATE_DAILY_PLANS = PREFIX + "010";
    
    public static final String M_011_CLEAR_DATABASE = PREFIX + "011";
    
    public static final String M_012_CREATE_HUMAN_RESOURCE = PREFIX + "012";
    
    public static final String M_013_CREATE_HUMAN_RESOURCE2 = PREFIX + "013";
}
