package net.intelliboard.next.services.api.pojo.onesecmail;

import lombok.Data;

import java.util.ArrayList;

@Data
public class OneSecMailMessagesListPOJO {

    ArrayList<OneSecMailMessagesShortPOJO> messagesList;
}
