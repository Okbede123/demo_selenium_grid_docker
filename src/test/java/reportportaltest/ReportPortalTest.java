package reportportaltest;


import com.epam.reportportal.utils.files.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Base64;


public class ReportPortalTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReportPortalTest.class);

    @Test
    public void TC_01() throws IOException {
        System.out.println("tesst");
        LOGGER.info("test 1 ti");
        LOGGER.info("test 2 ti");
//        ReportPortal.emitLog("test","info",new Date(),new File("C:\\Users\\Admin\\Downloads\\huongdan.png"));

    }

    @Test
    public void TC_02() throws IOException {
        System.out.println("test2");
        LOGGER.info("RP_MESSAGE#BASE64#{}#{}", Base64.getEncoder().encodeToString(Utils.getFileAsByteSource(new File("C:\\Users\\Admin\\Downloads\\huongdan.png")).read()),"test");
    }
}
