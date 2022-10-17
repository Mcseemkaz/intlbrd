package net.intelliboard.next.services.pages.connections.connection;

public enum ConnectionRolesBlockEnum {

    INSTRUCTOR_ROLES("Instructor Roles"),
    LEARNER_ROLES("Learner Roles"),
    ADMIN_ROLES("Admin Roles");

    public String value;

    ConnectionRolesBlockEnum(String value) {
        this.value = value;
    }
}
