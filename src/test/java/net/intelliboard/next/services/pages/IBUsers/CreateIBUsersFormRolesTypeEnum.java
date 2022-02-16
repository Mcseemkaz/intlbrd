package net.intelliboard.next.services.pages.IBUsers;

public enum CreateIBUsersFormRolesTypeEnum {

    ALL_ACCESS("ALL Access"),
    MANAGER("Manager"),
    DEPARTMENT_CHAIR("Department Chair"),
    TEACHER("Teacher"),
    LTI_ROLE_TEST("LTI Role test");

    String value;

    CreateIBUsersFormRolesTypeEnum(String value) {
        this.value = value;
    }

}
