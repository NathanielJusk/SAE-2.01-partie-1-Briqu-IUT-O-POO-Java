package fr.univorleans.iut45.briquiuto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SystemInfoTest {
    
    @Test
    public void testJavaVersion() {
        String version = SystemInfo.javaVersion();
        assertNotNull(version);
        assertFalse(version.isEmpty());
    }
    
    @Test
    public void testJavafxVersion() {
        String version = SystemInfo.javafxVersion();
        assertNotNull(version);
        assertFalse(version.isEmpty());
    }
    
}
