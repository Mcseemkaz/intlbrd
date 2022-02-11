package net.intelliboard.next.services.pages.signup;

public enum SignUpFormFieldTypeEnum {

    COUNTRY("//select[@name='country']"),
    FULL_NAME("//input[@name='fullname']"),
    EMAIL("//input[@name='email']"),
    PASSWORD("//input[@name='password']"),
    CONFIRM_PASSWORD("//input[@name='password_confirmation']"),
    INSTITUTION("//input[@name='company']"),
    PHONE_NUMBER("//input[@name='phone']");

    String value;

    SignUpFormFieldTypeEnum(String value) {
        this.value = value;
    }

}
