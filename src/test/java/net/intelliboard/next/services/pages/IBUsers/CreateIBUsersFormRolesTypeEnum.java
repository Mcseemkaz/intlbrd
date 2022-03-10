package net.intelliboard.next.services.pages.IBUsers;

public enum CreateIBUsersFormRolesTypeEnum {

    ALL_ACCESS("ALL Access"),
    MANAGER("Manager"),
    DEPARTMENT_CHAIR("Department Chair");

// Disabled for clarifying requirements
//    TEACHER("Teacher"),
//    LTI_ROLE_TEST("LTI Role test");

    final String value;

    CreateIBUsersFormRolesTypeEnum(String value) {
        this.value = value;
    }

}
