package com.tahsin.accountant;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.tahsin.accountant");

        noClasses()
            .that()
            .resideInAnyPackage("com.tahsin.accountant.service..")
            .or()
            .resideInAnyPackage("com.tahsin.accountant.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.tahsin.accountant.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
