package net.intelliboard.next.tests.core.successbar;

import io.qameta.allure.Description;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.pages.header.HeaderObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SuccessBarTest extends IBNextAbstractTest {

    @Test
    @DisplayName("SP-T1976: Displaying Release Notes tab after clicking on the New features button in the Success bar")
    @Description("Verify that Release Notes tab is opened after clicking on the New features button in the Success bar")
    @Tags(value = {@Tag("smoke"), @Tag("normal"), @Tag("SP-T1976"), @Tag("smoke_core")})
    public void testReleaseNotesInSuccessBar() {

        assertTrue(HeaderObject
                .init()
                .openSuccessBar()
                .openNewFeatures()
                .isReleaseNoteCardsExisted());
    }
}
