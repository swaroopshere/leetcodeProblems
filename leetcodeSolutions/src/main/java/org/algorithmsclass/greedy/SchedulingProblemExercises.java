package org.algorithmsclass.greedy;

import com.google.inject.internal.util.Lists;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class SchedulingProblemExercises {
    private List<JobInfo> jobList;
    private List<JobInfo> jobList2;
    private static class JobInfo {
        private Integer weight;
        private Integer length;
        public JobInfo(Integer weight, Integer length) {
            this.weight = weight;
            this.length = length;
        }
        public Integer getWeight() {return weight;}
        public Integer getLength() {return length;}
    }


    public SchedulingProblemExercises() throws IOException, URISyntaxException {
        jobList = generateListFromFile();
        jobList2 = jobList.stream().collect(Collectors.toList());
    }

    private static class JobComparator1 implements Comparator<JobInfo> {
        @Override
        public int compare(JobInfo j1, JobInfo j2) {
            int weight1 = j1.getWeight() - j1.getLength();
            int weight2 = j2.getWeight() - j2.getLength();
            if(weight1 == weight2) {
                return Integer.compare(j1.getWeight(), j2.getWeight());
            } else {
                return Integer.compare(weight1, weight2);
            }
        }
    }

    private static class JobComparator2 implements Comparator<JobInfo> {
        @Override
        public int compare(JobInfo j1, JobInfo j2) {
            float weight1 = (float)j1.getWeight() / (float)j1.getLength();
            float weight2 = (float)j2.getWeight() / (float)j2.getLength();
            return Float.compare(weight1, weight2);
        }
    }

    private List generateListFromFile() throws IOException, URISyntaxException {
        List<JobInfo> jobInfos = Lists.newArrayList();
        Path graphFilePath = Paths.get("c:\\Users\\swaro_000\\Documents\\git\\leetcodeProblems\\ProgrammingExercises\\jobs.txt");
        Files.lines(graphFilePath).forEach(word -> {
            Integer jobCount = 0;
            Integer length = 0;
            Integer weight = 0;
            StringTokenizer tokenizer = new StringTokenizer(word, " ");
            while(tokenizer.hasMoreElements()) {
                if(tokenizer.countTokens() < 2) {
                    jobCount = Integer.parseInt(tokenizer.nextToken());
                } else {
                    weight = Integer.parseInt(tokenizer.nextToken());
                    length = Integer.parseInt(tokenizer.nextToken());
                    jobInfos.add(new JobInfo(weight, length));
                }
            }
        });
        return jobInfos;
    }

    public void getOptimizedValue() {
        long optimizedValue1 = 0;
        long completionTime1 = 0;
        long optimizedValue2 = 0;
        long completionTime2 = 0;

        Collections.sort(jobList, new JobComparator1().reversed());
        Collections.sort(jobList2, new JobComparator2().reversed());
        for (JobInfo jobInfo : jobList) {
            completionTime1+=jobInfo.getLength();
            optimizedValue1+=jobInfo.getWeight()*completionTime1;
        }

        for(JobInfo jobInfo : jobList2) {
            completionTime2+=jobInfo.getLength();
            optimizedValue2+=jobInfo.getWeight()* completionTime2;
        }
        System.out.println(optimizedValue1);
        System.out.println(optimizedValue2);
    }
}