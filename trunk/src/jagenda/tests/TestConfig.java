/*
 * Fichier :    TestConfig.java
 * Package :    jagenda.tests
 * Projet jAgenda - Info 1 2011-2012
 *
 */
package jagenda.tests;

import java.util.Arrays;
import jagenda.Principale;
import jagenda.util.Config;

/**
 * Batterie de tests pour la classe <code>jagenda.util.Config</code>
 * 
 * @author      Jason BOURLARD
 * @author      David PELISSIER
 * @version 0.3
 */
public class TestConfig {

    /**
     * Batterie de tests pour la classe <code>jagenda.util.Config</code>
     * @param args  Non utilisé
     */
    public static void main(String[] args) {
        Config config = new Config();
        config.chargeConfig();
        System.out.println(config.getAnneeDebut());
    }

}