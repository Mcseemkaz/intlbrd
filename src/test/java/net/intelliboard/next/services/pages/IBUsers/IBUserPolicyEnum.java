package net.intelliboard.next.services.pages.IBUsers;

public enum IBUserPolicyEnum {

    PRIVACY_POLICY("Privacy Policy"),
    DATA_PROCESSING_ADDENDUM("Data Processing Addendum"),
    TERMS_OF_USE("Terms of Use");

    public String value;

    IBUserPolicyEnum(String value){
        this.value = value;
    }
}
