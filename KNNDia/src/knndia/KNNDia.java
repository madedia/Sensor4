package knndia;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MD Dia Agustya
 */
public class KNNDia {
    Scanner input = new Scanner(System.in);
        
    int nDataTesting = 0, nDataTraining = 0;
    int countTesting = 0, countTraining = 0;
    int K = 0;
    double x, y;
    double euclidian;
    double[] fin_euclidian;
    double[] dataLine;

    Point[] listTesting, listTraining;
    
    public void readFileTraining() {
        //String csvFile = "FP_DM/creditATraining.csv";
        String csvFile = "FP_DM/dataTrain.csv";
        BufferedReader br = null;
        String line = "";
        String comma = ",";
        String enter = "\n";
        
        try {
            br = new BufferedReader(new FileReader(csvFile));
            int flag = 0;
            
            while ((line = br.readLine()) != null) 
            {
                // use enter as separator
                String[] dot = line.split(enter);
                
                if (flag != 1) {
                    String[] dot2;
                    do  {
                        dot2 = line.split(comma);
                        countTraining ++;
                    } while (countTraining < line.split(comma).length);
                    flag = 1;
                }

                nDataTraining++;
            }
            System.out.println("DATA TRAINING");
            System.out.println("----------------------------------------------------------");
            System.out.println("Jumlah titik = "+nDataTraining);
            System.out.println("Panjang tiap data = "+countTraining);
            System.out.println("----------------------------------------------------------");
            listTraining = new Point[nDataTraining];
            
            br = new BufferedReader(new FileReader(csvFile));
            int i = 0;
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] dot;
                
                dataLine = new double[countTraining];
                
                for (int j = 0; j < dataLine.length; j++) {
                    dot = line.split(comma);
                    dataLine[j] = Double.parseDouble(dot[j]);
                    //System.out.print("\t" + dot[j] + "\t");
                }
                
                //System.out.println("");
                
                Point point = new Point(dataLine);
                
                listTraining[i] = point;
                i++;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(KNNDia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(KNNDia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void readFileTesting() {
        //String csvFile = "FP_DM/creditATesting.csv";
        String csvFile = "FP_DM/dataTestC.csv";
        BufferedReader br = null;
        String line = "";
        String comma = ",";
        String enter = "\n";
        
        try {
            
            br = new BufferedReader(new FileReader(csvFile));
            int flag = 0;
            
            while ((line = br.readLine()) != null) 
            {
                // use enter as separator
                String[] dot = line.split(enter);
                
                if (flag != 1) {
                    String[] dot2;
                    do  {
                        dot2 = line.split(comma);
                        //System.out.println(Double.parseDouble(dot2[count]));
                        countTesting ++;
                    } while (countTesting < line.split(comma).length);
                            
                    dataLine = new double[countTesting];
                    flag = 1;
                }
                nDataTesting++;
            }
            System.out.println("DATA TESTING");
            System.out.println("----------------------------------------------------------");
            System.out.println("Jumlah titik = "+nDataTesting);
            System.out.println("Panjang tiap data = "+countTesting);
            System.out.println("----------------------------------------------------------");
            listTesting = new Point[nDataTesting];
            
            br = new BufferedReader(new FileReader(csvFile));
            int i = 0;
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] dot;
                
                dataLine = new double[countTesting+1];
                
                for (int j = 0; j < dataLine.length-1; j++) {
                    dot = line.split(comma);
                    dataLine[j] = Double.parseDouble(dot[j]);
                    //System.out.print("\t" + dot[j] + "\t");
                }
                
                //System.out.println("");
                
                Point point = new Point(dataLine);
                
                listTesting[i] = point;
                i++;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(KNNDia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(KNNDia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void doKNN() {
        System.out.println("PERHITUNGAN KNN");
        System.out.println("----------------------------------------------------------");
        System.out.print("Masukkan nilai K iterasi KNN: ");
        K = input.nextInt();
        System.out.println("");
        System.out.println("...mulai perhitungan");
        System.out.println("----------------------------------------------------------");
        
        double[] candidat;
        int[] indexClass;
        
        int itFlag = 0,jumlah1 = 0,jumlah0 = 0;
        fin_euclidian = new double[nDataTraining];
        
        for (int i = 0; i < nDataTesting; i++) {
            euclidian = 0;
            
            double maxVal;
            int idxMax = 0;
            int idx = 0;
            candidat = new double[K];
            indexClass = new int[K];
            Arrays.fill(candidat, 0);
            Arrays.fill(indexClass, 0);
            
            for (int j = 0; j < nDataTraining; j++) {
                for (int k = 0; k < countTraining-1; k++) {
                    euclidian = euclidian + Math.pow((listTesting[i].getData()[k] - listTraining[j].getData()[k]),2);
                }
                euclidian = Math.sqrt(euclidian);
                
                if (idx < K) {
                    candidat[idx] = euclidian;
                    indexClass[idx] = j;
                    idx++;
                }
                else {
                    //int txt = input.nextInt();
                    maxVal = -1;
                    for (int k = 0; k < candidat.length; k++) {
                        if(candidat[k] > maxVal) {
                            maxVal = candidat[k];
                            idxMax = k;
                        }
                    }
                    if (euclidian < maxVal) {
                        //System.out.println("ganti");
                        candidat[idxMax] = euclidian;
                        indexClass[idxMax] = j;
                    }
                }
            }
            
            for (int j = 0; j < candidat.length; j++) {
                if (listTraining[indexClass[j]].getData()[countTraining-1] == 0) {
                    jumlah0++;
                } else if (listTraining[indexClass[j]].getData()[countTraining-1] == 1) {
                    jumlah1++;
                }
            }
            
            //System.out.println((i+1) +" "+"0 = "+ jumlah0 +  "\t 1 = " + jumlah1);
            if (jumlah0 > jumlah1){
                listTesting[i].setDataClass(0);
            } 
            else if (jumlah1 > jumlah0){
                listTesting[i].setDataClass(1);
            }

            for(int j = 0; j < countTesting; j++){
                //System.out.print(listTesting[i].getData()[j]+" ");
            }

            //System.out.println("klasifikasi ke "+listTesting[i].getDataClass());
            //System.out.println(" ");
            jumlah1 = 0;
            jumlah0 = 0;
            itFlag = 0;
        }
        //int temp1 = input.nextInt();
    }
    
    public void whatToDo() {
        int nilai0 = 0, nilai1 = 0, nilai2 = 0;
        for (int i = 0; i < listTesting.length; i++) {
            if(listTesting[i].getDataClass()==0) {
                nilai0++;
            }
            else if (listTesting[i].getDataClass()==1) {
                nilai1++;
            }
            else if (listTesting[i].getDataClass()==2) {
                nilai2++;
            }
        }
        if ((nilai0 > nilai1) && (nilai0 > nilai2)) {
            System.out.println("-----------------------------------------------SEDANG BERJALAN");
        } else if ((nilai1 > nilai0) && (nilai1 > nilai2)) {
            System.out.println("-----------------------------------------------SEDANG BERLARI");
        } else if ((nilai2 > nilai1) && (nilai2 > nilai0)) {
            System.out.println("--------------------------------------------------------------SEDANG BERSEPEDA");
        }
    } 
            
    public void writeFileTesting() {
        System.out.println("AKURASI ALGORITMA KNN");
        System.out.println("----------------------------------------------------------");
        String csvFileRead = "FP_DM/dataTest.csv";
        String csvFileWrite = "FP_DM/dataTest_TestingResultJumlahK"+K+ ".csv";
        BufferedReader br = null;
        String line = "";
        String comma = ",";
        
        try {
            br = new BufferedReader(new FileReader(csvFileRead));
            FileWriter writer = new FileWriter(csvFileWrite);
            
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] dot;
                double[] dataLine = new double[countTesting];
                
                for (int j = 0; j < dataLine.length; j++) {
                    dot = line.split(comma);
                    dataLine[j] = Double.parseDouble(dot[j]);
                    String tempWrite = String.valueOf(dataLine[j]);
                    writer.append(tempWrite);
                    writer.append(",");
                }
                String tempWrite = String.valueOf(listTesting[i].getDataClass());
                writer.append(tempWrite);
                writer.append('\n');   
                i++;
            }
            writer.flush();
            writer.close();  
            System.out.println("----------------------------------------------------------");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(KNNDia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(KNNDia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void checkAccuracy() {
        int matchClass = 0;
        double accuracy = 0;
        for (int i = 0; i < nDataTesting; i++) {
            if(listTesting[i].getData()[countTesting-1] == listTesting[i].getDataClass()) {
                matchClass++;
            }
        }
        accuracy = matchClass * 100 / nDataTesting;
        
        System.out.println("");
        System.out.println("Match\t\t: " + matchClass );
        System.out.println("Miss\t\t: " + (nDataTesting - matchClass));
        System.out.println("Accuracy\t: " + accuracy + "%");
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        KNNDia knn = new KNNDia();
        knn.readFileTraining();
        knn.readFileTesting();
        knn.doKNN();
        //knn.writeFileTesting();
        knn.checkAccuracy();
        knn.whatToDo();
    }
    
}