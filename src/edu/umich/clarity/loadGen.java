package edu.umich.clarity;

import com.opencsv.CSVWriter;
import org.apache.commons.math3.distribution.PoissonDistribution;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class loadGen {
    // common definition
    private static final Logger LOG = Logger.getLogger(loadGen.class);

    // for poisson load
    private static double poisson_target_mean=200;
    private static double poisson_bg_mean=200;
    private static String POISSON_TARGET_SAMPLE_FILE="input/target_load.csv";
    private static String POISSON_BG_SAMPLE_FILE="input/bg_load.csv";
    
    // for burst load
//    private static double burst_high_mean;
//    private static double burst_low_mean;
    private static String BURST_HIGH_SAMPLE_FILE="input/burst_high.csv";
    private static String BURST_LOW_SAMPLE_FILE="input/burst_low.csv";
//    private static int BURST_SWITCH_NUM;
//    private static float BURST_RATIO;
//    private static String OPERATION;

    public loadGen() {
        PropertyConfigurator.configure(System.getProperty("user.dir") + File.separator + "log4j.properties");
//        LOG.info("the operation mode is " + OPERATION);
//        LOG.info("the number of queries will be generated is " + num_client);
    }

    /**
     * @param args args[0]: scheduler_ip, args[1]: scheduler_port, args[2]: distribution_file, args[3]: query_num, args[4]: warm_up_query
     */
    public static void main(String[] args) {
 
        loadGen client = new loadGen();
        client.genPoissonSamples(poisson_target_mean, 10000, POISSON_TARGET_SAMPLE_FILE);
        client.genPoissonSamples(poisson_bg_mean, 10000, POISSON_BG_SAMPLE_FILE);
//        client.genBurstSamples(100, 200, 10000);
        LOG.info("Load generation completes");
    }

    private void genPoissonSamples(double mean, int sampleNum, String sample_file) {
        PoissonDistribution poi_dist = new PoissonDistribution(mean);
        CSVWriter sampleWriter;
		try {
				sampleWriter = new CSVWriter(new FileWriter(sample_file), ',', CSVWriter.NO_QUOTE_CHARACTER);

            ArrayList<String> csvEntry = new ArrayList<String>();
            for (int i = 0; i < sampleNum; i++) {
                csvEntry.add(poi_dist.sample() + "");
            }
            sampleWriter.writeNext(csvEntry.toArray(new String[csvEntry.size()]));
            sampleWriter.flush();
            sampleWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    private void genBurstSamples(double high_mean, double low_mean, int sampleNum) {
        PoissonDistribution burstLowDist = new PoissonDistribution(low_mean);
        PoissonDistribution burstHighDist = new PoissonDistribution(high_mean);
        try {
            CSVWriter lowSampleWriter = new CSVWriter(new FileWriter(BURST_LOW_SAMPLE_FILE), ',', CSVWriter.NO_QUOTE_CHARACTER);
            CSVWriter highSampleWriter = new CSVWriter(new FileWriter(BURST_HIGH_SAMPLE_FILE), ',', CSVWriter.NO_QUOTE_CHARACTER);
            ArrayList<String> csvEntry = new ArrayList<String>();
            for (int i = 0; i < sampleNum; i++) {
                csvEntry.add(burstLowDist.sample() + "");
            }
            lowSampleWriter.writeNext(csvEntry.toArray(new String[csvEntry.size()]));
            lowSampleWriter.flush();
            lowSampleWriter.close();
            csvEntry.clear();
            for (int i = 0; i < sampleNum; i++) {
                csvEntry.add(burstHighDist.sample() + "");
            }
            highSampleWriter.writeNext(csvEntry.toArray(new String[csvEntry.size()]));
            highSampleWriter.flush();
            highSampleWriter.close();
        } catch (IOException ex) {

        }
    }
}
