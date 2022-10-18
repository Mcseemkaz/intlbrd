package net.intelliboard.next.services.pages.IBUsers;

public enum IBUsersRolesTypeEnum {

    ALL_ACCESS("ALL Access"),
    MANAGER("Manager"),
    DEPARTMENT_CHAIR("Department Chair");

// Disabled for clarifying requirements
//    TEACHER("Teacher"),
//    LTI_ROLE_TEST("LTI Role test");

    public final String value;

    IBUsersRolesTypeEnum(String value) {
        this.value = value;
    }

}
