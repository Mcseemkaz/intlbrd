package net.intelliboard.next.services.pages.connections.connection;

public enum ConnectionRolesBlockEnum {

    INSTRUCTOR_ROLES("Instructor Roles", "roles[teacher][]"),
    LEARNER_ROLES("Learner Roles", "roles[learner][]"),
    ADMIN_ROLES("Admin Roles", "roles[admin][]");

    public String value;
    public String xpath_input;

    ConnectionRolesBlockEnum(String value, String xpath_input) {
        this.value = value;
        this.xpath_input = xpath_input;
    }
}
