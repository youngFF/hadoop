package wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.util.StringTokenizer;


/**
 * 现在的问题是这个代码没有与hadoop系统勾搭上,所以想办法把
 * <p>
 * <p>
 * 要想和hadoop的hdfs勾搭上，有两种解决办法
 * 1.在src下面添加配置文件： core-site.xml , hdfs-site.xml ， log4j.properties
 * 配套的FileInputFormat和OutputFormat
 *      FileInputFormat.addInputPath(job, new Path("input"));
 *      FileOutputFormat.setOutputPath(job, new Path("output"));
 *
 * 2 . 直接修改InputFormat和OutputFormat
 *     FileInputFormat.addInputPath(job, new Path("hdfs://localhost:9000/input"))
 *     FileOutputFormat.setOutputPath(job, new Path("hdfs://localhost:9000/output"))
 *
 *
 * 3.本地运行
 *      1）不用在src下添加任何文件 ， hdfs-site.xml , core-site.xml
 *      2）FileInputFormat.addInputPath(job, new Path("input"))
 *         FileOutputFormat.setOutputPath(job, new Path("output"))
 *      3) input , output 文件夹在项目路径下
 *
 * 4.将项目作业提交到hadoop集群，由hadoop-cli 运行
 *         1) 生成项目jar包。具体生成方法根据ide
 *               eg： intellij idea生成jar包
 *               project structure-> artifacts ->
 *                  jar empty, 勾选include build in project , 添加Module Output
 *
 *         2)hadoop-cli执行作业
 *              hadoop jar .../wordcount.jar [MainClass]  [args...]
 */


/**
 * 把它打包成jar包，然后扔到集群
 */
public class WordCount {


    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        // configuration object
        Configuration configuration = new Configuration();
        // job obbject
        Job job = Job.getInstance(configuration, "wordCount");
        job.setJobName("wordCount");
        job.setJarByClass(WordCount.class);
        job.setMapperClass(TokenizerMapper.class);
        job.setReducerClass(IntSumReducer.class);
        job.setCombinerClass(IntSumReducer.class);
        job.setOutputKeyClass(Text.
                class);
        job.setOutputValueClass(IntWritable.class);


        FileInputFormat.addInputPath(job, new Path("input"));
        FileOutputFormat.setOutputPath(job, new Path("output"));
        // 不能省略 ， 一定要注意
        job.waitForCompletion(true);
    }

    public static class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable> {


        // big int
        private final static IntWritable one = new IntWritable(1);
        // big line
        private Text word = new Text();


        @Override
        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            StringTokenizer itr = new StringTokenizer(value.toString());
            while (itr.hasMoreTokens()) {
                word.set(itr.nextToken());
                context.write(word, one);

            }


        }
    }

    public static class IntSumReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

        private IntWritable result = new IntWritable();

        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable val : values) {
                sum += val.get();

            }
            result.set(sum);
            context.write(key, result);
        }
    }


}

