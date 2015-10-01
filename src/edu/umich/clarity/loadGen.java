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

    private static double poisson_dig_target_mean=300;
    private static double poisson_dig_bg_mean=140;
    private static String POISSON_DIG_TARGET_SAMPLE_FILE="input/dig_target_load.csv";
    private static String POISSON_DIG_BG_SAMPLE_FILE="input/dig_bg_load.csv";

    private static double poisson_face_target_mean=600;
    private static double poisson_face_bg_mean=300;
    private static String POISSON_FACE_TARGET_SAMPLE_FILE="input/face_target_load.csv";
    private static String POISSON_FACE_BG_SAMPLE_FILE="input/face_bg_load.csv";

    private static double poisson_imc_target_mean=400;
    private static double poisson_imc_bg_mean=250;
    private static String POISSON_IMC_TARGET_SAMPLE_FILE="input/imc_target_load.csv";
    private static String POISSON_IMC_BG_SAMPLE_FILE="input/imc_bg_load.csv";
    
    private static double poisson_ner_target_mean=50;
    private static double poisson_ner_bg_mean=10;
    private static String POISSON_NER_TARGET_SAMPLE_FILE="input/ner_target_load.csv";
    private static String POISSON_NER_BG_SAMPLE_FILE="input/ner_bg_load.csv";
    
    private static double poisson_pos_target_mean=50;
    private static double poisson_pos_bg_mean=10;
    private static String POISSON_POS_TARGET_SAMPLE_FILE="input/pos_target_load.csv";
    private static String POISSON_POS_BG_SAMPLE_FILE="input/pos_bg_load.csv";

    private static double poisson_chk_target_mean=50;
    private static double poisson_chk_bg_mean=10;
    private static String POISSON_CHK_TARGET_SAMPLE_FILE="input/chk_target_load.csv";
    private static String POISSON_CHK_BG_SAMPLE_FILE="input/chk_bg_load.csv";

    private static double poisson_stemmer_target_mean=410;
    private static double poisson_stemmer_bg_mean=150;
    private static String POISSON_STEMMER_TARGET_SAMPLE_FILE="input/stemmer_target_load.csv";
    private static String POISSON_STEMMER_BG_SAMPLE_FILE="input/stemmer_bg_load.csv";
    
    private static double poisson_asr_target_mean=410;
    private static double poisson_asr_bg_mean=200;
    private static String POISSON_ASR_TARGET_SAMPLE_FILE="input/asr_target_load.csv";
    private static String POISSON_ASR_BG_SAMPLE_FILE="input/asr_bg_load.csv";
    
    private static double poisson_gmm_target_mean=110;
    private static double poisson_gmm_bg_mean=50;
    private static String POISSON_GMM_TARGET_SAMPLE_FILE="input/gmm_target_load.csv";
    private static String POISSON_GMM_BG_SAMPLE_FILE="input/gmm_bg_load.csv";
    
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
        
        client.genPoissonSamples(poisson_dig_target_mean, 10000, POISSON_DIG_TARGET_SAMPLE_FILE);
        client.genPoissonSamples(poisson_dig_bg_mean, 10000, POISSON_DIG_BG_SAMPLE_FILE);

        client.genPoissonSamples(poisson_face_target_mean, 10000, POISSON_FACE_TARGET_SAMPLE_FILE);
        client.genPoissonSamples(poisson_face_bg_mean, 10000, POISSON_FACE_BG_SAMPLE_FILE);

        client.genPoissonSamples(poisson_imc_target_mean, 10000, POISSON_IMC_TARGET_SAMPLE_FILE);
        client.genPoissonSamples(poisson_imc_bg_mean, 10000, POISSON_IMC_BG_SAMPLE_FILE);

        client.genPoissonSamples(poisson_ner_target_mean, 10000, POISSON_NER_TARGET_SAMPLE_FILE);
        client.genPoissonSamples(poisson_ner_bg_mean, 10000, POISSON_NER_BG_SAMPLE_FILE);
        
        client.genPoissonSamples(poisson_pos_target_mean, 10000, POISSON_POS_TARGET_SAMPLE_FILE);
        client.genPoissonSamples(poisson_pos_bg_mean, 10000, POISSON_POS_BG_SAMPLE_FILE);
        
        client.genPoissonSamples(poisson_chk_target_mean, 10000, POISSON_CHK_TARGET_SAMPLE_FILE);
        client.genPoissonSamples(poisson_chk_bg_mean, 10000, POISSON_CHK_BG_SAMPLE_FILE);
        
        client.genPoissonSamples(poisson_stemmer_target_mean, 10000, POISSON_STEMMER_TARGET_SAMPLE_FILE);
        client.genPoissonSamples(poisson_stemmer_bg_mean, 10000, POISSON_STEMMER_BG_SAMPLE_FILE);
        
        client.genPoissonSamples(poisson_asr_target_mean, 10000, POISSON_ASR_TARGET_SAMPLE_FILE);
        client.genPoissonSamples(poisson_asr_bg_mean, 10000, POISSON_ASR_BG_SAMPLE_FILE);
        
        client.genPoissonSamples(poisson_gmm_target_mean, 10000, POISSON_GMM_TARGET_SAMPLE_FILE);
        client.genPoissonSamples(poisson_gmm_bg_mean, 10000, POISSON_GMM_BG_SAMPLE_FILE);
        
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
//                csvEntry.add(poi_dist.sample() + "");
                csvEntry.add((int)(mean) + "");
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
