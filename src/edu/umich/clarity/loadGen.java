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

    private static double poisson_stemmer_target_mean=210;
    private static double poisson_stemmer_bg_mean=100;
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

//Rodinia workloads, backprop
    private static double poisson_backprop_mean=250;
    private static String POISSON_BACKPROP_SAMPLE_FILE="input/backprop_load.csv";    
//Rodinia workloads, bfs
    private static double poisson_bfs_mean=250;
    private static String POISSON_BFS_SAMPLE_FILE="input/bfs_load.csv";    
//Rodinia workloads, cfd
    private static double poisson_cfd_mean=10;
    private static String POISSON_CFD_SAMPLE_FILE="input/cfd_load.csv";    
//Rodinia workloads, dwt2d
    private static double poisson_dwt2d_mean=100;
    private static String POISSON_DWT2D_SAMPLE_FILE="input/dwt2d_load.csv";    
//Rodinia workloads, gaussian
    private static double poisson_gaussian_mean=250;
    private static String POISSON_GAUSSIAN_SAMPLE_FILE="input/gaussian_load.csv";    
//Rodinia workloads, heartwall
    private static double poisson_heartwall_mean=500;
    private static String POISSON_HEARTWALL_SAMPLE_FILE="input/heartwall_load.csv";    
//Rodinia workloads, hotspot
    private static double poisson_hotspot_mean=100;
    private static String POISSON_HOTSPOT_SAMPLE_FILE="input/hotspot_load.csv";    
//Rodinia workloads, dwt2d
    private static double poisson_hybridsort_mean=400;
    private static String POISSON_HYBRIDSORT_SAMPLE_FILE="input/hybridsort_load.csv";    
//Rodinia workloads, dwt2d
    private static double poisson_kmeans_mean=400;
    private static String POISSON_KMEANS_SAMPLE_FILE="input/kmeans_load.csv";    
  //Rodinia workloads, dwt2d
    private static double poisson_lavaMD_mean=200;
    private static String POISSON_LAVAMD_SAMPLE_FILE="input/lavaMD_load.csv";    
  //Rodinia workloads, dwt2d
    private static double poisson_leukocyte_mean=500;
    private static String POISSON_LEUKOCYTE_SAMPLE_FILE="input/leukocyte_load.csv";    
  //Rodinia workloads, dwt2d
    private static double poisson_lud_mean=10;
    private static String POISSON_LUD_SAMPLE_FILE="input/lud_load.csv";    
  //Rodinia workloads, dwt2d
    private static double poisson_mummergpu_mean=50000;
    private static String POISSON_MUMMERGPU_SAMPLE_FILE="input/mummergpu_load.csv";    
  //Rodinia workloads, dwt2d
    private static double poisson_myocyte_mean=100;
    private static String POISSON_MYOCYTE_SAMPLE_FILE="input/myocyte_load.csv";    
  //Rodinia workloads, dwt2d
    private static double poisson_nn_mean=100;
    private static String POISSON_NN_SAMPLE_FILE="input/nn_load.csv";    
  //Rodinia workloads, dwt2d
    private static double poisson_nw_mean=400;
    private static String POISSON_NW_SAMPLE_FILE="input/nw_load.csv";    
  //Rodinia workloads, dwt2d
    private static double poisson_particlefilter_mean=150;
    private static String POISSON_PARTICLEFILTER_SAMPLE_FILE="input/particlefilter_load.csv";    
  //Rodinia workloads, dwt2d
    private static double poisson_pathfinder_mean=250;
    private static String POISSON_PATHFINDER_SAMPLE_FILE="input/pathfinder_load.csv";    
  //Rodinia workloads, dwt2d
    private static double poisson_srad_mean=50;
    private static String POISSON_SRAD_SAMPLE_FILE="input/srad_load.csv";    
  //Rodinia workloads, dwt2d
    private static double poisson_streamcluster_mean=5000;
    private static String POISSON_STREAMCLUSTER_SAMPLE_FILE="input/streamcluster_load.csv";    

    
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

//Rodinia
        client.genPoissonSamples(poisson_backprop_mean, 10000, POISSON_BACKPROP_SAMPLE_FILE);
        client.genPoissonSamples(poisson_bfs_mean, 10000, POISSON_BFS_SAMPLE_FILE);
        client.genPoissonSamples(poisson_cfd_mean, 10000, POISSON_CFD_SAMPLE_FILE);
        client.genPoissonSamples(poisson_dwt2d_mean, 10000, POISSON_DWT2D_SAMPLE_FILE);
        client.genPoissonSamples(poisson_gaussian_mean, 10000, POISSON_GAUSSIAN_SAMPLE_FILE);
        client.genPoissonSamples(poisson_heartwall_mean, 10000, POISSON_HEARTWALL_SAMPLE_FILE);
        client.genPoissonSamples(poisson_hotspot_mean, 10000, POISSON_HOTSPOT_SAMPLE_FILE);
        client.genPoissonSamples(poisson_hybridsort_mean, 10000, POISSON_HYBRIDSORT_SAMPLE_FILE);
        client.genPoissonSamples(poisson_kmeans_mean, 10000, POISSON_KMEANS_SAMPLE_FILE);
        client.genPoissonSamples(poisson_lavaMD_mean, 10000, POISSON_LAVAMD_SAMPLE_FILE);
        client.genPoissonSamples(poisson_leukocyte_mean, 10000, POISSON_LEUKOCYTE_SAMPLE_FILE);
        client.genPoissonSamples(poisson_lud_mean, 10000, POISSON_LUD_SAMPLE_FILE);
        client.genPoissonSamples(poisson_mummergpu_mean, 10000, POISSON_MUMMERGPU_SAMPLE_FILE);
        client.genPoissonSamples(poisson_myocyte_mean, 10000, POISSON_MYOCYTE_SAMPLE_FILE);
        client.genPoissonSamples(poisson_nn_mean, 10000, POISSON_NN_SAMPLE_FILE);
        client.genPoissonSamples(poisson_nw_mean, 10000, POISSON_NW_SAMPLE_FILE);
        client.genPoissonSamples(poisson_particlefilter_mean, 10000, POISSON_PARTICLEFILTER_SAMPLE_FILE);
        client.genPoissonSamples(poisson_pathfinder_mean, 10000, POISSON_PATHFINDER_SAMPLE_FILE);
        client.genPoissonSamples(poisson_srad_mean, 10000, POISSON_SRAD_SAMPLE_FILE);
        client.genPoissonSamples(poisson_streamcluster_mean, 10000, POISSON_STREAMCLUSTER_SAMPLE_FILE);
        
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
