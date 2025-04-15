import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
class KarateTest {
    
@Test

    void runKarateTests() {
        Results results = Runner.path("classpath:tests/sample.feature")
                .outputCucumberJson(true)
                .parallel(1);
        System.out.println("Karate output dir: " + results.getReportDir());
        listOutputFiles(results.getReportDir());
        generateReport(results.getReportDir());
        assertEquals(0, results.getFailCount(), results.getErrorMessages());
    }
    private void listOutputFiles(String dirPath) {
        File[] allFiles = new File(dirPath).listFiles();
        System.out.println("Files in output dir:");
        if (allFiles != null) {
            for (File f : allFiles) {
                System.out.println(" -> " + f.getName());
            }
        }
    }
    private void generateReport(String karateOutputPath) {
        File reportOutputDirectory = new File("target/cucumber-html-reports");
        Collection<File> jsonFiles = org.apache.commons.io.FileUtils.listFiles(
                new File(karateOutputPath), new String[] { "json" }, true);
        if (jsonFiles.isEmpty()) {
            throw new RuntimeException(":x: No JSON files found at: " + karateOutputPath);
        }
        List<String> jsonPaths = new ArrayList<>();
        for (File file : jsonFiles) {
            jsonPaths.add(file.getAbsolutePath());
        }
        Configuration config = new Configuration(reportOutputDirectory, "Karate API Tests");
        ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
        reportBuilder.generateReports();
    }
}
