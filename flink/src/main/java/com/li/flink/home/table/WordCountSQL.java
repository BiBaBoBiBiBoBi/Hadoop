package com.li.flink.home.table;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableEnvironment;
import org.apache.flink.table.api.java.BatchTableEnvironment;

public class WordCountSQL {


    public static void main(String[] args) throws Exception {

        // set up execution environment
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        BatchTableEnvironment tEnv = TableEnvironment.getTableEnvironment(env);

        DataSet<WC> input = env.fromElements(
                new WC("Hello", 1),
                new WC("Ciao", 1),
                new WC("Hello", 1));

        // register the DataSet as table "WordCount"
        tEnv.registerDataSet("WordCount", input, "word, frequency");

        // run a SQL query on the Table and retrieve the result as a new Table
        Table table = tEnv.sqlQuery(
                "SELECT word, SUM(frequency) as frequency FROM WordCount GROUP BY word");

        DataSet<WC> result = tEnv.toDataSet(table, WC.class);

        result.print();
    }

    /**
     * Simple POJO containing a word and its respective count.
     */
    private static class WC {
        public String word;
        public long frequency;

        // public constructor to make it a Flink POJO
        public WC() {
        }

        public WC(String word, long frequency) {
            this.word = word;
            this.frequency = frequency;
        }

        @Override
        public String toString() {
            return "AB " + word + " " + frequency;
        }
    }
}
