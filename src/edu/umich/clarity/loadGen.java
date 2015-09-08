package edu.umich.clarity;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.apache.commons.math3.distribution.PoissonDistribution;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.thrift.TException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class loadGen {
    // common definition
    public static final String AUDIO_PATH = "/input/distribution";
    private static final Logger LOG = Logger.getLogger(loadGen.class);

    //private static int num_client = 10000;
    private static int num_client;
    private static final String LOAD_TYPE_EXPONENTIAL = "exponential";
    private static final String LOAD_TYPE_POISSON = "poisson";
    private static final String LOAD_TYPE_BURST = "burst";
    //private static String loadType = LOAD_TYPE_POISSON;
    private static String loadType;
    // for poisson load
    //private static double poisson_mean = 1400;
    private static double poisson_mean;
    // private static String POISSON_SAMPLE_FILE = "poisson_sample_.6_1000.csv";
    //private static String POISSON_SAMPLE_FILE = "poisson_sample_.8_1000.csv";
    //private static String POISSON_SAMPLE_FILE = "poisson_sample_1.4_10000.csv";
    private static String POISSON_SAMPLE_FILE="input/poisson.csv";

    // for burst load
    //private static double burst_high_mean = 600;
    private static double burst_high_mean;
    //private static double burst_low_mean = 1000;
    private static double burst_low_mean;
    //private static String BURST_HIGH_SAMPLE_FILE = "poisson_sample_.9_1000.csv";
    private static String BURST_HIGH_SAMPLE_FILE="input/burst_high.csv";
    //private static String BURST_LOW_SAMPLE_FILE = "poisson_sample_1.4_1000.csv";
    private static String BURST_LOW_SAMPLE_FILE="input/burst_low.csv";
    //private static int BURST_SWITCH_NUM = 200;
    private static int BURST_SWITCH_NUM;
    //private static float BURST_RATIO = 0.5f;
    private static float BURST_RATIO;
    //private static String OPERATION = "load";
    //private static String OPERATION = "sample";
    private static String OPERATION;

    public loadGen() {
        PropertyConfigurator.configure(System.getProperty("user.dir") + File.separator + "log4j.properties");
        LOG.info("the operation mode is " + OPERATION);
        LOG.info("the number of queries will be generated is " + num_client);
    }

    /**
     * @param args args[0]: scheduler_ip, args[1]: scheduler_port, args[2]: distribution_file, args[3]: query_num, args[4]: warm_up_query
     */
    public static void main(String[] args) {
 
        loadGen client = new loadGen();
        client.genPoissonSamples(200, 1000);
        client.genBurstSamples(100, 200, 1000);
    }


    private void genPoissonSamples(double mean, int sampleNum) {
        PoissonDistribution poi_dist = new PoissonDistribution(mean);
        CSVWriter sampleWriter;
		try {
				sampleWriter = new CSVWriter(new FileWriter(POISSON_SAMPLE_FILE), ',', CSVWriter.NO_QUOTE_CHARACTER);

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

    /**
     * Generate the load that follows Poisson distribution.
     *
     * @param mean       the poisson_mean time interval to submit each query
     * @param num_client that submitting the query concurrently
     */
    public void genPoissonLoad(double mean, int num_client) {
        PoissonDistribution poi_dist = new PoissonDistribution(mean);
        for (int i = 0; i < num_client; i++) {
            new Thread(new ConcurrentClient(poi_dist.sample(), Integer.toString(i))).start();
        }
    }

    private class ConcurrentClient implements Runnable {
        private static final String NEXT_STAGE = "asr";
        private static final String SCHEDULER_IP = "localhost";
        private static final int SCHEDULER_PORT = 8888;
        private double nap_time;
        private File[] audioFiles;
        private String name;

        public ConcurrentClient(double nap_time, String name) {
            this.nap_time = nap_time;
            this.name = name;
            File audioDir = new File(AUDIO_PATH);
            this.audioFiles = audioDir.listFiles();
        }

        @Override
        public void run() {

        }
    }
    }
