package com.qa.pom.test;

import com.qa.pom.base.BaseTest;
import com.qa.pom.pages.ChaChaFsChamhFormulas;
import com.qa.pom.pages.Dashboard;
import com.qa.pom.pages.Login;
import com.qa.pom.pages.Pathways;
import org.junit.Test;

import java.io.IOException;

public class AcutenetTestChaChaFSFormulasCAPs extends BaseTest {

    @Test
    public void testOpenSiteAndTestCAPs() throws IOException, InterruptedException {

        Login login = openSite();
        log("Click Submit and login ");
        Dashboard dashboard = login.logIn();
        log("On Dashboard");
        Pathways pathways = dashboard.existedPatient();
        log("On Pathways");
        ChaChaFsChamhFormulas chaChaFsChamhFormulas = pathways.chooseInterRaiChaChafsChamh();
        chaChaFsChamhFormulas.formulasTestCaps();

        Thread.sleep(20000);
        closeSite();

    }




}
