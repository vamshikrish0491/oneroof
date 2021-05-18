package com.acculytixs.wayuparty.enums;

public enum Result {
    SUCCESS("Operation executed successfully"),
    FAILED("Operation failed, please try again"),
    AWKWARD("Something went wrong"),
    INVALID_DATA("Invalid data"),
    INVALID_VERIFICATION_CODE("Invalid Verification Code"),
    VALID_DATA("Valid data"),
    VALID_EMAIL_DATA("Valid Email"),
    VALID_MOBILE_DATA("Valid Mobile"),
    INVALID_EMAIL_DATA("No User existed with this email"),
    INVALID_MOBILE_DATA("No User existed with this mobile number"),
    Data_exists("Data Already Exists"),
    INVALID_CREDENTIALS("Invalid UserName Or Password"),
    NOT_REGISTERED("User Not Registered"),
    INACTIVE_USER("In-Active User"),
    INVALID_USER("Invalid User Or User doesn't exists"),
    DISABLED("Your account is disable, please contact adminstrator"),
    SESSION_TIMEDOUT("You have been logged out, please login and try again."),
    DUPLICATE_EMAIL("Record already existed with given email id"),
    DUPLICATE_PHONE("Record already existed with given phone number"),
    INVALID_EMAIL("Invalid Email ID"),
    UNAUTHORIZED("You are not allowed to access this service"),
	DATA_NOT_FOUND("Data not found"),
    UNABLE_SET_PAYMENT_ACCOUNT("Payment account is created, but unable create WePay account."),
	DUPLICATE_NAME("Name already exists"),
	DUPLICATE_TAX_RECORD("Tax Record already exists for the selected user"),
	DUPLICATE_EMAIL_CONFIGURATION_RECORD("Email account with same Email/Password already exists"),
	CART_SERVICE_EXISTS("Service Already Added In Cart"),
	CLEAR_EXISTING_CART("Please Clear Existing Cart And Continue"),
	UN_AUTHORIZED_USER("Unauthorized User"),
	INVALID_ORDER_DATE("Please Provide Order Date"),
	INVALID_TIME_SLOT("Please Provide Time Slot"),
	CART_ITEM_REMOVED("Well done! You successfully removed items from cart"),
	FIELDS_MISSING("Some fields are missing"),
	INVALID_AGE("Your age should not less than 21"),
	ORDER_NOT_CANCELED("Your order can't cancel"),
	ORDER_CANCELED("Your order has canceled"),
	ACCOUNT_NOT_VERIFIED("Account Not Verified. Login to your email to verify"),
	NOT_REGISTERED_EMAIL("No user exists with this email"),
	USER_EMAIL_EXISTED("User exists with this email"),
	USER_MOBILE_EXISTED("User exists with this mobile number"),
	ADD_PACKAGE_MENU_ITEMS("Select offered package menu items"),
    INVALID_ORDER_DATE_TIME_SLOT("Please provide valid order date and time slot"),
    GUEST_USER_EXISTS("User Exists As Guest"),
    GUEST_USER_NOT_EXISTS("User Not Exists As Guest"),
    PASSWORD_NOT_MATCHED("Password and Confirm Password not matched"),
    DRINK_PREFERENCES("Select any three drinks"),
    MUSIC_PREFERENCES("Select any three music");

    private String value;

    private Result(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Result getNameByValue(String scenarioId) {
        for (Result result : Result.values()) {
            if (scenarioId.equalsIgnoreCase(result.value)) {
                return result;
            }
        }
        return null;
    }

}
