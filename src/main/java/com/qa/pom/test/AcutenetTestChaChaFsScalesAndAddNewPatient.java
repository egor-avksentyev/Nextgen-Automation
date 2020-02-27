package com.qa.pom.test;

import com.qa.pom.base.BaseTest;
import com.qa.pom.pages.*;
import org.junit.Test;

import java.io.IOException;

public class AcutenetTestChaChaFsScalesAndAddNewPatient extends BaseTest {
    @Test
    public void testOpenSiteAndTestScales() throws IOException, InterruptedException {
        testName = "Acutenet (Nextgen) Test INTERRAI CHA-FS CHA-MH Formulas Scales";
        Login login = openSite();
        log("Click Submit and login ");
        Dashboard dash = login.logIn();
        log("On Dashboard");
        AddnewClient newclient = dash.addnewClientclick();
        log("On Add New Patient");
        Pathways path = newclient.FillinNewpatientInformation();
        ChaChaFsChamhFormulas cha = path.chooseInterRaiChaChafsChamh();
        cha.formulasTestScales();

        Thread.sleep(20000);
        closeSite();
    }



}
